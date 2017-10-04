public class List<E extends Comparable> implements ListInterface<E>{

	Node firstNode,
		currentNode,
		lastNode;
	int size = 0;
	
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
    	Node n = new Node(d, currentNode, currentNode.next);
    	if (currentNode != lastNode) {
    		currentNode.next.prior = n;
    	}
		currentNode.next = n;
		currentNode = n;
    }
    
	@SuppressWarnings("unchecked")
	@Override
    public ListInterface<E> insert(E d) {
		if (this.find(d)) {
        	addExisting(d);
    	} 
		if (this.isEmpty()) {
    		Node n = new Node(d);
    		firstNode = n;
    		currentNode = n;
    		lastNode = n;
    	} else {
	    	if (firstNode.data.compareTo(d) == 1) {
	    		Node n = new Node(d, null, firstNode);
	    		currentNode = n;
	    		firstNode.prior = n;
	    		firstNode = n;
	    	} 
	    	if (lastNode.data.compareTo(d) == -1){		//WAAROM WERKT NIET BIJ 'c'.compareTo('f')???
	    		Node n = new Node(d, lastNode, null);
	    		currentNode = n;
	    		lastNode.next = n;
	    		lastNode = n;
	    	} else {
	    		currentNode = firstNode;
	    		while (currentNode != null) {
	    			if (currentNode.data.compareTo(d) == -1 && currentNode.next.data.compareTo(d) == 1) {
	    				Node n = new Node(d, currentNode, currentNode.next);
	    				if (currentNode == firstNode) {
	    					firstNode.next.prior = n;
	    					firstNode.next = n;
	    				}
	    				currentNode.next.prior = n;
	    				currentNode.next = n;
	    				currentNode = n;
	    			} else {
	    				currentNode = currentNode.next;
	    			}
	    		}
	    	}
    	}
    	size++;
        return this;
    }

    @Override
    public E retrieve() {
    	/*if (this.isEmpty()) {
    		throw new APException("blabla");
    	}*/
        return currentNode.data;
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
