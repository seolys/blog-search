package com.kakaobank.blogsearch.keyword.infra;

import com.kakaobank.blogsearch.keyword.domain.KeywordInfo.PopularKeywordInfo;
import com.kakaobank.blogsearch.keyword.domain.KeywordReader;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KeywordReaderImpl implements KeywordReader {

	private final KeywordRepository keywordRepository;

	@Override public List<PopularKeywordInfo> findPopularKeywordsTop10() {
		return keywordRepository.findGroupByWordTop10()
				.stream()
				.map(result -> PopularKeywordInfo.of(result.getKeyword(), Long.valueOf(result.getCnt())))
				.collect(Collectors.toList());
	}
}
