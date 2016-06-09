package test;

class Customer {
	public Customer(int age) {
		this.age = age;
	}

	public String toString() { 
	    return "Customer age:" + this.age;
	}
	
	public int age;
}