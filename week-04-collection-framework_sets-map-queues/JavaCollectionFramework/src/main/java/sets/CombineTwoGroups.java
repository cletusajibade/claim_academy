package sets;

import java.util.LinkedHashSet;
import java.util.Set;

public class CombineTwoGroups {
    static void main(String[] args) {
        Set<String> coding = new LinkedHashSet<>();
        coding.add("Ava");
        coding.add("Ben");
        coding.add("Mia");
        coding.add("Julie");
        IO.println("coding: " + coding);

        Set<String> robotics = new LinkedHashSet<>();
        robotics.add("Ben");
        robotics.add("Julie");
        robotics.add("Noah");
        IO.println("robotics: " + robotics);

        // Union: Combine first and second sets
        Set<String> either = new LinkedHashSet<>(coding);
        either.addAll(robotics);
        //Set<String> either = new LinkedHashSet<>();
        //either.addAll(coding);
        IO.println("Union/All:"+either);

        // Intersection: both
        // You keep only the items in both groups
        Set<String> both = new LinkedHashSet<>(coding);
        IO.println("Both at creation: "+both);
        both.retainAll(robotics);
        IO.println("Both: "+both);

        // Difference:
        // You keep items in the first group and remove items in the second group
        Set<String> onlyCoding = new LinkedHashSet<>(coding);
        onlyCoding.removeAll(robotics);
        IO.println("onlyCoding: "+ onlyCoding);

        Set<String> onlyRobotics = new LinkedHashSet<>(robotics);
        onlyRobotics.removeAll(coding);
        IO.println("onlyRobotics : "+onlyRobotics);


        //This is an alias
        Set<String> coding2 = coding;
        IO.println("coding2: "+coding2);
        coding2.clear();
        IO.println("coding2: "+coding2);
        IO.println("coding: "+coding);


    }
}
