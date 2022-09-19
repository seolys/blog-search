package com.kakaobank.blogsearch.keyword.domain;

import com.kakaobank.blogsearch.keyword.domain.KeywordInfo.PopularKeywords;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {

	private final KeywordStore keywordStore;

	@Transactional
	@Override public void saveKeyword(final KeywordCommand.Search commond) {
		final Keyword keyword = commond.toEntity();
		keywordStore.save(keyword);
	}

	@Transactional(readOnly = true)
	@Override public PopularKeywords getPopularKeywordsTop10() {
		return null;
	}

}
