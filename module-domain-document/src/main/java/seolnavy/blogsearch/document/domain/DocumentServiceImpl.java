package seolnavy.blogsearch.document.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seolnavy.blogsearch.document.domain.DocumentInfo.Documents;
import seolnavy.blogsearch.document.domain.apicaller.DocumentApiTemplate;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

	private final DocumentApiTemplate apiCallTemplate;

	@Override
	public Documents getDocuments(final DocumentCommand.GetDocuments command) {
		command.validate();
		return apiCallTemplate.getDocuments(command);
	}

}
