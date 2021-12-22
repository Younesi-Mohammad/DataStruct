
public class HashObjectTester {

  // A test method just to see the difference between the three different
  // hash functions available in HashObject.

  public static void main( String[] args ) {

    // Create HashObjects that each use a different hash method.

    HashObject object1 = new HashObject( HashObject.FirstLetterHash ) ;
    HashObject object2 = new HashObject( HashObject.FirstWordAsHash ) ;
    HashObject object3 = new HashObject( HashObject.StringHashCode ) ;

    // Load up the objects with sample strings

    object1.setString( "maple" );
    object2.setString( "42 test string" );
    object3.setString( "maple" );

    // Show what the hash values are

    int value1 = object1.hashValue();
    System.out.println("First letter hash example.  String is \"" + object1.getString() + "\" hash value is " + value1 );
    System.out.println("First word as hash example.  String is \"" + object2.getString() + "\" hash value is " + object2.hashValue() );
    System.out.println("String default hash example.  String is \"" + object3.getString() + "\" hash value is " + object3.hashValue() );

  }
}

