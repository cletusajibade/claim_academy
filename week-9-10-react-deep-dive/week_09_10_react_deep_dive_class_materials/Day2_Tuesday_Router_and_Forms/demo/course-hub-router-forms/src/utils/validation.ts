import type { Category, Course } from '../data/types';

export type CourseFormValues = {
  code: string;
  title: string;
  category: Category | '';
  credits: string; // inputs always give strings — convert on submit
  startDate: string;
  online: boolean;
  description: string;
};

export type FormErrors<T> = Partial<Record<keyof T, string>>;

/** Pure function: values in → errors out. Easy to unit test. */
export function validateCourse(v: CourseFormValues, existing: Course[]): FormErrors<CourseFormValues> {
  const errors: FormErrors<CourseFormValues> = {};
  if (!/^[A-Z]{2,5}-\d{3}$/.test(v.code.trim())) errors.code = 'Use the format ABC-123 (capital letters, dash, 3 digits).';
  else if (existing.some((c) => c.code === v.code.trim())) errors.code = 'That course code already exists.';
  if (v.title.trim().length < 5) errors.title = 'Title must be at least 5 characters.';
  if (!v.category) errors.category = 'Pick a category.';
  const credits = Number(v.credits);
  if (!Number.isInteger(credits) || credits < 1 || credits > 6) errors.credits = 'Credits must be a whole number from 1 to 6.';
  if (!v.startDate) errors.startDate = 'Start date is required.';
  else if (new Date(v.startDate) < new Date(new Date().toDateString())) errors.startDate = 'Start date cannot be in the past.';
  if (v.description.length > 300) errors.description = 'Keep the description under 300 characters.';
  return errors;
}

export const EMAIL_REGEX = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
