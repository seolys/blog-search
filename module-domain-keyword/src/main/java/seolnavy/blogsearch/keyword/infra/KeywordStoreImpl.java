package seolnavy.blogsearch.keyword.infra;

import seolnavy.blogsearch.keyword.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import seolnavy.blogsearch.keyword.domain.KeywordStore;

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
