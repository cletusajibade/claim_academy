// Mirrors TaskResponse.java
export interface Task {
  id: number;
  title: string;
  description: string | null;
  dueDate: string | null;
  completed: boolean;
}

// Mirrors ApiError.java (the error body Spring sends back)
export interface ApiError {
  status: number;
  message: string;
  fieldErrors: Record<string, string>;
}
