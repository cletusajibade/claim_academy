import { useEffect, useState } from 'react';

// Same shape as TaskResponse.java
interface Task {
  id: number;
  title: string;
  completed: boolean;
}

export default function App() {
  const [tasks, setTasks] = useState<Task[]>([]);

  // After the first render: ask Spring Boot for the tasks
  useEffect(() => {
    fetch('http://localhost:8080/api/tasks')
      .then((res) => res.json())
      .then((data: Task[]) => setTasks(data));
  }, []);

  return (
    <main>
      <h1>Tasks</h1>
      {tasks.map((t) => (
        <div className="card" key={t.id}>{t.title}</div>
      ))}
    </main>
  );
}
