package seolnavy.blogsearch.document.infra.apicaller.client.kakao;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import seolnavy.blogsearch.document.domain.DocumentCommand;
import seolnavy.blogsearch.document.domain.DocumentInfo;
import seolnavy.blogsearch.document.domain.DocumentInfo.Documents;
import seolnavy.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto.GetDocumentsRequest;
import seolnavy.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto.GetDocumentsResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoDocumentApiDtoMapper {

	public static GetDocumentsRequest of(final DocumentCommand.GetDocuments request) {
		return GetDocumentsRequest.of(
				request.getQuery(),
				KakaoDocumentApiDto.Sort.of(request.getSort()),
				request.getPage(),
				request.getSize()
		);
	}

	public static Documents of(final GetDocumentsResponse response) {
		final DocumentInfo.Meta meta = of(response.getMeta());
		final List<DocumentInfo.Document> documentList = response.getDocuments()
				.stream()
				.map(KakaoDocumentApiDtoMapper::of)
				.collect(Collectors.toList());
		return Documents.of(meta, documentList);
	}

	private static DocumentInfo.Document of(final KakaoDocumentApiDto.Document document) {
		return DocumentInfo.Document.of(
				document.getTitle(),
				document.getContents(),
				document.getUrl(),
				document.getBlogname(),
				document.getThumbnail(),
				getRegistrationDate(document.getDatetime())
		);
	}

	private static LocalDate getRegistrationDate(final ZonedDateTime datetime) {
		if (Objects.isNull(datetime)) {
			return null;
		}
		return datetime.toLocalDate();
	}

	public static DocumentInfo.Meta of(final KakaoDocumentApiDto.Meta meta) {
		return DocumentInfo.Meta.of(meta.getTotalCount(), meta.getIsEnd());
	}

}
