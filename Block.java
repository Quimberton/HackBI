
public class Block {
	
	private char data;
	private boolean seen = false;
	
	public Block(Character data) {
		if(data.equals('*')||data.equals('o')||data.equals('x')||data.equals('#'))this.data = data;
		else if(data.equals('#'))seen = true;
		else throw new IllegalArgumentException();
	}
	
	public char getData() {
		return data;
	}

	public void setData(char data) {
		this.data = data;
	}
	
	public boolean wasSeen() {
		return seen;
	}
	
	public void visit() {
		this.seen = true;
	}
	public boolean equals(Block a) {
		return this.getData()==a.getData();
	}
	public String toString() {
		return " "+data;
	}
	public boolean isPath() {
		if(this.data=='x')return true;
		return false;
	}
	public boolean isWall() {
		if(this.data == '#')return true;
		return false;
	}
}
