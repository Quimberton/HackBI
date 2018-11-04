
public class NodeList<T extends Comparable<T>> {
	
	private Node<T> head;
	private Node<T> tail;
	public int size = 0;
	
	public NodeList() {
		this.head = null;
		this.tail = null;
	}
	
	public NodeList(Node<T> head) {
		this.head = head;
		this.tail = head;
		size++;
	}

	public NodeList(T data){
		this.head = new Node<T>(data);
		this.tail = this.head;
		size++;
	}
	
	public void add(T data){
		if(this.head == null) this.head = new Node<T>(data);
		else{
			Node<T> temp = head;
			while(temp.getNext()!= null)temp = temp.getNext();
			temp.setNext(new Node<T>(data,null,temp));
			this.tail = temp.getNext();
		}
		size++;
	}
	
	public void addFirst(T data) {
		setHead(new Node<T>(data));
	}
	
	public void addAfter(T key, T dataPack){
		Node<T> temp = this.head;
		while(temp.getNext()!= null&&!temp.getData().equals(key)) temp = temp.getNext();
		if (temp.getNext()!=null) { temp.setNext(new Node<T>(dataPack, temp.getNext(),temp)) ;size++;} 
	}
	
	public void addBefore(T key, T dataPack) {
		Node<T> temp = this.tail;
		while(temp.getPrev()!=null&& !temp.getData().equals(key))temp = temp.getPrev();
		if(temp.getPrev()!=null) {temp.setPrev(new Node<T>(dataPack,temp, temp.getPrev()));size++;}
	}
	
	public void addSorted(T data) {
		if(this.getHead()==null) {
			addFirst(data);
		}
		else if(this.getHead().getData().compareTo(data)>0) {
			addFirst(data); 
			}
		else if(this.getHead().getData().equals(data)) {
			this.getHead().increment();
		}
		else {
			Node<T> cur = this.getHead();
			Node<T> next = cur.getNext();
			while(next != null) {
				if(next.getData().compareTo(data)==0) {next.increment();return;}
				else if(next.getData().compareTo(data)>0) {cur.setNext(new Node<T>(data, next, cur));return;}
				cur = next;
				next = cur.getNext();
			}
			cur.setNext(new Node<T>(data, null, cur));
		}
	}
	
	public void remove(T data){
		Node<T> temp = head;
		Node<T> last = null;
		
		if (head.getData() == null)return;
		if (head.getData().equals(data)){
			head = head.getNext();
			return;
		}
		while(temp!=null&&!temp.getData().equals(data)){
			last = temp;
			temp = temp.getNext();
		}
		last.setNext(temp.getNext());
		last.getNext().setPrev(last);
	}
	
	public String toString(){

        Node<T> current = head;
        String retStr ="["+current.toString();
        while(current.getNext() != null){
        	current = current.getNext();
            retStr +=  ", " +current.toString() ;
        }
		retStr+="]";
        return retStr;
    }
	
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public Node<T> getHead() {
		return head;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setHead(Node<T> head) {
		if(this.head==null)	{this.head = head; this.tail = head;size = 1;}
		else {
			Node<T> temp = this.head;
			this.head = head;
			head.setNext(temp);
			head.getNext().setPrev(this.getHead());
			size++;
		}
	}
	public Node<T> getTail() {
		return tail;
	}
	public void setTail(Node<T> tail) {
		if(this.head==null) {this.tail = tail; this.head = tail; size = 1;}
		else {
			Node<T> temp = head;
			while(temp.getNext()!=null) temp = temp.getNext();
			temp.setNext(tail);
			tail.setPrev(temp);
			size++;
		}
	}
}
