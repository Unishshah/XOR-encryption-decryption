package javaapplication13;


import java.math.BigInteger;
import java.util.Random;
import java.util.regex.Pattern;

public class CampusIDA1 {

    private static String msg;
    private static String msgE;
    private static String msgD;
    private static String key;
    private static int n2;
   

    public static void main(String[] args){
        key = generateKey();
        msg = generateMsg();
        
        System.out.println("The random generated message is "+msg);
    
        msgE = encryption(key,msg);
        System.out.println("The Eecryption message is "+msgE);
        
        msgD = decryption(key,msgE);
        System.out.println("The Decryption message is "+msgD);
        
       // String msgbrute = bruteForce(msgE);
        	
        //System.out.println("The number of tries taken for brute force is"+msgbrute);
        
        
        
    }

    private static String generateKey() {
    	String key = "";
		
		for(int i = 0; i<16;i++){
			Random randomnum = new Random(); 
			int number = randomnum.nextInt(2);
			key = key+number;
		}
		return key;
    }

    private static String generateMsg() {
		String choices = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
		
	    Random r = new Random();
	    int sizeofmsg = r.nextInt(25)+1;
	 
	    String msg = "";
	    for (int i = 0; i < sizeofmsg; i++) {
	       msg = msg+ choices.charAt(r.nextInt(choices.length()));
	    }
	    
	    if(msg.length()%2 != 0){
	    	msg = msg+" ";
	    }
	    
	    return msg;
    }

    private static String encryption(String key, String msg) {
    	BigInteger msgtobit1 = new BigInteger(msg.getBytes());
		String msgtobit2 = msgtobit1.toString(2);
		
		int n = msgtobit2.length()/8;
		n2 = n/2;
	
		
		BigInteger k = new BigInteger(key,2);
		for(int i = 0; i<n2; i++){
			k = k.or(k);
		
		}
		
		BigInteger newmsg = k.xor(msgtobit1);
		String msgE = new String(newmsg.toString(2));

		return msgE;
    }

    private static String decryption(String key, String msgE) {
    	
		BigInteger k = new BigInteger(key,2);
		for(int i = 0; i<n2; i++){
			k = k.or(k);
		
		}
		
		BigInteger out1 = new BigInteger(msgE,2);
		BigInteger out2 = k.xor(out1);
		String newmsg = new String(out2.toByteArray());

		
        return newmsg;
    }

    /*
    private static String bruteForce(String msgE) {
    	
    	int tries = 0;
    	String newstring = "";
    	long time = System.currentTimeMillis(); 
    	
    	while(!Pattern.matches("[a-zA-Z ]+", newstring)){
    		String key1 = generateKey();
          	newstring = decryption(key1, msgE);
    		tries++;
    	}
    	
    	long timetaken = System.currentTimeMillis()-time;
    	System.out.println(timetaken+" milliseconds");
    	System.out.println("attemped keys: " + tries);
        return newstring;
    }
*/

}
