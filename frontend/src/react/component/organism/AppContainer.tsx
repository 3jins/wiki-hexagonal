import styled, { css } from 'styled-components';

const AppContainer = styled.main`
  @media screen and (max-width: 500px) {
    margin: 0 2rem;
  }
  margin: 0 3rem;
`;

const mainTheme = css`
  font-size: 16px;
`;

export default styled(AppContainer)`
  ${mainTheme};
`;
