package theSystem;

import queue.ArrayQueue;

public class MonitorMLMSBWT {
	public MonitorMLMSBWT(Fila theList, ProcessingPost pQ){
		
		theMonitorMethod(theList, pQ);
	}
	
	
	public int theMonitorMethod(Fila filas, ProcessingPost pQ){
		
		int sum1, sum2, sum3, sum4, sum5;
		if(filas.filaAmount == 1){
			return 1;
		}
		else if(filas.filaAmount == 3){
			int dummy1, dummy2, result;
			sum1= getFirstSum(filas.getFila(1), pQ.postPerson(1));
			sum2 =  getFirstSum(filas.getFila(2), pQ.postPerson(2));
			sum3 =  getFirstSum(filas.getFila(3), pQ.postPerson(3));
			dummy1 = Math.min(sum1, sum2);
			dummy2 = Math.min(dummy1, sum3);
			result = Math.min(dummy2, dummy1);
			if(result == sum1){
				return 1;
			}
			else if(result == sum2){
				return 2;
			}
			else{
				return 3;
			}
			
		}
		else{
			int dummy1, dummy2, dummy3, dummy4, result;
			sum1 = getFirstSum(filas.getFila(1), pQ.postPerson(1));
			sum2 =  getFirstSum(filas.getFila(2), pQ.postPerson(2));
			sum3 =  getFirstSum(filas.getFila(3), pQ.postPerson(3));
			sum4 =  getFirstSum(filas.getFila(4), pQ.postPerson(4));
			sum5 =  getFirstSum(filas.getFila(5), pQ.postPerson(5));
			
			dummy1 = Math.min(sum1, sum2);
			dummy2 = Math.min(sum3, sum4);
			dummy3 = Math.min(dummy1, dummy2);
			result = Math.min(dummy3, sum5);
			if(result == sum1){
				return 1;
			}
			else if(result == sum2){
				return 2;
			}
			else if(result == sum3){
				return 3;
			}
			else if(result == sum4){
				return 4;
			}
			else{
				return 5;
			}		
		}
	
	}
	
	public int getFirstSum(ArrayQueue<Persona> arrayQ, Persona persona){
		int sum = 0;
		ArrayQueue<Persona> dummy = new ArrayQueue();
		
		for(int i = 0; i < arrayQ.size(); i++){
			sum+=arrayQ.first().getRemainingTime();
			dummy.enqueue(arrayQ.dequeue());
		}
		for(int i = 0; i < dummy.size(); i++){
			arrayQ.enqueue(dummy.dequeue());
		}
		if(persona != null)
			sum+=persona.getRemainingTime();
		return sum;
	}
	
}