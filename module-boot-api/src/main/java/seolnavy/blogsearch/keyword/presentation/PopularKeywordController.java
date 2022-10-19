package seolnavy.blogsearch.keyword.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import seolnavy.blogsearch.common.response.CommonResponse;
import seolnavy.blogsearch.keyword.domain.KeywordInfo.PopularKeywords;
import seolnavy.blogsearch.keyword.presentation.PopularKeywordDto.PopularKeywordsResponse;
import seolnavy.blogsearch.keyword.facade.PopularKeywordFacade;

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
