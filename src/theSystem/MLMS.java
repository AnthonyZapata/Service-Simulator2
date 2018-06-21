package theSystem;

import java.util.ArrayList;

public class MLMS {
	public static double averageWaitingTotalT = 0;
	public static double amountOfClients;
	public static int lastT;
	public static float valueOfM;
	public int clerks;
	static ArrayList<Persona> finishedJobs = new ArrayList<Persona>();

	
	public MLMS(ArrayList<Persona> inputList, int i) {
		// TODO Auto-generated constructor stub
		processSLMS(inputList, i);
		this.clerks = i;
	}

	
	
	public static void processSLMS(ArrayList<Persona> inputList, int amountOfClerks){
		Fila lineQ = new Fila(amountOfClerks);
		ProcessingPost pQ = new ProcessingPost(amountOfClerks);
		ArrayList<Persona> finishedJobs = new ArrayList<Persona>();
		
		amountOfClients = inputList.size();
		int t = inputList.get(0).getArrivalTime();
		
		while(!inputList.isEmpty() || !lineQ.allLinesEmpty() || !pQ.allPostsEmpty()){
			
			while((!inputList.isEmpty()) && t>=inputList.get(0).getArrivalTime()) {
				
				lineQ.getFila(getShortestLine(pQ, lineQ, amountOfClerks)).enqueue(inputList.get(0));
				inputList.remove(0);
			}

			for(int i=1; i<= amountOfClerks;i++){
				if(!pQ.postAvailable(i)) {
					pQ.postPerson(i).setRemainingTime(pQ.postPerson(i).getRemainingTime()-1);
					if(pQ.postPerson(i).getRemainingTime()<=0) {
						finishedJobs.add(pQ.exitPost(i));
						if(lineQ.getFila(i).first() != null){
							pQ.enterPost(i, lineQ.getFila(i).dequeue());
							pQ.postPerson(i).setDepartureTime(t + pQ.postPerson(i).getRemainingTime());
							pQ.postPerson(i).setServiceTime(t);
							pQ.postPerson(i).setWaitingTime(t - (double) pQ.postPerson(i).getArrivalTime());
							averageWaitingTotalT += pQ.postPerson(i).getWaitingTime();
						}
					}
				}
				else{
					if(lineQ.getFila(i).first() != null){
						lineQ.getFila(i).first().setWaitingTime(t - (double) lineQ.getFila(i).first().getArrivalTime());
						averageWaitingTotalT += lineQ.getFila(i).first().getWaitingTime();
						pQ.enterPost(i, lineQ.getFila(i).dequeue());
						pQ.postPerson(i).setDepartureTime(t + pQ.postPerson(i).getRemainingTime());
						pQ.postPerson(i).setServiceTime(t);
					}
				}
				
			}
			valueOfM = getValueOfM(finishedJobs);
			lastT = t;
			t++;
		}
		
	}
	
	/**
	 * Gets the value of M for the finishedJobs list given, m is the amount of the average number of customers 
	 * that reached the service post (started receiving service) before another customer who arrived earlier
	 * @param finishedJobs the arraylist of people who finished getting attended
	 * @return the float value of m
	 */
	public static float getValueOfM(ArrayList<Persona> finishedJobs){
		int counter = 0;
		for(int i = 0; i < finishedJobs.size(); i++){
			for(int j = 0; j < finishedJobs.size(); j++){
				if(finishedJobs.get(i).getArrivalTime() < finishedJobs.get(j).getArrivalTime() 
						&& finishedJobs.get(i).getServiceTime() > finishedJobs.get(j).getServiceTime()){
					counter++;
				}	
			}
		}
		float etr = (float)(counter/amountOfClients)*100;
		int etr2 = (int) etr;
		float etr3 = (float)etr2/100;
		return etr3;
	}
	
	
	
	/**
	 * Gets the shortest line for the next person who is arriving but has not yet chosen a line	
	 * @param pQ the processingPost, used to count how many people are in that line
	 * @param line is the array of lines, used to count how many people are in line 
	 * @param numberOfClerks is the number of post available
	 * @return the line where the person who is arriving should get into
	 */
	public static int getShortestLine(ProcessingPost pQ, Fila line, int numberOfClerks){
		int indexOfShortestLine = 1;
		int sizeOfIOSL =0;
		if(pQ.postAvailable(1)){
			sizeOfIOSL = line.getFila(1).size();
		}
		else{
			sizeOfIOSL = line.getFila(1).size() + 1;
		}
		for(int i = 2; i<= numberOfClerks; i++){
			if(pQ.postAvailable(i)){
				if(line.getFila(i).size() < sizeOfIOSL){
					sizeOfIOSL = line.getFila(i).size();
					indexOfShortestLine = i;
				}
			}
			else{
				if(line.getFila(i).size() + 1 < sizeOfIOSL){
					sizeOfIOSL = line.getFila(i).size() + 1;
					indexOfShortestLine = i;
				}
			}
		}
		return indexOfShortestLine;
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
	
	/**
	 * Returns the String value of M
	 * @return String value of M
	 */
	public String valueOfM(){
		return "" + valueOfM;
	}
	/**
	 * Returns the string value of amount of clerks
	 * @return String value of clerks
	 */
	public String clerks() {
		return "" + clerks;
	}
	

	

}