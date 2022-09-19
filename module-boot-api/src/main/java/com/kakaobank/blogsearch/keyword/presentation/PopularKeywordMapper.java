package com.kakaobank.blogsearch.keyword.presentation;

import com.kakaobank.blogsearch.keyword.domain.KeywordInfo;
import com.kakaobank.blogsearch.keyword.domain.KeywordInfo.PopularKeywords;
import com.kakaobank.blogsearch.keyword.presentation.PopularKeywordDto.PopularKeywordInfo;
import com.kakaobank.blogsearch.keyword.presentation.PopularKeywordDto.PopularKeywordsResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PopularKeywordMapper {

	public static PopularKeywordsResponse of(final PopularKeywords popularKeywords) {
		final List<PopularKeywordInfo> popularKeywordsDto = popularKeywords.getPopularKeywordInfos()
				.stream()
				.map(PopularKeywordMapper::of)
				.collect(Collectors.toList());
		return PopularKeywordsResponse.of(popularKeywordsDto);
	}

	public static PopularKeywordInfo of(final KeywordInfo.PopularKeywordInfo popularKeywordInfo) {
		return PopularKeywordInfo.of(popularKeywordInfo.getKeyword(), popularKeywordInfo.getSearchCount());
	}

}
