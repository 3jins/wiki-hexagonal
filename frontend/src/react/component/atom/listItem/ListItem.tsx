import React, { HTMLAttributes } from 'react';
import { ListItemStyled } from '@src/react/component/atom/listItem/styles';

export default (props: HTMLAttributes<HTMLUListElement>) => <ListItemStyled>
  {props.children}
</ListItemStyled>;
