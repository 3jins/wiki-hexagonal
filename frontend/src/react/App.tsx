import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import DocumentsPage from '@src/react/page/DocumentsPage';
import AppContainer from '@src/react/component/organism/AppContainer';
import { DOCUMENT_URI } from '@src/document/adapter/out/DocumentUri';
import DocumentDetailPage from '@src/react/page/DocumentDetailPage';

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
                path={DOCUMENT_URI}
                element={<DocumentsPage/>}
              />
              <Route
                path={`${DOCUMENT_URI}/:documentId`}
                element={<DocumentDetailPage/>}
              />
            </Routes>
          </BrowserRouter>
        </AppContainer>
      </React.Fragment>
    </QueryClientProvider>
  );
};
