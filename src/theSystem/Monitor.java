package theSystem;

import java.util.ArrayList;

import queue.ArrayQueue;

public class Monitor {
	public Monitor(Fila theList){
		this.theList = theList;
		theMonitorMethod(theList);
	}
	
	public Fila theList;
	public int longestLineIndex;
	public int shortestLineIndex;
	
	/**
	 * Balances the lines
	 * @param filas is the object of the lines 
	 * @return the lines balanced
	 */
	public Fila theMonitorMethod(Fila filas){
		ArrayQueue<Persona> longest = getL(filas);
		ArrayQueue<Persona> shortest = getS(filas);
		while(longest.size()-shortest.size() >= 2) {

		
			shortest.enqueue(longest.removeLast());
			longest = getL(filas);
			shortest = getS(filas);
		}		
		return filas;
	}
	/**
	 * Gets the longest line 
	 * @param filas the Lines unordered
	 * @return the ArrayQueue of the longest line
	 */
	public ArrayQueue<Persona> getL(Fila filas) {
		ArrayQueue<Persona> longest = filas.getFila(1);
		longestLineIndex = 1;
		for(int i = 2; i<= filas.filaAmount;i++) {  
			if(!filas.getFila(i).isEmpty()) {
				if(filas.getFila(i).size() == longest.size() 
						&& filas.getFila(i).last().getArrivalTime() == longest.last().getArrivalTime()
						&& filas.getFila(i).last().getPid() < longest.last().getPid()){
					longest = filas.getFila(i);
					setLongestLineIndex(i);
				}
				else if(filas.getFila(i).size() == longest.size() 
						&& filas.getFila(i).last().getArrivalTime() < longest.last().getArrivalTime()){
					longest = filas.getFila(i);
					setLongestLineIndex(i);
				}
				else if(filas.getFila(i).size() > longest.size()) {
					longest = filas.getFila(i);
					setLongestLineIndex(i);
				}
			}
		}
		return longest;
	}
	/**
	 * Gets the shortest line and most rightest to the longest line
	 * @param filas the lines unordered
	 * @return the shortest and most rightest line to the longest line
	 */
	public ArrayQueue<Persona> getS(Fila filas) {
		ArrayQueue<Persona> shortest = filas.getFila(((getLongestLineIndex())% filas.filaAmount)+1);
		for(int i = 2; i <= filas.filaAmount; i++) {
			if(filas.getFila(((getLongestLineIndex()+i) % filas.filaAmount)+1).size() < shortest.size()) {
				shortest = filas.getFila(((getLongestLineIndex()+i) % filas.filaAmount)+1);
				setShortestLineIndex(((getLongestLineIndex()+i) % filas.filaAmount)+1);
			}
		}
		return shortest;
	}
	/*
	 * getters and setters for the index of longest and shortest line
	 */
	public int getLongestLineIndex(){
		return longestLineIndex;
	}
	
	public void setLongestLineIndex(int i){
		this.longestLineIndex = i;
	}
	
	public int getShortestLineIndex(){
		return longestLineIndex;
	}
	
	public void setShortestLineIndex(int i){
		this.shortestLineIndex = i;
	}
	
	
}