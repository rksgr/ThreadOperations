public class StaticSynchronizationDemo {
    synchronized static void printTable(int n){
        for(int i=1;i<=3;i++){
            System.out.println(n*i);
            System.out.println(Thread.currentThread().getName());
            try{
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
class Thread1 extends Thread{
    public void run(){
        StaticSynchronizationDemo.printTable(2);
    }
}
class Thread2 extends Thread{
    public void run(){
        StaticSynchronizationDemo.printTable(5);
    }
}
class GFG1{
    static void main() {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
    }
}