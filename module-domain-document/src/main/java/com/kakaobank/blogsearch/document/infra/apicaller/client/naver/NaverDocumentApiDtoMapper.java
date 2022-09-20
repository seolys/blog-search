package com.kakaobank.blogsearch.document.infra.apicaller.client.naver;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Documents;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Meta;
import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.GetDocumentsRequest;
import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.GetDocumentsResponse;
import com.kakaobank.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.Item;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
