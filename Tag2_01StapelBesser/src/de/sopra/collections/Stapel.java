package de.sopra.collections;

/**
 * 
 * Eine Tolle Klasse
 * 
 * 
 * @author JoachimWagner
 *
 */
public class Stapel<T> {
	
	/**
	 * Ein toller Konstrukur
	 */
	private static final int DEFAULTSIZE = 10;
	private int index ;
	private T [] data;
	public Stapel() {
		this(DEFAULTSIZE);
	}
	
	public Stapel(int groesse) {
		index = 0;
		data = (T[]) new Object[groesse< 1 ? DEFAULTSIZE: groesse];
	}
	
	/**
	 * Fügt einen Teller ein
	 * 
	 * @param value
	 */
	public void push (T value) { // Verhalten im Fehlerfall
		if(isFull())
			return;
		addItem(value);
	}

	private void addItem(T value) {
		data[index++] = value;
	}
	
	public T pop() {
		if(isEmpty())
			return null;
		return data[--index];
	}
	
	
	public boolean isEmpty() {
		return index <= 0;
	}

	public boolean isFull() {
		
		return index >= data.length;
	}

}
