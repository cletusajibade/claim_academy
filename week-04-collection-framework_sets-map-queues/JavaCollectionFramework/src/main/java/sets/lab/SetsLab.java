package sets.lab;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetsLab {
    static void main(String[] args) {
        String[] topics = {
                "SQL",
                "Java",
                "SQL",
                "HTML",
                "Java",
                "Python"
        };
        IO.println(Arrays.toString(topics));

        Set<String> uniqueTopics = new LinkedHashSet<>(Arrays.asList(topics));
        IO.println(uniqueTopics);

        Set<String> topicsSet = new LinkedHashSet<>();
        IO.println(topicsSet);
        int duplicate = 0;

        //topic = [SQL, Java, SQL, HTML, Java, Python]
        //topicsSet=[SQL];
        for (String topic: topics){ //iter 1, topic=SQL
            //add() returns 'true' or 'false'
            if(!topicsSet.add(topic)){
                duplicate++;
            }
        }
        IO.println("Duplicate attempts: "+duplicate);
        IO.println(topicsSet);

    }
}
