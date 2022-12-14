package seolnavy.blogsearch.common.exception;

import static seolnavy.blogsearch.common.response.ResponseCode.INVALID_PARAMETER;
import static seolnavy.blogsearch.common.response.ResponseCode.SYSTEM_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import seolnavy.blogsearch.common.response.CommonResponse;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * 비즈니스 로직 처리 중 에러 발생
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(CustomException.class)
	public CommonResponse handleCustomException(final CustomException e) {
		log.error("handleCustomException: ", e);
		return CommonResponse.of(e.getErrorCode(), e.getErrorMessage());
	}

	/**
	 * 요청 파라미터 오류
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({IllegalArgumentException.class, BindException.class})
	public CommonResponse handleIllegalArgumentException(final Exception e) {
		log.error("handleIllegalArgumentException: ", e);
		return CommonResponse.of(INVALID_PARAMETER);
	}

	/**
	 * 시스템 에러 발생
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public CommonResponse handleException(final Exception e) {
		log.error("handleException: ", e);
		return CommonResponse.of(SYSTEM_ERROR);
	}

}
