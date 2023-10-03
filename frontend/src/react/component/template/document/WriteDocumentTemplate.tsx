import React, { Dispatch } from 'react';
import Label from '@src/react/component/atom/Label';
import FileInput from '@src/react/component/atom/FileInput';

type WriteDocumentTemplateProps = {
  setUploadedPostFile: Dispatch<FileList | null>,
  onWriteDocumentSubmitClicked: () => {},
}

const WriteDocumentTemplate = (props: WriteDocumentTemplateProps) => {
  const documentFileInputId = 'document-file-input';

  return (
    <React.Fragment>
      <Label htmlFor={documentFileInputId}>
        문서 파일
      </Label>
      <FileInput
        id={documentFileInputId}
        type="file"
        name="file"
        accept=".md"
        onChange={e => props.setUploadedPostFile(e.target!.files)}
      />
      <button type="button" onClick={() => props.onWriteDocumentSubmitClicked()}>작성하기</button>
    </React.Fragment>
  );
}

export default WriteDocumentTemplate;
