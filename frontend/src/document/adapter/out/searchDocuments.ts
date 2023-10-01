import axios from 'axios';
import SearchDocumentsResponse from '@src/document/application/port/out/response/SearchDocumentsResponse';
import SearchDocumentsRequest from '@src/document/application/port/out/request/SearchDocumentsRequest';
import { DOCUMENT_API_URI } from '@src/document/adapter/out/DocumentApiUri';
import Document, { DocumentId, DocumentVersionId } from '@src/document/domain/Document';
import Member, { MemberId } from '@src/member/domain/Member';
import mockedDocuments from '@src/document/adapter/out/MockedDocuments';

const searchDocuments = async (request: SearchDocumentsRequest): Promise<Document[]> => {
  const baseUrl = import.meta.env.VITE_BASE_API_URL;

  const rawResponse = await axios.get(
    baseUrl + DOCUMENT_API_URI,
    { params: request },
  );

  const searchDocumentsResponses: SearchDocumentsResponse[] = rawResponse.data?.documents || [];

  return searchDocumentsResponses.map(
    (searchDocumentsResponse: SearchDocumentsResponse) => convertResponseToDomain(searchDocumentsResponse),
  );
};

const searchDocumentsMock = async (request: SearchDocumentsRequest): Promise<Document[]> => mockedDocuments.map(
  (searchDocumentsResponse: SearchDocumentsResponse) => convertResponseToDomain(searchDocumentsResponse),
);

const convertResponseToDomain = (searchDocumentsResponse: SearchDocumentsResponse): Document => Document.of(
  new DocumentId(searchDocumentsResponse.documentId),
  Member.of(
    new MemberId(searchDocumentsResponse.author.memberId),
    searchDocumentsResponse.author.name,
  ),
  searchDocumentsResponse.status,
  new DocumentVersionId(searchDocumentsResponse.latestVersionId),
  searchDocumentsResponse.title,
  searchDocumentsResponse.content,
  searchDocumentsResponse.createdAt,
  searchDocumentsResponse.updatedAt,
);

export default import.meta.env.MODE === 'mock' ? searchDocumentsMock : searchDocuments;
