package com.kakaobank.blogsearch.document.presentation;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Documents;
import com.kakaobank.blogsearch.document.presentation.DocumentDto.GetDocumentRequest;
import com.kakaobank.blogsearch.document.presentation.DocumentDto.GetDocumentResponse;
import com.kakaobank.blogsearch.document.presentation.DocumentDto.Sort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentDtoMapper {

	public static DocumentCommand.GetDocuments of(final GetDocumentRequest request) {
		return DocumentCommand.GetDocuments.of(
				request.getQuery(),
				Sort.toDocumentCommandSort(request.getSort()),
				request.getPage(),
				request.getSize()
		);
	}

	public static GetDocumentResponse of(final Documents documents) {
		final DocumentDto.Meta meta = of(documents.getMeta());
		final List<DocumentDto.Document> documentList = documents.getDocuments()
				.stream()
				.map(DocumentDtoMapper::of)
				.collect(Collectors.toList());
		return GetDocumentResponse.of(meta, documentList);
	}

	private static DocumentDto.Document of(final DocumentInfo.Document document) {
		return DocumentDto.Document.of(
				document.getTitle(),
				document.getContents(),
				document.getUrl(),
				document.getBlogname(),
				document.getThumbnail(),
				document.getRegistrationDate()
		);
	}

	public static DocumentDto.Meta of(final DocumentInfo.Meta meta) {
		return DocumentDto.Meta.of(meta.getTotalCount(), meta.getIsEnd());
	}
}
