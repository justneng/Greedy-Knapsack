import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        int[] price = new int[9];
        int[] weight = new int[9];
        int backWeight = 0;
        Scanner scan = new Scanner(System.in);
        for(int x = 1; x <= 9; x++){
            System.out.print("Product No. " + x + "\nprice : ");
            price[x-1] = scan.nextInt();
            System.out.print("weight : ");
            weight[x-1] = scan.nextInt();
        }

        System.out.print("back weight : ");
        backWeight = scan.nextInt();

        Knapsack knapsack = new Knapsack();
        List<GreedyKnapsack> greedyKnapsacks = knapsack.knapsackAlgorithm(price, weight, backWeight);

        logger.info(greedyKnapsacks.toString());

        System.out.println("Result");
        int sumWeight = 0;
        int sumPrice = 0;
        for(int i = 0; i < greedyKnapsacks.size(); i++){
            sumWeight = sumWeight + greedyKnapsacks.get(i).getWeight();
            sumPrice = sumPrice + greedyKnapsacks.get(i).getPrice();
            System.out.println("No. "+greedyKnapsacks.get(i).getChoice()+"\t"+greedyKnapsacks.get(i).getWeight()+"\t"+greedyKnapsacks.get(i).getPrice()
                                +"\t" + greedyKnapsacks.get(i).getPricePerWeight());

            if(i == 8){
                System.out.println("Sum price" + sumPrice + "\t" + " Sum Weight" + sumWeight);
            }
        }
    }
}
