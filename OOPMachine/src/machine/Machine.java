package machine;

import java.util.Arrays;
import java.util.List;

public class Machine {

    // Initial state of the machine:

    State currentState;
    private int waterSupply = 400;
    private int milkSupply = 540;
    private int beansSupply = 120;
    private int cupsSupply = 9;
    private int money = 550;


    public Machine() {
        currentState = State.MENU;
    }

    public Machine(int waterSupply, int milkSupply, int beansSupply, int cupsSupply, int money) {
        this.waterSupply = waterSupply;
        this.milkSupply = milkSupply;
        this.beansSupply = beansSupply;
        this.cupsSupply = cupsSupply;
        this.money = money;
    }

    //takes an(String) order and executes it according with the state the coffee machine is in.
    //if the order equals exit, method returns true, causing the "machine" to stop.
    public boolean takeOrder(String order) {

        switch (currentState) {
            case MENU:

                if (order.equals("buy")) {
                    currentState = State.BUY;
                    break;
                }
                if (order.equals("fill")) {
                    currentState = State.FILL_WATER;
                    break;
                }
                if (order.equals("take")) {
                    take();
                    break;
                }
                if (order.equals("r")) {
                    getStatus();
                    break;
                }
                if (order.equals("exit")) {
                    return true;
                }
                break;

            case BUY:
                List<String> existingChoices = Arrays.asList("1", "2", "3");
                if (existingChoices.contains(order) ) {
                    buy(order);
                } else if (order.equals("back")) {

                } else {
                    System.out.println("Invalid buy choice!");
                }
                currentState = State.MENU;
                break;

            case FILL_WATER:
                fillWater(order);
                break;
            case FILL_MILK:
                fillMilk(order);
                break;
            case FILL_BEANS:
                fillBeans(order);
                break;
            case FILL_CUPS:
                fillCups(order);
                break;
            default:
                System.out.println("Invalid state!");
                break;
        }
        return false;
    }

    private void getStatus() {
        System.out.println();
        System.out.println("The coffee machine has:\n" +
                waterSupply + " of water\n" +
                milkSupply + " of milk\n" +
                beansSupply + " of coffee beans\n" +
                cupsSupply + " of disposable cups\n" +
                "$" + money + " of money");

    }


    public void printMenu() {
        switch (currentState) {
            case MENU:
                printMainMenu();
                break;
            case BUY:
                System.out.println();
                printBuyChoicesMenu();
                break;
            case FILL_WATER:
                printFillWaterMenu();
                break;
            case FILL_MILK:
                printFillMilkMenu();
                break;
            case FILL_BEANS:
                printFillBeansMenu();
                break;
            case FILL_CUPS:
                printFillCupsMenu();
                break;
            default:
                break;
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.print("Write action (buy, fill, take, remaining, exit): ");
    }

    private void printBuyChoicesMenu() {
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
    }

    private void printFillWaterMenu() {
        System.out.println();
        System.out.print("Write how many ml of water do you want to add: ");
    }

    private void printFillMilkMenu() {
//        System.out.println();
        System.out.print("Write how many ml of milk do you want to add: ");
    }

    private void printFillBeansMenu() {
//        System.out.println();
        System.out.print("Write how many grams of coffee beans do you want to add: ");
    }

    private void printFillCupsMenu() {
//        System.out.println();
        System.out.print("Write how many disposable cups of coffee do you want to add: ");
    }

    private void take() {
        System.out.println();
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void buy(String coffeeChoice) {
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

    private void fillWater(String input) {
        int water = Integer.parseInt(input);
        waterSupply += water;
        currentState = State.FILL_MILK;

        //nextInt() reads a number, but doesnt’t consume line separator so line separator is feed to a future nextLine()
        //resulting in having this EOL as input in the nextLine() scanner usage!!
        //to avoid this we use:
//        sc.nextLine();
    }

    private void fillMilk(String input) {
        int milk = Integer.parseInt(input);
        milkSupply += milk;
        currentState = State.FILL_BEANS;
    }

    private void fillBeans(String input) {
        int beans = Integer.parseInt(input);
        beansSupply += beans;
        currentState = State.FILL_CUPS;
    }

    private void fillCups(String input) {
        int cups = Integer.parseInt(input);
        cupsSupply += cups;
        currentState = State.MENU;
    }
}
