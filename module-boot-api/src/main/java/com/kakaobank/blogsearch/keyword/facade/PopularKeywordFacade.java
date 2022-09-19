package com.kakaobank.blogsearch.keyword.facade;

import com.kakaobank.blogsearch.keyword.domain.KeywordInfo.PopularKeywords;
import com.kakaobank.blogsearch.keyword.domain.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PopularKeywordFacade {

	private final KeywordService keywordService;

	public PopularKeywords getPopularKeywordsTop10() {
		return keywordService.getPopularKeywordsTop10();
	}
}
