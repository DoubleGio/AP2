/**	@elements : objects of type E
 *	@structure : unknown
 *	@domain : The given set of elements E
 *	@constructor - Set();
 *	<dl>
 *		<dt><b>PRE-condition</b><dd>		-
 *		<dt><b>POST-condition</b><dd> 	The new Set-object is an empty set.
 * </dl>
 **/

public interface SetInterface<E extends Comparable<E>> {
	
	/** @precondition  -
     *	@postcondition - set-POST is empty and has been returned.
     **/
    SetInterface<E> init();
	
    /**
     * @precondition  -
     * @postcondition The element E has been added at the end of the Set if it's not already there.
     */
    void add(E e);

    /**
     *
     * @precondition  The set is not empty.
     * @postcondition The specified element has been removed. The size of the Set has been reduced by 1.
     */
    void remove(E e);

    /**
     *
     * @param e The element that has to be retrieved.
     * @precondition  The set is not empty.
     * @postcondition A boolean specifying whether the element is present in the list.
     */
    boolean find(E e);

    /**
     * @precondition  -
     * @postcondition The number of elements in the list has been returned.
     */
    int size();
	
    /**
     * @precondition  -
     * @postcondition A boolean specifying whether the set is empty.
     */
    boolean isEmpty();
    
    /**
     * @precondition  -
     * @postcondition A deep copy of the Set has been returned.
     */
    
    /**
     * @precondition  -
     * @postcondition The union of 2 sets has been returned, or an error is has been thrown.
     */
    SetInterface<E> union(Set<E> s);
    
    /**
     * @precondition  -
     * @postcondition The intersection of 2 sets has been returned, or an error is has been thrown.
     */
    SetInterface<E> intersection(Set<E> s);
    
    /**
     * @precondition  -
     * @postcondition The complement of 2 sets has been returned, or an error is has been thrown.
     */
    SetInterface<E> complement(Set<E> s);
    
    /**
     * @precondition  -
     * @postcondition The symmetric difference of 2 sets has been returned, or an error is has been thrown.
     */
    SetInterface<E> symmetricDifference(Set<E> s);

	/**
	 * @precondition  -
	 * @postcondition All elements in the list have been returned as single string with spaces in between the elements.
	 */
	String print();
}
