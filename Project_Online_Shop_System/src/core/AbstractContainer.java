package core;

abstract public class AbstractContainer implements Container{
	
	public boolean equals(Object o) throws ClassCastException{
		Container c;
		try {
			c = (Container)o;
		}catch(ClassCastException e){
			return false;
		}
		
		if(this.size() != c.size()) {
			return false;
		}
		
		for (int i = 0; i < size(); i++) {
			if( !get(i).equals(c.get(i)) ) {
				return false;
			}
		}
		
		return true;
	}
	
	public String toString() {
		String str = "";
		
		for (int i = 0; i < size(); i++) {
			str += "index: " + i + ", Object: " + get(i) + "\n";
		}
		
		return str;
	}
	
}
