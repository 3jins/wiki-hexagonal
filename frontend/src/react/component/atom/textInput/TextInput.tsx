import React, { InputHTMLAttributes } from 'react';
import { StyledTextInput } from '@src/react/component/atom/textInput/styles';

export default (props: InputHTMLAttributes<HTMLInputElement>) => <StyledTextInput
  id={props.id}
  type="text"
  onChange={props.onChange}
>
  {props.children}
</StyledTextInput>;
