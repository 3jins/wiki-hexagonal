import React, { SelectHTMLAttributes } from 'react';
import { StyledSelectBox, StyledSelectBoxOption } from '@src/react/component/molecule/selectBox/styles';

interface SelectBoxProps extends SelectHTMLAttributes<HTMLSelectElement> {
  options: {
    key: string,
    value: string,
    displayName: string,
    isDefault?: boolean,
  }[];
}

export default (props: SelectBoxProps) => <StyledSelectBox
  onChange={props.onChange}
  defaultValue={props.options.find((option) => option.isDefault)?.value}
>
  {props.options.map((option) =>
    <StyledSelectBoxOption
      key={`${option.key}`}
      value={option.value}
    >
      {option.displayName}
    </StyledSelectBoxOption>
  )}
</StyledSelectBox>;
