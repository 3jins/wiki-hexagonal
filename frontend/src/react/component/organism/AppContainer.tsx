import styled, { css } from 'styled-components';
import { ReactNode } from 'react';

type AppContainerProps = {
  children: ReactNode;
};

export default (props: AppContainerProps) => {
  const { children } = props;

  return (
    <MainThemeAppContainer>
      {children}
    </MainThemeAppContainer>
  );
};

const AppContainer = styled.main`
  @media screen and (max-width: 500px) {
    margin: 0 2rem;
  }
  margin: 0 3rem;
`;

const mainTheme = css`
  font-size: 16px;
`;

const MainThemeAppContainer = styled(AppContainer)`
  ${mainTheme};
`;
