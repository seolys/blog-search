package seolnavy.blogsearch.keyword.domain;

import seolnavy.blogsearch.keyword.domain.KeywordInfo.PopularKeywords;

public interface KeywordService {

	/**
	 * 검색어 저장
	 * @param commond
	 */
	void saveKeyword(KeywordCommand.Search commond);

	/**
	 * 인기 검색어 조회
	 * @return
	 */
	PopularKeywords getPopularKeywordsTop10();

}
