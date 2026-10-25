public class BankAccount {

    // shared resource (bank balance)
    private int balance = 1000;

    // synchronized method for deposit operation
    public synchronized void deposit(int amount){
        balance = balance + amount;
        System.out.println("Deposited: "+ amount + " balance: " + balance);
    }
    // synchronized method for withdrawal operation
    public synchronized void withdraw(int amount){
        if(balance >= amount){
            balance = balance -amount;
            System.out.println("Withdrawn : "+ amount + " balance: " + balance);
        }else{
            System.out.println("Insufficient balance to withdraw: "+ amount);
        }
    }
    public int getBalance(){
        return balance;
    }
}
class Geek{
    static void main() {
        BankAccount ba = new BankAccount();
        //Thread1 to deposit money into account
        Thread t1 = new Thread(()->{
            for(int i =0;i<3;i++){
                ba.deposit(200);
                try{
                    Thread.sleep(50);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        // Thread2 to withdraw money from account
        Thread t2 = new Thread(()->{
            for(int j =0; j<3;j++){
                ba.withdraw(300);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        // start both threads
        t1.start();
        t2.start();
        // wait for threads to finish
        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        //Print final balance
        System.out.println("Final balance: "+ ba.getBalance());
    }
}
