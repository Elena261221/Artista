
public class Semaforo {
    private int s = 0; // variabile di stato   
    
    public Semaforo(int n) { // costruttore    	
    	s = n;
    }
    synchronized public void down() { // primitiva P()
        s--;
        if (s<0) {
            try{
            	wait();
            } 
            catch(InterruptedException e) {};
        }        
    }    
    synchronized public void up(){ // primitiva V()
       s++;
       notify();
    }
}