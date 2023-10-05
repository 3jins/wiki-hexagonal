import React, { InputHTMLAttributes } from 'react';
import { FileInputStyled } from '@src/react/component/atom/fileInput/styles';

export default (props: InputHTMLAttributes<HTMLInputElement>) => <FileInputStyled
  id={props.id}
  type="file"
  name="file"
  accept={props.accept}
  onChange={props.onChange}
/>;
