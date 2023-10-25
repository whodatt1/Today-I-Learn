//package com.example.demo.filter;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import com.example.demo.response.RestResponse;
//
//@ControllerAdvice
//public class AjaxBadResponseHandler {
//	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<RestResponse> validException(MethodArgumentNotValidException ex) {
//		
//		RestResponse restResponse = new RestResponse(false, "유효성 검사 실패 : " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
//		
//		return ResponseEntity.badRequest().body(restResponse);
//	}
//}
