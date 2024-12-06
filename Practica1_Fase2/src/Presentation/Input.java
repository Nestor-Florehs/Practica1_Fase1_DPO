package Presentation;

import java.util.Scanner;

import static Presentation.Controller.*;

public class Input {
    static private Scanner sc;

    public Input() {
        sc = new Scanner(System.in);
    }

    public int askInteger(String prompt){
        int integer;
        try{
            System.out.print(prompt);
            integer = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e) {
            Output.printPhrase(INVALID_OPTION_MESSAGE);
            return askInteger(prompt);
        }
        return integer;
    }

    public String askString(String prompt){
        String string;
        System.out.print(prompt);
        string = sc.nextLine();
        return string;
    }

    public void pressAnyKeyToContinue(){
        Scanner scanner = new Scanner(System.in);
        Output.printPhrase("\n<Press any key to continue...>");
        scanner.nextLine(); // Espera a que el usuario presione Enter
    }

    public String askStrategy(int index){
        Output.printPhrase("Game strategy for character #" + index+ "?");
        Output.printPhrase(STRATEGY_BALANCED_MESSAGE);
        int option = askInteger(CHOOSE_OPTION_MESSAGE);
        if (option == 1) {
            return "balanced";
        } else {
            Output.printPhrase(INVALID_OPTION_MESSAGE);
            return askStrategy(index);
        }
    }
}
