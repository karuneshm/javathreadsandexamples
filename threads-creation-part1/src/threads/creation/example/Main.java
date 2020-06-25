package threads.creation.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws InterruptedException {
 
		Thread thread = new NewThread();
		thread.start();
		
	}
		private static class NewThread extends Thread {
			
			@Override
			public void run() {
				System.out.println(" Thread is running"+ this.getName());
			}
			
		}
		
		public class MultiExecutor {
		    
		    private final List<Runnable> tasks;
		 
		    /*
		     * @param tasks to executed concurrently
		     */
		    public MultiExecutor(List<Runnable> tasks) {
		        this.tasks = tasks;
		    }
		 
		    /**
		     * Executes all the tasks concurrently
		     */
		    public void executeAll() {
		        List<Thread> threads = new ArrayList<>(tasks.size());
		        
		        for (Runnable task : tasks) {
		            Thread thread = new Thread(task);
		            threads.add(thread);
		        }
		        
		        for(Thread thread : threads) {
		            thread.start();
		        }
		    }
		}
		
	
}
