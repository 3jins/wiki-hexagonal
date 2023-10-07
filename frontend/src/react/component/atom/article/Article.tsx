import React, { HTMLAttributes } from 'react';
import { StyledArticle } from '@src/react/component/atom/article/styles';

export default (props: HTMLAttributes<any>) => <StyledArticle>
  {props.children}
</StyledArticle>;
