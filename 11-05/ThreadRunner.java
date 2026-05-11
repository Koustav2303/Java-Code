class MyTask extends Thread {
    public void run() {
        System.out.println("Task running in: " + Thread.currentThread().getName());
    }
}

public class ThreadRunner {
    public static void main(String[] args) {
        MyTask t1 = new MyTask();
        t1.start();
    }
}