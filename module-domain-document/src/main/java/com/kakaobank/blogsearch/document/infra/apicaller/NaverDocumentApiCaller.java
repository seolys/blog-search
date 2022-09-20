package com.kakaobank.blogsearch.document.infra.apicaller;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Documents;
import com.kakaobank.blogsearch.document.domain.apicaller.DocumentApiCaller;
import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiClient;
import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto;
import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.GetDocumentsResponse;
import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
@RequiredArgsConstructor
public class NaverDocumentApiCaller implements DocumentApiCaller {

	private final NaverDocumentApiClient apiClient;

	@Override public Documents getDocuments(final DocumentCommand.GetDocuments command) {
		final NaverDocumentApiDto.GetDocumentsRequest apiRequest = NaverDocumentApiDtoMapper.of(command);
		apiRequest.validate();

		final GetDocumentsResponse response = apiClient.getDocuments(apiRequest);

		return NaverDocumentApiDtoMapper.of(apiRequest.getStart(), response);
	}
}
