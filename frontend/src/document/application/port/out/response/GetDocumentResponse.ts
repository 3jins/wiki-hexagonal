import DocumentStatus from '@src/document/domain/DocumentStatus';

type GetDocumentResponse = {
    documentId: number,
    author: GetDocumentResponseAuthorType,
    status: DocumentStatus,
    latestVersionId: number,
    title: string,
    content: string,
    createdAt: Date,
    updatedAt: Date,
}

type GetDocumentResponseAuthorType = {
    memberId: number,
    name: string,
}

export default GetDocumentResponse;
