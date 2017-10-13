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
     * @pre -
     * @post The element E has been added at the end of the Set.
     */
    void add(E e);

    /**
     *
     * @pre	The set is not empty.
     * @post The specified element has been removed. The size of the Set has been reduced by 1.
     */
    void remove(E e);

    /**
     *
     * @param e The element in the set that has to be replaced.
     * @param d The element that has to be placed in the set, overwriting the previous element.
     * @pre   The set is not empty.
     * @post  The element e has been overwritten by the element d.
     */
    void set(E e, E d);

    /**
     *
     * @param e The element that has to be retrieved.
     * @pre The set is not empty.
     * @post A boolean specifying whether the element is present in the list.
     */
    boolean find(E e);

    /**
     * @pre -
     * @post The number of elements in the list has been returned.
     */
    int size();
	
    /**
     * @pre -
     * @post A boolean specifying whether the set is empty.
     */
    boolean isEmpty();
    
    /**
     * 
     * @pre -
     * @post The union of 2 sets has been returned, or an error is has been thrown.
     */
    SetInterface<E> union(Set<E> s);
    
    /**
     * 
     * @pre -
     * @post The intersection of 2 sets has been returned, or an error is has been thrown.
     */
    SetInterface<E> intersection(Set<E> s);
    
    /**
     * 
     * @pre -
     * @post The complement of 2 sets has been returned, or an error is has been thrown.
     */
    SetInterface<E> complement(Set<E> s);
    
    /**
     * 
     * @pre -
     * @post The symmetric difference of 2 sets has been returned, or an error is has been thrown.
     */
    SetInterface<E> symmetricDifference(Set<E> s);
}
