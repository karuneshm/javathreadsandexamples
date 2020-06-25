package main;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		
		BankAccount jointAccount = new BankAccount(1000,"12345");
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				jointAccount.deposit(300);
				jointAccount.withdraw(50);
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				jointAccount.deposit(203.75);
				jointAccount.withdraw(100);
				
			}
			
		});
		
		t1.start();
		t2.start();
		
		//Thread.sleep(1000);
		//System.out.println(jointAccount.getBalance());

	}

}
