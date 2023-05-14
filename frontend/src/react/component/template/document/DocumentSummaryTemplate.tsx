import React from 'react';
import moment from 'moment/moment';
import parse from 'html-react-parser';
import styled from 'styled-components';
import Document from '@src/document/domain/Document';
import PlainParagraph from '@src/react/component/atom/PlainParagraph';
import RenderedArticle from '@src/react/component/molecule/RenderedArticle';
import ClickableListItem from '@src/react/component/molecule/ClickableListItem';

type DocumentSummaryTemplateProps = {
  document: Document,
}
const DocumentSummaryTemplate = (props: DocumentSummaryTemplateProps) => {
  const { document } = props;

  return (
    <ClickableListItem
      key={`document-${document.id?.value}`}
      linkUrl={`/document/${document.id?.value}`}
    >
      <h2>{document.title}</h2>
      <PlainParagraph>작성자: {document.author.name}</PlainParagraph>
      <PlainParagraph>작성일: {moment(document.createdAt).format('lll')}</PlainParagraph>
      <PlainParagraph>마지막 수정일: {moment(document.updatedAt).format('lll')}</PlainParagraph>
      <RenderedArticle>
        {parse(document.getThumbnailContent())}
      </RenderedArticle>
    </ClickableListItem>
  );
};

export default DocumentSummaryTemplate;
