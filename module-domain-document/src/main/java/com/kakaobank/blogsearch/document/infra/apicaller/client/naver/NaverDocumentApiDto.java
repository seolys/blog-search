package com.kakaobank.blogsearch.document.infra.apicaller.client.naver;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakaobank.blogsearch.document.domain.DocumentSort;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NaverDocumentApiDto {

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class GetDocumentsRequest {

		/** 검색을 원하는 질의어 */
		private String query;

		/** 결과 문서 정렬 방식 */
		private Sort sort;

		/** 검색 시작 위치(기본값: 1, 최댓값: 1000) */
		private Integer start;

		/** 한 번에 표시할 검색 결과 개수(기본값: 10, 최댓값: 100) */
		private Integer display;

		public Object getSortName() {
			return Optional.ofNullable(sort)
					.map(Enum::name)
					.orElse(null);
		}

		public void validate() {
		}
	}

	/**
	 * 블로그 검색 결과
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(staticName = "of")
	@ToString
	public static class GetDocumentsResponse {

		private Integer total;
		private Integer start;
		private Integer display;
		private List<Item> items;

		public Boolean isEnd(final Integer start) {
			return start >= total;
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(staticName = "of")
	@ToString
	public static class Item {

		/** 블로그 포스트의 제목 */
		private String title;

		/** 블로그 포스트의 내용을 요약한 패시지 정보 */
		private String description;

		/** 블로그 포스트의 URL */
		private String link;

		/** 블로그 포스트가 있는 블로그의 이름 */
		private String bloggername;

		/** 블로그 포스트가 작성된 날짜 */
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
		private LocalDate postdate;

	}

	/**
	 * 결과 문서 정렬 방식
	 * */
	@Getter
	@AllArgsConstructor
	public enum Sort {
		/** 정확도순 */
		sim(DocumentSort.accuracy),
		/** 최신순 */
		date(DocumentSort.recency);

		private final DocumentSort documentCommandSort;

		public static Sort of(final DocumentSort sort) {
			if (Objects.isNull(sort)) {
				return null;
			}
			for (final Sort value : values()) {
				if (value.getDocumentCommandSort().equals(sort)) {
					return value;
				}
			}
			return null;
		}
	}
}
