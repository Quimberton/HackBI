import java.util.Stack;

public class Stack2 implements Agenda<Loc>{
	private Stack<Loc> stack = new Stack<Loc>();
	
	public Stack2() {
	}
	
	public Stack2(Loc b) {
		stack.add(b);
	}
	
	public boolean contains(Loc a) {
		return stack.contains(a);
	}
	
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public int getSize() {
		return stack.size();
	}
	@Override
	public Loc add(Loc data) {
		stack.push(data);
		return data;
	}

	@Override
	public Loc remove() {
		return stack.pop();
	}

	@Override
	public Loc peek() {
		if(!stack.isEmpty())return stack.get(stack.size()-1);
		return stack.get(stack.size());
	}
	
	public String toString() {
		String result = "start,";
		for(int x = 1; x < stack.size(); x++) {
			if(stack.get(x).getX()>stack.get(x-1).getX())result += "D,";
			else if(stack.get(x).getX()<stack.get(x-1).getX())result += "U,";
			else if(stack.get(x).getY()<stack.get(x-1).getY())result += "L,";
			else result+= "R,";
		}
		result += "end";
		return result;
	}
}
