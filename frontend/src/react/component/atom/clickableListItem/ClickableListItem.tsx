import React, { HTMLAttributes } from 'react';
import { ClickableListItemStyled } from '@src/react/component/atom/clickableListItem/styles';

interface ClickableListItemProps extends HTMLAttributes<HTMLElement> {
  key: string;
  to: string;
}

export default (props: ClickableListItemProps) => <ClickableListItemStyled
  key={props.key}
  to={props.to}
>
  {props.children}
</ClickableListItemStyled>;
