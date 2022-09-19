package com.kakaobank.blogsearch.document.facade;

import static com.kakaobank.blogsearch.document.DocumentTestSupport.mockApiTemplate;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo.Documents;
import com.kakaobank.blogsearch.document.domain.DocumentSort;
import com.kakaobank.blogsearch.document.domain.apicaller.DocumentApiTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class GetDocumentsTest {

	@Autowired private DocumentFacade documentFacade;

	@MockBean private DocumentApiTemplate apiTemplate;


	@Test
	@DisplayName("블로그 검색 검증")
	void searchBlog_success() {
		// given
		final DocumentCommand.GetDocuments request = DocumentCommand.GetDocuments.of("커피", DocumentSort.accuracy, 1, 20);
		mockApiTemplate(apiTemplate, request);

		// when
		final Documents search = documentFacade.getDocuments(request);

		// then
		assertThat(search.getMeta()).isNotNull();
		assertThat(search.getDocuments()).isNotEmpty();
		verify(apiTemplate, atLeastOnce()).getDocuments(any());
	}

}