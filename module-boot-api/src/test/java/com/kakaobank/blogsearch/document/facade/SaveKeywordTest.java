package com.kakaobank.blogsearch.document.facade;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.kakaobank.blogsearch.document.domain.DocumentCommand.GetDocuments;
import com.kakaobank.blogsearch.document.domain.DocumentCommand.Sort;
import com.kakaobank.blogsearch.document.domain.DocumentService;
import com.kakaobank.blogsearch.keyword.domain.Keyword;
import com.kakaobank.blogsearch.keyword.infra.KeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SaveKeywordTest {

	@Autowired private DocumentFacade documentFacade;
	@Autowired private KeywordRepository keywordRepository;

	@MockBean private DocumentService documentService;

	@Test
	@DisplayName("검색어 저장 검증")
	void saveKeyword_success() {
		// given
		final String keyword = "커피";
		final GetDocuments request = GetDocuments.of(keyword, Sort.accuracy, 1, 20);
		mockSearchService();

		// when
		documentFacade.getDocuments(request);

		// then
		final Keyword findKeyword = keywordRepository.findById(1L).get();
		assertThat(findKeyword.getKeyword()).isEqualTo(keyword);
	}

	private void mockSearchService() {
		given(documentService.getDocuments(any())).willReturn(null);
	}

}