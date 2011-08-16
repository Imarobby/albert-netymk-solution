class First{
    
    public static void main(String [] commandline){
        // to test your methods:
        
        hello(commandline[0]);
        sumBetween(1,3);
        additionTable();
        multiplicationTable();
        System.out.println("max(1,2,3)= " + max(1,2,3));
        System.out.println("max(4,1,2,3)= " + max(4,1,2,3));
        intPairs(1000);
        format(100,30);
        fibs(20);
        fibsSmallerThan(10000);
    }

    
    

    private static final void hello(String name){
        // A method that writes to the terminal a line saying
        //     Hello to you
        //     followed by the argument name
        //     followed by an exclamation mark.
        // It should then write an empty line.
		  System.out.println("Hello"+name+"!");
		  System.out.println();
    }
    

    private static final void sumBetween(int lower, int upper){
        // a method that writes to the terminal the sum of all integer
        // numbers between lower and upper.
        // you should also write a message telling what the result stands for, 
        // something like "sumBetween(1,3) = 6" whenever lower is 1 and upper is 3.
        // It should then write an empty line.
		  int sum=0;
		  for(int i=lower+1;i<upper;i++)
		          sum=sum+i;
		  System.out.println("sumBetween("+lower+","+upper+")="+sum);
		  System.out.println();
    }

    private static final void additionTable(){
        // generate the addition table for single digit numbers
        // It should then write an empty line.
		  System.out.println("             Addition Table");
		  System.out.println("   |  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 |");
		  System.out.println("------------------------------------------------------");	
		  System.out.println();	  
    }

    
    private static final void multiplicationTable(){
        // generate the multiplication table for single digit numbers
        // It should then write an empty line.
    }

    
        
    private static final int max(int x, int y, int z){
        // return the maximum of three integers
        // because the compiler requires a return statement
        // for the moment it returns 0 without inspecting the arguments!
		  int max=x;
		  if(max<y)
		  max=y;
		  if(max<z)
		  max=z;
		  System.out.println();
        return max;
    }
    
    private static final int max(int x, int y, int z, int u){
        // return the maximum of four integers
        // because the compiler requires a return statement
        // for the moment it returns 0 without inspecting the arguments!
		   int max=x;
		  if(max<y)
		  max=y;
		  if(max<z)
		  max=z;
		  if(max<u)
		  max=u;
		  System.out.println();
        return max;
		  
    }
    
    private static final void intPairs(int limit){
        // A method that writes to the terminal all pairs of positive 
        // integers (a, b) such that 
        //               a < b < limit 
        // and 
        //       (a*a + b*b + 1) / (a*b) is an integer.
        // It should then write an empty line.
		  int b;
		  for(int a=1; a<limit-1; a++)
		  {
		     b=a+1;
			  while(b<limit)
			  {
			  if((a*a + b*b + 1)%(a*b)==0)
			  {System.out.print(a+"  "+b);
			  System.out.print("\n");}
			  b++;
			  }
		 }
		  System.out.println();
    }
    
    private static final void format(int howMany, int lineLength){
        // A method that writes to the terminal the positive integers 
        // upto howMany in the format
        // [1][2][3][4]
        // It should not output more than lineLength characters 
        // on any one line. 
        // It should not start a [ unless it can fit the corresponding ].
        // It should then write an empty line.
		  int j=1;
	   	while(j<=howMany)
			 {        
			{ for(int i=0;i<lineLength;i++)
			     {	System.out.print("["+j+"]");
				      j++;
				  }
			}
				System.out.print("\n");
			}

				System.out.println();
    }
    
    
    
    private static final void fibs(int n){
        // The series of fibonacci numbers is as follows:  
        // 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 ...
        // where you see that
        // the series at place 0 is 1, 
        // the series at place 1 is also 1,
        // and the series at all other places can be computed by just
        // adding the values of the series at the previous two places.
        // This method should write to the terminal the series of 
        // fibonacci numbers upto position n.
        // It should then write an empty line.
		  int[] fibs={1,1};
		  int a=1,b=1,c;
		  if(n>2)
		  {
				  for(int i=0;i<2;i++)
				  {
				   System.out.print(fibs[i]+" ");
		        }
				  for(int i=2;i<=n;i++)
				  {
				  c=a+b;
				  System.out.print(c+" ");
				  a=b;
				  b=c;
				  }
				  
		  }
		  else if(n>0)
		  {
		   for(int i=0;i<n;i++)
				  {
				   System.out.print(fibs[i]+" ");
		        }
		  }
		  System.out.println();
    }
    
    private static final void fibsSmallerThan(int n) {
        // A method to write to the terminal all fibonacci 
        // numbers smaller that n.
        // It should then write an empty line.
		  if(n>0)
		  {
					  switch(n)
					  {case 1: System.out.print("1 1");break;
					   case 2: System.out.print("1 1 2");break;
						default :
						int a=1,b=1,c=2;
						 System.out.print("1 1");
						while(c<n)				  
					       {
							  System.out.print(c+" ");
							  c=a+b;
							  a=b;
							  b=c;
							  }
		           }
			} 
		  System.out.println();
		  
     }
}

   




