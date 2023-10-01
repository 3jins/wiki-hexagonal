interface ImportMetaEnv {
  readonly VITE_BASE_API_URL: string,
  readonly MODE: 'mock' | undefined,
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
