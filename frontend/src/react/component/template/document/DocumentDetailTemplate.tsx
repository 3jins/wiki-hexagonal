import React from 'react';
import moment from 'moment/moment';
import parse from 'html-react-parser';
import Document from '@src/document/domain/Document';
import PlainParagraph from '@src/react/component/atom/PlainParagraph';
import RenderedArticle from '@src/react/component/molecule/RenderedArticle';

type DocumentDetailTemplateProps = {
  document: Document,
}
const DocumentDetailTemplate = (props: DocumentDetailTemplateProps) => {
  const { document } = props;

  return (
    <React.Fragment>
      <h2>{document.title}</h2>
      <PlainParagraph>작성자: {document.author.name}</PlainParagraph>
      <PlainParagraph>작성일: {moment(document.createdAt).format('lll')}</PlainParagraph>
      <PlainParagraph>마지막 수정일: {moment(document.updatedAt).format('lll')}</PlainParagraph>
      <RenderedArticle>
        {parse(document.content)}
      </RenderedArticle>
    </React.Fragment>
  );
};

export default DocumentDetailTemplate;
