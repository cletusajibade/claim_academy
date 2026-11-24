/// <reference types="vite/client" />

// Give TypeScript the type of our custom env variables
interface ImportMetaEnv {
  readonly VITE_API_URL: string;
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
