package seolnavy.blogsearch.document.infra.apicaller;

import seolnavy.blogsearch.document.domain.apicaller.DocumentApiCaller;
import seolnavy.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import seolnavy.blogsearch.document.domain.DocumentCommand;
import seolnavy.blogsearch.document.domain.DocumentInfo.Documents;
import seolnavy.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiClient;
import seolnavy.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto;
import seolnavy.blogsearch.document.infra.apicaller.client.kakao.KakaoDocumentApiDto.GetDocumentsResponse;

@Order(value = 1)
@Component
@RequiredArgsConstructor
public class KakaoDocumentApiCaller implements DocumentApiCaller {

	private final KakaoDocumentApiClient apiClient;

	@Override public Documents getDocuments(final DocumentCommand.GetDocuments command) {
		final KakaoDocumentApiDto.GetDocumentsRequest apiRequest = KakaoDocumentApiDtoMapper.of(command);
		apiRequest.validate();

		final GetDocumentsResponse response = apiClient.getDocuments(apiRequest);

		return KakaoDocumentApiDtoMapper.of(response);
	}
}
