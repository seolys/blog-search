package com.kakaobank.blogsearch.document.facade;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo;
import com.kakaobank.blogsearch.document.domain.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentFacade {

	private final DocumentService documentService;

	public DocumentInfo.Documents getDocuments(final DocumentCommand.GetDocuments command) {
		// 조회
		final DocumentInfo.Documents documentInfos = documentService.getDocuments(command);

		// TODO: 검색어 저장

		// 결과 응답
		return documentInfos;
	}
}
