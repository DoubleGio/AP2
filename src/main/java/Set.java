public class Set<E extends Comparable<E>> implements SetInterface<E> {
		
		private List<E> list;
		
		Set() {
			list = new List<E>();
		}
		
		Set(List<E> l) {
			list = removeDuplicates(l);
		}
	    
		private List<E> removeDuplicates(List<E> l) {
			l.goToFirst();
			while (l.goToNext()) {
				if (l.retrieve().equals(l.retrievePrior()) ) {
					l.goToPrevious();
					l.remove();
				}
			}
			return l;
		}
		
	    public Set<E> init(){
	    	while (!isEmpty()) {
	    		list.remove();
	    	}
	    	return this;
	    }
		
	    public void add(E e){
	    	if (!find(e)) {
	    		list.insert(e);
	    	}
	    }
	    
	    public void remove(E e){
	    	list.find(e);
	    	list.remove();
	    }
	    
	    public boolean find(E e){
	    	return list.find(e);
	    }
	    
	    public int size(){
	    	return list.size();
	    }		
	    
	    public boolean isEmpty(){
	    	return size() == 0;
	    }
	    
	    public Set<E> copy() {
	    	Set<E> copySet = new Set<E>();
	    	if (this.isEmpty()) {
	    		return copySet;
	    	}
	    	this.list.goToFirst();
	    	copySet.add(this.list.retrieve());
	    	while (this.list.goToNext()) {
	    		copySet.add(this.list.retrieve());
	    	}
	    	return copySet;
	    }	
	   
	    public Set<E> union(Set<E> s){
	    	Set<E> unionSet = this.copy();
	    	Set<E> sCopy = s.copy();
	    	if (s.isEmpty()) {
	    		return copy();
	    	}
	    	if (this.isEmpty()) {
	    		return s.copy();
	    	}
	    	if (s.isEmpty() && this.isEmpty()) {
	    		return copy();
	    	}
	    	sCopy.list.goToFirst();
	    	unionSet.list.insert(sCopy.list.retrieve());
	    	while (sCopy.list.goToNext()) {
	    		unionSet.list.insert(sCopy.list.retrieve());
	    	}
	    	unionSet.list = removeDuplicates(unionSet.list);
	    	return unionSet;
	    }	    
	   
	    public Set<E> intersection(Set<E> s){
	    	Set<E> intersectionSet = new Set<E>();
	    	Set<E> tCopy = this.copy();
	    	Set<E> sCopy = s.copy();
	    	if (tCopy.isEmpty()) {
	    		return tCopy;
	    	}
	    	if (sCopy.isEmpty()) {
	    		return sCopy;
	    	}
	    	sCopy.list.goToFirst();
	    	if (tCopy.list.find(sCopy.list.retrieve())) {
    			intersectionSet.list.insert(sCopy.list.retrieve());
    		}
	    	while (sCopy.list.goToNext()) {
	    		if (tCopy.list.find(sCopy.list.retrieve())) {
	    			intersectionSet.list.insert(sCopy.list.retrieve());
	    		}
	    	}
	    	return intersectionSet;
	    }	    
	    
	    public Set<E> complement(Set<E> s){
	    	Set<E> complementSet = this.copy();
	    	Set<E> sCopy = s.copy();
	    	if (this.isEmpty() || s.isEmpty()) {
	    		return complementSet;
	    	}
	    	sCopy.list.goToFirst();
	    	if (complementSet.find(sCopy.list.retrieve())) {
	    		complementSet.list.remove();
	    	}
	    	while(sCopy.list.goToNext()) {
	    		if (complementSet.find(sCopy.list.retrieve())) {
		    		complementSet.list.remove();
		    	}
	    	}
	    	return complementSet; 
	    }	    
	   
	    public Set<E> symmetricDifference(Set<E> s){
	    	Set<E> tCopy = this.copy();
	    	Set<E> sCopy = s.copy();
	    	if (this.isEmpty()) {
	    		return sCopy;
	    	}
	    	if (s.isEmpty()) {
	    		return tCopy;
	    	}
	    	Set<E> complement1 = tCopy.complement(sCopy);
	    	Set<E> complement2 = sCopy.complement(tCopy);
	    	Set<E> result = complement1.union(complement2);
	    	return result;
	    }
	    
	    public String print() {
	    	String result = "";
	    	list.goToFirst();
	    	if (list.size() == 1) {
	    		result += list.retrieve().toString();
	    	} else {
	    		result += list.retrieve().toString() + " ";
	    		while (list.goToNext()) {
		    		result += list.retrieve().toString() + " ";
		    	}
		    	result = result.substring(0, result.length()-1);
	    	}
	    	return result;
	    }
	}
