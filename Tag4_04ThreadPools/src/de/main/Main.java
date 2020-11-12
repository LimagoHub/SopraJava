package de.main;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception{

		final int availableProcessors = Runtime.getRuntime().availableProcessors();
		
		ExecutorService service = Executors.newFixedThreadPool(availableProcessors);
		
		Future<Integer> ergebnis = service.submit(new MyCallable());
		Future<Integer> ergebnis2 = service.submit(new MyCallable());
		
		service.shutdown();
		System.out.println("Noch nicht....");
		//service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		
		int sinnDesLebens = ergebnis.get();
		int andererWert = ergebnis2.get();
		
		System.out.println("Fertisch!");

	}
	
	static class MyCallable implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			Thread.sleep(1000);
			return 42;
		}
		
	}

}
