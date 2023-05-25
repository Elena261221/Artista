import java.util.Random;



public class Artista extends Thread {
	
	private int min = 100;
	private int max = 500;
	public Artista() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		while(true) {
			if(Data.buffer[Data.In] != -1) {
				try {
					sleep(min + new Random().nextInt(max - min));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("L'artista ha completato il ristratto di " + Data.buffer[Data.In] + " in posizione " + Data.In);
				Data.buffer[Data.In] = -1;
				Data.mutexArtista.up();
			}
			else {
				try {
					sleep(Data.min + new Random().nextInt(Data.max - Data.min));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Aspetto");
			}
			Data.In = (Data.In + 1) % Data.N;
		}
		
	}
}
