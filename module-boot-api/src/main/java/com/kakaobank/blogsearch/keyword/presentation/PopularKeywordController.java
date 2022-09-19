package com.kakaobank.blogsearch.keyword.presentation;

import com.kakaobank.blogsearch.common.response.CommonResponse;
import com.kakaobank.blogsearch.keyword.domain.KeywordInfo.PopularKeywords;
import com.kakaobank.blogsearch.keyword.facade.PopularKeywordFacade;
import com.kakaobank.blogsearch.keyword.presentation.PopularKeywordDto.PopularKeywordsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PopularKeywordController {

	private final PopularKeywordFacade facade;

	/**
	 * 인기 검색어 조회.
	 * @return
	 */
	@GetMapping("/v1/keywords/popularTop10")
	public CommonResponse<PopularKeywordsResponse> getPopularKeywords() {
		final PopularKeywords popularKeywords = facade.getPopularKeywordsTop10();

		final PopularKeywordsResponse response = PopularKeywordMapper.of(popularKeywords);
		return CommonResponse.of(response);
	}


}
