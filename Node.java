
public class Node<T extends Comparable<T>> implements Comparable<Node> {

	private T data;
	private int frequency = 0;
	private Node<T> next;
	private Node<T> prev;
	
	public Node() {
		
	}
	
	public Node(T data){
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
		this.prev = null;
	}
	
	public Node(T data, Node<T> next, Node<T> prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	
	public Node<T> getNext(){
		return this.next;
	}
	
	public Node<T> getPrev(){
		return this.prev;
	}
	
	public T getData() {
		return this.data;
	}
	
	public int getFrq() {
		return frequency;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	public int increment() {
		return ++frequency;
	}
	
	public int getIndex() {
		int count = 0;
		Node<T> temp = this;
		while(this.getPrev()!= null) {
			count++;
			temp = temp.getPrev();
		}
		return count;
	}	
	public String toString() {
		return this.data.toString();
	}

	@Override
	public int compareTo(Node o) {
		return this.getData().compareTo((T) o.getData());
	}
	
	
}
