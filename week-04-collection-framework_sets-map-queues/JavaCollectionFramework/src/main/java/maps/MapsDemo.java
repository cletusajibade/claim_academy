package maps;

import java.util.*;

public class MapsDemo {
    static void main(String[] args) {

        Map<String, String> students = new HashMap<>();
        students.put("A101", "Jules");
        students.put("A102", "Jules");
        students.put("A103", "Reggie");
        students.put("A104", "Starlet");

        IO.println(students);
        IO.println(students.size());

//        IO.println(students.get("A103"));

        students.put("A103", "Ava");
//        IO.println(students);

//        IO.println(students.get("A110"));
//        IO.println(students.getOrDefault("A110", "Clem"));
//        IO.println(students.containsKey("A110"));
//        IO.println(students.containsKey("A102"));
//        IO.println(students.containsValue("A102"));
//        IO.println(students.containsValue("Starlet"));

        Map<Integer, String> names = new HashMap<>();
        names.put(1, "James");
        names.put(0, "Maria");
        names.put(2, "Eddy");

//        IO.println(names.get(0));
//        IO.println(names.get(1));

        Map<String, Integer> scores = new HashMap<>();
        scores.put("A104", 80);
//        System.out.println(scores.containsKey("A104")); // true
//        System.out.println(scores.containsValue(80)); // true
//        System.out.println(scores.getOrDefault("Z999", 0)); // 0
//        System.out.println(scores.size()); // 1
//        System.out.println(scores.remove("A104")); // 80
//        System.out.println(scores.remove("A104")); // null
//        System.out.println(scores.isEmpty());// true

        //int scoreValue = scores.get("A105");
        //int scoreValue = scores.get("A105") == null ? 0 : scores.get("A105");
        int scoreValue = scores.getOrDefault("A105", 0);
//        IO.println(scoreValue);

        Set<String> keys = students.keySet();
//        IO.println("All students keys: " + keys);
        Collection<String> values = students.values();
        ArrayList<String> valuesArrayList = (ArrayList<String>) students.values();
//        IO.println("All values: "+values);

//        for (String key: students.keySet()){
//            IO.println(key);
//            IO.println(students.get(key));
//        }
//        Set<Map.Entry<String, String>> entry = students.entrySet();
//        IO.println(entry);
        IO.println(students.entrySet());

        for (Map.Entry<String, String> entry : students.entrySet()) {
            IO.println(entry.getKey() + "->" + entry.getValue());
        }

        for(Map.Entry<String, String> names2: students.entrySet()){

        }
    }
}
