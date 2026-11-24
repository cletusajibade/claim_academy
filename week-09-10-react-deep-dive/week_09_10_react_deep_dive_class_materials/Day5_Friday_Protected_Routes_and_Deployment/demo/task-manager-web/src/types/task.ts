// Mirrors TaskResponse.java
export interface Task {
  id: number;
  title: string;
  description: string | null;
  dueDate: string | null; // "2026-11-20" — LocalDate serializes as ISO string
  completed: boolean;
  createdAt: string;
}

// Mirrors TaskRequest.java
export interface TaskRequest {
  title: string;
  description?: string;
  dueDate?: string | null;
}

// Mirrors ApiError.java
export interface ApiError {
  status: number;
  message: string;
  fieldErrors: Record<string, string>;
  timestamp: string;
}
