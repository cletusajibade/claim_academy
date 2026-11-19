export interface Movie {
  id: number;
  title: string;
  year: number;
  genre: string;
}

export const INITIAL_MOVIES: Movie[] = [
  { id: 1, title: 'The Matrix', year: 1999, genre: 'Sci-Fi' },
  { id: 2, title: 'Inside Out', year: 2015, genre: 'Animation' },
  { id: 3, title: 'The Social Network', year: 2010, genre: 'Drama' },
];
