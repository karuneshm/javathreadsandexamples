package main;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	
	private double balance;
	private String accountNumber;
	private Lock lock;
	
	
	
	
	public BankAccount(double balance, String accountNumber) {
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.lock = new ReentrantLock();
	}
	
	//THIS
//	public synchronized void deposit(double amount) {
//		balance +=amount;
//	}
//	
//	public synchronized void withdraw(double amount) {
//		balance -=amount;
//	}
	
	//OR THIS
//	public void deposit(double amount) {
//		synchronized(this) {
//			balance +=amount;
//		}
//	}
//	
//	public void withdraw(double amount) {
//		synchronized(this) {
//			balance -=amount;
//		}
//	}
	
	
//	public void deposit(double amount) {
//		lock.lock();
//		try {
//			balance += amount;
//		} finally {
//			lock.unlock();
//		}
//
//	}
//	
//	public void withdraw(double amount) {
//		lock.lock();
//		try {
//			balance -= amount;
//		} finally {
//			lock.unlock();
//		}
//
//	}
	
	public void deposit(double amount) {
		boolean status = false;
		try {
			if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
				try {
					balance += amount;
					status = true;
				} finally {
					lock.unlock();
				}
			} else {
				System.out.println("Could not get lock");
			}
				
		} catch(InterruptedException e) {
			
		}
		
		System.out.println("Transaction Status -"+status);

	}
	
	public void withdraw(double amount) {
		boolean status = false;
		try {
			if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
				try {
					balance -= amount;
					status = true;
				} finally {
					lock.unlock();
				}
			} else {
				System.out.println("Could not get lock");
			}
				
		} catch(InterruptedException e) {
			
		}
		
		System.out.println("Transaction Status -"+status);

	}


	public double getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void printAccountNumber() {
		System.out.println("Account Number = "+accountNumber);
	}


	
	

}
