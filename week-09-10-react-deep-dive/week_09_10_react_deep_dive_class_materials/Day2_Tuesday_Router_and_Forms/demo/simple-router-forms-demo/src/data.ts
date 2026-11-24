export interface Student {
  id: number;
  name: string;
  track: string;
}

export const INITIAL_STUDENTS: Student[] = [
  { id: 1, name: 'Ada Lovelace', track: 'Java' },
  { id: 2, name: 'Alan Turing', track: 'JavaScript' },
  { id: 3, name: 'Grace Hopper', track: 'C#/.NET' },
];
