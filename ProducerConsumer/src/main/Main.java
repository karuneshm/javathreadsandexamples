package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	public static final String EOF = "EOF";
	public static void main(String[] args) {
		
		List<String> buffer = new ArrayList<String>();
		
		MyProducer  myProducer = new MyProducer(buffer,"1");
		MyConsumer  myConsumer1 = new MyConsumer(buffer,"2");
		MyConsumer  myConsumer2 = new MyConsumer(buffer,"3");
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
	
	public MyProducer(List<String> buffer, String name) {
		this.buffer = buffer;
		this.name = name;
	}


	@Override
	public void run() {
		Random random = new Random();
		String [] nums = {"1","2","3","4","5"};
		
		for(String num :  nums) {
			try {
				System.out.println(name +" Adding number "+ num);
				synchronized(buffer) {
					buffer.add(num);
				}
				
				Thread.sleep(random.nextInt(1));
			} catch(InterruptedException e) {
				System.out.println("Producer is interrupted");
			}
		}
		System.out.println(name + "Adding EOF and exiting ");
		synchronized(buffer) {
		buffer.add(Main.EOF);
		}
		
	}
	
}

class MyConsumer implements Runnable {
	
	private List<String> buffer;
	
	private String name;
	
	

	public MyConsumer(List<String> buffer, String name) {
		this.buffer = buffer;
		this.name = name;
	}



	@Override
	public void run() {
		while(true) {
			synchronized(buffer) {
			if(buffer.isEmpty()) {
				continue;
			}
			if(buffer.get(0).equals(Main.EOF)) {
				System.out.println("Exiting");
				break;
			} else {
				System.out.println(name +" Removed " + buffer.remove(0));
			}
			}
			
		}
		
	}
	
}


















