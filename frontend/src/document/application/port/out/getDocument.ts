import adapter from '@src/document/adapter/out/getDocument';
import Document from '@src/document/domain/Document';
import GetDocumentRequest from '@src/document/application/port/out/request/GetDocumentRequest';
import { Undefinedable } from '@src/type';

const getDocument: (request: GetDocumentRequest) => Promise<Undefinedable<Document>> = adapter;

export default getDocument;
