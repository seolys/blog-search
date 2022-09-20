package com.kakaobank.blogsearch.document.infra.apicaller.client.naver;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NaverApiPath {
	GET_DOCUMENTS("/v1/search/blog.json");

	private final String path;
}
