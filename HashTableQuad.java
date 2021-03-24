public class HashTableQuad {
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
	public HashTableQuad(int maxNum, double load){
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
	
	
	//run time: max run = n
	public void insert(int n){
		//check if the value exist in the table
		//check if the table is full
																	//initiate check value
		for(int i=0; i<TableSize; i++) {											//search through the table
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
		int a=n%TableSize;						//identify the place to hatch
		//linear hashing the value
		int j = 0;
		int k = a;
		int track = 0;
		while(h==0) {
			while(k<TableSize) {
				if(table[k]==null) {
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
			k= track-TableSize;
		}
	}
	
		
	private void rehash(){
		HashTableQuad big = new HashTableQuad(this.NumKeys*2,this.MaxLoadFactor);
		
		for(int x=0; x<TableSize;x++) {
			if(table[x]!=null) {
				big.insert(this.table[x]);
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
	
	public void printKeys() {
		//print
		for(int i=0; i<table.length; i++) {
			if(table[i]!=null) {
				System.out.println("Print Keys" + table[i] + " ");
			}
		}
	}
	
	public void printKeysAndIndexes() {
		//print
		for(int i=0; i<table.length; i++) {
			if(table[i]!=null) {
				System.out.println("Print Keys" + table[i] + " index:" + i + " ");
			}
		}
	}
	
	public int insertCount(int n){
		if((double)(NumKeys+1)/TableSize > MaxLoadFactor) {	
			rehash();
		}
			
		//insert the value in the hashing table
		int h = 0;									//check if the value being has or have to return back to the beginning of the table to hash
		int a=n%TableSize;						//identify the place to hatch
		//linear hashing the value
		int j = 0;
		int k = a;
		int track = 0;
		int count = 0;
		while(h==0) {
			while(k<TableSize) {
				count ++;
				if(table[k]==null) {
					table[k] = n;
					h =1;								//1 means the value is inserted
					break;
				}
				else {
					if(table[k] == n) {
						return count;
					}
					else {
						k+=2^j;
						j++;
						track = k;
					}
				}
			}
			k= track-TableSize;
		}
		return count;
	}
	
}
