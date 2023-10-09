import React from 'react';
import AmendDocumentRequest from '@src/document/application/port/out/request/AmendDocumentRequest';
import useAmendDocument from '@src/react/hook/document/useAmendDocument';
import { REQUEST_MEMBER_ID } from '@src/common/TemporarilyHardCoded';
import { useParams } from 'react-router-dom';
import UploadDocumentTemplate from '@src/react/component/template/document/UploadDocumentTemplate';

export default () => {
  const { documentId: rawDocumentId } = useParams();

  if (!rawDocumentId || isNaN(Number(rawDocumentId))) {
    // TODO: 에러페이지 만들기
    alert('잘못된 요청입니다');
    return null;
  }

  const documentId = Number(rawDocumentId);

  const {
    mutate: submitAmendDocument,
    isLoading: isDocumentAmendPending,
  } = useAmendDocument(documentId);

  const onUploadedFileAnalyzed = (title: string, content: string) => {
    const request: AmendDocumentRequest = {
      documentId,
      body: { title, content },
      headers: {
        requestMemberId: REQUEST_MEMBER_ID,
      }
    };

    submitAmendDocument(request);
  }

  if (isDocumentAmendPending) {
    return <p>loading...</p>;
  }

  return (
    <section>
      <UploadDocumentTemplate
        buttonText="개정하기"
        onUploadedFileAnalyzed={onUploadedFileAnalyzed}
      />
    </section>
  );
}
