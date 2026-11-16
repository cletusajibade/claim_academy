import { ThemeProvider, useTheme } from '../context/ThemeContext';

export default function UseContextDemo() {
  return (
    <ThemeProvider>
      <Page />
    </ThemeProvider>
  );
}

// Page and Sidebar don't need to know about the theme at all — no prop drilling.
function Page() {
  return (
    <div className="card">
      <h2>Context avoids prop drilling</h2>
      <Toolbar />
      <Sidebar />
    </div>
  );
}

function Toolbar() {
  const { theme, toggleTheme } = useTheme();
  return <button onClick={toggleTheme}>Switch to {theme === 'light' ? 'dark' : 'light'} mode</button>;
}

function Sidebar() {
  return (
    <div style={{ marginTop: 12 }}>
      <ThemedPanel />
    </div>
  );
}

function ThemedPanel() {
  const { theme } = useTheme();
  const style = theme === 'dark'
    ? { background: '#1d2433', color: '#f6f7fb' }
    : { background: '#e6eefb', color: '#1d2433' };
  return (
    <div style={{ ...style, padding: 16, borderRadius: 8 }}>
      I am 3 levels deep and I read <code>theme = "{theme}"</code> straight from context.
    </div>
  );
}
