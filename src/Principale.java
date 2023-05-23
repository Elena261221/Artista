import java.util.ArrayList;
import java.util.Random;


public class Principale {

	public static ArrayList<Persona> Persone = new ArrayList<Persona>();
	public static Artista A = new Artista();

	public static void main (String[] args) {
		int n = Data.min + new Random().nextInt(Data.max - Data.min);
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
