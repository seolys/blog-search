package com.kakaobank.blogsearch.keyword.infra;

import com.kakaobank.blogsearch.keyword.domain.Keyword;
import com.kakaobank.blogsearch.keyword.domain.KeywordInfo.PopularKeywordInfo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KeywordRedisRepositoryImpl implements KeywordRedisRepository {

	/**
	 * 전체 키워드 검색 횟수<br/>
	 * - 집계 기준이 추가될 경우, key 설계 예시
	 * <pre>{@code
	 * 일별 카운트 증가: zincrby search:blog:keyword:20220917 1 {keyword}
	 * 월별 카운트 증가: zincrby search:blog:keyword:202209 1 {keyword}
	 * }</pre>
	 */
	private static final String REDIS_KEYWORD_TOTAL_KEY = "search:blog:keyword:total";

	private final RedisTemplate<String, String> redisTemplate;

	/**
	 * Redis에 Keyword를 저장한다.<br/>
	 * <pre>{@code zincrby search:blog:keyword:total 1 {keyword}}</pre>
	 */
	public void save(final Keyword keyword) {
		//
		operations().incrementScore(REDIS_KEYWORD_TOTAL_KEY, keyword.getKeyword(), 1);
	}

	/**
	 * 인기검색어 10개 조회
	 * <pre>{@code zrevrange search:blog:keyword:total 0 9 withscores}</pre>
	 */
	@Override public List<PopularKeywordInfo> findPopularKeywordsTop10() {
		//
		return operations().reverseRange(REDIS_KEYWORD_TOTAL_KEY, 0, 9).stream()
				.map(keyword -> PopularKeywordInfo.of(keyword, operations().score(REDIS_KEYWORD_TOTAL_KEY, keyword).longValue()))
				.collect(Collectors.toList());
	}

	private ZSetOperations<String, String> operations() {
		return redisTemplate.opsForZSet();
	}

}
