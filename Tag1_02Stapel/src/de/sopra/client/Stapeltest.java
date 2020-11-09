package de.sopra.client;

import de.sopra.collections.Stapel;

public class Stapeltest {

	public static void main(String[] args) {
		Stapel myStapel = new Stapel();
		
		
		for(int i = 0 ; i < 10 ; i++) {
			if( ! myStapel.isFull()) {
				myStapel.push(i);
			}
		}
		
		while(! myStapel.isEmpty()) {
			System.out.println(myStapel.pop());
		}

	}

}
