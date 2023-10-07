import React, { HTMLAttributes } from 'react';
import { StyledClickableListItem } from '@src/react/component/atom/clickableListItem/styles';

interface ClickableListItemProps extends HTMLAttributes<HTMLElement> {
  to: string;
}

export default (props: ClickableListItemProps) => <StyledClickableListItem
  to={props.to}
>
  {props.children}
</StyledClickableListItem>;
