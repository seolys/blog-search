package com.kakaobank.blogsearch.document.domain.apicaller;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Documents;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentApiTemplate {

	private final List<DocumentApiCaller> documentApiCallerImpls;

	public Documents getDocuments(final DocumentCommand.GetDocuments command) {
		for (final DocumentApiCaller documentApiCaller : documentApiCallerImpls) {
			try {
				return documentApiCaller.getDocuments(command);
			} catch (final Exception e) {
				// 실패 시, 운영자에게 알림 발송 또는 로그출력
				log.warn("ApiCaller {} is failed. {}", documentApiCaller.getClass().getName(), e);
			}
		}
		throw new DocumentApiCallFailException("블로그 검색 API 최종 실패.");
	}

}
