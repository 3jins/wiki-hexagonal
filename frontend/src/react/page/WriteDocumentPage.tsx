import React from 'react';
import WriteDocumentRequest from '@src/document/application/port/out/request/WriteDocumentRequest';
import useWriteDocument from '@src/react/hook/document/useWriteDocument';
import { REQUEST_MEMBER_ID } from '@src/common/TemporarilyHardCoded';
import UploadDocumentTemplate from '@src/react/component/template/document/UploadDocumentTemplate';

export default () => {
  const {
    mutate: submitWriteDocument,
    isLoading: isDocumentWritePending,
  } = useWriteDocument();

  const onUploadedFileAnalyzed = (title: string, content: string) => {
    const request: WriteDocumentRequest = {
      body: { title, content },
      headers: {
        requestMemberId: REQUEST_MEMBER_ID,
      }
    };

    submitWriteDocument(request);
  }

  if (isDocumentWritePending) {
    return <p>loading...</p>;
  }

  return (
    <section>
      <UploadDocumentTemplate
        buttonText="작성하기"
        onUploadedFileAnalyzed={onUploadedFileAnalyzed}
      />
    </section>
  );
}
