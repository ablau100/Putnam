import java.math.*;
import java.util.Scanner;
import java.util.ArrayList;

public class RatToPrimeFacts {


	static boolean divide = false;

	//check if a number is prime
	private static boolean isprime(int a){
	  boolean prime = true;
	  for(int i=2; i<=Math.sqrt(a) ;i++){
	    if(a%i==0) prime = false;
	  }
	  return prime;
	}

	//returns the biggest prime number thats less than the input
	private static int pastprime(int a){
	    for(int i =a-1; i>=2;i--){
	        if(isprime(i)) return i;
	    }
	    
	    return 2;
	}

	//returns a list of the prime factorization of a composite number
	private static  ArrayList<Integer> primefacts(int a){
	    ArrayList<Integer> facts = new ArrayList<Integer>();
	    while (a%2 == 0){
	        facts.add(2);
	        a /= 2;
	        //i++;
	    }
	    
	    for (int j = 3; j <=a; j+=2) {
	        while (a%j == 0){
	            facts.add(j);
	            a /= j;
	            //i++;
	        }
	    }

	    return facts;
	}
	    
	//recursive function that does most of the work
	public static String nfact(int a){
	  //bool divide = false;
	  String fact = "";
	  if(a == 2){
	     return fact +="2!";
	  } 
	  if(isprime(a)){
	    fact+= Integer.toString(a)+"!";
	    fact += "/("+Integer.toString(pastprime(a))+"!)";
	    divide = true;
	    for(int i=pastprime(a)+1;i<a;i++)
	       fact += nfact(i);
	    divide = false;
	  }
	  if(!isprime(a)){
	      ArrayList<Integer> pfacts = primefacts(a);
	      if (divide == true){
	          fact+="/(";
	      } 
	      for(int i=0; i< pfacts.size(); i++){
	          if(i!=0) fact+="*(";
	          fact += nfact(pfacts.get(i));
	          if(i!=0) fact+=")";
	      }
	      if(divide == true){
	          fact += ")";
	      }
	  }
	  return fact;
	}


	  public static void main(String[] args) {
	        
	      System.out.println("Please enter a numerator and denominator.");
	      
	      Scanner in = new Scanner(System.in);
	      int numerator = in.nextInt();
	      int denominator = in.nextInt();
	      
	      in.close();
	  
	      System.out.println("The rational number "+numerator+'/'+denominator+" can be expressed as");
	      System.out.println(nfact(numerator));
	      System.out.println("divided by");
	      System.out.println(nfact(denominator)+'.');

	    }

}
