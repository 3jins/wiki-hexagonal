import adapter from '@src/document/adapter/out/searchDocuments';
import SearchDocumentsRequest from '@src/document/application/port/out/request/SearchDocumentsRequest';
import Document from '@src/document/domain/Document';

const searchDocuments: (request: SearchDocumentsRequest) => Promise<Document[]> = adapter;

export default searchDocuments;
