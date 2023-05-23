
public class Data {
	
	public static int N = 4;
	public static int[] buffer = new int[N];
	public static int In = 0;
	public static int Out = 0;
	public static int min = 200;
	public static int max = 1000;
	public static Semaforo mutexc = new Semaforo(1);
	public static Semaforo mutex = new Semaforo(1);

	public Data() {
		// TODO Auto-generated constructor stub
	}

}
