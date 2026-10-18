package linkedlist;

import java.util.LinkedList;

public class LinkedListDemo {
    static void main(String[] args) {
        {
            // For a linkedlist the add() method automatically transforms the items to a doubly connected item
            LinkedList<String> names = new LinkedList<>();
            names.add("Ada");
            names.add("John");
            names.add("David");

            IO.println(names);
            IO.println(names.size());

            LinkedList<String> shoppingList = new LinkedList<>();
            shoppingList.add("Bread");
            shoppingList.add("Milk");
            shoppingList.add("Eggs");

            IO.println(shoppingList);

            shoppingList.add(1, "Rice");
            IO.println(shoppingList);
            shoppingList.add(3, "Jam");
            IO.println(shoppingList);

            //      [Bread, Rice, Beans, Eggs] -> size()=4
            //index->  0     1      2      3= size()-1

            //      [Bread, Rice, Beans, Eggs,(null)] -> size()=4
            //index->  0     1      2      3    4=size()
            IO.println(shoppingList.size());
            //IO.println(shoppingList.get(5));
            //shoppingList.add(5, "Beans");
            // insertion can run from 0 to size()
            shoppingList.add(shoppingList.size(), "Beans");
            //shoppingList.add("Beans");
            IO.println(shoppingList);

            //shoppingList.add("Potatoes");
            //shoppingList.add(shoppingList.size(), "Potatoes");
            shoppingList.addLast("Potatoes");
            IO.println(shoppingList);
        }
        {
            LinkedList<String> names = new LinkedList<>();
            IO.println(names);
//            names.add(1,"Julie");
            //names.add(0,"Julie");
            names.add("Julie");
            names.add("Sam");
            names.add("Willie");
            names.add("James");
            IO.println(names);

            String oldName = names.set(2, "Williams");
            IO.println(names);
            IO.println("oldName: "+oldName);

            String removedName = names.remove(1);
            boolean isRemoved = names.remove("Sam");
            names.remove("Sam");
            //names.remove(); //removes the first item

            IO.println(names);

            names.isEmpty(); //returns true or false
            IO.println(names.isEmpty());

            //names.peek(); //retrieves the first item but does not remove it. So the list remains the same
            names.poll(); //retrieves the first item and removes it. So the list shrinks.

            IO.println(names.peek());
            IO.println(names);
            IO.println(names.poll());
            IO.println(names);

        }
    }
}
