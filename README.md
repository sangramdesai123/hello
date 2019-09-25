# Common repo
Just another respository to teach github


# JAVA String/StringBuffer 

### Difference Between String and StringBuffer Class in Java :


String and StringBuffer both are the classes which operate on strings. The object of  String class is of fixed length. 

The object of the StringBuffer class is growable. The basic difference between String and StringBuffer is that the object of the “String” class is immutable. 

The object of the class “StringBuffer” mutable.


| Basis for comparison | String | StringBuffer |
| --- | --- | --- |
| Length  | Length of string object is fixed | Length of stringBuffer can be increased |
| Speed  | Slower during concatenation | Fast during concatenation |
| Memory  | More memory | Less memory |
| Modification  | Object is immutable | Object is mutable |
| Storage | String Constant pool | Heap Memory |


**What is meant by immutable:** 

we cannot append,remove new string to created object.StringBuffer objects provide more functionality to the strings as compared to the class String. 

Hence, it is preferable to work with StringBuffer instead of class String.so when we want to make fixed size string use string object and when we want perform operation like append remove we use stringBuffer class object.

```
String Object:

Creating empty string object    →  String str = new String (“Hello”); 

Appending new string            →  str.concat(“World !”);

Printing string object          →  System.out.println(str);

Output		                →  Hello

StringBuffer Object:

Creating empty string object    →  String str = new String (“Hello”); 

Appending new string            →  str.append(“ World !”);

Printing string object          →  System.out.println(str);

Output		                →  Hello World !

```

### Important Note :
- Concat operation on StringBuffer is O(1 ) while for String Class it is O(n).

- Because String is immutable hence new object is created for every concat.

### Important Constructors of String class:

| Constructor | Description |
| --- | --- |
| String()  | creates an empty string |
| String(String str)  | creates a string  with the specified string. | 
| String(StringBuffer sb) | creates new  string from string buffer. |


### Important methods of String class:

| Method | Description |
| --- | --- |
| s1.concat(String s)  | Return new string by concatenating s1 and s. |
| s1.compareTo(s2)  | The value 0 if the argument is a string lexicographically equal to this string; a value less than 0 if the argument is a string lexicographically greater than this string; and a value greater than 0 if the argument is a string lexicographically less than this string. | 
| s1.equals(s2) | This method returns true if the String are equal; false otherwise |
| s1.indexOf(‘letter’)  | This method returns the index within this string of the first occurrence of the specified character or -1, if the character does not occur. |
| s1.matches(regex)  | This method tells whether or not this string matches the given regular expression. | 
| s1.split(regex) | Split string at given regex |
| charAt(int index)  | is used to return the character at the specified position. |
| s1.toLowerCase()  | Converts all of the characters in this String to lowercase using the rules of the default locale. | 
| s1.toUpperCase() | Converts all of the characters in this String to uppercase using the rules of the default locale. |
| s1.valueOf(type) | This method returns the string representation of the passed argument.more Info refere:https://www.tutorialspoint.com/java/java_string_valueof.htm |


**For more Information visit:**
  https://docs.oracle.com/javase/7/docs/api/java/lang/String.html

### Important Constructors of StringBuffer class:

| Constructor | Description |
| --- | --- |
| StringBuffer()  | creates an empty string buffer with the initial capacity of 16. |
| StringBuffer(String str)  | creates a string buffer with the specified string | 
| StringBuffer(int capacity)) | creates an empty string buffer with the  capacity as length. |


### Important methods of StringBuffer class:


| Method | Description |
| --- | --- |
| append(String s)  | is used to append the specified string with this string. The append() method is overloaded like append(char), append(boolean), append(int), append(float), append(double) etc. |
| insert(int offset, String s)  | is used to insert the specified string with this string at the specified position. The insert() method is overloaded like insert(int, char), insert(int, boolean), insert(int, int), insert(int, float), insert(int, double) etc. | 
| replace(int startIndex, int endIndex, String str) | is used to replace the string from specified startIndex and endIndex. |
| delete(int startIndex, int endIndex)  |is used to delete the string from specified startIndex and endIndex.
| reverse()  | is used to reverse the string. | 
| capacity() | is used to return the current capacity. |
| charAt(int index)  | is used to return the character at the specified position. |

**For more Information visit:**
http://edelstein.pebbles.cs.cmu.edu/jadeite/main.php?api=java6&state=class&package=java.lang&class=StringBuffer








