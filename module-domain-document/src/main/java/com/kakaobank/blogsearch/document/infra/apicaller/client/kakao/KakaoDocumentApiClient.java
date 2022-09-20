package com.kakaobank.blogsearch.document.infra.apicaller.client.kakao;

import com.kakaobank.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto.GetDocumentsRequest;
import com.kakaobank.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto.GetDocumentsResponse;
import java.net.URI;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoDocumentApiClient {

	private final RestTemplate restTemplate;

	@Value("${app.api.kakao.url}")
	private String url;
	@Value("${app.api.kakao.authorization}")
	private String authorization;
	@Value("${app.api.kakao.rest-api-key}")
	private String apiKey;

	public KakaoDocumentApiDto.GetDocumentsResponse getDocuments(final KakaoDocumentApiDto.GetDocumentsRequest request) {
		final URI uri = getUri(KakaoApiPath.GET_DOCUMENTS.getPath(), request);

		final ResponseEntity<GetDocumentsResponse> responseEntity = restTemplate.exchange(
				uri, HttpMethod.GET, getHttpEntity(), GetDocumentsResponse.class);
		log.debug("uri={}, statusCode={}, responseBody={}", uri, responseEntity.getStatusCode(), responseEntity.getBody());

		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			throw new RestClientException("API 응답에 실패했습니다.");
		}
		return responseEntity.getBody();
	}

	private HttpEntity getHttpEntity() {
		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", getAuthorization());
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return new HttpEntity<>(headers);
	}

	private String getAuthorization() {
		return authorization + " " + apiKey;
	}

	private URI getUri(final String path, final GetDocumentsRequest request) {
		return UriComponentsBuilder.fromHttpUrl(url + path)
				.queryParam("query", request.getQuery())
				.queryParam("sort", request.getSortName())
				.queryParam("page", request.getPage())
				.queryParam("size", request.getSize())
				.build()
				.toUri();
	}

}
