// Mirrors TaskResponse.java
export interface Task {
  id: number;
  title: string;
  completed: boolean;
}

// Mirrors AuthResponse.java
export interface AuthResponse {
  token: string;
  username: string;
  expiresAt: string;
}
