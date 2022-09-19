package com.kakaobank.blogsearch.document.domain.apicaller;

import com.kakaobank.blogsearch.document.domain.DocumentCommand;
import com.kakaobank.blogsearch.document.domain.DocumentInfo;

public interface DocumentApiCaller {

	DocumentInfo.Documents getDocuments(DocumentCommand.GetDocuments command);

}
