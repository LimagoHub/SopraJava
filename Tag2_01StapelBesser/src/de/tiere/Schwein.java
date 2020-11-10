package de.tiere;

public class Schwein {
	
	// Instanzvariablen
	private String name;
	private int gewicht;
	
	// Klassenvariable
	private static int counter;
	
	
	
	
	public static int getCounter() {
		return counter;
	}

	// Klassenkonstruktor
	static {
		counter = 0;
	}
	
	// Instanz-Konstruktor
	public Schwein() {
		this( "Nobody");
		
	}
	
	
	// Instanz-Konstruktor
	public Schwein(String name) {
		setName(name);
		gewicht = 10;
		counter ++;
	}
	
	
	// Destruktor
	protected void finalize() throws Throwable {
		System.out.println("Quieeek");
		counter --;
	}
	
	public String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public int getGewicht() {
		return gewicht;
	}

	private void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}

	public void fressen() {
		gewicht ++;
	}

	public String toString() {
		return "Schwein [name=" + name + ", gewicht=" + gewicht + "]";
	}
	
	public static void main(String[] args) {
		System.out.println("Ich auch");
	}

}
