package com.kakaobank.blogsearch.document.infra.apicaller;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Documents;
import com.kakaobank.blogsearch.document.domain.apicaller.DocumentApiCaller;
import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

@Order(value = 2)
//@Component // TODO
@RequiredArgsConstructor
public class NaverDocumentApiCaller implements DocumentApiCaller {

	private final NaverDocumentApiClient apiClient;

	@Override public Documents getDocuments(final DocumentCommand.GetDocuments command) {
		return null;
	}
}
