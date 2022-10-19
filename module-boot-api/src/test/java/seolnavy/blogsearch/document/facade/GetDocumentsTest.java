package seolnavy.blogsearch.document.facade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import seolnavy.blogsearch.document.DocumentTestSupport;
import seolnavy.blogsearch.document.domain.DocumentCommand;
import seolnavy.blogsearch.document.domain.DocumentInfo.Documents;
import seolnavy.blogsearch.document.domain.DocumentSort;
import seolnavy.blogsearch.document.domain.apicaller.DocumentApiTemplate;

@SpringBootTest
class GetDocumentsTest {

	@Autowired private DocumentFacade documentFacade;

	@MockBean private DocumentApiTemplate apiTemplate;


	@Test
	@DisplayName("블로그 검색 검증")
	void searchBlog_success() {
		// given
		final DocumentCommand.GetDocuments request = DocumentCommand.GetDocuments.of("커피", DocumentSort.accuracy, 1, 20);
		DocumentTestSupport.mockApiTemplate(apiTemplate, request);

		// when
		final Documents search = documentFacade.getDocuments(request);

		// then
		assertThat(search.getMeta()).isNotNull();
		assertThat(search.getDocuments()).isNotEmpty();
		verify(apiTemplate, atLeastOnce()).getDocuments(any());
	}

}