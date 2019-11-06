package machine;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Machine {
    // Initial state of the machine
    private int waterSupply = 400;
    private int milkSupply = 540;
    private int beansSupply = 120;
    private int cupsSupply = 9;
    private int money = 550;

    public Machine() {
    }

    public Machine(int waterSupply, int milkSupply, int beansSupply, int cupsSupply, int money) {
        this.waterSupply = waterSupply;
        this.milkSupply = milkSupply;
        this.beansSupply = beansSupply;
        this.cupsSupply = cupsSupply;
        this.money = money;
    }

        Scanner sc = new Scanner(System.in);

    private void getStatus() {
        System.out.println("The coffee machine has:\n" +
                waterSupply + " of water\n" +
                milkSupply + " of milk\n" +
                beansSupply + " of coffee beans\n" +
                cupsSupply + " of disposable cups\n" +
                "$" + money + " of money");
    }

    public void buy() {
        String coffeeChoice = "";
        List<String> existingChoices = Arrays.asList("1", "2", "3", "back");
        while (true) {
            System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
            coffeeChoice = sc.nextLine();
            if (existingChoices.contains(coffeeChoice)) {
                break;
            }
        }

        if (!coffeeChoice.equals("back")) {
            int choice = Integer.parseInt(coffeeChoice);

            Coffee coffee = new Coffee(choice);
            int remainingWater = waterSupply - coffee.getWater();
            int remainingMilk = milkSupply - coffee.getMilk();
            int remainingBeans = beansSupply - coffee.getCoffeeBeans();
            int remainingCups = cupsSupply - 1;

            if (remainingWater >= 0 && remainingMilk >= 0 &&
                    remainingBeans >= 0 && cupsSupply > 0) {

                waterSupply -= coffee.getWater();
                milkSupply -= coffee.getMilk();
                beansSupply -= coffee.getCoffeeBeans();
                cupsSupply--;
                money += coffee.getCost();
                System.out.println("I have enough resources, making you a coffee!");
            } else {
                if (remainingWater < 0) {
                    System.out.println("Sorry, not enough water!");
                }

                if (remainingMilk < 0) {
                    System.out.println("Sorry, not enough milk!");
                }

                if (remainingBeans < 0) {
                    System.out.println("Sorry, not enough coffee beans!");
                }

                if (remainingCups < 0) {
                    System.out.println("Sorry, not enough cups!");
                }
            }

        }
    }

    public void fill() {
        System.out.print("Write how many ml of water do you want to add: ");
        int water = sc.nextInt();
        waterSupply += water;
        System.out.print("Write how many ml of milk do you want to add: ");
        int milk = sc.nextInt();
        milkSupply += milk;
        System.out.print("Write how many grams of coffee beans do you want to add: ");
        int beans = sc.nextInt();
        beansSupply += beans;
        System.out.print("Write how many disposable cups of coffee do you want to add: ");
        int cups = sc.nextInt();
        cupsSupply += cups;

        //nextInt() reads a number, but doesnt’t consume line separator so line separator is feed to a future nextLine()
        //resulting in having this EOL as input in the nextLine() scanner usage!!
        //to avoid this we use:
        sc.nextLine();
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public void run() {

        boolean exit = false;
        while (!exit) {
            System.out.print("Write action (buy, fill, take, remaining, exit): ");
            String response = sc.nextLine();
            switch (response) {
                case "buy":
                    System.out.println();
                    buy();
                    System.out.println();
                    break;
                case "fill":
                    System.out.println();
                    fill();
                    System.out.println();
                    break;
                case "take":
                    System.out.println();
                    take();
                    System.out.println();
                    break;
//                case "remaining":
                case "r":
                    System.out.println();
                    getStatus();
                    System.out.println();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }


    }
}
