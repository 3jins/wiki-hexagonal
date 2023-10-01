import { useMutation, useQueryClient } from 'react-query';
import WriteDocumentRequest from '@src/document/application/port/out/request/WriteDocumentRequest';
import DocumentQueryKeys from '@src/react/hook/document/DocumentQueryKeys';
import writeDocument from '@src/document/application/port/out/writeDocument';
import { DOCUMENT_URI } from '@src/document/adapter/out/DocumentUri';

const useWriteDocument = () => {
  const queryClient = useQueryClient();
  return useMutation(
    (request: WriteDocumentRequest) => writeDocument(request),
    {
      onSuccess: (newDocumentId) => {
        queryClient.invalidateQueries(DocumentQueryKeys.LIST);
        alert('게시물이 등록되었습니다.');
        window.location.href = `${DOCUMENT_URI}/${newDocumentId}`;
      },
    }
  );
}

export default useWriteDocument;
