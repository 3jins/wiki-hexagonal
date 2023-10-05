import React, { HTMLAttributes } from 'react';
import { ArticleStyled } from '@src/react/component/atom/article/styles';

export default (props: HTMLAttributes<any>) => <ArticleStyled>
  {props.children}
</ArticleStyled>;
