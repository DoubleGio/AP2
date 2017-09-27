/**	@elements : objects of type E
 *	@structure : linear
 *	@domain : The given set of elements E
 *	@constructor - Set();
 *	<dl>
 *		<dt><b>PRE-conditie</b><dd>		-
 *		<dt><b>POST-conditie</b><dd> 	The new Set-object is an empty set.
 * </dl>
 **/

public interface SetInterface<E> {
	
    /** @precondition  -
     *	@postcondition - set-POST is empty and has been returned.
     **/
    SetInterface<E> init();
	
	/**
	 * @param index The index of the element in the set that gets checked.
	 * @pre	-
	 * @post A boolean is returned signifying whether said element is a BigInteger or not.
	 */
	boolean isBigInteger(int index);
	
    /**
     * @pre -
     * @post The element E has been added at the end of the Set, preserving the previous order.
     */
    void add(E e);

    /**
     *
     * @pre	index < size() and index >= 0
     * @post The element at location 'index' has been removed, preserving the previous order. The size of the Set has been reduced by 1.
     */
    void remove(int index);

    /**
     *
     * @param index The index to be set
     * @param token The value to set the element at location index to.
     * @pre   index < size();
     * @post  The element at location 'index' has the value 'e', preserving the previous order.
     */
    void set(int index, E e);

    /**
     *
     * @param index The index of the element to be returned.
     * @return The element and index index.
     * @pre index < size();
     * @post The element at index 'index' has been returned.
     */
    E get(int index);

    /**
     * @pre -
     * @post The number of elements in the list has been returned.
     */
    int size();
	
}
