package com.kakaobank.blogsearch.document.controller;

import static com.kakaobank.blogsearch.document.DocumentTestSupport.mockApiTemplate;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakaobank.blogsearch.document.domain.DocumentCommand.GetDocuments;
import com.kakaobank.blogsearch.document.domain.DocumentSort;
import com.kakaobank.blogsearch.document.domain.apicaller.DocumentApiTemplate;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class DocumentControllerTest {

	@Autowired private MockMvc mockMvc;
	@MockBean private DocumentApiTemplate apiTemplate;

	@Test
	@DisplayName("블로그 검색 성공")
	void search_success() throws Exception {
		final GetDocuments request = GetDocuments.of("커피", DocumentSort.accuracy, 1, 20);
		mockApiTemplate(apiTemplate, request);

		mockMvc.perform(
						MockMvcRequestBuilders.get("/v1/documents")
								.param("query", request.getQuery())
								.param("sort", getSortName(request.getSort()))
								.param("page", String.valueOf(request.getPage()))
								.param("size", String.valueOf(request.getSize()))
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code").exists())
				.andExpect(jsonPath("$.message").exists())
				.andExpect(jsonPath("$.data.meta.totalCount").exists())
				.andExpect(jsonPath("$.data.meta.isEnd").exists())
				.andExpect(jsonPath("$.data.documents[0].title").exists())
				.andExpect(jsonPath("$.data.documents[0].contents").exists())
				.andExpect(jsonPath("$.data.documents[0].url").exists())
				.andExpect(jsonPath("$.data.documents[0].blogname").exists())
				.andExpect(jsonPath("$.data.documents[0].thumbnail").exists())
				.andExpect(jsonPath("$.data.documents[0].registrationDate").exists())
				.andDo(print());
	}


	static Stream<GetDocuments> search_invalidParameter_fail_arguments() {
		return Stream.of(
				GetDocuments.of(null, null, null, null),
				GetDocuments.of("", null, null, null),
				GetDocuments.of(" ", null, null, null),
				GetDocuments.of("검색어", null, 0, null),
				GetDocuments.of("검색어", null, 51, null),
				GetDocuments.of("검색어", null, null, 0),
				GetDocuments.of("검색어", null, null, 81)
		);
	}

	@ParameterizedTest
	@MethodSource("search_invalidParameter_fail_arguments")
	@DisplayName("블로그 검색 실패 - 잘못된 파라미터")
	void search_invalidParameter_fail(final GetDocuments request) throws Exception {
		mockApiTemplate(apiTemplate, request);

		mockMvc.perform(
						MockMvcRequestBuilders.get("/v1/documents")
								.param("query", request.getQuery())
								.param("sort", getSortName(request.getSort()))
								.param("page", String.valueOf(request.getPage()))
								.param("size", String.valueOf(request.getSize()))
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code").exists())
				.andExpect(jsonPath("$.message").exists())
				.andDo(print());
	}

	private String getSortName(final DocumentSort sort) {
		if (Objects.isNull(sort)) {
			return null;
		}
		return sort.name();
	}
}