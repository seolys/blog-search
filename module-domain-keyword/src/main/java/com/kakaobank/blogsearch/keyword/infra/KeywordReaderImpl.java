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
		/*
		 * 대용량 트래픽에 대한 고민
		 * (1) 캐시된 데이터 조회 후 응답으로 개선하면, RDBMS에서 집계하는것보다 응답속도를 크게 개선할 수 있을것이라 기대.
		 * - Redis로 구현할 경우 Key설계 및 명령어
		 *  - 일별 인기검색어 10개 조회: zrevrange search:blog:keyword:20220917 0 10 withscores
		 *  - 월별 인기검색어 10개 조회: zrevrange search:blog:keyword:20220917 0 10 withscores
		 *  - 전체 인기검색어 10개 조회: zrevrange search:blog:keyword:total 0 10 withscores
		 * (2) 또는 Redis가 아닌 다른 RDMBS or NoSQL에 캐시된 데이터 조회.
		 */
		return keywordRepository.findGroupByWordTop10()
				.stream()
				.map(result -> PopularKeywordInfo.of(result.getKeyword(), Long.valueOf(result.getCnt())))
				.collect(Collectors.toList());
	}
}
