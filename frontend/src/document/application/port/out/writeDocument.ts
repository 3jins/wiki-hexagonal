import adapter from '@src/document/adapter/out/writeDocument';
import WriteDocumentRequest from '@src/document/application/port/out/request/WriteDocumentRequest';

const writeDocument: (request: WriteDocumentRequest) => Promise<string> = adapter;

export default writeDocument;
