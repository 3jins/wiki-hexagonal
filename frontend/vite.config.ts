import { defineConfig } from 'vitest/config';
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
      {
        find: '@test',
        replacement: resolve(__dirname, 'test'),
      },
    ],
  },

  plugins: [react(), tsconfigPaths()],

  build: {
    sourcemap: true,
  },

  test: {
    include: ['test/**/*Test.ts'],
    exclude: ['src/**/*.ts'],
    environment: 'jsdom',
  },
});
