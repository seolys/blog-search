package seolnavy.blogsearch.keyword.infra;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import seolnavy.blogsearch.keyword.domain.KeywordInfo.PopularKeywordInfo;
import seolnavy.blogsearch.keyword.domain.KeywordReader;

@Repository
@RequiredArgsConstructor
public class KeywordReaderImpl implements KeywordReader {

	private final KeywordJpaRepository keywordJpaRepository;
	private final KeywordRedisRepository redisRepository;

	@Override
	public List<PopularKeywordInfo> findPopularKeywordsTop10() {
		// Redis에 저장된 인기 키워드를 조회한다.
		final List<PopularKeywordInfo> popularKeywordsTop10 = redisRepository.findPopularKeywordsTop10();
		if (!popularKeywordsTop10.isEmpty()) {
			return popularKeywordsTop10;
		}
		// Redis 캐시된 데이터가 없을경우를 대비하여 RDBMS 조회.
		// - Redis에 데이터가 없을 경우, RDBMS에서 조회하여 Redis에 저장하는 전략을 취할 수도 있다.
		return keywordJpaRepository.findGroupByWordTop10()
				.stream()
				.map(result -> PopularKeywordInfo.of(result.getKeyword(), Long.valueOf(result.getCnt())))
				.collect(Collectors.toList());
	}
}
