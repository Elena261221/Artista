import java.util.Random;


public class Artista extends Thread {
	
	public Artista() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		while(true) {
			if(Data.buffer[Data.In] != -1) {
				try {
					sleep(Data.min + new Random().nextInt(Data.max - Data.min));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("L'artista ha completato il ristratto di " + Data.buffer[Data.In] + " in posizione " + Data.In);
				Data.buffer[Data.In] = -1;
				Data.mutexc.up();
			}
			else {
				System.out.println("Aspetto");
			}
			Data.In = (Data.In + 1) % Data.N;
		}
		
	}
}
