package queue;


public class SLLQueue<E> implements Queue<E> {
	
	private static class Node<E> { 
		public Node(){
			
		}
		private E element; 
		private Node<E> next;
		
		public Node<E> getNext(){
			return next;
		}
		public E getElement(){
			return element;
		}
	}	
	
	private Node<E> first, last;   // references to first and last node
	private int size; 

	public SLLQueue() {           // initializes instance as empty queue
		first = last = null; 
		size = 0; 
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public E first() {
		if (isEmpty()) return null;
		return first.element; 
	}
	public E dequeue() {
		Node<E> etr = first;
		if (isEmpty()) return null;		
		first = first.getNext();
		size--;
		if(size == 0){
			last = null;
		}
		
		return etr.element;
	}
	
	public void enqueue(E e) {
		if (size == 0){ 
			first = last = new Node<E>(); 
			first.element = last.element = e;
		}
		else { 
			Node<E> newLast = new Node<E>();
			newLast.element = e;
			last.next = newLast;
			last = newLast;	
		}
		size++; 
	}

	@Override
	public void showReverse() { 
	    if (size == 0)
		   System.out.println("Queue is empty."); 
		else
		   recSR(first);
    } 
    private void recSR(Node<E> f) { 
		if (f != null) { 
		   recSR(f.getNext()); 
		   System.out.println(f.getElement()); 
	     } 
    } 
  
}

