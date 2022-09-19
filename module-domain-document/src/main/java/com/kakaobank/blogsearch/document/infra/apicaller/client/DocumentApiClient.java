package com.kakaobank.blogsearch.document.infra.apicaller.client;

import com.kakaobank.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto.GetDocumentsRequest;
import com.kakaobank.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto.GetDocumentsResponse;

public interface DocumentApiClient {

	GetDocumentsResponse getDocuments(final GetDocumentsRequest request);

}
