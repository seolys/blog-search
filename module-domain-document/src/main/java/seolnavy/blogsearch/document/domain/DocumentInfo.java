package seolnavy.blogsearch.document.domain;

import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentInfo {

	/**
	 * 블로그 검색 결과
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(staticName = "of")
	@ToString
	public static class Documents {

		private Meta meta;
		private List<Document> documents;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(staticName = "of")
	@ToString
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
	@NoArgsConstructor
	@AllArgsConstructor(staticName = "of")
	@ToString
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

}
