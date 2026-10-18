package sets;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class TraversalDemo {
    static void main(String[] args) {
        Set<Integer> quantity = new LinkedHashSet<>();
        quantity.add(4);
        quantity.add(0);
        quantity.add(7);

        IO.println(quantity);

        //[4, 0, 7]
        Iterator<Integer> it = quantity.iterator();

        //hasNext() checks if there are still items in the iterator
        while (it.hasNext()){
            if(it.next() == 0){
                it.remove();
            }
        }

        IO.println(quantity);

    }
}
