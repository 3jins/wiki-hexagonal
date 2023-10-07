import React from 'react';
import { useParams } from 'react-router-dom';
import Document from '@src/document/domain/Document';
import { Undefinedable } from '@src/type';
import GetDocumentRequest from '@src/document/application/port/out/request/GetDocumentRequest';
import useGetDocument from '@src/react/hook/document/useGetDocument';
import DocumentDetailTemplate from '@src/react/component/template/document/DocumentDetailTemplate';

export default () => {
  const { documentId: rawDocumentId } = useParams();

  if (!rawDocumentId || isNaN(Number(rawDocumentId))) {
    // TODO: 에러페이지 만들기
    alert('잘못된 요청입니다');
    return null;
  }

  const documentId = Number(rawDocumentId);

  const request: GetDocumentRequest = { documentId };
  const {
    isLoading: isDocumentLoading,
    data: getDocumentQueryResponse,
  } = useGetDocument(request);

  if (isDocumentLoading) {
    return <p>loading...</p>;
  }

  const document: Undefinedable<Document> = getDocumentQueryResponse?.pages[0];
  if (!document) {
    // TODO: 에러페이지 만들기
    alert('존재하지 않는 페이지입니다.');
    return null;
  }

  return (
    <section>
      {DocumentDetailTemplate({ document })}
    </section>
  );
}
