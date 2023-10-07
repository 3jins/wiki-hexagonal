import React, { HTMLAttributes } from 'react';
import { StyledSection } from '@src/react/component/organism/documentsListSection/styles';

export default (props: HTMLAttributes<HTMLUListElement>) => <StyledSection>
  <ul>
    {props.children}
  </ul>
</StyledSection>;
