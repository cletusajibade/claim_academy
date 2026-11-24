import { createContext, useContext, useState, type ReactNode } from 'react';

/**
 * PREVIEW ONLY: a fake login so we can practise protected routes today.
 * On Day 4/5 this is replaced by a real JWT from Spring Boot.
 */
interface FakeAuth {
  user: string | null;
  login: (name: string) => void;
  logout: () => void;
}

const FakeAuthContext = createContext<FakeAuth | undefined>(undefined);

export function FakeAuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<string | null>(null);
  return (
    <FakeAuthContext.Provider value={{ user, login: setUser, logout: () => setUser(null) }}>
      {children}
    </FakeAuthContext.Provider>
  );
}

export function useFakeAuth() {
  const ctx = useContext(FakeAuthContext);
  if (!ctx) throw new Error('useFakeAuth must be used inside <FakeAuthProvider>');
  return ctx;
}
