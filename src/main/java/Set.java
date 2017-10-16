public class Set<E extends Comparable<E>> implements SetInterface<E> {
		
		private ListInterface<E> list;
		
		Set () {
			list = new List<E>();
		}
		
		Set (List<E> l) {
			removeDuplicates(l);
			list = l;
		}
	    
		private void removeDuplicates(List<E> l) {
			l.goToFirst();
			while (l.goToNext()) {
				if (l.retrieve() == l.retrievePrior()) {
					l.goToPrevious();
					l.remove();
				}
			}
		}
		
	    public Set<E> init(){
	    	return null;
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
	    	return isEmpty();
	    }
	     
	
	   
	    public Set<E> union(Set<E> s){
	    	Set<E> unionSet = new Set<E>();
	    	unionSet.list = list.copy();
	    	s.list.goToLast();
	    	E temp = s.list.retrieve();
	    	s.list.goToFirst();
	    	while (s.list.retrieve() != temp) {
	    		unionSet.list.insert(s.list.retrieve());
	    		s.list.goToNext();
	    	}
	    	return unionSet;
	    }
	    
	   
	    public Set<E> intersection(Set<E> s){
	    	Set<E> intersectionSet = new Set<E>();
	    	s.list.goToLast();
	    	E temp = s.list.retrieve();
	    	s.list.goToFirst();
	    	while (s.list.retrieve() != temp) {
	    		if (list.find(s.list.retrieve())) {
	    			intersectionSet.list.insert(s.list.retrieve());
	    		}
	    		s.list.goToNext();
	    	}
	    	return intersectionSet;
	    }
	    
	    
	    public Set<E> complement(Set<E> s){
	    	Set<E> complementSet = new Set<E>();
	    	s.list.goToLast();
	    	E temp = s.list.retrieve();
	    	s.list.goToFirst();
	    	while (s.list.retrieve() != temp) {
	    		if (!s.list.find(list.retrieve())) {
	    			complementSet.list.insert(list.retrieve());
	    		}
	    		list.goToNext();
	    	}
	    	return complementSet; 
	    }
	    
	   
	    public Set<E> symmetricDifference(Set<E> s){
	    	Set<E> sDSet = union(s);
	    	Set<E> intersectionSet = intersection(s);
	    	intersectionSet.list.goToLast();
	    	E temp = intersectionSet.list.retrieve();
	    	intersectionSet.list.goToFirst();
	    	while (intersectionSet.list.retrieve() != temp) {
	    		sDSet.list.find(intersectionSet.list.retrieve());
	    		sDSet.list.remove();
	    	}
	    	return sDSet;
	    }
	    
	    public String print() {
	    	String result = "";
	    	list.goToFirst();
	    	result += list.retrieve().toString();
	    	while (list.goToNext()) {
	    		if (!list.goToNext()) {
	    			result += list.retrieve().toString();
	    		} else {
	    			result += list.retrieve().toString() + " ";
	    		}
	    	}
	    	return result;
	    }
	    
	}
