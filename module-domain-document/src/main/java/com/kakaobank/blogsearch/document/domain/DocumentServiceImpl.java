package com.kakaobank.blogsearch.document.domain;

import com.kakaobank.blogsearch.document.domain.DocumentInfo.Documents;
import com.kakaobank.blogsearch.document.domain.apicaller.DocumentApiTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
