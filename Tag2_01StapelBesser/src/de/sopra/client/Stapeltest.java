package de.sopra.client;

import de.geometrie.Kreis;
import de.geometrie.Punkt;
import de.sopra.collections.Stapel;
import de.tiere.Schwein;

public class Stapeltest {

	public static void main(String[] args) {
		Stapel<Punkt> myStapel = new Stapel<>(40);
		
		
		for(int i = 0 ; i < 10 ; i++) {
			if( ! myStapel.isFull()) {
				//myStapel.push(new Schwein("Schwein Nr. " + i));
				myStapel.push(new Punkt(i,i));
				myStapel.push(new Kreis(i));
				//myStapel.push(i);
				
			}
		}
		
		while(! myStapel.isEmpty()) {
			
			Punkt p = myStapel.pop();
			p.rechts();
			System.out.println(p);
			
			//System.out.println(myStapel.pop());
		}
		


	}

}
