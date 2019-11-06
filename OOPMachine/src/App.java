import machine.Machine;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Machine machine = new Machine();

        Scanner s = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            machine.printMenu();
            String order = s.nextLine();
            exit = machine.takeOrder(order);
        }
    }
}
