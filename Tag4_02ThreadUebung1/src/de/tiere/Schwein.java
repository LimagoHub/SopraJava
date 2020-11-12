package de.tiere;

public class Schwein {
	
	// Instanzvariablen
	private String name;
	private volatile int gewicht;
	
	// Instanz-Konstruktor
	public Schwein() {
		this( "Nobody");
		
	}
	
	
	// Instanz-Konstruktor
	public Schwein(String name) {
		setName(name);
		gewicht = 10;
		
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
	
		new Thread(this::fressenImpl).start();
	}


	private void fressenImpl() {
		try {
			Thread.sleep(2000);
			gewicht ++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return "Schwein [name=" + name + ", gewicht=" + gewicht + "]";
	}
	
	

}
