public class List<E extends Comparable<E>> implements ListInterface<E>{
 
	private Node firstNode,
		currentNode,
		lastNode;
	private int size;
	
    private class Node {

        E data;
        Node prior,
                next;

        public Node(E d) {
            this(d, null, null);
        }

        public Node(E data, Node prior, Node next) {
            this.data = data == null ? null : data;
            this.prior = prior;
            this.next = next;
        }
    }
    
    List() {
    	firstNode = null;
    	currentNode = null;
    	lastNode = null;
    	size = 0;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    @Override
    public ListInterface<E> init() {
    	if (firstNode != null) {
    		firstNode = null;
    		currentNode = null;
    		lastNode = null;
    		size = 0;
    	}
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    private void addExisting(E d) {
    	if (currentNode == lastNode) {
    		Node n = new Node(d, currentNode, null);
    		currentNode.next = n;
    		if (firstNode == lastNode) {
    			firstNode.next = n;
    		}
    		currentNode = n;
    		lastNode = n;
    	} else {
	    	Node n = new Node(d, currentNode, currentNode.next);
	    	if (currentNode != lastNode) {
	    		currentNode.next.prior = n;
	    	}
			currentNode.next = n;
			currentNode = n;
    	}
    }
    
	private void loopAndInsert(E d) {
    	currentNode = firstNode;
    	if (currentNode.data.compareTo(d) >= 1) {
    		Node n = new Node(d, null, currentNode);
			firstNode.prior = n;
			firstNode = n;
			currentNode = n;
    	} else {
			while (goToNext()) {
				if (currentNode.data.compareTo(d) >= 1) {	//if d is smaller than currentNode
					Node n = new Node(d, currentNode.prior, currentNode);
					currentNode.prior.next = n;
					if (currentNode.prior == firstNode) {
						firstNode.next = n;
					}
					if (currentNode == lastNode) {
						lastNode.prior = n;
					}
					currentNode.prior = n;
				}
			}
    	}
    }
    
    @Override
    public ListInterface<E> insert(E d) {
		if (this.find(d)) {
        	addExisting(d);
    	} else if (this.isEmpty()) {
    		Node n = new Node(d);
    		firstNode = n;
    		currentNode = n;
    		lastNode = n;
    	} else {
    		if (lastNode.data.compareTo(d) <= -1) {		//if d is larger than lastNode
	    		Node n = new Node(d, lastNode, null);
	    		currentNode = n;
	    		lastNode.next = n;
	    		lastNode = n;
	    	} else {
	    		loopAndInsert(d);
	    	}
    	}
    	size++;
        return this;
    }

    @Override
    public E retrieve() {
        return currentNode.data;
    }
    
    public E retrievePrior() {
    	return currentNode.prior.data;
    }

    @Override
    public ListInterface<E> remove() {
    	if (this.size == 1) {
    		return init();
    	} 
    	if (currentNode == lastNode) {
    		currentNode.prior.next = null;
    		currentNode = currentNode.prior;
    		lastNode = currentNode;
    	} else {
    		currentNode.prior.next = currentNode.next;
    		currentNode.next.prior = currentNode.prior;
    		currentNode = currentNode.next;
    	}
    	size--;
        return this;
    }

    @Override
    public boolean find(E d) {
    	if (this.isEmpty()) {
    		return false;
    	}
    	if (firstNode.data.compareTo(d) == 0) {
    		return true;
    	}
    	currentNode = firstNode;
    	while (goToNext()) {
    		if (currentNode.data.compareTo(d) == 0) {
    			return true;
    		}
    	}
    	if (firstNode.data.compareTo(d) >= 1) {
    		currentNode = firstNode;
    	} else {
    		currentNode = firstNode;
    		while (currentNode.data.compareTo(d) <= -1) {
    			if (this.goToNext() && currentNode.data.compareTo(d) >= 1) {
    				currentNode = currentNode.prior;
    				return false;
    			}
    			if (currentNode == lastNode) {
    				return false;
    			}
    		}
    	}
        return false;
    }

    @Override
    public boolean goToFirst() {
    	if (this.isEmpty()) {
    		return false;
    	} else {
    		currentNode = firstNode;
    		return true;
    	}
    }

    @Override
    public boolean goToLast() {
    	if (this.isEmpty()) {
    		return false;
    	} else {
    		currentNode = lastNode;
    		return true;
    	}
    }

    @Override
    public boolean goToNext() {
    	if (this.isEmpty() || currentNode == lastNode) {
    		return false;
    	} else {
    		currentNode = currentNode.next;
    		return true;
    	}
    }

    @Override
    public boolean goToPrevious() {
    	if (this.isEmpty() || currentNode == firstNode) {
    		return false;
    	} else {
    		currentNode = currentNode.prior;
    		return true;
    	}
    }
    
    @Override
    public ListInterface<E> copy() {
        return this;
    }
}
