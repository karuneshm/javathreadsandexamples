package interrupts;

import java.math.BigInteger;

public class JoinExample {

	public static void main(String[] args) {
		System.out.println(calculateResult(BigInteger.valueOf(2),BigInteger.valueOf(2),
				BigInteger.valueOf(2),BigInteger.valueOf(2)));
		
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println(cores);

	}
	
	public static BigInteger calculateResult(BigInteger base1,BigInteger power1, BigInteger base2, BigInteger power2) {
		BigInteger result = BigInteger.ZERO;
		
		CalculatePowerThread thread1 = new CalculatePowerThread(BigInteger.valueOf(5),BigInteger.valueOf(5));
		CalculatePowerThread thread2 = new CalculatePowerThread(BigInteger.valueOf(6),BigInteger.valueOf(6));
		
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result = thread1.getResult().add( thread2.getResult());
		
		return result;
	}
	
	private static class CalculatePowerThread extends Thread {
		
		private BigInteger base;
		private BigInteger power;
		
		private BigInteger result = BigInteger.ZERO;
		
		public CalculatePowerThread(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}
		
		public BigInteger getResult() {
			return result;
		}
		
		public void setResult(BigInteger result) {
			this.result = result;
		}

		@Override
		public void run() {
			System.out.println(this.getName()+ base + "^" +power +" = " +power(base,power));
		}

		private BigInteger power(BigInteger base, BigInteger power) {
			BigInteger resultInner = BigInteger.ONE;
			
			for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
				resultInner = resultInner.multiply(base);

			}
			this.setResult(resultInner);
			return resultInner;

		}
		
	}

}
