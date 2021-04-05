package interrupts;

import java.math.BigInteger;

public class InterruptExample {

	public static void main(String[] args) {
		
//		Thread thread = new Thread(new BlockingClass());
//		thread.start();
//		//thread.interrupt();
		
		LongComputationTask task = new LongComputationTask(BigInteger.valueOf(200000), BigInteger.valueOf(200000));
		Thread thread = new Thread(task);
		thread.start();
		thread.interrupt();
		
		

	}
	
	private static class BlockingClass implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Exiting the blocking thread");
			}
			
		}
		
	}
	
	private static class LongComputationTask implements Runnable {
		
		private BigInteger base;
		private BigInteger power;
		
		public LongComputationTask(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}

		@Override
		public void run() {
			
			System.out.println(base+"^"+power+ " =" + pow(base,power));

		}

		private BigInteger pow(BigInteger base, BigInteger power) {
			BigInteger result = BigInteger.ONE;
			for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("Long Running Thread is interuppted");
					return BigInteger.ZERO;
				}
				result = result.multiply(base);
			}

			return result;
		}

	}
		

}
