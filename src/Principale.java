import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Principale {

	public static ArrayList<Persona> Persone = new ArrayList<Persona>();
	public static Artista A = new Artista();

	public static void main (String[] args) {
		
		A.start();
		for(int i =0; i<Data.N;i++) {
			Data.buffer[i] = -1;
		}		
		while(true) {		
			Persona p = new Persona();
			p.start();
		}
		
	}
}
