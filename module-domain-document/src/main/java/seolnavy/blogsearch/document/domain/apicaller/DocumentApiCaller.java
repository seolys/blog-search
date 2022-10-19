package seolnavy.blogsearch.document.domain.apicaller;

import seolnavy.blogsearch.document.domain.DocumentCommand.GetDocuments;
import seolnavy.blogsearch.document.domain.DocumentInfo.Documents;

public interface DocumentApiCaller {

	Documents getDocuments(GetDocuments command);

}
