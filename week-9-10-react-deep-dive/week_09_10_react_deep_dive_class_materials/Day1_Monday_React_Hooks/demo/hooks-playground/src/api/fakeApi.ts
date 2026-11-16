export interface User {
  id: number;
  name: string;
  email: string;
}

const USERS: User[] = [
  { id: 1, name: 'Ada Lovelace', email: 'ada@example.com' },
  { id: 2, name: 'Alan Turing', email: 'alan@example.com' },
  { id: 3, name: 'Grace Hopper', email: 'grace@example.com' },
  { id: 4, name: 'James Gosling', email: 'james@example.com' },
];

/** Simulates a network call: ~800 ms latency, optional failure, abortable. */
export function fetchUsers(query: string, opts: { fail?: boolean; signal?: AbortSignal } = {}): Promise<User[]> {
  return new Promise((resolve, reject) => {
    const timer = setTimeout(() => {
      if (opts.fail) reject(new Error('500 Internal Server Error (simulated)'));
      else resolve(USERS.filter((u) => u.name.toLowerCase().includes(query.toLowerCase())));
    }, 800);
    opts.signal?.addEventListener('abort', () => {
      clearTimeout(timer);
      reject(new DOMException('Aborted', 'AbortError'));
    });
  });
}
