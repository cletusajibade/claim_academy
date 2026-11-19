import Header from './components/Header';
import Main from './components/Main';
import TaskProvider from './context/TaskProvider';

// 4. No props! Everything inside <TaskProvider> can call useTasks()
export default function App() {
  return (
    <TaskProvider>
      <Header />
      <Main />
    </TaskProvider>
  );
}
