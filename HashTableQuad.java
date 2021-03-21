public class HashTableQuad {
	private int [] table;
	
	boolean prime(int num) {
		for(int i = 2; i<= num; i++) {
			if(num%i == 0) {
				return false;		
			}
		}
		return true;
	}
	
	public HashTableQuad(int maxNum, double load){
		int a = (int)(maxNum*load);
		while(prime(a)== false) {
			a++;
		}
		this.table = new int[a];
	}
	
	public void insert(int n) throws ArithmeticException {
		//check if the value exist in the table
		//check if the table is full
		int check =0;																//initiate check value
		for(int i=0; i<table.length; i++) {											//search through the table
			if(n == table[i]) {
				throw new ArithmeticException ("Value exist in the hash table");
			}
			if(table[i] == 0) {														//check for an available spot
				check ++;															//count available spots
			}
		}
		if(check==0) {																//if there is none available spot, rehash
			rehash();
		}
			
		//insert the value in the hashing table
		int h = 0;									//check if the value being has or have to return back to the beginning of the table to hash
		int a=n%table.length;						//identify the place to hatch
		//linear hashing the value
		int j = 0;
		int k = a;
		int track = 0;
		while(h==0) {
			while(k<table.length) {
				if(table[k]==0) {
					table[k] = n;
					h =1;								//1 means the value is inserted
					break;
				}
				else {
					k+=2^j;
					j++;
					track = k;
				}
			}
			k= track-table.length;
		}
	}
		
	private void rehash(){
		int a = table.length*2;
		while(prime(a)== false) {
			a++;
		}
		this.table = new int[a];
	}
	
	public boolean isIn(int n) {
		//check if the value exists or not
		for(int i=0; i<table.length; i++) {
			if(n == table[i]) {
				return true;
			}
		}
		return false;
	}
	
	public void printKeys() {
		//print
		for(int i=0; i<table.length; i++) {
			if(table[i]!=0) {
				System.out.print(table[i] + " ");
			}
		}
	}
	
	public void printKeysAndIndexes() {
		//print
		for(int i=0; i<table.length; i++) {
			if(table[i]!=0) {
				System.out.print(table[i] + " index:" + i + " ");
			}
		}
	}
}