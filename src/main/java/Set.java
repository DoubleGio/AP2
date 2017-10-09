

public class Set<E extends Comparable> implements SetInterface<E> {
		
		List list = new List();
	    
	    public Set<E> init(){
	    	return null;
	    }
		
	    
	    public void add(E e){
	    	list.insert(e);
	    }

	    
	    public void remove(E e){
	    	list.find(e);
	    	list.remove();
	    }

	    
	    public void set(E e, E d){
	    	remove(e);
	    	add(d);
	    }

	    
	    public boolean find(E e){
	    	return list.find(e);
	    }

	    
	    public int size(){
	    	return list.size;
	    }
		
	    
	    public boolean isEmpty(){
	    	return isEmpty();
	    }
	    
	   
	    public Set union(Set s){
	    	Set unionSet = new Set();
	    	unionSet.list = (List) list.copy();
	    	s.list.goToFirst();
	    	while (s.list.currentNode != s.list.lastNode) {
	    		unionSet.list.insert(s.list.retrieve());
	    		s.list.goToNext();
	    	}
	    	return unionSet;
	    }
	    
	   
	    public Set intersection(Set s){
	    	Set intersectionSet = new Set();
	    	s.list.goToFirst();
	    	while (s.list.currentNode != s.list.lastNode) {
	    		if (list.find(s.list.retrieve())) {
	    			intersectionSet.list.insert(s.list.retrieve());
	    		}
	    		s.list.goToNext();
	    	}
	    	return intersectionSet;
	    }
	    
	    
	    public Set complement(Set s){
	    	Set complementSet = new Set();
	    	list.goToFirst();
	    	while (list.currentNode != list.lastNode) {
	    		if (!s.list.find(list.retrieve())) {
	    			complementSet.list.insert(list.retrieve());
	    		}
	    		list.goToNext();
	    	}
	    	return complementSet; 
	    }
	    
	   
	    public Set symmetricDifference(Set s){
	    	Set sDSet = union(s);
	    	Set intersectionSet = intersection(s);
	    	intersectionSet.list.goToFirst();
	    	while (intersectionSet.list.currentNode != intersectionSet.list.lastNode) {
	    		sDSet.list.find(intersectionSet.list.retrieve());
	    		sDSet.list.remove();
	    	}
	    	return sDSet;
	    }
	}
