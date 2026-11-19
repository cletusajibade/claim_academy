// Mirrors AuthResponse.java
export interface AuthResponse {
  token: string;
  username: string;
  expiresAt: string;
}

export interface Credentials {
  username: string;
  password: string;
}
