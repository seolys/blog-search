package seolnavy.blogsearch.document.domain;

import seolnavy.blogsearch.document.domain.DocumentInfo.Documents;

public interface DocumentService {

	/**
	 * 블로그 검색
	 * @param command
	 * @return
	 */
	Documents getDocuments(DocumentCommand.GetDocuments command);

}
