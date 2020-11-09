package de.sopra.collections;

/**
 * 
 * Eine Tolle Klasse
 * 
 * 
 * @author JoachimWagner
 *
 */
public class Stapel {
	
	/**
	 * Ein toller Konstrukur
	 */
	public Stapel() {
		System.out.println("Ctor");
	}
	
	/**
	 * Fügt einen Teller ein
	 * 
	 * @param value
	 */
	public void push (int value) { // Verhalten im Fehlerfall
		System.out.println("push");
	}
	
	public int pop() {
		System.out.println("pop");
		return 0;
	}
	
	
	public boolean isEmpty() {
		System.out.println("isEmpty");
		return true;
	}

	public boolean isFull() {
		System.out.println("isFull");
		return true;
	}

}
