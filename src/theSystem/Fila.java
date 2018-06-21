package theSystem;

import queue.ArrayQueue;
import queue.SLLQueue;

public class Fila {
	ArrayQueue<Persona>[] filas;
	int filaAmount;
	@SuppressWarnings("unchecked")
	
	public Fila(int filaAmount) {
		this.filaAmount = filaAmount;
		filas = new ArrayQueue[filaAmount];//deque
		for(int i = 0; i<filaAmount;i++) {
			filas[i] = new ArrayQueue<Persona>();//deque
		}
	}
	
	public ArrayQueue<Persona> getFila(int Fila){
		return filas[Fila-1];
	}

	public boolean allLinesEmpty() {
		for(int i =0; i < filaAmount; i++) {
			if(!filas[i].isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
