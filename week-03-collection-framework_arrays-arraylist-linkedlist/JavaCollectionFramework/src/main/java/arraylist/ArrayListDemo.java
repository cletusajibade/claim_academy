package arraylist;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListDemo {

    static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        IO.println("Is this blank? - " + names);
        names.add("Chloe"); // index 0
        names.add("Ben"); // index 1
        names.add("Ada");// index 2
        //names.add(26); // this does not work because the names is a String array list.

        IO.println(names);
        IO.println("Size = " + names.size());

        names.add("Reggie"); // Index 3

        IO.println(names);

        IO.println("Size = " + names.size());

        IO.println(Arrays.toString(names.toArray()));

        ArrayList<Integer> ages = new ArrayList<>();
        int anything = 23;
        ages.add(anything); //Autoboxing
        ages.add(45);

        int anythingValue = ages.get(0);
        IO.println(anythingValue);

        names.add(2, "Jules");
        IO.println(names);

        //IO.println(names.get(7));throws exception

        names.set(1, "Eddy");
        IO.println(names);
        //names.set(7,"Eddy"); throws exception
        names.remove(1);
        IO.println(names);

        int lastIndex = names.size() - 1;
        //names.length-1
        IO.println(names.get(names.size() - 1));
        IO.println(names.getLast());
        names.addFirst("Moses");
        names.add("Joshua");
        IO.println(names);
        //names.clear();
        IO.println(names);
        IO.println(names.contains("Michael"));

        names.remove(names.size() - 1);
        names.removeLast();
        IO.println(names);

        ArrayList<String> names2 = new ArrayList<>();
        names2.add("Banana");
        names2.add("Banana");
        names2.add("Banana");
        IO.println(names2);
//        names2.remove("Banana");
        IO.println(names2);

        names2.removeIf(item -> item.equals("Banana"));

//        for (String item : names2) {
//            if (item.equals("Banana")) {
//                names2.remove(item);
//            }
//        }

//        for (int i = 0; i < names2.size() ; i++) {
//            names2.remove(i);
//        }

        IO.println(names2);

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        IO.println(numbers);
        numbers.remove(1);
        IO.println(numbers);
        //numbers.remove(0); //removes value 10
        numbers.remove(Integer.valueOf(10)); //removes object 10.
        IO.println(numbers);

        numbers.remove(Integer.valueOf(30)); //removes object 10.

        IO.println(numbers);

        if (numbers.isEmpty()) {
            IO.println("The list is empty");
        } else {
            IO.println("The list is not empty");
        }

        ArrayList<String> names3 = new ArrayList<>();
        names3.add("Ada");
        names3.add("Ben");
        names3.add("John");
        names3.add("Ben");
        IO.println(names3);

        IO.println(names3.contains("Ben"));
        IO.println(names3.indexOf("Ben"));
        IO.println(names3.lastIndexOf("Ben"));

        names3.add("Jules");
        names3.add(2, "Jules");
        IO.println(names3);

        // Traversing the array list
        for (int i = 0; i < names3.size(); i++) {
            IO.println(i + ": " + names3.get(i));
        }

        for (String name : names3) {
            name = "Naomi";
            IO.println(name);
        }

        IO.println(names3);

        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(80);
        scores.add(90);
        scores.add(105);
        scores.add(88);
        scores.add(98);

        if (scores.isEmpty()) {
            IO.println("Array is empty");
        } else {
            int highest = scores.get(0);
            //object scope
            for (int score : scores) {
                if (score > highest) {
                    // The left change not the right
                    highest = score;
                }

            }
            IO.println("Highest: "+highest);
        }


        int total = 0;
        for (int score : scores) {
            total += score;
        }

        IO.println(scores);

        IO.println((double)total/scores.size());

    }
}
