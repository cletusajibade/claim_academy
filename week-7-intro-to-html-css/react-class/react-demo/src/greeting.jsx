export default function Greeting({ message, number }) {
  return (
    <div>
      <h1>{message}</h1>
      <p>React is interesting</p>
      <ul>
        <li>{number}</li>
        <li>2</li>
        <li>3</li>
      </ul>
    </div>
  );
}
