import adapter from '@src/document/adapter/out/amendDocument';
import AmendDocumentRequest from '@src/document/application/port/out/request/AmendDocumentRequest';

const amendDocument: (request: AmendDocumentRequest) => Promise<string> = adapter;

export default amendDocument;
