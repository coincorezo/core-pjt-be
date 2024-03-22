package com.pjt.core.common.error.response;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ExceptionResponse {
	private final String path;
	private final String code;
	private final String message;
	private final List<FieldError> errors;
	private final LocalDateTime errorDateTime;

	private ExceptionResponse(ErrorCode errorCode) {
		this.path = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.errors = new ArrayList<>();
		this.errorDateTime = LocalDateTime.now();
	}

	private ExceptionResponse(ErrorCode errorCode, List<FieldError> errors) {
		this.path = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.errors = errors;
		this.errorDateTime = LocalDateTime.now();
	}

	public static ExceptionResponse of(ErrorCode errorCode) {
		return new ExceptionResponse(errorCode);
	}

	public static ExceptionResponse of(ErrorCode errorCode, BindingResult bindingResult) {
		return new ExceptionResponse(errorCode, FieldError.of(bindingResult));
	}

	@Getter
	public static class FieldError {
		private final String field;
		private final String value;
		private final String message;

		private FieldError(String field, String value, String message) {
			this.field = field;
			this.value = value;
			this.message = message;
		}

		public static List<FieldError> of(String field, String value, String message) {
			List<FieldError> errors = new ArrayList<>();
			errors.add(new FieldError(field, value, message));
			return errors;
		}

		public static List<FieldError> of(BindingResult bindingResult) {
			return bindingResult.getFieldErrors().stream()
					.map(error -> new FieldError(
							error.getField(),
							error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
							error.getDefaultMessage()
					))
					.toList();
		}
	}

}
