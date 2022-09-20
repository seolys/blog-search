package com.kakaobank.blogsearch.document.infra.apicaller.client.naver;

import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.GetDocumentsRequest;
import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.GetDocumentsResponse;
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
public class NaverDocumentApiClient {

	private final RestTemplate restTemplate;

	@Value("${app.api.naver.url}")
	private String url;
	@Value("${app.api.naver.client-id}")
	private String clientId;
	@Value("${app.api.naver.client-secret}")
	private String clientSecret;

	public NaverDocumentApiDto.GetDocumentsResponse getDocuments(final NaverDocumentApiDto.GetDocumentsRequest request) {
		final URI uri = getUri(NaverApiPath.GET_DOCUMENTS.getPath(), request);

		final ResponseEntity<NaverDocumentApiDto.GetDocumentsResponse> responseEntity = restTemplate.exchange(
				uri, HttpMethod.GET, getHttpEntity(), GetDocumentsResponse.class);
		log.debug("uri={}, statusCode={}, responseBody={}", uri, responseEntity.getStatusCode(), responseEntity.getBody());

		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			throw new RestClientException("API 응답에 실패했습니다.");
		}
		return responseEntity.getBody();
	}

	private HttpEntity getHttpEntity() {
		final HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id", clientId);
		headers.set("X-Naver-Client-Secret", clientSecret);
		headers.setAccept(Collections.singletonList(MediaType.ALL));
		return new HttpEntity<>(headers);
	}

	private URI getUri(final String path, final GetDocumentsRequest request) {
		return UriComponentsBuilder.fromHttpUrl(url + path)
				.queryParam("query", request.getQuery())
				.queryParam("display", request.getDisplay())
				.queryParam("start", request.getStart())
				.queryParam("sort", request.getSortName())
				.build()
				.toUri();
	}

}
