import React, { Dispatch } from 'react';
import MultiTypeSearchSection from '@src/react/component/organism/searchSection/MultiTypeSearchSection';

type DocumentSearchTemplateProps = {
  searchOptions: {
    value: string,
    displayName: string,
    isDefault?: boolean,
  }[],
  setSearchType: Dispatch<string>,
  setSearchText: Dispatch<string>,
  onWriteDocumentSubmitClicked: () => void,
}
const DocumentSearchTemplate = (props: DocumentSearchTemplateProps) => {
  return MultiTypeSearchSection({
    searchOptions: props.searchOptions.map((searchOption) => ({
      key: `document-search-option-${searchOption.value}`,
      value: searchOption.value,
      displayName: searchOption.displayName,
    })),
    setSearchType: props.setSearchType,
    textInputId: 'search-text-input',
    setSearchText: props.setSearchText,
    buttonText: '검색',
    onSubmitClicked: props.onWriteDocumentSubmitClicked,
  });
};

export default DocumentSearchTemplate;
