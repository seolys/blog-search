package seolnavy.blogsearch.common.exception;

import lombok.Getter;
import seolnavy.blogsearch.common.response.ResponseCode;

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
