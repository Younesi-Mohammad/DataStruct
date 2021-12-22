
// A container object to store data in our hash table.

class HashObject {
  // Define different types of hash functions that could be used by the object

  public static final int FirstLetterHash = 1;
  public static final int FirstWordAsHash = 2;
  public static final int StringHashCode = 3;

  // Internal constant to know when the hash function options end;

  private static final int LastHashValue = StringHashCode;

  // The data actually stored by the class

  private String storedString;
  private int whichHashFunction = StringHashCode;

  // Constructor that defines which hash function to use in the object

  public HashObject ( int hashType ) {
    storedString = null;

    // Accept the type of hash function to use if it's within the valid range.

    if ((hashType >= FirstLetterHash) && (hashType <= LastHashValue)) {
      whichHashFunction = hashType;
    }
  }

  // Constructor that defines which hash function to use int the object.  Also
  // initialize the object with a non-null string.

  public HashObject ( String value, int hashType ) {
    // Don't store null strings in this object

    if (value != null) {
      storedString = value;

    // Accept the type of hash function to use if it's within the valid range.

      if ((hashType >= FirstLetterHash) && (hashType <= LastHashValue)) {
        whichHashFunction = hashType;
      }
    }
  }

  // Update the object with a new non-null string to store.  Return true if 
  // the string was stored.  Return false if there is any error.

  public boolean setString( String value ) {
    boolean stringSet = false;

    // Only store non-null strings.

    if (value != null) {
      storedString = value;
      stringSet = true;
    }

    return stringSet;
  }

  // Retrieve the string stored in the object

  public String getString( ) {
    return storedString;
  }

  // Use the stored string to generate some hash value for this object.

  public int hashValue( ) {
    int theHash = 0;

    // Invoke the hash function dedfined as the object was created.

    switch( whichHashFunction ) {
      case FirstLetterHash : {
        // Assume that the string is lower case alphabetic string.
        // The position of the first letter in the string defines the hash
        // value.  So, we only expect hash values to be 0 to 25.
        // As an example, string "ape" will have hash value 0 and "cat" will have hash value 2.

        if (storedString.length() > 0) {
          theHash = storedString.charAt(0) - 'a';
        }
        break;
      }

      case FirstWordAsHash : {
        // Use the string as if it starts with a number and then contains more characters
        // (after a space).  The leading number is then the value of the hash value
        // returned for this object.  This variant is useful for testing as it lets
        // the caller tightly control which hash table entry each string maps to.
        // For example, string "45 hello" will have hash value 45

        String[] divided;

        // Split the string to get that first "word" as a number.

        divided = storedString.split(" ");
        if (divided.length > 0) {
          try {
            // Convert the first word to its integer value.

            theHash = Integer.parseInt( divided[0] );
          } catch (NumberFormatException e) {
            theHash = 0;
          }
        }
        break;
      }

      default : {
        // Use the built-in string hashing function

        theHash = storedString.hashCode();
        break;
      }
    }

    return theHash;
  }
}

