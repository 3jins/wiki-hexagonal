import DocumentStatus from '@src/document/domain/DocumentStatus';

type SearchDocumentsRequest = {
  params: {
    authorId?: string,
    status?: DocumentStatus,
    title?: String,
    content?: String,
  }
};

export default SearchDocumentsRequest;
