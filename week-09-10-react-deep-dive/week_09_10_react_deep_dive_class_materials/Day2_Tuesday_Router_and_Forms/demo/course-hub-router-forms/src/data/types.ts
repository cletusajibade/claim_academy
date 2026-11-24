export type Category = 'Backend' | 'Frontend' | 'Cloud' | 'Data';

export interface Course {
  id: number;
  code: string;
  title: string;
  category: Category;
  credits: number;
  startDate: string; // yyyy-mm-dd
  online: boolean;
  description: string;
}

export interface Enrollment {
  id: number;
  courseId: number;
  fullName: string;
  email: string;
  experience: 'beginner' | 'intermediate' | 'advanced';
}
