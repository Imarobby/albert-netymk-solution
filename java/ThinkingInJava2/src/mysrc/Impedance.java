class Impedance{
   private double freq;	
   private Complex R,L,C;
   
   public Impedance(double f){
	   freq=f;
   }	
   public void resistor(double r){
	R= new Complex(r,0);
	   
   }
   public void inductor(double l){
	L= new Complex(0,freq*l);
	   
   }
   public void capacitor(double c){
	C=new Complex(0,-1/(freq*c));
	   
   }
   public static Complex serial(Complex a,Complex b){
	Complex sum=new Complex(); 
	sum=Complex.plus(a, b);
	return sum;
	   
   }
   public static Complex parallal(Complex a,Complex b){
	   Complex sum=new Complex();
	   Complex temp;
	   sum=Complex.times(a,b);
	   temp=Complex.plus(a,b);
	   sum=Complex.divide(sum, temp);
	   return sum;
	   
   }
   public static void main(String[] args){
	   Complex temp=new Complex();
	   Impedance circue=new Impedance(1);
	   circue.resistor(10);
	   circue.capacitor(0.1);
	   circue.inductor(10);
	   temp=Impedance.serial(circue.R,circue.L);
	   System.out.println(temp);
	   System.out.println(circue.C);
	   temp=Impedance.parallal(temp,circue.C);
	   System.out.println(temp);
   }
}