package seolnavy.blogsearch.document.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seolnavy.blogsearch.document.domain.DocumentCommand;
import seolnavy.blogsearch.document.domain.DocumentInfo;
import seolnavy.blogsearch.document.domain.DocumentService;
import seolnavy.blogsearch.keyword.domain.KeywordCommand;
import seolnavy.blogsearch.keyword.domain.KeywordService;

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
