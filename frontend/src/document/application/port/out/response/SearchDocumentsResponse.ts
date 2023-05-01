import DocumentStatus from '@src/document/domain/DocumentStatus';

type SearchDocumentsResponse = {
    documentId: number,
    authorId: number,
    status: DocumentStatus,
    latestVersionId: number,
    title: string,
    content: string,
    createdAt: Date,
    updatedAt: Date,
}
export default SearchDocumentsResponse;
