package de.threading;
import static java.lang.Thread.*;
import static java.lang.Math.*;
public class MyThread extends Object implements Runnable{
	
	private final String text;

	public MyThread(final String text) {
		this.text = text;
	}
	
	
	@Override
	public void run() {
		
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(text);
				sleep((long)(random() * 1000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
