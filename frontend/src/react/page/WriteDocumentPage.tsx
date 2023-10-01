import React, { useState } from 'react';
import WriteDocumentRequest from '@src/document/application/port/out/request/WriteDocumentRequest';
import useWriteDocument from '@src/react/hook/document/useWriteDocument';
import WriteDocumentTemplate from '@src/react/component/template/document/WriteDocumentTemplate';
import { REQUEST_MEMBER_ID } from '@src/common/TemporarilyHardCoded';

export default () => {
  const [uploadedPostFile, setUploadedPostFile] = useState<FileList | null>(null);

  const {
    mutate: submitWriteDocument,
    isLoading: isDocumentWritePending,
  } = useWriteDocument();

  const onWriteDocumentSubmitClicked = async () => {
    if (!uploadedPostFile) {
      alert('업로드 된 파일이 없습니다.');
      return;
    }

    const post = uploadedPostFile.item(0)
    if (!post) {
      alert('업로드 된 파일이 없습니다.');
      return;
    }

    const request: WriteDocumentRequest = {
      params: {
        title: post.name,
        content: await post.text(),
      },
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
      {WriteDocumentTemplate({
        setUploadedPostFile,
        onWriteDocumentSubmitClicked,
      })}
    </section>
  );
}
