package com.kakaobank.blogsearch.keyword.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KeywordTest {

	@Test
	@DisplayName("검색어 생성 검증")
	void searched_success() {
		final String query = "keyword";

		final Keyword keyword = Keyword.searched(query);

		assertThat(keyword.getKeyword()).isEqualTo(query);
		assertThat(keyword.getId()).isNull();
		assertThat(keyword.getCreatedAt()).isNotNull();
	}
	
}