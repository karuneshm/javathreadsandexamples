package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	
	public static void main(String args[]) {
		
	List<String> buffer = new ArrayList<String>();
	Lock lock = new ReentrantLock();
	Condition added = lock.newCondition();
	Condition removed = lock.newCondition();
	
	MyProducer producer = new MyProducer(buffer, lock, added, removed);
	MyConsumer consumer = new MyConsumer(buffer, lock, added, removed);
	
	
	Thread myProducerThread = new Thread(producer);
	myProducerThread.start();
	Thread myConsumerThread = new Thread(consumer);
	myConsumerThread.start();
	Thread myConsumerThread1 = new Thread(consumer);
	myConsumerThread1.start();
	Thread myConsumerThread2 = new Thread(consumer);
	myConsumerThread2.start();
	}

}


class MyProducer implements Runnable {
	
	private List<String> buffer;
	Lock lock = new ReentrantLock();
	Condition added = lock.newCondition();
	Condition removed = lock.newCondition();
	

	public MyProducer(List<String> buffer, Lock lock, Condition added, Condition removed) {
		this.buffer = buffer;
		this.lock = lock;
		this.added = added;
		this.removed = removed;
	}

	String [] nums = {"1","2","3","4","5"};

	@Override
	public void run() {
		
	for(int i = 0; i <nums.length ;i++) {
		lock.lock();
		try {
			if(buffer.size() == nums.length) {
				removed.await();
			}
			
			buffer.add(nums[i]);
			System.out.println("Added elemets is +"+nums[i]);
			added.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
		
	}
	
}


class MyConsumer implements Runnable {
	
	private List<String> buffer;
	Lock lock = new ReentrantLock();
	Condition added = lock.newCondition();
	Condition removed = lock.newCondition();

	public MyConsumer(List<String> buffer, Lock lock, Condition added, Condition removed) {
		this.buffer = buffer;
		this.lock = lock;
		this.added = added;
		this.removed = removed;
	}



	@Override
	public void run() {
		lock.lock();
		try {
			if(buffer.size() == 0) {
				added.await();
			}
			String data = buffer.get(0);
		
			System.out.println("Data Removed is "+ data);
			buffer.remove(0);
			removed.signal();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		} finally {
			lock.unlock();
		}
		
	}
	
}














