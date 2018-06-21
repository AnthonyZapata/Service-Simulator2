package theSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

import queue.SLLQueue;
import theSystem.SLMS;
import queue.ArrayQueue;

      //////////////////////////////////////////////////////If all mentioned files exist, the program runs 

public class QueueSimulation {
	    //updateVariables
		static boolean filesThatExist;
		static boolean nextFileExists;
		
		
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		Scanner scan = new Scanner(new File("dataFiles.txt"));
		
		//////////////////////////////
		int files=0;				
		filesThatExist = true;
		while(scan.hasNextLine()) {
			files++;
			scan.next();
		}
		scan.close();
		/////////////////////////////
	
		//run
		for(int i =0; i<files; i++) {
			 
			
			////////////////////////////////////////////// this checks for the skipping of inexistent files
			while(getFileExists()==false) {//if current file doesnt exist // no esta entrando
				i++;
				setFileExists(getNextFileExists()); //si este no existe, verifica y copia si el next existe
				
				if(i>=files-1) {//corta si pasa cantidad de files
					break;					
				}
			}
			////////////////////////////////////////////
			
			
			if(getFileExists()&&i<files){
				ArrayList<Persona> inputList;
				if(i>=dataReader().size()) {
					inputList = sortThis(dataReader().get(i-1));
				}
				else {
					inputList = sortThis(dataReader().get(i));
				}
				
				int numberCustomers = inputList.size();
				//1
				SLMS slms = new SLMS(inputList, 1);
				System.out.println("SLMS "+ slms.clerks+":" + slms.lastT() + " " + slms.averageWaitingTime() + " 0");
				String file = "inputFiles/data_"+i+"_OUT.txt";

				PrintWriter writer = new PrintWriter(file, "UTF-8");
				writer.println("Amount of clients: "+numberCustomers);



				writer.println("SLMS "+ slms.clerks+":" + slms.lastT() + " " + slms.averageWaitingTime() + " 0");
				//3
				inputList = sortThis(dataReader().get(i));
				slms = new SLMS(inputList, 3);

				System.out.println("SLMS "+ slms.clerks+":" + slms.lastT() + " " + slms.averageWaitingTime() + " 0");

				writer.println("SLMS "+ slms.clerks+":" + slms.lastT() + " " + slms.averageWaitingTime() + " 0");

				//5
				inputList = sortThis(dataReader().get(i));
				slms = new SLMS(inputList, 5);

				System.out.println("SLMS "+ slms.clerks+":" + slms.lastT() + " " + slms.averageWaitingTime() + " 0");

				writer.println("SLMS "+ slms.clerks+":" + slms.lastT() + " " + slms.averageWaitingTime() + " 0");

				writer.println("----------------------");

				//1
				inputList = sortThis(dataReader().get(i));
				MLMS mlms = new MLMS(inputList, 1);			
				System.out.println("MLMS "+ mlms.clerks+":" + mlms.lastT() + " " + mlms.averageWaitingTime() + " "+mlms.valueOfM());
				writer.println("MLMS "+ mlms.clerks+":" + mlms.lastT() + " " + mlms.averageWaitingTime() + " "+mlms.valueOfM());
				//2
				inputList = sortThis(dataReader().get(i));
				mlms = new MLMS(inputList, 3);			
				System.out.println("MLMS "+ mlms.clerks+":" + mlms.lastT() + " " + mlms.averageWaitingTime() + " "+mlms.valueOfM());
				writer.println("MLMS "+ mlms.clerks+":" + mlms.lastT() + " " + mlms.averageWaitingTime() + " "+mlms.valueOfM());
				//3
				inputList = sortThis(dataReader().get(i));
				mlms = new MLMS(inputList, 5);			
				System.out.println("MLMS "+ mlms.clerks+":" + mlms.lastT() + " " + mlms.averageWaitingTime() + " "+mlms.valueOfM());
				writer.println("MLMS "+ mlms.clerks+":" + mlms.lastT() + " " + mlms.averageWaitingTime() + " "+mlms.valueOfM());
				//3ro
				writer.println("----------------------");
				//1
				
				inputList = sortThis(dataReader().get(i));
				MLMSBLL mlmsbll = new MLMSBLL(inputList, 1);			
				System.out.println("MLMSBLL "+ mlmsbll.clerks+":" + mlmsbll.lastT() + " " + mlmsbll.averageWaitingTime() + " "+mlmsbll.valueOfM());
				writer.println("MLMSBLL "+ mlmsbll.clerks+":" + mlmsbll.lastT() + " " + mlmsbll.averageWaitingTime() + " "+mlmsbll.valueOfM());
				//2
				inputList = sortThis(dataReader().get(i));
				mlmsbll = new MLMSBLL(inputList, 3);			
				System.out.println("MLMSBLL "+ mlmsbll.clerks+":" + mlmsbll.lastT() + " " + mlmsbll.averageWaitingTime() + " "+mlmsbll.valueOfM());
				writer.println("MLMSBLL "+ mlmsbll.clerks+":" + mlmsbll.lastT() + " " + mlmsbll.averageWaitingTime() + " "+mlmsbll.valueOfM());
				//2
				inputList = sortThis(dataReader().get(i));
				mlmsbll = new MLMSBLL(inputList, 5);			
				System.out.println("MLMSBLL "+ mlmsbll.clerks+":" + mlmsbll.lastT() + " " + mlmsbll.averageWaitingTime() + " "+mlmsbll.valueOfM());
				writer.println("MLMSBLL "+ mlmsbll.clerks+":" + mlmsbll.lastT() + " " + mlmsbll.averageWaitingTime() + " "+mlmsbll.valueOfM());
				//2
				
				writer.println("----------------------");
				
				inputList = sortThis(dataReader().get(i));
				MLMSBWT mlmsbwt = new MLMSBWT(inputList, 1);			
				System.out.println("MLMSBWT "+ mlmsbwt.clerks+":" + mlmsbwt.lastT() + " " + mlmsbwt.averageWaitingTime() + " "+mlmsbwt.valueOfM());
				writer.println("MLMSBWT "+ mlms.clerks+":" + mlms.lastT() + " " + mlms.averageWaitingTime() + " "+mlms.valueOfM());
				//3
				inputList = sortThis(dataReader().get(i));
				mlmsbwt = new MLMSBWT(inputList, 3);			
				System.out.println("MLMSBWT "+ mlmsbwt.clerks+":" + mlmsbwt.lastT() + " " + mlmsbwt.averageWaitingTime() + " "+mlmsbwt.valueOfM());
				writer.println("MLMSBWT "+ mlms.clerks+":" + mlms.lastT() + " " + mlms.averageWaitingTime() + " "+mlms.valueOfM());
				//5
				inputList = sortThis(dataReader().get(i));
				mlmsbwt = new MLMSBWT(inputList, 5);			
				System.out.println("MLMSBWT "+ mlmsbwt.clerks+":" + mlmsbwt.lastT() + " " + mlmsbwt.averageWaitingTime() + " "+mlmsbwt.valueOfM());
				writer.println("MLMSBWT "+ mlms.clerks+":" + mlms.lastT() + " " + mlms.averageWaitingTime() + " "+mlms.valueOfM());
				
				
				
				
				
			
				
				
				writer.close();
				setFileExists(getNextFileExists());
			}
		}	
	}
	
	
	
	////////////////////////////////////////////////setters getters
	public static boolean getFileExists() {
		return filesThatExist;
	}
	public static void setFileExists(boolean i) {
		filesThatExist = i;
	}
	public static boolean getNextFileExists() {
		return nextFileExists;
	}
	public static void setNextFileExists(boolean i) {
		nextFileExists = i;
	}
	/////////////////////////////////////////////////
	
	/**
	 * Sorts the initial list of all persons by time of arrival and read first from files
	 * @param input List of all the people to be attended
	 * @return The same list but sorted
	 */
	
	
	//sorts Input List
	public static ArrayList<Persona> sortThis(ArrayList<Persona> input){
		ArrayList<Persona> ltr = new ArrayList<>();
		int max = input.get(0).getArrivalTime();
		for(int i = 1; i < input.size(); i++) {
			if(input.get(i).getArrivalTime() > max) {
				max = input.get(i).getArrivalTime();
			}
		}
		for(int x =0; x <= max; x++) {
			for(int n = 0; n<input.size();n++) {
				if(input.get(n).getArrivalTime()==x) {
					ltr.add(input.get(n));
				}
			}
		}
		for(int x = 0; x<ltr.size(); x++) {
			for(int y = 0; y < ltr.size(); y++) {
				if((ltr.get(x).getArrivalTime() == ltr.get(y).getArrivalTime()) && (ltr.get(x).getPid() < ltr.get(y).getPid())) {
					Persona dummy = ltr.get(x);
					ltr.set(x,ltr.get(y));
					ltr.set(y, dummy);
				}
			}
		}		
		return ltr;
	}

		
			//done --> Creates an ArrayList with all the people to process arrayname: input
	/**
	 * Reads all the files and creates an arrayList of these people
	 * @return An arrayList of all the people to attend
	 * @throws FileNotFoundException 
	 */
	
	
	//verified
	public static ArrayList<ArrayList<Persona>> dataReader() throws FileNotFoundException{
		Scanner scan = new Scanner(new File("dataFiles.txt"));
		Scanner scan2 = scan;
		ArrayList<ArrayList<Persona>> input = new ArrayList<ArrayList<Persona>>();
		
		while(scan.hasNext()) {
			
			setFileExists(true);//resets file Exists
			
			ArrayList<Persona> toAdd = new ArrayList<Persona>();
			int pid = 1;
			Scanner scanSingleFile=null;
			String scanClone = scan.next();
			File file = new File("inputFiles/"+scanClone);
			
			//determines if file exists or not.
			if(file.exists()) {
				scanSingleFile = new Scanner(file);
			}
			else {
				 setFileExists(false);
				System.out.println("file " + scanClone + " not found, will be skipped" );
			}
			////
			
			if(file.exists()) {
				while(scanSingleFile.hasNext()) {
					int arrival = scanSingleFile.nextInt();
					int timeToSpend = scanSingleFile.nextInt();
					Persona person = new Persona(pid, arrival,timeToSpend);
					toAdd.add(person);
					pid++;
				}
			}
		
			if(new File("inputFiles/"+scanClone).exists()) { //if current file exists
				scanSingleFile.close();
				input.add(toAdd);
			
			}
			if(scan.hasNext()) { //if there is another file name
				if(new File("inputFiles/"+scan2.nextLine()).exists()) {
					setNextFileExists(true);

				} 
				else {
					setNextFileExists(false);
				}
			}
			 
		}
		scan.close();
		return input;
	}
}
