package de.arrayfiller;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class ArrayFiller {

	private static final int MAX_ARRAYSIZE = Integer.MAX_VALUE/2;
	private final int [] data = new int[MAX_ARRAYSIZE];
	
	public static void main(String[] args) {
		new ArrayFiller().go();

	}

	private void go() {
		Random random = new Random();
		
		Instant start = Instant.now();
		for(int i = 0 ;i < MAX_ARRAYSIZE; i ++) {
			data[i] = random.nextInt();
		}
		Instant ende = Instant.now();
		
		Duration duration = Duration.between(start, ende);
		
		System.out.println("Duration = " + duration.toMillis());
		
	}

}
