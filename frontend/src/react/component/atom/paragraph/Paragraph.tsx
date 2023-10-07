import React, { HTMLAttributes } from 'react';
import { StyledParagraph } from '@src/react/component/atom/paragraph/styles';

export default (props: HTMLAttributes<HTMLParagraphElement>) => <StyledParagraph>
  {props.children}
</StyledParagraph>;
