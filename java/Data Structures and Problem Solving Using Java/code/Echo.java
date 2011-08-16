public class Echo
{
    public static void main( String [ ] args )
    {
		if(args.length == 0) {
            System.out.println( "No arguments to echo" );
		} else {
			for( int i = 0; i < args.length; i++ )
				System.out.print( args[ i ] + " " );
		}
		System.out.println();
		System.out.println("*******************");


        for( int i = 0; i < args.length - 1; i++ )
            System.out.print( args[ i ] + " " );
        if( args.length != 0 )
            System.out.println( args[ args.length - 1 ] );
        else
            System.out.println( "No arguments to echo" );
    }
}
