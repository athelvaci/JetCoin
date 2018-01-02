package jetCoin;

import java.util.Date;

public class Block {
	
	public String hash;
	public String previousHash; 
	private String data; 
	private long timeStamp; 
	private int nonce;
	
	//construstor
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calculateHash(); //hash calculator
	}
	
	//Finds the new hash
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	//increase nonce value until finding true one
	public void mineBlock(int difficulty) {
		String target = StringUtil.getDificultyString(difficulty); 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined--- " + hash);
	}
	
}
