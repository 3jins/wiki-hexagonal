import { useInfiniteQuery } from 'react-query';
import GetDocumentRequest from '@src/document/application/port/out/request/GetDocumentRequest';
import DocumentQueryKeys from '@src/react/hook/document/DocumentQueryKeys';
import Document from '@src/document/domain/Document';
import getDocument from '@src/document/application/port/out/getDocument';
import { Undefinedable } from '@src/type';

const useGetDocument = (request: GetDocumentRequest) =>
  useInfiniteQuery<Undefinedable<Document>>(
    [...DocumentQueryKeys.DETAIL, request],
    () => getDocument(request),
  );

export default useGetDocument;
