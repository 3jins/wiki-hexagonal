import axios from 'axios';
import SearchDocumentsResponse from '@src/document/application/port/out/response/SearchDocumentsResponse';
import SearchDocumentsRequest from '@src/document/application/port/out/request/SearchDocumentsRequest';
import { DOCUMENT_URI } from '@src/document/adapter/out/DocumentUri';
import Document, { DocumentId, DocumentVersionId } from '@src/document/domain/Document';
import { MemberId } from '@src/member/domain/Member';

const searchDocuments = async (request: SearchDocumentsRequest): Promise<Document[]> => {
  const baseUrl = import.meta.env.VITE_BASE_API_URL;

  const rawResponse = await axios.get(
    baseUrl + DOCUMENT_URI,
    { params: request },
  );

  const searchDocumentsResponses: SearchDocumentsResponse[] = rawResponse.data?.documents || [];

  return searchDocumentsResponses.map(
    (searchDocumentsResponse: SearchDocumentsResponse) => convertResponseToDomain(searchDocumentsResponse),
  );
};

const searchDocumentsMock = (request: SearchDocumentsRequest): Document[] => {
  const rawData = {
    documents: [
      {
        documentId: 1,
        authorId: 1,
        status: "ON_DISPLAY",
        latestVersionId: 4,
        title: "첫 번째 게시글",
        content: "v3",
        createdAt: "2023-03-26T15:00:00",
        updatedAt: "2023-03-27T17:00:00"
      },
      {
        documentId: 2,
        authorId: 1,
        status: "ON_DISPLAY",
        latestVersionId: 2,
        title: "두 번째 게시글",
        content: "v1",
        createdAt: "2023-03-27T15:00:00",
        updatedAt: "2023-03-27T15:00:00"
      }
    ]
  };

  const searchDocumentsResponses = rawData.documents as SearchDocumentsResponse[];

  return searchDocumentsResponses.map(
    (searchDocumentsResponse: SearchDocumentsResponse) => convertResponseToDomain(searchDocumentsResponse),
  );
}

const convertResponseToDomain = (searchDocumentsResponse: SearchDocumentsResponse): Document => ({
  id: new DocumentId(searchDocumentsResponse.documentId),
  authorId: new MemberId(searchDocumentsResponse.authorId),
  status: searchDocumentsResponse.status,
  latestVersionId: new DocumentVersionId(searchDocumentsResponse.latestVersionId),
  title: searchDocumentsResponse.title,
  content: searchDocumentsResponse.content,
  createdAt: searchDocumentsResponse.createdAt,
  updatedAt: searchDocumentsResponse.updatedAt,
});

export default import.meta.env.MODE === 'mock' ? searchDocumentsMock : searchDocuments;
