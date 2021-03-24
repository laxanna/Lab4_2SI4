public class HashTableLin {
	private Integer [] table;
	private int TableSize;
	private int NumKeys;
	private double MaxLoadFactor;
	
	public int isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return 0;
            }
        }
        return 1;
    }
	
	//assigned all the private parameters
	public HashTableLin(int maxNum, double load){
		int a = (int)(maxNum/load);
		int h = 0;
		while(h == 0) {
			if(isPrime(a) != h) {
				h=1;
			}
			else {
				a++;
			}
		}
		this.table = new Integer[a];
		this.TableSize = a;
		this.NumKeys = 0;
		this.MaxLoadFactor = load;
	}
	//get methods
	public int getTableSize() {
		return TableSize;
	}
	public int getNumKeys() {
		int num = 0;
		for(int i=0; i<table.length; i++) {
			if(table[i] !=null) {
				num++;
			}
		}
		NumKeys = num;
		return NumKeys;
	}
	public double getMaxLoadFactor() {
		return MaxLoadFactor;
	}
	
	//run time n
	//methods
	public void insert(int n){
		//check if the value exist in the table
		//check if the table is full												//initiate check value
		for(int i=0; i<table.length; i++) {											//search through the table
			if(null != table[i]) {
				if(n == table[i]) {
					return;
				}
			}
		}
		if((double)(NumKeys+1)/TableSize > MaxLoadFactor) {	
			rehash();
		}
		
		//insert the value in the hashing table
		int h = 0;									//check if the value being has or have to return back to the beginning of the table to hash
		int a=n%table.length;						//identify the place to hatch
		//linear hashing the value
		for(int i =a; i<table.length; i++) {
			if(table[i]==null) {
				table[i] = n;
				h =1;								//1 means the value is inserted
				break;
			}
		}
		
		//hash from the beginning if the value is not inserted yet
		if(h == 0) {
			for(int i=0; i<a; i++) {
				if(table[i] == null) {
					table[i] = n;
					break;
				}
			}
		}
	}

	//run time: n (number of keys, using insert function)
	private void rehash(){
		HashTableLin big = new HashTableLin(this.NumKeys*2,this.MaxLoadFactor);
		
		for(int x=0; x<table.length;x++) {
			if(table[x]!=null) {
				big.insert(table[x]);
			}
		}
		this.table = big.table;
		this.TableSize = big.TableSize;
		this.NumKeys = big.NumKeys;
		return;
	}
	
	public boolean isIn(int n) {
		//check if the value exists or not
		for(int i=0; i<table.length; i++) {
			if(table[i] != null){
				if(n == table[i]) {
					return true;
				}
			}
		}
		return false;	
		
	}
	
	//run time n, total keys
	public void printKeys() {
		//print
		for(int i=0; i<table.length; i++) {
			if(table[i]!=null) {
				System.out.println("Print Keys" + table[i] );
			}
		}
	}
	
	//run time n: total keys
	public void printKeysAndIndexes() {
		//print
		for(int i=0; i<table.length; i++) {
			if(table[i]!=null) {
				System.out.println("Print Keys" + table[i] + " index:" + i );
			}
		}
	}
	
	public int insertCount(int n) {
		
		int count = 0;
		
		if((double)(NumKeys+1)/TableSize > MaxLoadFactor) {	
			rehash();
		}
		
		//insert the value in the hashing table
		int h = 0;									//check if the value being has or have to return back to the beginning of the table to hash
		int a=n%table.length;						//identify the place to hatch
		//linear hashing the value
		for(int i =a; i<table.length; i++) {
			count ++;
			if(table[i]==null) {
				table[i] = n;
				h=1;
				break;
			}
			else if(table[i] != null){
				if(table[i] == n) {
					return count;
				}
			}
		}
		
		//hash from the beginning if the value is not inserted yet
		if(h==0) {
			for(int i=0; i<a; i++) {
				count ++;
				if(table[i]==null) {
					table[i] = n;
					h=1;
					break;
				}
				else {
					if(table[i] == n) {
						return count;
					}
				}
			}
		}
		return count;
	}
	
public double searchCount(int n) {
		
		int count = 0;
		
		//insert the value in the hashing table
		int h = 0;									//check if the value being has or have to return back to the beginning of the table to hash
		int a=n%table.length;						//identify the place to hatch
		//linear hashing the value
		for(int i =a; i<table.length; i++) {
			if(table[i]==null) {
				count++;
				h=1;
				break;
			}
			else if(table[i] != null){
				if(table[i] == n) {
					h=1;
					break;
				}
			}
			count++;
		}
		
		//hash from the beginning if the value is not inserted yet
		if(h==0) {
			for(int i=0; i<a; i++) {
				if(table[i]==null) {
					count++;
					h=1;
					break;
				}
				else {
					if(table[i] == n) {
						h=1;
						break;
					}
				}
				count++;
			}
		}
		return count;
	}
}
