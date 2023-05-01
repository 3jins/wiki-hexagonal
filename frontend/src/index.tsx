import React from 'react';
import App from '@src/react/App';
import { createRoot } from 'react-dom/client';

const rootNode = document.getElementById('root');

createRoot(rootNode).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);
