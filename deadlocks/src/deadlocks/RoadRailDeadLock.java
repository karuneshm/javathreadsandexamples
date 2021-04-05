package deadlocks;

import java.util.Random;

public class RoadRailDeadLock {
	
	public static void main(String args[]) {
		
		Intersection intersection = new Intersection();
		Thread trainA = new Thread(new TrainA(intersection));
		Thread trainB = new Thread(new TrainB(intersection));
		
		trainA.start();
		trainB.start();
		
		
	}
	

	private static class TrainA implements Runnable {

		private Intersection intersection;
		private Random random = new Random();

		public TrainA(Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			while (true) {
				long sleepingTime = random.nextInt(5);
				try {
					Thread.sleep(sleepingTime);
				} catch (InterruptedException e) {

				}

				intersection.takeRoadA();
			}

		}

	}

	private static class TrainB implements Runnable {

		private Intersection intersection;
		private Random random = new Random();

		public TrainB(Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			while (true) {
				long sleepingTime = random.nextInt(5);
				try {
					Thread.sleep(sleepingTime);
				} catch (InterruptedException e) {

				}

				intersection.takeRoadB();
			}

		}

	}

	private static class Intersection {

		private Object roadA = new Object();
		private Object roadB = new Object();

		public void takeRoadA() {
			synchronized (roadA) {
				System.out.println("Road A is locked by " + Thread.currentThread().getName());
			

			synchronized (roadB) {
				System.out.println("Train passing through road A");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			}

		}

		public void takeRoadB() {
			synchronized (roadB) {
				System.out.println("Road B is locked by " + Thread.currentThread().getName());
			

			synchronized (roadA) {
				System.out.println("Train passing through road B");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			}

		}
	}

}
