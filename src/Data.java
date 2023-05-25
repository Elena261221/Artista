
public class Data {
	
	public static int N = 4;
	public static int[] buffer = new int[N];
	public static volatile int In = 0;
	public static volatile int Out = 0;
	public static int min = 5000;
	public static int max = 10000;
	public static Semaforo mutexArtista = new Semaforo(1);
	public static Semaforo mutexSedie = new Semaforo(1);

	public Data() {
		// TODO Auto-generated constructor stub
	}

}
