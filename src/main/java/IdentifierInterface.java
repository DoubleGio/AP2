/**	@elements : key
 *	@structure : linear
 *	@domain : 	all rows of keys
 *	@constructor - Identifier(char[] input);
 *	<dl>
 *		<dt><b>PRE-conditie</b><dd>		An array of characters.
 *		<dt><b>POST-conditie</b><dd> 	A key.
 * </dl>
 **/

/**	@precondition	- A key has already been made out of a character array.
 *  @postcondition	- A character array made out of the key has been returned.
 **/

public interface IdentifierInterface {

	char[] getValues();
	
    /**	@precondition	- 
     *  @postcondition	
     **/
	//boolean isBigInteger(); 

}
