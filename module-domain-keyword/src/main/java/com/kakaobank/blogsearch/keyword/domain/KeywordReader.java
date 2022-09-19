package com.kakaobank.blogsearch.keyword.domain;

import com.kakaobank.blogsearch.keyword.domain.KeywordInfo.PopularKeywordInfo;
import java.util.List;

public interface KeywordReader {
 
	List<PopularKeywordInfo> findPopularKeywordsTop10();
}
