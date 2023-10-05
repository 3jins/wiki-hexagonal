import React, { HTMLAttributes } from 'react';
import { ParagraphStyled } from '@src/react/component/atom/paragraph/styles';

export default (props: HTMLAttributes<HTMLParagraphElement>) => <ParagraphStyled>
  {props.children}
</ParagraphStyled>;
