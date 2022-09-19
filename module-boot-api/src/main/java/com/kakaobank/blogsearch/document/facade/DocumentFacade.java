package com.kakaobank.blogsearch.document.facade;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo;
import com.kakaobank.blogsearch.document.domain.DocumentService;
import com.kakaobank.blogsearch.keyword.domain.KeywordCommand;
import com.kakaobank.blogsearch.keyword.domain.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentFacade {

	private final DocumentService documentService;
	private final KeywordService keywordService;

	public DocumentInfo.Documents getDocuments(final DocumentCommand.GetDocuments command) {
		// 조회
		final DocumentInfo.Documents documentInfos = documentService.getDocuments(command);

		// 검색어 저장
		keywordService.saveKeyword(KeywordCommand.Search.of(command.getQuery()));

		// 결과 응답
		return documentInfos;
	}
}
