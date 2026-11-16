export interface Product {
  id: number;
  name: string;
  category: string;
  price: number;
}

export const PRODUCTS: Product[] = [
  { id: 1, name: 'Mechanical Keyboard', category: 'Accessories', price: 89.99 },
  { id: 2, name: 'Wireless Mouse', category: 'Accessories', price: 24.5 },
  { id: 3, name: '27" 4K Monitor', category: 'Displays', price: 329 },
  { id: 4, name: 'USB-C Hub', category: 'Accessories', price: 39.99 },
  { id: 5, name: 'Noise-Cancelling Headphones', category: 'Audio', price: 199 },
  { id: 6, name: 'Laptop Stand', category: 'Accessories', price: 29.99 },
  { id: 7, name: 'Webcam 1080p', category: 'Video', price: 59.95 },
  { id: 8, name: 'Desk Lamp', category: 'Office', price: 34 },
  { id: 9, name: 'Portable SSD 1TB', category: 'Storage', price: 109.99 },
  { id: 10, name: 'Bluetooth Speaker', category: 'Audio', price: 49.99 },
  { id: 11, name: 'Ergonomic Chair', category: 'Office', price: 249 },
  { id: 12, name: 'Standing Desk', category: 'Office', price: 399 },
  { id: 13, name: 'Microphone', category: 'Audio', price: 129 },
  { id: 14, name: '24" 1080p Monitor', category: 'Displays', price: 149.99 },
  { id: 15, name: 'Ring Light', category: 'Video', price: 27.5 },
  { id: 16, name: 'Cable Organizer', category: 'Office', price: 12.99 },
  { id: 17, name: 'External HDD 4TB', category: 'Storage', price: 94.99 },
];
