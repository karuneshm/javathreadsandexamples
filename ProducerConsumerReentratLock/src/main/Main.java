package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static final String EOF = "EOF";
	public static void main(String[] args) {
		
		List<String> buffer = new ArrayList<String>();
		
		ReentrantLock bufferLock = new ReentrantLock();
		
		MyProducer  myProducer = new MyProducer(buffer,"1",bufferLock);
		MyConsumer  myConsumer1 = new MyConsumer(buffer,"2",bufferLock);
		MyConsumer  myConsumer2 = new MyConsumer(buffer,"3",bufferLock);
		Thread t1 = new Thread(myProducer);
		Thread t2 = new Thread(myConsumer1);
		Thread t3 = new Thread(myConsumer2);
		t1.start();
		t2.start();
		t3.start();
		
		
		
		
	}

}


class MyProducer implements Runnable {
	private List<String> buffer;
	private String name;
	private ReentrantLock bufferLock;
	
	public MyProducer(List<String> buffer, String name, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.name = name;
		this.bufferLock = bufferLock;
	}

	@Override
	public void run() {
		Random random = new Random();
		String [] nums = {"1","2","3","4","5"};
		
		for(String num :  nums) {
			try {
				System.out.println(name +" Adding number "+ num);
				bufferLock.lock();
				try {
				buffer.add(num);
				} finally {
					bufferLock.unlock();
				}
				
				Thread.sleep(random.nextInt(1000));
			} catch(InterruptedException e) {
				System.out.println("Producer is interrupted");
			}
		}
		System.out.println(name + "Adding EOF and exiting ");
		bufferLock.lock();
		try {
			buffer.add(Main.EOF);
		} finally {
			bufferLock.unlock();
		}
		
		
		
		
	}
	
}

class MyConsumer implements Runnable {
	
	private List<String> buffer;
	
	private String name;
	
	private ReentrantLock bufferLock;
	
	public MyConsumer(List<String> buffer, String name, ReentrantLock bufferLock) {
		this.buffer = buffer;
		this.name = name;
		this.bufferLock = bufferLock;
	}



	@Override
	public void run() {
		int counter = 0;
		while(true) {
			if(bufferLock.tryLock()) {
				try {
					if(buffer.isEmpty()) {
						continue;
					}
					
					System.out.println(name + "The Counter" + counter);
					counter = 0;
					
					if(buffer.get(0).equals(Main.EOF)) {
						System.out.println("Exiting");
						break;
					} else {
						System.out.println(name +" Removed " + buffer.remove(0));
					}
					
					
				}finally {
					bufferLock.unlock();
				}
			} else {
				counter++;
			}
			
		}
		
	}
	
}


















