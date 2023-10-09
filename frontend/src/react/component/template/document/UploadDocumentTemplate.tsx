import React, { useState } from 'react';
import Label from '@src/react/component/atom/label/Label';
import FileInput from '@src/react/component/atom/fileInput/FileInput';
import Button from '@src/react/component/atom/button/Button';

type UploadDocumentTemplateProps = {
  onUploadedFileAnalyzed: (title: string, content: string) => void,
  buttonText: string,
}

export default (props: UploadDocumentTemplateProps) => {
  const [uploadedPostFile, setUploadedPostFile] = useState<FileList | null>(null);

  const onUploadDocumentSubmitClicked = async () => {
    if (!uploadedPostFile) {
      alert('업로드 된 파일이 없습니다.');
      return null;
    }

    const post = uploadedPostFile.item(0)
    if (!post) {
      alert('업로드 된 파일이 없습니다.');
      return null;
    }

    const fileName = post.name.normalize('NFC');
    const fileExtension = fileName.split('.').reverse()[0];

    const title = fileName.slice(0, -(fileExtension.length + 1));
    const content = await post.text();

    props.onUploadedFileAnalyzed(title, content);
  };

  const documentFileInputId = 'document-file-input';

  return (
    <React.Fragment>
      <Label htmlFor={documentFileInputId}>
        문서 파일
      </Label>
      <FileInput
        id={documentFileInputId}
        accept=".md"
        onChange={e => setUploadedPostFile(e.target!.files)}
      />
      <Button onClick={() => onUploadDocumentSubmitClicked()}>{props.buttonText}</Button>
    </React.Fragment>
  );
};
