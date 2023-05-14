import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import DocumentsPage from '@src/react/page/DocumentsPage';
import AppContainer from '@src/react/component/organism/AppContainer';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: false,
    },
  },
});

export default () => {
  return (
    <QueryClientProvider client={queryClient}>
      <React.Fragment>
        <AppContainer>
          <BrowserRouter>
            <Routes>
              <Route
                path="/documents"
                element={<DocumentsPage/>}
              />
            </Routes>
          </BrowserRouter>
        </AppContainer>
      </React.Fragment>
    </QueryClientProvider>
  );
};
