import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import tsconfigPaths from 'vite-tsconfig-paths';
import { resolve } from 'path';

export default defineConfig({
  resolve: {
    alias: [
      {
        find: '@src',
        replacement: resolve(__dirname, 'src'),
      },
    ],
  },

  plugins: [react(), tsconfigPaths()],

  build: {
    sourcemap: true,
  },
});
