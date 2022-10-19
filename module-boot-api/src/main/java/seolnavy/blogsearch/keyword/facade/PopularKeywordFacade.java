package seolnavy.blogsearch.keyword.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seolnavy.blogsearch.keyword.domain.KeywordInfo.PopularKeywords;
import seolnavy.blogsearch.keyword.domain.KeywordService;

@Component
@RequiredArgsConstructor
public class PopularKeywordFacade {

	private final KeywordService keywordService;

	public PopularKeywords getPopularKeywordsTop10() {
		return keywordService.getPopularKeywordsTop10();
	}
}
