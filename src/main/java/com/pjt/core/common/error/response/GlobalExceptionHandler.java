package com.pjt.core.common.error.response;

import com.pjt.core.board.exception.BoardException;
import com.pjt.core.common.error.exception.NoDataException;
import com.pjt.core.common.error.exception.StorageException;
import com.pjt.core.user.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoDataException.class)
	public ResponseEntity<ExceptionResponse> handleNoDataException(NoDataException e) {
		log.debug("::handleNoDataException::", e);
		ExceptionResponse exceptionResponse = ExceptionResponse.of(ErrorCode.NO_DATA);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("::handleMethodArgumentNotValidException::", e);
		ExceptionResponse exceptionResponse = ExceptionResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StorageException.class)
	public ResponseEntity<ExceptionResponse> handleStorageException(StorageException e) {
		log.error("::handleStorageException::", e);
		ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getErrorCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ExceptionResponse> handleMemberException(UserException e) {
		log.error("::handleMemberException::", e);
		ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getErrorCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception e) {
		log.error("::handleException::", e);
		ExceptionResponse exceptionResponse = ExceptionResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BoardException.class)
	public ResponseEntity<ExceptionResponse> handleNoDataException(BoardException e) {
		log.debug("::handleNoDataException::", e);
		ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getErrorCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
