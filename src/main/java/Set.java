public class Set<E extends Comparable<E>> implements SetInterface<E> {
		
		private List<E> list;
		
		Set() {
			list = new List<E>();
		}
		
		Set(List<E> l) {
			list = removeDuplicates(l);
		}
		
		private Set(Set<E> s) {
			this.list = s.list;
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
	    	return new Set<E>(this);
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
	    	if (s.isEmpty()) {
	    		return s.copy();
	    	}
	    	s.list.goToFirst();
	    	if (list.find(s.list.retrieve())) {
    			intersectionSet.list.insert(s.list.retrieve());
    		}
	    	while (s.list.goToNext()) {
	    		if (list.find(s.list.retrieve())) {
	    			intersectionSet.list.insert(s.list.retrieve());
	    		}
	    		s.list.goToNext();
	    	}
	    	return intersectionSet;
	    }
	    
	    
	    public Set<E> complement(Set<E> s){
	    	Set<E> complementSet = new Set<E>();
	    	if (this.isEmpty()) {
	    		return s.copy();
	    	}
	    	if (s.isEmpty()) {
	    		return this.copy();
	    	}
	    	if (this.isEmpty() && s.isEmpty()) {
	    		return this.copy();
	    	}
	    	list.goToFirst();
	    	if (!s.list.find(list.retrieve())) {
    			complementSet.list.insert(list.retrieve());
    		}
	    	while (list.goToNext()) {
	    		if (!s.list.find(list.retrieve())) {
	    			complementSet.list.insert(list.retrieve());
	    		}
	    		list.goToNext();
	    	}
	    	return complementSet; 
	    }
	    
	   
	    public Set<E> symmetricDifference(Set<E> s){
	    	Set<E> result = new Set<E>();
	    	list.goToFirst();
	    	if (!s.find(list.retrieve())) {
	    		result.add(list.retrieve());
	    	}
	    	while (list.goToNext()) {
	    		if (!s.find(list.retrieve())) {
		    		result.add(list.retrieve());
		    	}
	    	}
	    	
	    	s.list.goToFirst();
	    	if (!list.find(s.list.retrieve())) {
	    		result.add(s.list.retrieve());
	    	}
	    	while (s.list.goToNext()) {
	    		if (!list.find(s.list.retrieve())) {
		    		result.add(s.list.retrieve());
		    	}
	    	}
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
