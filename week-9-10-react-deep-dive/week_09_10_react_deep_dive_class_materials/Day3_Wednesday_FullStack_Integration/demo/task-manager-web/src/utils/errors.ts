import axios from 'axios';
import type { ApiError } from '../types/task';

/** Turn any thrown value into a message a user can read. */
export function getErrorMessage(err: unknown): string {
  if (axios.isAxiosError<ApiError>(err)) {
    if (!err.response) {
      // No response at all: server down, wrong URL, or blocked by CORS
      return 'Cannot reach the server. Is the Spring Boot API running on port 8080?';
    }
    return err.response.data?.message ?? `Request failed with status ${err.response.status}`;
  }
  return err instanceof Error ? err.message : 'Something went wrong';
}

/** Field errors from a 400 Validation failed response (or {}). */
export function getFieldErrors(err: unknown): Record<string, string> {
  if (axios.isAxiosError<ApiError>(err) && err.response?.status === 400) {
    return err.response.data?.fieldErrors ?? {};
  }
  return {};
}
