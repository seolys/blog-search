package seolnavy.blogsearch.document.infra.apicaller.client.naver;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import seolnavy.blogsearch.document.domain.DocumentCommand;
import seolnavy.blogsearch.document.domain.DocumentInfo;
import seolnavy.blogsearch.document.domain.DocumentInfo.Documents;
import seolnavy.blogsearch.document.domain.DocumentInfo.Meta;
import seolnavy.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.GetDocumentsRequest;
import seolnavy.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.GetDocumentsResponse;
import seolnavy.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.Item;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NaverDocumentApiDtoMapper {

	public static GetDocumentsRequest of(final DocumentCommand.GetDocuments request) {
		return GetDocumentsRequest.of(
				request.getQuery(),
				NaverDocumentApiDto.Sort.of(request.getSort()),
				(request.getPage() - 1) * request.getSize() + 1,
				request.getSize()
		);
	}

	public static Documents of(final Integer start, final GetDocumentsResponse response) {
		final DocumentInfo.Meta meta = ofMeta(start, response);
		final List<DocumentInfo.Document> documentList = response.getItems()
				.stream()
				.map(NaverDocumentApiDtoMapper::of)
				.collect(Collectors.toList());
		return Documents.of(meta, documentList);
	}

	private static Meta ofMeta(final Integer start, final GetDocumentsResponse response) {
		return Meta.of(response.getTotal(), response.isEnd(start));
	}

	private static DocumentInfo.Document of(final Item document) {
		return DocumentInfo.Document.of(
				document.getTitle(),
				document.getDescription(),
				document.getLink(),
				document.getBloggername(),
				null,
				document.getPostdate()
		);
	}


}
