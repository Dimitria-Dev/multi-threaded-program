// Dimitria Deveaux, Course:COP 3330 CRN 24680, Date: 02-11-2024
//Program Objective: A battle between Donkey Kong and Bowser to see who can collect the most coins
public class Contender implements Runnable{
    //attributes
    private String contenderName;
    private int coins;
    private int totalCoinsCollected;
    private int maxRest;
    private static volatile boolean winner = false;
    Water waterBreak = new Water();

    //constructor
    public Contender(String contenderName, int coins, int totalCoinsCollected, int maxRest, Water waterBreak){
        this.contenderName = contenderName;
        this.coins = coins;
        this.totalCoinsCollected = totalCoinsCollected;
        this.maxRest = maxRest;
        this.waterBreak = waterBreak;
    }

    //getters and setters
    public String getContenderName(){
        return contenderName;
    }

    public void setContenderName(String contenderName) {
        this.contenderName = contenderName;
    }
    public int getCoins(){
        return coins;
    }
    public void setCoins(int coins){
        this.coins = coins;
    }
    public int getTotalCoinsCollected(){
        return totalCoinsCollected;
    }
    public void setTotalCoinsCollected(int totalCoinsCollected){
        this.totalCoinsCollected = totalCoinsCollected;
    }
    public int getMaxRest() {
        return maxRest;
    }
    public void setMaxRest(int maxRest) {
        this.maxRest = maxRest;
    }

    //battle method:
    @Override
    public void run(){
        //a loop that checks for a winner
        while(!winner){
            try {
                //to have the contender rest randomly no greater than the maxRest declared
                int rest = (int) (Math.random() * maxRest);
                Thread.sleep(rest);
                System.out.println(contenderName + " rested for " + rest + " seconds");

                totalCoinsCollected += coins; //iterates the current total by the amount contained in the coin variable
                System.out.println(contenderName + " collected " + totalCoinsCollected + " coins");

                //to ensure contenders doesn't take a water break at the same time
                synchronized (waterBreak){
                    waterBreak.drink(contenderName);
                }

                //declares a winner when they reach a certain amount of coins and breaks the loop.
                if (totalCoinsCollected >= 200){
                    System.out.println(contenderName + " wins!");
                    winner = true;
                    break;
                }

            } catch (InterruptedException e) {
                System.out.println(contenderName + " got interrupted");
            }
        }
    }

}
