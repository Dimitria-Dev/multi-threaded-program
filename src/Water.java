// Dimitria Deveaux, Course:COP 3330 CRN 24680, Date: 02-11-2024
//Purpose: To synchronize the contenders taking a break to drink water.
public class Water {
    //declare synchronized method
    public synchronized void drink(String contenderName){
        System.out.println(contenderName + " has stopped for a drink of water");
        try {
            Thread.sleep(1000); //contenders that stop to drink water sleeps longer than the other contender
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(contenderName + " water break ended!");

    }
}
