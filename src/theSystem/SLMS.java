package theSystem;

import java.util.ArrayList;

public class SLMS {
	public static double averageWaitingTotalT = 0;
	public static double amountOfClients;
	public static int lastT;
	public int clerks;
	
	
	public SLMS(ArrayList<Persona> inputList, int i) {
		// TODO Auto-generated constructor stub
		processSLMS(inputList, i);
		this.clerks = i;
	}


	/**
	 * Processes the first implementation
	 * @param inputList List of all the people to be attended
	 * @param amountOfClerks number of Clerks working (1 - 5)
	 */
	public static void processSLMS(ArrayList<Persona> inputList, int amountOfClerks){
		averageWaitingTotalT =0;
		Fila lineQ = new Fila(1);
		ProcessingPost pQ = new ProcessingPost(amountOfClerks);
		amountOfClients = inputList.size();
	
		
		int t = inputList.get(0).getArrivalTime();
		
		
		while(!inputList.isEmpty() || !lineQ.allLinesEmpty() || !pQ.allPostsEmpty()){

				
			while((!inputList.isEmpty()) && t>=inputList.get(0).getArrivalTime()) {
				
					lineQ.getFila(1).enqueue(inputList.get(0));
					inputList.remove(0);
			}
			

			//
			for(int i=1; i<= amountOfClerks;i++){
				if(!pQ.postAvailable(i)) {
					pQ.postPerson(i).setRemainingTime(pQ.postPerson(i).getRemainingTime()-1);
					if(pQ.postPerson(i).getRemainingTime()<=0) {
						pQ.exitPost(i);
						if(lineQ.getFila(1).first() != null){
							pQ.enterPost(i, lineQ.getFila(1).dequeue());
							pQ.postPerson(i).setWaitingTime(t - (double) pQ.postPerson(i).getArrivalTime());
							pQ.postPerson(i).setDepartureTime(t + pQ.postPerson(i).getRemainingTime());
							averageWaitingTotalT += pQ.postPerson(i).getWaitingTime();
						}
					}
				}
				else{
					if(lineQ.getFila(1).first() != null){
						lineQ.getFila(1).first().setWaitingTime(t - (double) lineQ.getFila(1).first().getArrivalTime());
						averageWaitingTotalT += lineQ.getFila(1).first().getWaitingTime();
						pQ.enterPost(i, lineQ.getFila(1).dequeue());
						pQ.postPerson(i).setDepartureTime(t + pQ.postPerson(i).getRemainingTime());
					}
				}				
			}
			lastT = t;
			t++;
		}
		
	}	
	
	
	
	/**
	 * Finds the highest amount of time it can skip to the moment someone is finished attended
	 * @param pQ the object that represents the clerks
	 * @param amountOfClerks number of maximum available Clerks
	 * @return time available to skip
	 */
	public static int findLowestValue(ProcessingPost pQ, int amountOfClerks) {
		int LowestValue = 1;
		ArrayList<Integer> clerksAvailable = new ArrayList<Integer>(amountOfClerks);
		clerksAvailable = pQ.availablePosts(pQ, amountOfClerks);

		//error: sale que los posts available son 2 y3 cuando realmente son todo, en el 1 sale que el size y el first es 1 
		//pero no es cierto que el size =1 pq no hay elemento ahi. Quizas deberia haber un elemento ahi
		if(pQ.allPostsEmpty()) {
			return LowestValue =1;
		}
		else if(pQ.postPerson(1) != null){
			LowestValue = pQ.postPerson(1).getRemainingTime();
		}
		
		for(int i = 1; i <= amountOfClerks; i++){
			if(pQ.postPerson(i) != null){
				LowestValue = Math.min(LowestValue, pQ.postPerson(i).getRemainingTime());
			}
		}
		return LowestValue;
	}
	

	/**
	 * Calculates the averageWaiting time for first implementation
	 * @return the average time people waited in line to be attended
	 */
	public String averageWaitingTime() {
		float etr = (float)(averageWaitingTotalT/amountOfClients)*100;
		int etr2 = (int) etr;
		float etr3 = (float)etr2/100;
		
		
		return "" + etr3 ;
	}
	/**
	 * Return the variable value for last time unit
	 * @return A string value of a the integer time unit
	 */
	public String lastT() {
		return "" + lastT;
	}
	
	public String clerks() {
		return "" + clerks;
	}
	
	
}