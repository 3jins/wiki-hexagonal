import React, { ButtonHTMLAttributes } from 'react';
import { StyledButton } from '@src/react/component/atom/button/styles';

export default (props: ButtonHTMLAttributes<HTMLButtonElement>) => <StyledButton
  type="button"
  onClick={props.onClick}
>
  {props.children}
</StyledButton>;
