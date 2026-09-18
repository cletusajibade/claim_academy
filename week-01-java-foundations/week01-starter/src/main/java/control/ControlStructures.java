package control;

public class ControlStructures {
    public static void main(String[] args) {
        int age = 40;
        String state = "IL";
        String city = "Edwardsville";

        if(age > 25 && state=="MO"){
            System.out.println("You are qualified");
        }

//        Boolean vs boolean
//        Double vs double
//        Char vs char
//        Integer vs int

        String numberstring = "36";
        int numberInt = Integer.parseInt(numberstring);

        int sum = numberInt + 4;

        age = 23;
        state = "MO";

        if (age > 25) {
            System.out.println("You are " + age + " years old. You are qualified");
            //Nesting
            if (city.equals("St Louis")) {
                System.out.println("You are " + age + " years old. You are from" + city + ". So, you are qualified");
            }
        } else if (city.equals("St Louis")) {
            System.out.println("You are in " + city + ". You are qualified");
        } else if (state.equals("CA")) {
            System.out.println("You are in " + state + ". You are qualified");
        } else {
            System.out.println("You are not qualified. Try next year.");
        }

        // Looping Statements
        //    1. For Loop
        // Three parts of a For Loop
        // 1. Initialization
        // 2. loop condition
        // 3. increment operator
        int number = 10;
        //i++ => i=i+1
        for (int i = 0; i < number; i++) {
            System.out.println(i);
        }

        System.out.println();
        System.out.println("----- WHILE LOOP -----");;
        // While Loop
        int i =0;
        while(i < number){
            System.out.println(i);
            i++;
        }
    }
}
