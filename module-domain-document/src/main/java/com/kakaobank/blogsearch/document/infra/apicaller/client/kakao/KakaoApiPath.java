package com.kakaobank.blogsearch.document.infra.apicaller.client.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KakaoApiPath {
	GET_DOCUMENTS("/v2/search/blog");

	private final String path;
}
