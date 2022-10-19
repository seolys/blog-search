package seolnavy.blogsearch.keyword.infra;

import seolnavy.blogsearch.keyword.domain.Keyword;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface KeywordJpaRepository extends CrudRepository<Keyword, Long> {

	/**
	 * 인기 검색어 10개 조회
	 * - 개선요소: QueryDSL 또는 jOOQ로 전환
	 * @return
	 */
	@Query(nativeQuery = true,
			value =
					"SELECT KEYWORD, CNT "
							+ "FROM "
							+ "( "
							+ "  SELECT KEYWORD, COUNT(*) AS CNT "
							+ "    FROM KEYWORD "
							+ "   GROUP BY KEYWORD "
							+ ") AGGREGATE "
							+ "ORDER BY CNT DESC "
							+ "LIMIT 10"
	)
	List<PopularKeywordInfoResult> findGroupByWordTop10();
}
