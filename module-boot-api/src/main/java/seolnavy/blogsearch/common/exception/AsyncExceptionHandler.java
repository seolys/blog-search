package seolnavy.blogsearch.common.exception;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	@Override
	public void handleUncaughtException(final Throwable throwable, final Method method, final Object... obj) {
		log.error("AsyncExceptionHandler", throwable);
	}

}
