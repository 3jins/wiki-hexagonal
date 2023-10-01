import axios from 'axios';
import GetDocumentResponse from '@src/document/application/port/out/response/GetDocumentResponse';
import GetDocumentRequest from '@src/document/application/port/out/request/GetDocumentRequest';
import { DOCUMENT_API_URI } from '@src/document/adapter/out/DocumentApiUri';
import Document, { DocumentId, DocumentVersionId } from '@src/document/domain/Document';
import Member, { MemberId } from '@src/member/domain/Member';
import mockedDocuments from '@src/document/adapter/out/MockedDocuments';
import { Undefinedable } from '@src/type';

const getDocument = async (request: GetDocumentRequest): Promise<Undefinedable<Document>> => {
  const baseUrl = import.meta.env.VITE_BASE_API_URL;

  const rawResponse = await axios.get(
    `${baseUrl}${DOCUMENT_API_URI}/${request.documentId}`,
  );

  const getDocumentResponse: Undefinedable<GetDocumentResponse> = rawResponse.data;

  return convertResponseToDomain(getDocumentResponse);
};

const getDocumentMock = (request: GetDocumentRequest): Undefinedable<Document> => {
  const documentMock = mockedDocuments.find(
    mockedDocument => mockedDocument.documentId.toString() === request.documentId,
  );
  return convertResponseToDomain(documentMock);
}

const convertResponseToDomain = (getDocumentResponse: Undefinedable<GetDocumentResponse>): Undefinedable<Document> => {
  if (getDocumentResponse === undefined) {
    return undefined;
  }

  return Document.of(
    new DocumentId(getDocumentResponse.documentId),
    Member.of(
      new MemberId(getDocumentResponse.author.memberId),
      getDocumentResponse.author.name,
    ),
    getDocumentResponse.status,
    new DocumentVersionId(getDocumentResponse.latestVersionId),
    getDocumentResponse.title,
    getDocumentResponse.content,
    getDocumentResponse.createdAt,
    getDocumentResponse.updatedAt,
  );
}

export default import.meta.env.MODE === 'mock' ? getDocumentMock : getDocument;
