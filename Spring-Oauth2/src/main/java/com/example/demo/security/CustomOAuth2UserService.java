package com.example.demo.security;

import java.util.Optional;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OAuth2UserInfo;
import com.example.demo.dto.OAuth2UserInfoFactory;
import com.example.demo.entity.SocialAuth;
import com.example.demo.entity.User;
import com.example.demo.exception.OAuth2AuthenticationProcessingException;
import com.example.demo.repo.UserRepository;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import lombok.RequiredArgsConstructor;

// 받아온 소셜 계정 정보를 OAuth2UserInfo로 반환하여 DB에 등록 및 수정 진행
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	
	private final UserRepository userRepository;
	
	// access token을 얻고나서 실행
	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (Exception e) {
			throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
		}
	}
	
	public OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuthUser) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
				oAuth2UserRequest.getClientRegistration().getRegistrationId(),
				oAuthUser.getAttributes());
		
		if (StringUtils.isBlank(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("empty email");
		}
		
		Optional<User> userOptional = userRepository.findFirstByEmailOrderByIdAsc(oAuth2UserInfo.getEmail());
		User user;
		
		// DB에 정보가 있다면
		if (userOptional.isPresent()) {
			// 다른 플랫폼으로 로그인 한 경우
			if (!userOptional.get().getSocialAuth().getProvider().equals(com.example.demo.entity.AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				throw new OAuth2AuthenticationProcessingException("already sign up other provider");
			}
			user = updateUser(userOptional.get(), oAuth2UserInfo);
		} else {
			user = registerUser(oAuth2UserRequest, oAuth2UserInfo);
		}
		
		return UserPrincipal.create(user, oAuthUser.getAttributes());
	}
	
	private User registerUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
		return userRepository.save(User.builder()
				.email(oAuth2UserInfo.getEmail())
				.nickname(oAuth2UserInfo.getName())
				.socialAuth(SocialAuth.builder()
						.providerId(oAuth2UserInfo.getId())
						.provider(com.example.demo.entity.AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
						.email(oAuth2UserInfo.getEmail())
						.name(oAuth2UserInfo.getName())
						.imageUrl(oAuth2UserInfo.getImageUrl())
						.attributes(oAuth2UserInfo.getAttributes().toString())
						.ip("127.0.0.1") // 루프백 주소 이 컴퓨터 현재 사용중인 주소
						.build()
						)
				.build());
				
	}
	
	private User updateUser(User user, OAuth2UserInfo oAuth2UserInfo) {
		user.getSocialAuth().update(oAuth2UserInfo.getName(), oAuth2UserInfo.getImageUrl(), oAuth2UserInfo.getAttributes());
		return user;
	}
}
