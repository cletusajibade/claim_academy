interface GreetingProps {
  message: string;
  number?: number;
}

export default function Greeting({ message, number = 1 }: GreetingProps) {
  return (
    <div>
      <h3>{message}</h3>
      <p>React is interesting </p>
      <ul>
        <li>{number}</li>
        <li>{number * 2}</li>
        <li>{number * 3}</li>
      </ul>
    </div>
  );
}
