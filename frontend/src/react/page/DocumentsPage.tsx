import React from 'react';
import styled from 'styled-components';
import Document from '@src/document/domain/Document';
import SearchDocumentsRequest from '@src/document/application/port/out/request/SearchDocumentsRequest';
import useSearchDocuments from '@src/react/hook/document/useSearchDocuments';
import { Undefinedable } from '@src/type';
import DocumentSummaryTemplate from '@src/react/component/template/document/DocumentSummaryTemplate';

export default () => {
  const request: SearchDocumentsRequest = {};
  const {
    isLoading: areDocumentsLoading,
    data: searchDocumentsQueryResponse,
  } = useSearchDocuments(request);

  if (areDocumentsLoading) {
    return <p>loading...</p>;
  }

  const documents: Undefinedable<Document[]> = searchDocumentsQueryResponse?.pages[0];

  return (
    <section>
      <DocumentsList>
        {
          documents?.map((document: Document) => DocumentSummaryTemplate({
            document: document,
          }))
        }
      </DocumentsList>
    </section>
  );
}

const DocumentsList = styled.ul`
  padding: 0;
`;
