//Dimitria Deveaux, Course:COP 3330 CRN 24680, Date: 02-11-2024
//Objective: To have contenders battle using threads and synchronization
//The output to be displayed will be each contender, the amount of coins they collect, if they take a water break and the winner
public class UltimateBattleApp {
    public static void main(String[] args) {
        Water waterBreak = new Water();
        //create an array of objects from the Contender class
        Contender[] coinRunner = new Contender[]{
                new Contender("Donkey Kong", 6, 0, 30, waterBreak),
                new Contender("Bowser", 2, 0, 15, waterBreak),
        };

        //creating an array of Threads for contender objects
        Thread[] coinRunners = new Thread[coinRunner.length];

        //using a loop to set Daemon thread to false
        for (int i = 0; i < coinRunner.length; i++){
            coinRunners[i] = new Thread(coinRunner[i]);
            coinRunners[i].setDaemon(false);
        }

        //starting the threads
        for (Thread battle: coinRunners){
            battle.start();
        }
        System.out.println("The battle has begun!");

        //ends the battle after a contender collect all coins required to win
        for (int i = 0; i < coinRunner.length; i++){
            if (coinRunner[i].getTotalCoinsCollected() >= 200){
                coinRunners[i].interrupt();
            }
        }
    }
}
