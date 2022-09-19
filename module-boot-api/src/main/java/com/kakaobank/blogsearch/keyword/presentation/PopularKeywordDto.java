package com.kakaobank.blogsearch.keyword.presentation;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PopularKeywordDto {

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class PopularKeywordsResponse {

		List<PopularKeywordInfo> popularKeywordInfos;
	}

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class PopularKeywordInfo {

		private String keyword;
		private Long searchCount;
	}

}
