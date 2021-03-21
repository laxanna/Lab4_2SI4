public class HashTableLin {
	private int [] table;
	
	boolean prime(int num) {
		for(int i = 0; i<= num; i++) {
			for(int j=2; i <= num; j++) {
				if(i!=j && i%j == 0) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	public HashTableLin(int maxNum, double load){
		int a = (int)(maxNum*load);
		while(prime(a)== false) {
			a++;
		}
		this.table = new int[a];
	}
	
}
