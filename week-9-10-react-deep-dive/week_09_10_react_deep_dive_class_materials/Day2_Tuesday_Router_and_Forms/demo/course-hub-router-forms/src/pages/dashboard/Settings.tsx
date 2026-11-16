import { useState } from 'react';

export default function Settings() {
  const [emailNotifications, setEmailNotifications] = useState(true);
  const [frequency, setFrequency] = useState('weekly');
  return (
    <div className="card stack">
      <h2>Settings</h2>
      <label><input type="checkbox" checked={emailNotifications} onChange={(e) => setEmailNotifications(e.target.checked)} /> Email notifications</label>
      <label>Digest frequency{' '}
        <select value={frequency} onChange={(e) => setFrequency(e.target.value)} disabled={!emailNotifications}>
          <option value="daily">Daily</option>
          <option value="weekly">Weekly</option>
          <option value="monthly">Monthly</option>
        </select>
      </label>
    </div>
  );
}
