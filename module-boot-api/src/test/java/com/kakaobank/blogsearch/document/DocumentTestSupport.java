package com.kakaobank.blogsearch.document;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.kakaobank.blogsearch.document.domain.DocumentCommand.GetDocuments;
import com.kakaobank.blogsearch.document.domain.DocumentInfo;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Document;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Meta;
import com.kakaobank.blogsearch.document.domain.apicaller.DocumentApiTemplate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocumentTestSupport {

	public static void mockApiTemplate(final DocumentApiTemplate mockApiClient, final GetDocuments request) {
		final int totalCount = 100;
		final Meta meta = Meta.of(totalCount, isEnd(request, totalCount));
		final List<Document> makeDocuments = makeDocuments(request);
		final DocumentInfo.Documents documents = DocumentInfo.Documents.of(meta, makeDocuments);
		given(mockApiClient.getDocuments(any())).willReturn(documents);
	}

	private static boolean isEnd(final GetDocuments request, final int totalCount) {
		if (Objects.isNull(request.getSize())) {
			return false;
		}
		return request.getSize() >= totalCount;
	}

	private static List<Document> makeDocuments(final GetDocuments request) {
		final List<Document> documents = new ArrayList<>();
		for (int i = 0; i < getSize(request); i++) {
			final Document document = makeDocument(request, i);
			documents.add(document);
		}
		return documents;
	}

	private static Integer getSize(final GetDocuments request) {
		if (Objects.isNull(request.getSize())) {
			return 10;
		}
		return request.getSize();
	}

	private static Document makeDocument(final GetDocuments request, final int i) {
		return Document.of(request.getQuery() + i, request.getQuery() + i, "url", "blogname", "thumbnail", LocalDate.now());
	}

}
