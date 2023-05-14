import styled from 'styled-components';
import { ReactNode } from 'react';
import { Link } from 'react-router-dom';

type ClickableListItemType = {
  linkUrl: string,
  children: ReactNode,
}
export default (props: ClickableListItemType) => (
  <ClickableListItem to={props.linkUrl}>
    <ListItem>
      {props.children}
    </ListItem>
  </ClickableListItem>
);

const ClickableListItem = styled(Link)`
  color: black;
  text-decoration: none;
`

const ListItem = styled.li`
  list-style: none;
  margin: 3rem 0;

  :hover {
    background-color: #eaeaea;
  }
`;
