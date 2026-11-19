import type { Course } from './types';

export const SEED_COURSES: Course[] = [
  { id: 1, code: 'JAVA-201', title: 'Spring Boot REST APIs', category: 'Backend', credits: 3, startDate: '2026-12-01', online: false, description: 'Controllers, services, JPA repositories and validation.' },
  { id: 2, code: 'WEB-301', title: 'React with TypeScript', category: 'Frontend', credits: 3, startDate: '2026-12-08', online: true, description: 'Components, hooks, routing and forms with strong typing.' },
  { id: 3, code: 'AWS-101', title: 'Cloud Practitioner Prep', category: 'Cloud', credits: 2, startDate: '2026-11-30', online: true, description: 'Core AWS services, pricing and the shared responsibility model.' },
  { id: 4, code: 'SQL-110', title: 'Relational Data Modeling', category: 'Data', credits: 2, startDate: '2027-01-05', online: false, description: 'Normalization, joins and indexing for application developers.' },
];
