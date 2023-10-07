import React, { LabelHTMLAttributes } from 'react';
import { StyledLabel } from '@src/react/component/atom/label/styles';

export default (props: LabelHTMLAttributes<HTMLLabelElement>) => <StyledLabel
  htmlFor={props.htmlFor}
>
  {props.children}
</StyledLabel>;
