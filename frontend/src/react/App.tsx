import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import DocumentsPage from '@src/react/page/DocumentsPage';

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
        <BrowserRouter>
          <Routes>
            <Route
              path='/documents'
              element={<DocumentsPage/>}
            />
          </Routes>
        </BrowserRouter>
      </React.Fragment>
    </QueryClientProvider>
  );
};
