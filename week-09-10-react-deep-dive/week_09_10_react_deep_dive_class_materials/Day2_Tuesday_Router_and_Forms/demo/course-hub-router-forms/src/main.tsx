import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import App from './App';
import { CourseProvider } from './context/CourseContext';
import { FakeAuthProvider } from './context/FakeAuthContext';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    {/* BrowserRouter must wrap everything that uses routing hooks */}
    <BrowserRouter>
      <FakeAuthProvider>
        <CourseProvider>
          <App />
        </CourseProvider>
      </FakeAuthProvider>
    </BrowserRouter>
  </React.StrictMode>,
);
