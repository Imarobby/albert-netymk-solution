public class Complex{

    private double re, im;

    /**
       to create a Complex with value 0 + i0
     */
    public Complex(){
        re = 0;
        im = 0;
    }

    /**
       to create a Complex with value rp + i ip  
     */
    public Complex(double rp, double ip){
        re = rp; im = ip;
    }

    /**
       For a  Complex z, 
       
       z.real() is its real part
     */
    public double real(){return re;}

    /**
       For a Complex z,

       z.imag() is its imaginary part
     */
    public double imag(){return im;}

    /**
       For a Complex z,

       z.toString() is its string representation.
    */
    public String toString(){
        if(im == 0) return ""+re;
        else if (re == 0) return ""+im+"i";
        else return ""+re+" + i"+im;
    }


    private static double sq(double a){
        return a*a;
    }
    
    /**
       Complex.plus(z1,z2) is the Complex that results from adding z1 and z2.
     */
    public static final Complex plus(Complex a, Complex b){
        return new Complex(a.real()+b.real(), a.imag()+b.imag());
    }

    /**
       Complex.fromDouble(x) is a Complex with value x + i0.
     */
    public static final Complex fromDouble(double a){
        return new Complex(a,0);
    }

    /**
       Complex.i is the Complex with value 0 + i.
     */
    public static final Complex i = new Complex(0,1);
    
    /**
       Complex.minus(z1,z2) is the Complex that results from substracting z2 from z1.       
     */
    public static final Complex minus(Complex a, Complex b){
        return new Complex(a.real()-b.real(), a.imag()-b.imag());
    }

    /**
       Complex.times(z1,z2) is the Complex that results from multiplying z1 and z2.
     */
    public static final Complex times(Complex a, Complex b){
        return new Complex(a.real()*b.real()-a.imag()*b.imag(),
                           a.real()*b.imag()+a.imag()*b.real());
    }


    private static final Complex distribute(Complex a, double x){
        return new Complex(a.real()/x, a.imag()/x);
    }

    /**
       Complex.conjugate(z) is, for z = a + ib, the Complex with value a - ib.
    */
    public static final Complex conjugate(Complex a){
        return new Complex(a.real(),-a.imag());
    }

   
    /**
       Complex.divide(z1,z2) is the Complex that results from dividing z1 by z2.
     */
    public static final Complex divide(Complex a, Complex b){
        // remember: it throws an exception in case the double we distribute is 0.
        return distribute(times(a,conjugate(b)),
                          sq(b.real())+sq(b.imag()));
    }
    
    /**
       Complex.invert(z) is the Complex 1/z.
       
     */
    public static final Complex invert(Complex a){
        return divide(fromDouble(1),a);
    }

    public static final double modulus(Complex a){
        return Math.sqrt(sq(a.real())+sq(a.imag()));
    }
    
    public static final double argument(Complex a){
        return Math.atan(a.imag()/a.real());
    }

    /**
       A method with which to test your operations.
     */
    public static void main(String [] args){
        Complex a = new Complex(1,1);
        Complex b = new Complex(5,2);
        Complex c = divide(a,b);
        System.out.println(i);
        System.out.println(times(i,i));
        System.out.println(c);
    }
}

