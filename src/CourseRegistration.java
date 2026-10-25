public class CourseRegistration {
    // shared resource
    private int noOfSeats = 3;

    static void main() throws InterruptedException {
        Course cse = new Course("cse", 1);
        RegisterThread regObj1 = new RegisterThread(cse);
        regObj1.start();
        System.out.println("Why control not going to registerForCourse?");
        RegisterThread regObj2 = new RegisterThread(cse);
        regObj2.start();

        Thread.sleep(2000);
        // running a cancellation thread after 2 seconds
        CancelRegistration cancelObj1 = new CancelRegistration(cse);
        cancelObj1.start();
    }


    public synchronized void registerForCourse(int rollNo) {
        try {
            if (this.noOfSeats - 1 < 0) {
                this.wait(5000);
                if (this.noOfSeats - 1 < 0) {
                    throw new Exception("No more seats available!");
                }
            }
            System.out.println("Booking successful");
            this.noOfSeats -= 1;
            System.out.println("Available seats: " + this.noOfSeats);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void cancelSeats() {
        try {
            this.noOfSeats += 1;
            System.out.println("Cancellation successful!");
            System.out.println("Available seats" + this.noOfSeats);
            this.notify();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
class CancelRegistration extends Thread{
    Course c;
    CancelRegistration(Course c){
        this.c = c;
    }
    public void run(){
        c.cancelSeats();
    }
}
class Course{

    public Course(String cse, int i) {
    }

    public void cancelSeats() {
    }
    public void registerForCourse(int rollNo){

    }
}
class RegisterThread extends Thread{
    private Course c;

    RegisterThread(Course c){
        this.c = c;
    }
    public void run(){
        c.registerForCourse(1);
    }
}