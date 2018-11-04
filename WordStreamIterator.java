import java.util.*;

public class WordStreamIterator {
	private Reader inFile;
	public WordStreamIterator(String nm)
	{
		String fileName = "E:\\Program Files (86) USB\\JCreatorV3LE\\MyProjects\\Hackathon\\src\\Anagram\\"+nm;	
   		inFile = new Reader(fileName);
   		if (inFile.bad())
   		{
     		System.err.println("Can't open " + fileName);	
     		System.exit(1);
   		}
   	}
   	
   	public boolean hasNext()
   	{
   		return (!inFile.eof());
   	}
   	
   	public Object next()
   	{
   		String result = inFile.readWord();
   		return result;
   	}
   	
   	public Object get(int x) {
   		while(x>0) {
   			this.next();
   			x--;
   		}
   		return this.next();
   	}
   	public int get(String a) {
   		int x = 0;
   		String word = (String) this.next();
   		while(!word.equals(a)) {
   			x++;
   			word = (String)this.next();
   		}
   		return x;
   	}
   	
   	public void remove()
   	{
   		// do nothing
   	}
	
}