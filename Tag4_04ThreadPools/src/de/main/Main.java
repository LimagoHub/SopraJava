package de.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception{

		final int availableProcessors = Runtime.getRuntime().availableProcessors();
		
		ExecutorService service = Executors.newFixedThreadPool(availableProcessors);
		for (int i = 0; i <100; i++) {
			service.execute(new Runnable() {
				
				@Override
				public void run() {
					// SEhr lange laufender Process
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Hallo");
					
				}
			});
		}
		
		service.shutdown();
		System.out.println("Noch nicht....");
		service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		
		System.out.println("Fertisch!");

	}

}
