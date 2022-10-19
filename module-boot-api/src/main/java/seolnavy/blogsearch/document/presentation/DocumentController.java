package seolnavy.blogsearch.document.presentation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import seolnavy.blogsearch.common.response.CommonResponse;
import seolnavy.blogsearch.document.domain.DocumentCommand;
import seolnavy.blogsearch.document.domain.DocumentInfo;
import seolnavy.blogsearch.document.facade.DocumentFacade;
import seolnavy.blogsearch.document.presentation.DocumentDto.GetDocumentResponse;

@RestController
@RequiredArgsConstructor
public class DocumentController {

	private final DocumentFacade documentFacade;

	/**
	 * 블로그 조회.
	 * @param request
	 * @return
	 */
	@GetMapping("/v1/documents")
	public CommonResponse<GetDocumentResponse> getDocuments(@NotNull @Valid final DocumentDto.GetDocumentRequest request) {
		final DocumentCommand.GetDocuments command = DocumentDtoMapper.of(request);

		final DocumentInfo.Documents DocumentInfos = documentFacade.getDocuments(command);

		final GetDocumentResponse response = DocumentDtoMapper.of(DocumentInfos);
		return CommonResponse.of(response);
	}

}
