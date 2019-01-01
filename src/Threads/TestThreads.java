package Threads;

public class TestThreads {
	public static void main(String[] a) {
		MyThread t0 = new MyThread();
		MyThread t1 = new MyThread();

		t0.start();
		t1.start();
		System.out.println("done main");
	}
	
}
