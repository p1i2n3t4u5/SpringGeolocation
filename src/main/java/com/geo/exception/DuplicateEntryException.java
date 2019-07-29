package com.geo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DuplicateEntryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateEntryException(String exception) {
		super(exception);
	}

}
