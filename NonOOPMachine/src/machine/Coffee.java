package machine;
import java.util.Scanner;

public class Coffee {
    private int water = 200;
    private int milk = 50;
    private int coffeeBeans = 15;
    private int cost =0;

    public Coffee(int water, int milk, int coffeeBeans, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
    }
    public Coffee(int choice){
        switch (choice) {
            case 1 :
                //espresso
                this.water = 250;
                this.milk = 0;
                this.coffeeBeans = 16;
                this.cost = 4;
                break;
            case 2:
                //latte
                this.water = 350;
                this.milk = 75;
                this.coffeeBeans = 20;
                this.cost = 7;
                break;
            case 3:
                //cappuccino
                this.water = 200;
                this.milk = 100;
                this.coffeeBeans = 12;
                this.cost = 6;
                break;
            default:
                System.out.println("Invalid coffee choice! Only 1,2 and 3 are valid options");
                break;
        }
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public int getCost() {
        return cost;
    }

    public void getIngredients() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Write how many cups of coffee you will need: ");
        int noOfCoffees = sc.nextInt();
//        System.out.println(noOfCoffees);
        System.out.println("For " + noOfCoffees + " cups of coffee you will need:\n" +
                noOfCoffees * water + " ml of water\n" +
                noOfCoffees * milk + " ml of milk\n" +
                noOfCoffees * coffeeBeans + " g of coffee beans");

    }
}