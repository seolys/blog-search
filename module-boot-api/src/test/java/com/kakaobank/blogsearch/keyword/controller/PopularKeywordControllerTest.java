package com.kakaobank.blogsearch.keyword.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakaobank.blogsearch.keyword.domain.Keyword;
import com.kakaobank.blogsearch.keyword.infra.KeywordJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PopularKeywordControllerTest {

	@Autowired private MockMvc mockMvc;
	@Autowired private KeywordJpaRepository keywordJpaRepository;

	@Test
	void popularTop10_empty_success() throws Exception {
		mockMvc.perform(
						MockMvcRequestBuilders.get("/v1/keywords/popularTop10")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code").exists())
				.andExpect(jsonPath("$.message").exists())
				.andDo(print());
	}

	@Test
	void popularTop10_notEmpty_success() throws Exception {
		final String keyword = "야호";
		searched(keyword);

		mockMvc.perform(
						MockMvcRequestBuilders.get("/v1/keywords/popularTop10")
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code").exists())
				.andExpect(jsonPath("$.message").exists())
				.andExpect(jsonPath("$.data.popularKeywordInfos[0].keyword", keyword).exists())
				.andExpect(jsonPath("$.data.popularKeywordInfos[0].searchCount").exists())
				.andDo(print());
	}

	private void searched(final String query) {
		keywordJpaRepository.save(Keyword.searched(query));
	}

}