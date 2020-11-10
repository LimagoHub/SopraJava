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
	private static final int DEFAULTSIZE = 10;
	private int index ;
	private int [] data;
	public Stapel() {
		this(DEFAULTSIZE);
	}
	
	public Stapel(int groesse) {
		index = 0;
		data = new int[groesse< 1 ? DEFAULTSIZE: groesse];
	}
	
	/**
	 * Fügt einen Teller ein
	 * 
	 * @param value
	 */
	public void push (int value) { // Verhalten im Fehlerfall
		if(isFull())
			return;
		addItem(value);
	}

	private void addItem(int value) {
		data[index++] = value;
	}
	
	public int pop() {
		if(isEmpty())
			return 0;
		return data[--index];
	}
	
	
	public boolean isEmpty() {
		return index <= 0;
	}

	public boolean isFull() {
		
		return index >= data.length;
	}

}
