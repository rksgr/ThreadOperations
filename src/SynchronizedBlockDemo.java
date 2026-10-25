public class SynchronizedBlockDemo {
    private int sharedVariable = 0;

    public synchronized void inc(){
        sharedVariable++;
    }

    public synchronized int get(){
        return sharedVariable;
    }
}
class Geeks{
    static void main() {
        SynchronizedBlockDemo sbd = new SynchronizedBlockDemo();
        Thread t1 = new Thread(()->{
            for(int i=0;i<1000;i++){
                sbd.inc();
            }
        });
        Thread t2 = new Thread(() ->{
            for(int j=0;j<1000;j++){
                sbd.inc();
            }
        });
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Counter: " + sbd.get());
    }
}
