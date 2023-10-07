import React, { InputHTMLAttributes } from 'react';
import { StyledFileInput } from '@src/react/component/atom/fileInput/styles';

export default (props: InputHTMLAttributes<HTMLInputElement>) => <StyledFileInput
  id={props.id}
  type="file"
  name="file"
  accept={props.accept}
  onChange={props.onChange}
/>;
