package seolnavy.blogsearch.document.infra.apicaller.client.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import seolnavy.blogsearch.document.domain.DocumentSort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoDocumentApiDto {

	@Getter
	@AllArgsConstructor(staticName = "of")
	public static class GetDocumentsRequest {

		/** 검색을 원하는 질의어 */
		private String query;

		/** 결과 문서 정렬 방식 */
		private Sort sort;

		/** 결과 페이지 번호 */
		private Integer page;

		/** 한 페이지에 보여질 문서 수 */
		private Integer size;

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

		private Meta meta;
		private List<Document> documents;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(staticName = "of")
	@ToString
	public static class Meta {

		/** 검색된 문서 수*/
		@JsonProperty("total_count")
		private Integer totalCount;
		/** total_count 중 노출 가능 문서 수 */
		@JsonProperty("pageable_count")
		private Integer pageableCount;
		/**
		 * 현재 페이지가 마지막 페이지인지 여부.
		 * 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
		 */
		@JsonProperty("is_end")
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

		/** 블로그 글 작성시간 */
		private ZonedDateTime datetime;

	}

	/**
	 * 결과 문서 정렬 방식
	 * */
	@Getter
	@AllArgsConstructor
	public enum Sort {
		/** 정확도순 */
		accuracy(DocumentSort.accuracy),
		/** 최신순 */
		recency(DocumentSort.recency);

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
