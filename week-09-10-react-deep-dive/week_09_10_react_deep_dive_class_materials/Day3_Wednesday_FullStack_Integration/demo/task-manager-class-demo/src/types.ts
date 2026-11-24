export interface Task {
  id: number;
  title: string;
  description: string | null;
  dueDate: string | null;
  completed: boolean;
}

export interface ApiError{
    status: number;
    message: string;
    fieldErrors: Record<string, string>
}
