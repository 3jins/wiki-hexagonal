import { Undefinedable } from '@src/type';

enum DocumentStatus {
  ON_DISPLAY, DELETED,
}

export const toDocumentStatus = (documentStatusName: string | null): Undefinedable<DocumentStatus> => {
  switch (documentStatusName) {
    case 'ON_DISPLAY':
      return DocumentStatus.ON_DISPLAY;
    case 'DELETED':
      return DocumentStatus.DELETED;
    case null:
    case undefined:
      return undefined;
    default:
      // TODO: 에러페이지 만들기 (전역적인 에러 상태를 만들고, 그에 따라 Bad Request 페이지 보여주기)
      throw Error('존재하지 않는 문서 상태입니다.');
  }
}

export default DocumentStatus;
