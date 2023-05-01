import { MemberId } from '@src/member/domain/Member';
import DocumentStatus from '@src/document/domain/DocumentStatus';

export default class Document {
  id?: DocumentId
  authorId: MemberId
  status: DocumentStatus
  latestVersionId: DocumentVersionId
  title: string
  content: string
  createdAt: Date
  updatedAt: Date
}

export class DocumentId {
  constructor(documentId: number) {
    this.value = documentId;
  }

  value: number
}

export class DocumentVersionId {
  constructor(documentVersionId: number) {
    this.value = documentVersionId;
  }

  value: number
}
