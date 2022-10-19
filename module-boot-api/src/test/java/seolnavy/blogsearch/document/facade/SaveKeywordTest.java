package seolnavy.blogsearch.document.facade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.concurrent.Executor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SyncTaskExecutor;
import seolnavy.blogsearch.BlogSearchApiApplication;
import seolnavy.blogsearch.document.domain.DocumentCommand.GetDocuments;
import seolnavy.blogsearch.document.domain.DocumentService;
import seolnavy.blogsearch.document.domain.DocumentSort;
import seolnavy.blogsearch.keyword.domain.Keyword;
import seolnavy.blogsearch.keyword.infra.KeywordJpaRepository;

@SpringBootTest
class SaveKeywordTest {

	@Autowired private DocumentFacade documentFacade;
	@Autowired private KeywordJpaRepository keywordJpaRepository;

	@MockBean private DocumentService documentService;

	@Configuration
	@Import(BlogSearchApiApplication.class)
	static class ContextConfiguration {

		@Primary
		@Bean(name = "threadPoolTaskExecutor")
		public Executor executor() {
			return new SyncTaskExecutor();
		}
	}

	@Test
	@DisplayName("검색어 저장 검증")
	void saveKeyword_success() {
		// given
		final String keyword = "커피";
		final GetDocuments request = GetDocuments.of(keyword, DocumentSort.accuracy, 1, 20);
		mockSearchService();

		// when
		documentFacade.getDocuments(request);

		// then
		final Keyword findKeyword = keywordJpaRepository.findById(1L).get();
		assertThat(findKeyword.getKeyword()).isEqualTo(keyword);
	}

	private void mockSearchService() {
		given(documentService.getDocuments(any())).willReturn(null);
	}

}