package main;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueueWaitAndNotify<Integer> {
	
	private Queue<Integer> queue;
	private int max =16;
	private Object notEmpty = new Object();
	private Object notFull = new Object();
	
	
	
	public MyBlockingQueueWaitAndNotify(int size) {
		queue = new LinkedList<Integer>();
		this.max = size;
	}
	
	public synchronized void put(Integer item) throws InterruptedException {

		while (queue.size() == max) {
			notFull.wait();
		}
		queue.add(item);
		notEmpty.notifyAll();

	}
	
	public synchronized Integer take() throws InterruptedException {

		while (queue.size() == 0) {
			notEmpty.wait();
		}
		Integer item = queue.remove();
		notFull.notifyAll();
		return item;

	}
	
	
	
	

}
