import React, { Dispatch } from 'react';
import { StyledSearchSection } from '@src/react/component/organism/searchSection/styles';
import Label from '@src/react/component/atom/label/Label';
import TextInput from '@src/react/component/atom/textInput/TextInput';
import Button from '@src/react/component/atom/button/Button';
import SelectBox from '@src/react/component/molecule/selectBox/SelectBox';

interface SearchSectionProps {
  labelText?: string;
  searchOptions: {
    key: string,
    value: string,
    displayName: string,
    isDefault?: boolean,
  }[];
  setSearchType: Dispatch<string>,
  textInputId: string;
  setSearchText: Dispatch<string>,
  buttonText: string;
  onSubmitClicked: () => void,
}

export default (props: SearchSectionProps) => <StyledSearchSection>
  <Label htmlFor={props.textInputId}>
    {props.labelText}
  </Label>

  <SelectBox
    onChange={e => props.setSearchType(e.target.value)}
    options={props.searchOptions}
  />

  <TextInput
    id={props.textInputId}
    onChange={e => props.setSearchText(e.target.value)}
  />

  <Button onClick={() => props.onSubmitClicked()}>
    {props.buttonText}
  </Button>
</StyledSearchSection>;
