package com.kakaobank.blogsearch.keyword.infra;

import com.kakaobank.blogsearch.keyword.domain.Keyword;
import com.kakaobank.blogsearch.keyword.domain.KeywordStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KeywordStoreImpl implements KeywordStore {

	private final KeywordJpaRepository jpaRepository;
	private final KeywordRedisRepository redisRepository;

	@Override public Keyword save(final Keyword keyword) {
		// Redis 저장
		redisRepository.save(keyword);

		// RDBMS 저장
		return jpaRepository.save(keyword);
	}
}
