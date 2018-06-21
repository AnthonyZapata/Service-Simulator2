package theSystem;

public class Persona {
	   private int pid;         // id of this job
	   private int arrivalTime;    // arrival time of this job
	   private int remainingTime;  // remaining service time for this job
	   private int departureTime;  // time when the service for this job is completed
	   private double waitingTime; // the difference between the time person is attended and arrival time
	   private int serviceStart;
	   public Persona(int id,int at, int rt) { 
	    pid=id;
	    arrivalTime = at; 
	    remainingTime = rt; 
	   }
	   
	   /*
	    * Sets the average waiting time for the person
	    */
	   public void setWaitingTime(double t) {
		   this.waitingTime = t;
	   }
	   /*
	    * returns the time spent waiting in line
	    */
	   public double getWaitingTime() {
		   return waitingTime;
	   }
	   /*
	    * Returns the remaining time for person to finish getting attended
	    */
	   public int getRemainingTime() {
		   return remainingTime;
	   }
	   /*
	    * Sets the time it takes a person to finish getting attended
	    */
	   public void setRemainingTime(int remainingTime){
		 this.remainingTime = remainingTime;
	   }
	   /*
	    * Gets the departure time
	    */
	   public int getDepartureTime() {
	    return departureTime;
	   }
	   /*
	    * Sets the departure time to the time given
	    */
	   public void setDepartureTime(int departureTime) {
	    this.departureTime = departureTime;
	   }
	   /*
	    * gets the ID of the person, this ID is a number given when read from file
	    */
	   public int getPid() {
		   return pid;
	   }
	   /*
	    * returns the time of arrival
	    */
	   public int getArrivalTime() {
	    return arrivalTime;
	   }
	  
	   
	    
	   /**
	    * Registers an update of serviced received by this job. 
	    * @param q the time of the service being registered. 
	    */
	   public void isServed(int q) { 
	    if (q < 0) return;   // only register positive value of q
	       remainingTime -= q; 
	   }
	   /**
	    * Sets the service time equal to the the given value, service time is the time someone starts receiving service
	    * @param t value of the time
	    */
	   public void setServiceTime(int t){
		   this.serviceStart = t;
	   }
	   /**
	    * Returns the service time
	    * @return time when service started
	    */
	   public int getServiceTime(){
		   return serviceStart;
	   }
	    
	   /**
	    * Generates a string that describes this job; useful for printing
	    * information about the job.
	    */
	   public String toString() { 
	    return 
	         "  Arrival Time = " + arrivalTime + 
	         "  Remaining Time = " + remainingTime +
	         "  Departure Time = " + departureTime +
	    	 "  ID = " + pid + "\n";
	   }
}