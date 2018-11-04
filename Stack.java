
public class Stack implements Agenda{
	
	private int size = 0;
	private LinkList stack;
	
	public Stack() {
		stack = new LinkList();
	}
	
	public Stack(LinkList l) {
		stack = l;
	}
	
	public Stack(Link l) {
		stack = new LinkList();
		size++;
	}
	public Stack(Boolean data) {
		stack = new LinkList();
		size++;
	}
	
	public T push(T data) {
		stack.addFirst(data);
		size++;
		return data;
	}
	
	public T pop() {
		Node<T> a = stack.getHead();
		if(!isEmpty()) {
			stack.remove(stack.getHead().getData()); 
			size--;
			return a.getData();
		}
		return null;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean isEmpty() {
		if(size!=0||stack.getHead()!=null)return false;
		else return true;
	}
	public Node<T> getNode(int a){
		Node<T> temp = stack.getHead();
		while (temp.getNext()!=null) {
			if(temp.getIndex()==a)return temp;
			temp = temp.getNext();
		}
		return null;
	}
	
	public String toString() {
		return stack.toString();
	}
	
	public double RPN(String a) {

        Stack<Double> stack = new Stack<Double>();

        for (String token : a.split(" ")) {


            double second = 0.0;
            double first = 0.0;

            switch (token) {
                case "+":
                    second = stack.pop();
                    first = stack.pop();

                    stack.push(first + second);
                    break;
                    
                case "-":
                    second = stack.pop();
                    first = stack.pop();

                    stack.push(first - second);
                    break;
                    
                case "*":
                    second = stack.pop();
                    first = stack.pop();

                    stack.push(first * second);
                    break;
                    
                case "/":
                    second = stack.pop();
                    first = stack.pop();

                    if (second == 0.0)break;

                    stack.push(first / second);
                    break;
                    
                case "^":
                	second = stack.pop();
                    first = stack.pop();

                    stack.push(Math.pow(first, second));
                    break;
                    
                default:
                    stack.push(Double.parseDouble(token));
                    break;
            }
        }

        return stack.pop();
    }
	
	@Override
	public T add(T data) {
		return this.push(data);
	}

	@Override
	public T remove() {
		return this.pop();
		
	}

	@Override
	public T peek() {
		return stack.getHead().getData();
	}
}
