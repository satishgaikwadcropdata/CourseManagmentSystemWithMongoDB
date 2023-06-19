//package com.cropdata.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//import com.cropdata.entity.Product;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//	
//	@ExceptionHandler(ProductNotFoundException.class)
//	public ResponseEntity<Product> handleProductNotFoundException(ProductNotFoundException exception,WebRequest request){
//		
//		Product product=new Product();
//		product.setProductCode(HttpStatus.NOT_FOUND.value());
//		product.setProductName(exception.getMessage());
//		product.setProductDescription(exception.getMessage());
//		product.setQuantityInStock(null);
//		product.setPrice(null);
//		
//		return new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND);
//		
//	}
//
//}
