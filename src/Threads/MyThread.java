package Threads;

import GUI.MyFarme;

public class MyThread extends Thread {


	MyFarme myF = new MyFarme(); 
	
	 public void run() {
		 
		myF.setVisible(true);

     }
}
