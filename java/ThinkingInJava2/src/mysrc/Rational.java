class Rational{
	private int numerator, denominator;
	
	public Rational(int num, int den){
		if(den!=0){
			numerator=num;
			denominator=den;
		}
	    else 
	    	System.out.println("ERROR INPUT");
	}
	public String toString(){
		if(numerator==0)
			return "0";
		else
			return numerator+"/"+denominator;
	}
	public static final Rational plus(Rational a, Rational b){
		
		Rational sum=new Rational(a.numerator*b.denominator+b.numerator+
				a.denominator,a.denominator*b.denominator);
		return sum;
	}
	public static final Rational multiply(Rational a, Rational b){
		Rational mul=new Rational(a.numerator*b.numerator,
				a.denominator*b.denominator);
		return mul;
	}
	public static final Rational divide(Rational a, Rational b){
		int temp=b.numerator;
		b.numerator=b.denominator;
		b.denominator=temp;
		return(multiply(a,b));
	}
	public static void main(String [] args){
		Rational a=new Rational(2,4);
		Rational b=new Rational(3,7);
		Rational c=plus(a,b);
		System.out.println(c);
	}
	
}