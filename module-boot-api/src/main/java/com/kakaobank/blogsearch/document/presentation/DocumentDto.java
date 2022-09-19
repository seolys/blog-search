package com.kakaobank.blogsearch.document.presentation;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentDto {

	/**
	 * 블로그 검색 조건
	 */
	@Getter
	@Setter
	@ToString
	public static class GetDocumentRequest {

		/** 검색을 원하는 질의어 */
		@NotNull @NotEmpty
		private String query;

		/** 결과 문서 정렬 방식 */
		private Sort sort;

		/** 결과 페이지 번호 */
		@Min(1) @Max(50)
		private Integer page;

		/** 한 페이지에 보여질 문서 수 */
		@Min(1) @Max(50)
		private Integer size;

	}

	/**
	 * 블로그 검색 결과
	 */
	@Getter
	@ToString
	@AllArgsConstructor(staticName = "of")
	public static class GetDocumentResponse {

		private Meta meta;
		private List<Document> documents;
	}

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class Meta {

		/** 검색된 문서 수*/
		private Integer totalCount;
		/**
		 * 현재 페이지가 마지막 페이지인지 여부.
		 * 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
		 */
		private Boolean isEnd;
	}

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class Document {

		/** 블로그 글 제목 */
		private String title;

		/** 블로그 글 요약 */
		private String contents;

		/** 블로그 글 URL */
		private String url;

		/** 블로그의 이름 */
		private String blogname;

		/** 검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음 */
		private String thumbnail;

		/** 블로그 글 작성일자 */
		private LocalDate registrationDate;

	}

	/**
	 * 결과 문서 정렬 방식
	 * */
	@Getter
	@AllArgsConstructor
	public enum Sort {
		/** 정확도순 */
		accuracy(DocumentCommand.Sort.accuracy),
		/** 최신순 */
		recency(DocumentCommand.Sort.recency);

		private final DocumentCommand.Sort documentCommandSort;

		public static DocumentCommand.Sort toDocumentCommandSort(final Sort sort) {
			if (Objects.isNull(sort)) {
				return null;
			}
			for (final Sort value : values()) {
				if (value.equals(sort)) {
					return value.getDocumentCommandSort();
				}
			}
			return null;
		}

	}

}
