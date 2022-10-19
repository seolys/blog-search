package seolnavy.blogsearch.keyword.domain;

import java.util.List;
import seolnavy.blogsearch.keyword.domain.KeywordInfo.PopularKeywordInfo;

public interface KeywordReader {

	List<PopularKeywordInfo> findPopularKeywordsTop10();
}
