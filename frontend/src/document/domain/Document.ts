import Member from '@src/member/domain/Member';
import DocumentStatus from '@src/document/domain/DocumentStatus';

const THUMBNAIL_SIZE = 200;

export default class Document {
  constructor(
    id: DocumentId,
    author: Member,
    status: DocumentStatus,
    latestVersionId: DocumentVersionId,
    title: string,
    content: string,
    createdAt: Date,
    updatedAt: Date,
  ) {
    this.id = id;
    this.author = author;
    this.status = status;
    this.latestVersionId = latestVersionId;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  id?: DocumentId
  author: Member
  status: DocumentStatus
  latestVersionId: DocumentVersionId
  title: string
  content: string
  createdAt: Date
  updatedAt: Date

  static of(
    id: DocumentId,
    author: Member,
    status: DocumentStatus,
    latestVersionId: DocumentVersionId,
    title: string,
    content: string,
    createdAt: Date,
    updatedAt: Date,
  ): Document {
    return new Document(
      id,
      author,
      status,
      latestVersionId,
      title,
      content,
      createdAt,
      updatedAt,
    );
  }

  public getThumbnailContent(): string {
    const bareContent: string = this.getTextContents(
      (new DOMParser()).parseFromString(
        this.content,
        'text/html',
      ).body as HTMLHtmlElement,
    ).join(' ');

    const croppedContent = bareContent.substring(0, THUMBNAIL_SIZE);

    return croppedContent.length < bareContent.length
      ? `${croppedContent}...`
      : croppedContent;
  }

  private getTextContents(
    node: HTMLHtmlElement,
  ): string[] {
    const textContents: string[] = [];
    if (node.childElementCount == 0) {
      if (node.textContent && node.textContent.length > 0) {
        textContents.push(node.textContent);
      }
      return textContents;
    }

    node.childNodes.forEach((childNode: ChildNode) => {
      this.getTextContents(childNode as HTMLHtmlElement).forEach((textContent) => {
        textContents.push(textContent);
      })
    });
    return textContents;
  }
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
