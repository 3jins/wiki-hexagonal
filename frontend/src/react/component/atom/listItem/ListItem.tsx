import React, { HTMLAttributes } from 'react';
import { StyledListItem } from '@src/react/component/atom/listItem/styles';

export default (props: HTMLAttributes<HTMLUListElement>) => <StyledListItem>
  {props.children}
</StyledListItem>;
