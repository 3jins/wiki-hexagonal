import React, { LabelHTMLAttributes } from 'react';
import { LabelStyled } from '@src/react/component/atom/label/styles';

export default (props: LabelHTMLAttributes<HTMLLabelElement>) => <LabelStyled
  htmlFor={props.htmlFor}
>
  {props.children}
</LabelStyled>;
