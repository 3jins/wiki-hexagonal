import Document from '@src/document/domain/Document';
import { faker } from '@faker-js/faker';
import FakeDocument from '@test/document/domain/FakeDocument';
import { describe, expect, it } from 'vitest';

const THUMBNAIL_SIZE = 200;

describe('Document Test', () => {
  describe('getHtmlContent', () => {
    it('should not contain any markup tags', () => {
      const fakeDocument: Document = FakeDocument({
        content: '<div class="mainContent"><h1>제목</h1><p>본문</p><img src="https://github.com/3jins/images/blob/test.png" alt="메롱메롱"/><li><ul><p>항목1</p></ul><ul><p>항목2</p></ul></li></div>',
      });

      const result = fakeDocument.getHtmlContent();

      const regExp = new RegExp('</?.+/?>');
      expect(regExp.test(result)).to.be.false;
    });
  });

  describe('getThumbnailContent', () => {
    it(`should be shorten to ${THUMBNAIL_SIZE} letters`, () => {
      // given
      const fakeContent = `한글이 껴있어도 된다? ${faker.random.words(THUMBNAIL_SIZE)}`;
      const fakeDocument: Document = FakeDocument({
        content: fakeContent,
      });

      // when
      const result = fakeDocument.getThumbnailContent();

      // then
      expect(result).to.have.lengthOf(THUMBNAIL_SIZE + 3);
    });

    it(`should not be with ellipse if the content is already short enough`, () => {
      // given
      const fakeContent = `한글이 껴있어도 된다? ${faker.random.words(THUMBNAIL_SIZE / 10)}`;
      const fakeDocument: Document = FakeDocument({
        content: fakeContent,
      });

      // when
      const result = fakeDocument.getThumbnailContent();

      // then
      expect(result.endsWith('...')).to.be.false;
    });
  });
});
