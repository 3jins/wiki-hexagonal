import { faker } from '@faker-js/faker';
import Document, { DocumentId, DocumentVersionId } from '@src/document/domain/Document';
import FakeMember from '@test/member/domain/FakeMember';

export default (params?: {
  content?: string,
}) => Document.of(
  // id
  new DocumentId(Number(faker.random.numeric())),

  // author
  FakeMember(),

  // status
  Math.round(Math.random()),

  // latestVersionId
  new DocumentVersionId(Number(faker.random.numeric())),

  // title
  faker.random.words(),

  // content
  params?.content || faker.random.words(),

  // createdAt
  faker.date.birthdate(),

  // updatedAt
  faker.date.birthdate(),
);
