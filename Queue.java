
public class Queue<T extends Comparable<T>> {
	
	private int size;
	private NodeList<T> queue;
	
	public Queue() {
		queue = new NodeList<T>();
	}
	public Queue(NodeList<T> list) {
		queue = list;
		size = list.size;
	}
	
	public Queue(T data) {
		queue = new NodeList<T>(data);
	}
	
	public T enqueue(T data) {
		queue.add(data);
		size++;
		return data;
	}
	public T dequeue() {
		Node<T> a = queue.getHead();
		if(!isEmpty()) {
			queue.remove(queue.getHead().getData());
			size--;
			return a.getData();
		}
		return null;
	}
	public Node<T> getNode(int a){
		Node<T> temp = queue.getHead();
		while (temp.getNext()!=null) {
			if(temp.getIndex()==a)return temp;
			temp = temp.getNext();
		}
		return null;
	}
	
	public String toString() {
		return queue.toString();
	}
	
	public boolean isEmpty() {
		if(size!=0||queue.getHead()!=null)return false;
		else return true;
	}
}
