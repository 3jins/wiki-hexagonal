import React, { useState } from 'react';
import Document from '@src/document/domain/Document';
import SearchDocumentsRequest from '@src/document/application/port/out/request/SearchDocumentsRequest';
import useSearchDocuments from '@src/react/hook/document/useSearchDocuments';
import { Undefinedable } from '@src/type';
import DocumentSummaryTemplate from '@src/react/component/template/document/DocumentSummaryTemplate';
import DocumentSearchTemplate from '@src/react/component/template/document/DocumentSearchTemplate';
import DocumentsListSection from '@src/react/component/organism/documentsListSection/DocumentsListSection';

export default () => {
  const [searchType, setSearchType] = useState<string>('title')
  const [searchText, setSearchText] = useState<string>('');

  const onWriteDocumentSubmitClicked = () => refetchSearchDocuments();

  const makeSearchDocumentsRequest = () => {
    switch(searchType) {
      case 'author':
        break;
      case 'title':
        return { params: { title: searchText }};
      case 'content':
        return { params: { content: searchText }};
    }
    return { params: {}};
  };

  const request: SearchDocumentsRequest = makeSearchDocumentsRequest();

  const {
    isLoading: areDocumentsLoading,
    data: searchDocumentsQueryResponse,
    refetch: refetchSearchDocuments,
  } = useSearchDocuments(request);

  if (areDocumentsLoading) {
    return <p>loading...</p>;
  }

  const documents: Undefinedable<Document[]> = searchDocumentsQueryResponse?.pages[0];

  return (
    <React.Fragment>
      {DocumentSearchTemplate({
        searchOptions: [
          { value: 'title', displayName: '제목', isDefault: true },
          { value: 'content', displayName: '내용' },
        ],
        setSearchType,
        setSearchText,
        onWriteDocumentSubmitClicked,
      })}
      <DocumentsListSection>
        {documents?.map((document: Document) => DocumentSummaryTemplate({ document }))}
      </DocumentsListSection>
    </React.Fragment>
  );
}
