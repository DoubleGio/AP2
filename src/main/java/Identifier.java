import java.util.Arrays;

public class Identifier implements IdentifierInterface {
	
	private char[] name;
	
	Identifier(char[] n) throws APException {
		if (n.length == 0) {
			throw new APException("Identifier cannot be empty/n");
		}
		name = n;
	}
	
	Identifier(String s) throws APException {
		if (s.isEmpty()) {
			throw new APException("Identifier cannot be empty/n");
		}
		name = s.toCharArray();
	}

	@Override
	public char[] getValues() {
		return name;
	}
	
	@Override
	public String getString() {
		return new String (name);
	}

	@Override
	public IdentifierInterface init() {
		name = null;
		return this;
	}

	@Override
	public int hashCode() {
		int result = 0;
		for (int i = 0; i < name.length; i++) {
			result = result + (int) name[i] * 4;
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Identifier)) {
			return false;
		}
		
		Identifier i = (Identifier) o;
		return Arrays.equals(name, i.name);
	}
}
