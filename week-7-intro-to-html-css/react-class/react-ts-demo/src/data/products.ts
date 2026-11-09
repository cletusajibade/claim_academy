import type { Product } from '../types/product';

import bagImage from '../assets/bag.jpg';
import shoeImage from '../assets/shoe.jpg';
import watchImage from '../assets/wristwatch.png';

export const products: Product[] = [
  { id: 1, name: 'Bag', price: 23.5, image: bagImage },
  { id: 2, name: 'Shoe', price: 44.5, image: shoeImage },
  { id: 3, name: 'Wristwatch', price: 127.99, image: watchImage },
];
