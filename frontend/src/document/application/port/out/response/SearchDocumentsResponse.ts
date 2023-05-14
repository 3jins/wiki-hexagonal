import DocumentStatus from '@src/document/domain/DocumentStatus';

type SearchDocumentsResponse = {
    documentId: number,
    author: SearchDocumentsResponseAuthorType,
    status: DocumentStatus,
    latestVersionId: number,
    title: string,
    content: string,
    createdAt: Date,
    updatedAt: Date,
}

type SearchDocumentsResponseAuthorType = {
    memberId: number,
    name: string,
}

export default SearchDocumentsResponse;
