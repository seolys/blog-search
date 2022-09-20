package com.kakaobank.blogsearch.keyword.infra;

import com.kakaobank.blogsearch.keyword.domain.Keyword;
import com.kakaobank.blogsearch.keyword.domain.KeywordInfo.PopularKeywordInfo;
import java.util.List;

public interface KeywordRedisRepository {

	void save(Keyword keyword);

	List<PopularKeywordInfo> findPopularKeywordsTop10();

}
