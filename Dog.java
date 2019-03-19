public class Dog implements Comparable<Dog> {
	private String dogName;
	private int count;
	
	public void setName(String n){
		dogName = n;
	}
	
	public String getName(){
		return dogName;
	}
	
	public void setCount(int c){
		count = c;
	}
	
	public int getCount(){
		return count;
	}
	
	public Dog(){
		dogName = "default name";
		count = 0;
	}
	
	public Dog(String n, int c){
		setName(n);
		setCount(c);
	}

	@Override
	public int compareTo(Dog o) {
		return this.getName().compareTo(o.getName());
	}
	
	
}