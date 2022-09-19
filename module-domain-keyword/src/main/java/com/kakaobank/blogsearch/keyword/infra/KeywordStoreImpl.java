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
		/*
		 * 트래픽이 많고, 저장되어 있는 데이터가 많은 상황에 대한 고민.
		 * (1) Redis로 Counting 및 조회
		 * (2) Redis에 저장하기 벅찬 데이터라면, 특정시간에 배치서버에서 집계 후 Redis에 캐시. 인기검색어는 캐시된 데이터 조회 후 응답.
		 * - Redis로 구현할 경우 Key설계 및 명령어
		 *  - 일별 카운트 증가: zincrby search:blog:keyword:20220917 1 {keyword}
		 *  - 월별 카운트 증가: zincrby search:blog:keyword:202209 1 {keyword}
		 *  - 전체 카운트 증가: zincrby search:blog:keyword:total 1 {keyword}
		 * */
		return keywordRepository.save(keyword);
	}
}
