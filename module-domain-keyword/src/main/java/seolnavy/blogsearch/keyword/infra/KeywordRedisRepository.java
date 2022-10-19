package seolnavy.blogsearch.keyword.infra;

import seolnavy.blogsearch.keyword.domain.Keyword;
import java.util.List;
import seolnavy.blogsearch.keyword.domain.KeywordInfo.PopularKeywordInfo;

public interface KeywordRedisRepository {

	void save(Keyword keyword);

	List<PopularKeywordInfo> findPopularKeywordsTop10();

}
