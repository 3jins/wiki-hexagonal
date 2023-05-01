import React from 'react';
import moment from 'moment';
import Document from '@src/document/domain/Document';
import SearchDocumentsRequest from '@src/document/application/port/out/request/SearchDocumentsRequest';
import useSearchDocuments from '@src/react/hook/useSearchDocuments';
import { Undefinedable } from '@src/type';

export default () => {
  const request: SearchDocumentsRequest = {};
  const {
    isLoading,
    data: searchDocumentsQueryResponse,
  } = useSearchDocuments(request);

  if (isLoading) {
    return <p>isLoading...</p>;
  }

  const documents: Undefinedable<Document[]> = searchDocumentsQueryResponse?.pages[0];

  return (
    <section>
      <ul>
        {
          documents?.map((document: Document) => (
            <li key={`document-${document.id?.value}`}>
              <h2>{document.title}</h2>
              <p>작성일: {moment(document.createdAt).format('lll')}</p>
              <p>마지막 수정일: {moment(document.updatedAt).format('lll')}</p>
              <p>{document.content}</p>
            </li>
          ))
        }
      </ul>
    </section>
  );
}
