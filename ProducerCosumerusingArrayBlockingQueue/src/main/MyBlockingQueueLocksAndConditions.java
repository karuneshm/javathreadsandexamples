package main;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueueLocksAndConditions<Integer> {
	
	private Queue<Integer> queue;
	private int max =16;
	private ReentrantLock lock = new ReentrantLock(true);
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();
	
	
	
	public MyBlockingQueueLocksAndConditions(int size) {
		queue = new LinkedList<Integer>();
		this.max = size;
	}
	
	public void put(Integer item) throws InterruptedException {
		lock.lock(); 
			try {
				if(queue.size() == max) {
					notFull.await();
				}
				queue.add(item);
				notEmpty.signalAll();
				
			} finally {
				lock.unlock();
			}	
	}
	
	public Integer take() throws InterruptedException {
		lock.lock();
		try {
			while(queue.size() == 0) {
				notEmpty.await();
			}
			Integer item = queue.remove();
			notFull.signalAll();
			return item;
		}finally {
			lock.unlock();
		}
		
		
	}
	
	
	
	

}
