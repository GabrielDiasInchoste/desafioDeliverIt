package br.com.gabrielDias.desafioDeliverIt.exception;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;
	private List<String> details;

	public ExceptionResponse(final HttpStatus errorCode, String message) {
		this.status = errorCode;
		this.message = message;
	}

	public ExceptionResponse(HttpStatus errorCode,String message, List<String> details) {
		this.status = errorCode;
		this.message = message;
		this.details = details;
	}
}
