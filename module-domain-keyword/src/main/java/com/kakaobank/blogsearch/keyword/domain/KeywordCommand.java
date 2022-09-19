package com.kakaobank.blogsearch.keyword.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeywordCommand {

	@AllArgsConstructor(staticName = "of")
	public static class Search {

		private String keyword;

		public Keyword toEntity() {
			return Keyword.searched(keyword);
		}
	}

}
