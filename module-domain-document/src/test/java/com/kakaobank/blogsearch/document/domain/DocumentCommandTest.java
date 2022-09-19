package com.kakaobank.blogsearch.document.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.kakaobank.blogsearch.document.domain.DocumentCommand.GetDocuments;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class DocumentCommandTest {

	@ParameterizedTest
	@MethodSource("validate_success_arguments")
	@DisplayName("검색 요청 검증 성공")
	void validate_success(final GetDocuments command) {
		assertDoesNotThrow(() -> command.validate());
	}

	static Stream<GetDocuments> validate_success_arguments() {
		return Stream.of(
				GetDocuments.of("query", DocumentSort.accuracy, 1, 10),
				GetDocuments.of("query", null, null, null)
		);
	}

	@ParameterizedTest
	@MethodSource("validate_queryIsEmpty_fail_arguments")
	@DisplayName("query가 비어있는 경우 예외가 발생한다.")
	void validate_queryIsEmpty_fail(final String query) {
		// given
		final GetDocuments command = GetDocuments.of(query, null, null, null);

		// when,then
		assertThatThrownBy(() -> command.validate()).isInstanceOf(IllegalArgumentException.class);
	}

	static Stream<String> validate_queryIsEmpty_fail_arguments() {
		return Stream.of(null, "", " ");
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 51})
	@DisplayName("page가 1~50이 아닌 경우 예외가 발생한다.")
	void validate_pageIsInvalid_fail(final int page) {
		// given
		final GetDocuments command = GetDocuments.of("query", null, page, null);

		// when,then
		assertThatThrownBy(() -> command.validate()).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 81})
	@DisplayName("size가 1~50 사이가 아닌 경우 예외가 발생한다.")
	void validate_sizeIsInvalid_fail(final int size) {
		// given
		final GetDocuments command = GetDocuments.of("query", null, null, size);

		// when,then
		assertThatThrownBy(() -> command.validate()).isInstanceOf(IllegalArgumentException.class);
	}

}