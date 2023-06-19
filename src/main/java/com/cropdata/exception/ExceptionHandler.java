package com.cropdata.exception;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.cropdata.customeresponse.CustomeResponse;
import com.fasterxml.jackson.core.JsonParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
	protected ResponseEntity<?> handleNoHandlerFoundException1(NoHandlerFoundException noHandlerFoundException) {
		String error = "No handler found for " + noHandlerFoundException.getHttpMethod() + " "
				+ noHandlerFoundException.getRequestURL();
		log.info("handleNoHandlerFoundException : {} ", noHandlerFoundException.getMessage() + error);
		return CustomeResponse.response("SERVICE_UNAVAILABLE");
	}

//	@org.springframework.web.bind.annotation.ExceptionHandler({ CommunicationException.class,
//			JDBCConnectionException.class, ConnectException.class, SocketTimeoutException.class })
//	protected ResponseEntity<?> handleNoHandlerFoundException(Exception exception) {
//		log.info("handleNoHandlerFoundException : {} ", exception.getMessage());
//		return CustomeResponse.response("SERVER_REQUEST_TIMEOUT");
//	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(
			ConstraintViolationException constraintViolationException) {
		log.info("handleConstraintViolationException : {}", constraintViolationException.getMessage());
		return CustomeResponse.response("FIELD_ERROR");
	}

//	@org.springframework.web.bind.annotation.ExceptionHandler(MappingException.class)
//	public ResponseEntity<?> handleMappingException(MappingException mappingException) {
//		log.info("handleMappingException : {}", mappingException.getMessage());
//		return CustomeResponse.response("MAPPING_ERROR");
//	}
//
//	@org.springframework.web.bind.annotation.ExceptionHandler(PersistenceException.class)
//	public ResponseEntity<?> handlePersistenceException(PersistenceException persistenceException) {
////	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
////	                .body("An error occurred while attempting to apply an AttributeConverter");
//		log.info("handlePersistenceException: {}", persistenceException.getMessage());
//		return CustomeResponse.response("DEFAULT_ERROR");
//	}

	@org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
	public ResponseEntity<?> handleSQLException(SQLException sqlException) {
		log.info("handleSQLException: {} ", sqlException.getMessage());
		return CustomeResponse.response("JPA_QUERY_EXCEPTION");
	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<?> handleMethodArgumentTypeMismatch(
			MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
		String error = methodArgumentTypeMismatchException.getName() + " should be of type "
				+ methodArgumentTypeMismatchException.getRequiredType().getName();
		log.info("handleMethodArgumentTypeMismatch: {} ", error);
		return CustomeResponse.response("METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION");
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> handleMethodArgumentNotValid(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		HandlerMethod handlerMethod = (HandlerMethod) request
				.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
		Map<String, String> errorsMap = new HashMap<>();
		methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach((error) -> {
			errorsMap.put(error.getField(), error.getDefaultMessage());
		});
		log.info("handleMethodArgumentNotValid:{}", errorsMap.toString());
		return CustomeResponse.response("METHOD_ARGUMENT_NOT_VALID_ERROR");
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException httpMessageNotReadableException) {
		log.info("handleHttpMessageNotReadableException : {} ", httpMessageNotReadableException.getMessage());
		return CustomeResponse.response("REQUEST_FAILED");
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<?> handleCourseNotFoundException(CourseNotFoundException courseNotFoundException) {
		log.info("handleOrderNotFoundException :{} ", courseNotFoundException.getMessage());
		return CustomeResponse.response("Course_NOT_FOUND");
	}

	/*
	 * @org.springframework.web.bind.annotation.ExceptionHandler(
	 * CustomerNotFoundException.class) public ResponseEntity<?>
	 * handleCustomerNotFoundException(CustomerNotFoundException
	 * customerNotFoundException) {
	 * log.info("handleCustomerNotFoundException :{} ",customerNotFoundException.
	 * getMessage()); return CustomResponse.response("CUSTOMER_NOT_FOUND"); }
	 */

	@org.springframework.web.bind.annotation.ExceptionHandler(WriterNotFoundException.class)
	public ResponseEntity<?> handleWriterNotFoundException(WriterNotFoundException writerNotFoundException) {
		log.info("handleProductNotFoundException :{} ", writerNotFoundException.getMessage());
		return CustomeResponse.response("Writer_NOT_FOUND_ERROR");
	}

//	@org.springframework.web.bind.annotation.ExceptionHandler(JpaSystemException.class)
//	public ResponseEntity<?> handleJpaSystemException(JpaSystemException jpaSystemException) {
//		log.info("handleJpaSystemException :{} ", jpaSystemException.getMessage());
//		return CustomeResponse.response("JPA_QUERY_EXCEPTION");
//	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ InvocationTargetException.class })
	public ResponseEntity<?> handleRuntimeException(InvocationTargetException invocationTargetException) {
		log.info("handleInvocationTargetException :{} ", invocationTargetException.getMessage());
		return CustomeResponse.response("RUNTIME_EXCEPTION");
	}

	// IllegalStateException
	@org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
		log.info("handleIllegalArgumentException:{}", illegalArgumentException.getMessage());
		return CustomeResponse.response("ILLEGAL_ARGUMENT_EXCEPTION");// ILLEGAL_ARGUMENT_EXCEPTION
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(JsonParseException.class)
	public ResponseEntity<?> handleJsonParseException(JsonParseException jsonParseException) {
		log.info("handleJsonParseException:{}", jsonParseException.getMessage());
		return CustomeResponse.response("JSON_PARSE_ERROR");
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(DefaultMessageException.class)
	public ResponseEntity<?> handleDefaultMessageException(DefaultMessageException defaultMessageException) {
		log.info("handleDefaultMessageException :{} ", defaultMessageException.getMessage());
		return CustomeResponse.response("DEFAULT_ERROR");
	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<?> handleRuntimeException(RuntimeException runtimeException) {
		log.info("handleRuntimeException :{} ", runtimeException.getMessage());
		return CustomeResponse.response("RUNTIME_EXCEPTION");
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception) {
		log.info("handleException : {} ", exception.getMessage());
		return CustomeResponse.response("EXCEPTION");
	}
}
