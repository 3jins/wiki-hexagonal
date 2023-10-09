import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ErrorBoundary } from 'react-error-boundary';
import DocumentsPage from '@src/react/page/DocumentsPage';
import AppContainer from '@src/react/component/organism/AppContainer';
import { DOCUMENT_URI } from '@src/document/adapter/out/DocumentUri';
import DocumentDetailPage from '@src/react/page/DocumentDetailPage';
import WriteDocumentPage from '@src/react/page/WriteDocumentPage';
import AmendDocumentPage from '@src/react/page/AmendDocumentPage';

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
        <ErrorBoundary
          fallbackRender={() => <h1>Uncaught Error Occured</h1>} // TODO: 폴백 처리 화면 필요
        >
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
              <Route
                path={`${DOCUMENT_URI}/new`}
                element={<WriteDocumentPage/>}
              />
              <Route
                path={`${DOCUMENT_URI}/:documentId/amend`}
                element={<AmendDocumentPage/>}
              />
            </Routes>
          </BrowserRouter>
        </AppContainer>
        </ErrorBoundary>
      </React.Fragment>
    </QueryClientProvider>
  );
};
