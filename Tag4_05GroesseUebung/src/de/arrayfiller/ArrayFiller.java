package de.arrayfiller;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ArrayFiller {

	private static final int MAX_ARRAYSIZE = Integer.MAX_VALUE/2;
	private final int [] data = new int[MAX_ARRAYSIZE];
	private final int availableProcessors = Runtime.getRuntime().availableProcessors();
	private int runningThreadCount=-1;
	private ExecutorService service;
	private int currentThreadNumber = -1;
	
	public static void main(String[] args) {
		new ArrayFiller().go();

	}

	private void go() {
		fillArrayWithRandomNumbers();
		findMaxValueInArray();
	}
	
	private void findMaxValueInArray() {
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		List<Future<Integer>> futures = new ArrayList<>();
		
		futures.add(service.submit(new FindMaxValueInSegmentWorker(0, MAX_ARRAYSIZE / 2)));
		futures.add(service.submit(new FindMaxValueInSegmentWorker(MAX_ARRAYSIZE / 2, MAX_ARRAYSIZE)));
		
		service.shutdown();
		
		int max = futures.stream().mapToInt(this::convert).max().getAsInt();
		
	}
	
	private int convert(Future<Integer> future) {
		try {
			return future.get();
		} catch (Exception e) {
			return Integer.MIN_VALUE;
		} 
	}

	private void fillArrayWithRandomNumbers() {
		for(runningThreadCount = 1; runningThreadCount <= availableProcessors + 1; runningThreadCount ++ ) {
			startTimeMeasureDecorator();
		}
		
	}

	private void startTimeMeasureDecorator() {
		final Instant start = Instant.now();
		startMultiThreadService();
		final Instant ende = Instant.now();
		final Duration duration = Duration.between(start, ende);
		System.out.println(String.format("Duration with %s Threads was %s ms.", runningThreadCount, duration.toMillis()));
		
	}

	private void startMultiThreadService() {
		try {
			tryStartMultiThreadService();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void tryStartMultiThreadService() throws InterruptedException {
		service = Executors.newFixedThreadPool(availableProcessors);
		startThreads();
		service.shutdown();
		service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
	}

	private void startThreads() {
		for(currentThreadNumber = 0; currentThreadNumber < runningThreadCount; currentThreadNumber ++) {
			startFillSegmentWorker();
		}
		
	}

	private void startFillSegmentWorker() {
		final int segmentSize = MAX_ARRAYSIZE / runningThreadCount;
		final int start = currentThreadNumber * segmentSize;
		final int end = (currentThreadNumber + 1) * segmentSize;
		service.execute(new FillSegmentWorker(start, end));
		
	}

	private class FillSegmentWorker implements Runnable {
		private final int start, end;
		

		public FillSegmentWorker(final int start, final int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			final Random random = new Random();
			for(int i = start; i < end; i ++) {
				data[i] = random.nextInt();
			}
			
		}
		
		
	}
	
	private class FindMaxValueInSegmentWorker implements Callable<Integer> {

		private final int start, end;
		
		
		
		public FindMaxValueInSegmentWorker(int start, int end) {
			this.start = start;
			this.end = end;
		}



		@Override
		public Integer call() throws Exception {
			int max = data[start];
			for(int i = start; i < end; i ++) {
				if(max < data[i])
					max = data[i];
			}
			return max;
		}
		
	}

}
