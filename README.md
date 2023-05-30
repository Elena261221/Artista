# Artista da strada

Per la realizzazione dell'esercizio sono stati utilizzati due semafori entrambi imposta a 1: 
- **mutexSedie** : per la gestione del buffer
- **mutexArtista** : per la gestione dei ritratti

 ---
 
## Classe Persona

La classe Persona eredita da Thread e rappresenta il singolo cliente.
Attraverso il *metodo run* gestisce l'attesa dei clienti e l'ottenimento del ritratto. Il cliente controlla che sia libera una sedia e si siede, in caso contrario aspetta un tempo random compreso tra **min** e **max** impostati inizialmente come 5000 e 10000 millisecondi. Al termine dell'attesa ricontrolla che una sedia sia libera, se la trova si siede altrimenti si allontana.
Se trova un posto libero prende il **mutexSedie** per prenotare univocamente il posto mettendo il suo id nel buffer. Liberato il mutex e prenota il **mutexArtista**.

Il frammento di codice sotto riportato mostra l'implementazione del *metodo run()* nella classe persona.

```java
public void run() {
		if(Data.buffer[(Data.Out+1) % Data.N] != -1) {			
			try {
				sleep(Data.min + new Random().nextInt(Data.max - Data.min));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		if(Data.buffer[Data.Out] == -1) {			
			Data.mutexSedie.down();						
			Data.buffer[Data.Out] = id;
			System.out.println(id + " si è seduto in posizione " + Data.Out);	
			Data.Out = (Data.Out+1) % Data.N;
			Data.mutexSedie.up();
			Data.mutexArtista.down();
		}else {
			try {
				sleep(Data.min + new Random().nextInt(Data.max - Data.min));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Addio " + id);
		}
	}
```

 ---
 
## Classe Artista

La classe Artista eredita da Thread e rappresenta l'artista.
Attraverso il *metodo run* gestiste i ritratti dei clienti. Quando si siede un cliente l'artista inizia il ritratto che dura un tempo random compreso tra 100 e 500 millisecondi. Al termine del ritratto libera la cella del buffer occupata dal cliente e rilascia il **mutexArtista**.
Il *metodo run* contiene un loop che esegue queste azioni.

Il frammento di codice sotto riportato mostra l'implementazione del *metodo run()* nella classe artista.

```java
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
```
 ---
 
## Classe Data

La classe Data contiene gli attributi comuni alle altre classi.
Il **buffer** rappresenta le quattro sedie che vengono occupate dai clienti, se la cella è impostata a -1 la sedia è libera altrimenti rappresenta l'id del cliente. 
**In** e **Out** impostate come volatile in modo che vengano prese dalla memoria RAM non dalla cache per permettere l'utilizzo in multithreading. Esse rappresentano rispettivamente l'indice dell'artista e dei clienti nel buffer.
**min** e **max** sono costanti e rappresentano i tempi minimi e massimi di attesa dei clienti.

 ---

