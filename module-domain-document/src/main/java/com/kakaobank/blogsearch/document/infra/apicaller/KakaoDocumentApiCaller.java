package com.kakaobank.blogsearch.document.infra.apicaller;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Documents;
import com.kakaobank.blogsearch.document.domain.apicaller.DocumentApiCaller;
import com.kakaobank.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiClient;
import com.kakaobank.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto;
import com.kakaobank.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto.GetDocumentsResponse;
import com.kakaobank.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
@RequiredArgsConstructor
public class KakaoDocumentApiCaller implements DocumentApiCaller {

	private final KakaoDocumentApiClient apiClient;

	@Override public Documents getDocuments(final DocumentCommand.GetDocuments command) {
		final KakaoDocumentApiDto.GetDocumentsRequest apiRequest = KakaoDocumentApiDtoMapper.of(command);
		apiRequest.validate();

		final GetDocumentsResponse response = apiClient.getDocuments(apiRequest);

		return KakaoDocumentApiDtoMapper.of(response);
	}
}
