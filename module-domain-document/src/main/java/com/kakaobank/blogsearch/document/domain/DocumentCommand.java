package com.kakaobank.blogsearch.document.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

public class DocumentCommand {

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class GetDocuments {

		/** 검색을 원하는 질의어 */
		private String query;

		/** 결과 문서 정렬 방식 */
		private DocumentSort sort;

		/** 결과 페이지 번호 */
		private Integer page;

		/** 한 페이지에 보여질 문서 수 */
		private Integer size;

		public void validate() {
			// 필수값 검증
			if (!StringUtils.hasText(query)) {
				throw new IllegalArgumentException("query is empty");
			}
			if (Objects.nonNull(page) && (page < 1 || page > 50)) {
				throw new IllegalArgumentException("page is invalid");
			}
			if (Objects.nonNull(size) && (size < 1 || size > 50)) {
				throw new IllegalArgumentException("size is invalid");
			}
		}
	}

}
