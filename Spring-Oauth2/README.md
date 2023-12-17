![](https://hudi.blog/static/7dced69214d91d7f1f0892720b1b5e1b/ca1dc/oauth2.0-process.png)

## 특이사항

-  WebSecurityConfigurerAdapter 가 Deprecated가 되어 SecurityFilterChain을 Bean으로 등록하여 사용

**기존 코드**

```java
@Configuration

@RequiredArgsConstructor

@EnableWebSecurity

@EnableGlobalMethodSecurity(

securedEnabled = true,

jsr250Enabled = true,

prePostEnabled = true

)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

  

private final CustomUserDetailsService customUserDetailsService;

private final CustomOAuth2UserService customOAuth2UserService;

private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

  

@Bean

public TokenAuthenticationFilter tokenAuthenticationFilter() {

return new TokenAuthenticationFilter();

}

  

@Bean

public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {

return new HttpCookieOAuth2AuthorizationRequestRepository();

}

  

@Bean

public PasswordEncoder passwordEncoder() {

return new BCryptPasswordEncoder();

}

  

@Bean(BeanIds.AUTHENTICATION_MANAGER)

@Override

public AuthenticationManager authenticationManagerBean() throws Exception {

return super.authenticationManagerBean();

}

  

@Override

protected void configure(AuthenticationManagerBuilder auth) throws Exception {

auth.userDetailsService(customUserDetailsService)

.passwordEncoder(passwordEncoder());

}

  

@Override

protected void configure(HttpSecurity http) throws Exception {

http

.cors()

.and()

.sessionManagement()

.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않음

.and()

.csrf().disable() // csrf 미사용

.headers().frameOptions().disable()

.and()

.formLogin().disable() // 로그인 폼 미사용

.httpBasic().disable() // Http basic Auth 기반으로 로그인 인증창이 열림(disable 시 인증창 열리지 않음)

.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint()) // 인증,인가가 되지 않은 요청 시 발생

.and()

.authorizeRequests()

.antMatchers("/auth/**", "/oauth2/**").permitAll() // Security 허용 Url

.anyRequest().authenticated() // 그 외엔 모두 인증 필요

.and()

.oauth2Login()

.authorizationEndpoint().baseUri("/oauth2/authorization") // 소셜 로그인 Url

.authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository()) // 인증 요청을 쿠키에 저장하고 검색

.and()

.redirectionEndpoint().baseUri("/oauth2/callback/*") // 소셜 인증 후 Redirect Url

.and()

.userInfoEndpoint().userService(customOAuth2UserService) // 소셜의 회원 정보를 받아와 가공처리

.and()

.successHandler(oAuth2AuthenticationSuccessHandler) // 인증 성공 시 Handler

.failureHandler(oAuth2AuthenticationFailureHandler); // 인증 실패 시 Handler

  

http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

}

  

}
```

**수정된 코드**

```java
@Configuration

@RequiredArgsConstructor

@EnableWebSecurity

@EnableGlobalMethodSecurity(

securedEnabled = true,

jsr250Enabled = true,

prePostEnabled = true

)

public class SecurityConfig {

private final CustomUserDetailsService customUserDetailsService;

private final CustomOAuth2UserService customOAuth2UserService;

private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

@Bean

public TokenAuthenticationFilter tokenAuthenticationFilter() {

return new TokenAuthenticationFilter();

}

@Bean

public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {

return new HttpCookieOAuth2AuthorizationRequestRepository();

}

@Bean

public DaoAuthenticationProvider authenticationProvider() {

DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

authProvider.setUserDetailsService(customUserDetailsService);

authProvider.setPasswordEncoder(passwordEncoder());

return authProvider;

}

@Bean

public PasswordEncoder passwordEncoder() {

return new BCryptPasswordEncoder();

}

public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

http

.cors()

.and()

.sessionManagement()

.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 미사용

.and()

.csrf().disable() // csrf 미사용

.headers().frameOptions().disable()

.and()

.formLogin().disable() // 로그인 폼 미사용

.httpBasic().disable() // Http basic Auth 기반으로 로그인 인증창이 열림 (disable 시 인증창 열리지 않음)

.exceptionHandling().authenticationEntryPoint(new AuthEntryPoint()) // 인증, 인가가 되지 않은 요청 시 발생

.and()

.authorizeHttpRequests()

.antMatchers("/auth/**", "/oauth2/**").permitAll() // Security 허용 URL

.anyRequest().authenticated() // 그 외엔 인증이 필요

.and()

.oauth2Login()

.authorizationEndpoint().baseUri("/oauth2/authorization") // 소셜 로그인 URL

.authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository()) // 인증 요청을 쿠키에 저장하고 검색

.and()

.redirectionEndpoint().baseUri("/oauth2/callback/*") // 소셜 인증 후 Redirect Url

.and()

.userInfoEndpoint().userService(customOAuth2UserService)

.and()

.successHandler(oAuth2AuthenticationSuccessHandler) // 인증 성공 시 Handler

.failureHandler(oAuth2AuthenticationFailureHandler); // 인증 실패 시 Handler

http.authenticationProvider(authenticationProvider());

http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

return http.build();

}

}
```

## 테스트

1. http://localhost:8080/oauth2/authorization/kakao?redirect_uri=http://localhost:8080/auth/token 로 접속하여 카카오 로그인 페이지 접근
2.  로그인

## 오류사항

![[Pasted image 20231217224147.png]]

-  localhost에서 리디렉션한 횟수가 너무 많습니다.