import SearchDocumentsResponse from '@src/document/application/port/out/response/SearchDocumentsResponse';
import SearchDocumentsRequest from '@src/document/application/port/out/request/SearchDocumentsRequest';

const searchDocumentsMock = async (request: SearchDocumentsRequest): Promise<SearchDocumentsResponse[]> =>
  new Promise<SearchDocumentsResponse[]>(resolve => resolve(
    [
      {
        documentId: 1,
        authorId: 1,
        status: 'ON_DISPLAY',
        latestVersionId: 4,
        title: '첫 번째 게시글',
        content: 'v3',
        createdAt: '2023-03-26T15:00:00',
        updatedAt: '2023-03-27T17:00:00'
      },
      {
        documentId: 2,
        authorId: 1,
        status: 'ON_DISPLAY',
        latestVersionId: 2,
        title: '두 번째 게시글',
        content: 'v1',
        createdAt: '2023-03-27T15:00:00',
        updatedAt: '2023-03-27T15:00:00'
      },
    ],
  ));

export default searchDocumentsMock;
