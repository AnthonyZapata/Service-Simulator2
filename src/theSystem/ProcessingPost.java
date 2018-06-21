package theSystem;

import java.util.ArrayList;

import queue.ArrayQueue;
import queue.SLLQueue;

public class ProcessingPost {
	
	int processingPostsAmount;
	ArrayQueue<Persona>[] processingPosts;
		
	/**
	 * Resumen
	 * @param a
	 */
	public ProcessingPost(int a) {   //create array of posts with a pre selected ammount a
		processingPosts = new ArrayQueue[a];
		processingPostsAmount = a;
		for(int i=0; i<a;i++) {
			processingPosts[i] = new ArrayQueue<Persona>();
		}
		
	}
	/**
	 * Gets the person the clerk is attending
	 * @param postNumber is the number of clerk number is between 1-5 in this project
	 * @return the person that the Clerk is attending at the moment
	 */
	public Persona postPerson(int postNumber) {
		return (processingPosts[postNumber-1].first());
	}
	
	
	/**
	 * Adds the person to be attended and adds it to the array of processing queue
	 * @param postNumber The number representing the clerk
	 * @param persona The person that is going to be attended
	 */
	public void enterPost(int postNumber, Persona persona) {
		if(postAvailable(postNumber)) {
			processingPosts[postNumber-1].enqueue(persona);
		}
	}
	/**
	 * Removes person from the current post
	 * @param postNumber the number representing the Clerk
	 * @return 
	 */
	public Persona exitPost(int postNumber) {
		return processingPosts[postNumber-1].dequeue();
		
	}
	/**
	 * Serves the persona a n time of units
	 * @param a Person that is being attended
	 * @param n Time units to be processed
	 */
	public void processPersona(Persona a,int n) {
		a.isServed(n);
	}
	
	/**
	 * Calculate if all the clerks are available for attending people
	 * @return true if all clerks are available and false otherwise
	 */
	public boolean allPostsEmpty() {
		for(int i =0; i < processingPostsAmount; i++) {
			if(processingPosts[i].first() != null) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Return true or false if clerk is available
	 * @param postNumber number representing the Clerk
	 * @return true if the clerk in the position given is available to attend someone new and false if not
	 */
	public boolean postAvailable(int postNumber){    //Verify if post empty //post are labeled: 1,2,3,4,5
		return(processingPosts[postNumber-1].size() == 0); 
	}
	
	/**
	 * Calculate the available clerks for 
	 * @param pQ the ProcesingPost object
	 * @param amountOfClerks the amount of clerks for this example, only 1-5 available
	 * @return an ArrayList of the available clerks, possible values are from 1-5
	 */
	public ArrayList<Integer> availablePosts(ProcessingPost pQ, int amountOfClerks){
		ArrayList<Integer> postsAvailable = new ArrayList<>(amountOfClerks);
		for(int i = 1; i<= amountOfClerks; i++) {
			if(pQ.postAvailable(i)){
				postsAvailable.add(i);
			}
		}
		return postsAvailable;
		
	}
}