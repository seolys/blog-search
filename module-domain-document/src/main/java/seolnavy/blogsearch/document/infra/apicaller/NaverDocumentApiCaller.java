package seolnavy.blogsearch.document.infra.apicaller;

import seolnavy.blogsearch.document.domain.apicaller.DocumentApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import seolnavy.blogsearch.document.domain.DocumentCommand;
import seolnavy.blogsearch.document.domain.DocumentInfo.Documents;
import seolnavy.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiClient;
import seolnavy.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto;
import seolnavy.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDto.GetDocumentsResponse;
import seolnavy.blogsearch.document.infra.apicaller.client.naver.NaverDocumentApiDtoMapper;

@Order(value = 2)
@Component
@RequiredArgsConstructor
public class NaverDocumentApiCaller implements DocumentApiCaller {

	private final NaverDocumentApiClient apiClient;

	@Override public Documents getDocuments(final DocumentCommand.GetDocuments command) {
		final NaverDocumentApiDto.GetDocumentsRequest apiRequest = NaverDocumentApiDtoMapper.of(command);
		apiRequest.validate();

		final GetDocumentsResponse response = apiClient.getDocuments(apiRequest);

		return NaverDocumentApiDtoMapper.of(apiRequest.getStart(), response);
	}
}
