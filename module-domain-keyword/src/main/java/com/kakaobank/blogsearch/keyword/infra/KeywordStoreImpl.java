package com.kakaobank.blogsearch.keyword.infra;

import com.kakaobank.blogsearch.keyword.domain.Keyword;
import com.kakaobank.blogsearch.keyword.domain.KeywordStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KeywordStoreImpl implements KeywordStore {

	private final KeywordRepository keywordRepository;

	@Override public Keyword save(final Keyword keyword) {
		return keywordRepository.save(keyword);
	}
}
