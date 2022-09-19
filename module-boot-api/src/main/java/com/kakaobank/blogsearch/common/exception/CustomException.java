package com.kakaobank.blogsearch.common.exception;

import com.kakaobank.blogsearch.common.response.ResponseCode;
import lombok.Getter;

/**
 * Java Exception Wrap.
 */
@Getter
public class CustomException extends RuntimeException {

	private String errorCode;
	private String errorMessage;

	public CustomException(final ResponseCode responseCode) {
		super(responseCode.getMessage());
		this.errorCode = responseCode.getCode();
		this.errorMessage = responseCode.getMessage();
	}

}
