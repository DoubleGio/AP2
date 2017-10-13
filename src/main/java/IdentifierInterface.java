/**	@elements : character
 *	@structure : linear
 *	@domain : 	all rows of characters
 *	@constructor - Identifier();
 *	<dl>
 *		<dt><b>PRE-conditie</b><dd>		-
 *		<dt><b>POST-conditie</b><dd> 	An array of characters.
 * </dl>
 **/

public interface IdentifierInterface {
	

	/**	@precondition	- The character array isn't empty.
	 *  @postcondition	- The character array is returned.
	 **/
	char[] getValues();
	
	/** @precondition	- The character array isn't empty.
	 * 	@postcondition	- The character array is returned as string.
	 */
	String getString();
	
    /** @precondition  -
     *	@postcondition - identifier-POST is empty and has been returned.
     **/
    IdentifierInterface init();
    
    /**
     * @precondition   - A character array that isn't empty.
     * @postcondition  - A hashcode: a key that corresponds with a specific character array.
     */
    @Override
    int hashCode();

}
