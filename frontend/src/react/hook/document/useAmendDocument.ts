import { useMutation, useQueryClient } from 'react-query';
import AmendDocumentRequest from '@src/document/application/port/out/request/AmendDocumentRequest';
import DocumentQueryKeys from '@src/react/hook/document/DocumentQueryKeys';
import amendDocument from '@src/document/application/port/out/amendDocument';
import { DOCUMENT_URI } from '@src/document/adapter/out/DocumentUri';

const useAmendDocument = (documentId: number) => {
  const queryClient = useQueryClient();
  return useMutation(
    (request: AmendDocumentRequest) => amendDocument(request),
    {
      onSettled: () => {
        queryClient.invalidateQueries(DocumentQueryKeys.LIST);
      },
      onSuccess: () => {
        alert('게시물이 수정되었습니다.');
        window.location.href = `${DOCUMENT_URI}/${documentId}`;
      },
      onError: () => {
        alert('게시물 수정에 실패하였습니다.');
        window.location.reload();
      }
    }
  );
}

export default useAmendDocument;
