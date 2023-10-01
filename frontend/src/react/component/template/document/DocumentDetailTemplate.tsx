import React from 'react';
import moment from 'moment/moment';
import parse from 'html-react-parser';
import Document from '@src/document/domain/Document';
import PlainParagraph from '@src/react/component/atom/PlainParagraph';
import RenderedArticle from '@src/react/component/molecule/RenderedArticle';
import ClickableListItem from '@src/react/component/molecule/ClickableListItem';
import { DOCUMENT_URI } from '@src/document/adapter/out/DocumentUri';

type DocumentDetailTemplateProps = {
  document: Document,
}
const DocumentDetailTemplate = (props: DocumentDetailTemplateProps) => {
  const { document } = props;

  return (
    <ClickableListItem
      key={`document-${document.id?.value}`}
      to={`${DOCUMENT_URI}/${document.id?.value}`}
    >
      <h2>{document.title}</h2>
      <PlainParagraph>작성자: {document.author.name}</PlainParagraph>
      <PlainParagraph>작성일: {moment(document.createdAt).format('lll')}</PlainParagraph>
      <PlainParagraph>마지막 수정일: {moment(document.updatedAt).format('lll')}</PlainParagraph>
      <RenderedArticle>
        {parse(document.getHtmlContent())}
      </RenderedArticle>
    </ClickableListItem>
  );
};

export default DocumentDetailTemplate;
