package seolnavy.blogsearch.keyword.domain;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeywordInfo {

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class PopularKeywords {

		List<PopularKeywordInfo> popularKeywordInfos;
	}

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class PopularKeywordInfo {

		private String keyword;
		private Long searchCount;
	}


}
