import { useInfiniteQuery } from 'react-query';
import SearchDocumentsRequest from '@src/document/application/port/out/request/SearchDocumentsRequest';
import SearchDocumentsQueryKeys from '@src/react/hook/document/DocumentQueryKeys';
import searchDocuments from '@src/document/application/port/out/searchDocuments';
import Document from '@src/document/domain/Document';

const useSearchDocuments = (request: SearchDocumentsRequest) =>
  useInfiniteQuery<Document[]>(
    [...SearchDocumentsQueryKeys.LIST, request],
    () => searchDocuments(request),
  );

export default useSearchDocuments;
