package site.rentofficevn.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import site.rentofficevn.customexception.FieldRequireException;
import site.rentofficevn.model.dto.ErrorResponseDTO;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<ErrorResponseDTO> handleArithemticException(ArithmeticException ex, WebRequest request){
		ErrorResponseDTO result = new ErrorResponseDTO();
		result.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("10 sao chia het cho 0?");
		result.setDetails(details);
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(FieldRequireException.class)
	public ResponseEntity<ErrorResponseDTO> handleFieldRequireException(FieldRequireException ex, WebRequest request){
		ErrorResponseDTO result = new ErrorResponseDTO();
		result.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		result.setDetails(details);
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
