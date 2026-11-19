import { useEffect, useState } from 'react';
import { authApi } from '../api/authApi';
import { useAuth } from '../context/AuthContext';
import { getErrorMessage } from '../utils/errors';

/** A second protected page — proves the token works for any endpoint (GET /api/auth/me). */
export default function AccountPage() {
  const { user } = useAuth();
  const [serverSays, setServerSays] = useState<string>('…');

  useEffect(() => {
    authApi.me()
      .then((me) => setServerSays(me.username))
      .catch((err) => setServerSays(`error: ${getErrorMessage(err)}`));
  }, []);

  return (
    <div className="card">
      <h1>Account</h1>
      <table>
        <tbody>
          <tr><th>Username (from login response)</th><td>{user?.username}</td></tr>
          <tr><th>Username (from GET /api/auth/me)</th><td>{serverSays}</td></tr>
          <tr><th>Token expires</th><td>{user && new Date(user.expiresAt).toLocaleString()}</td></tr>
          <tr><th>Token stored in</th><td>memory (refresh the page → logged out)</td></tr>
        </tbody>
      </table>
    </div>
  );
}
