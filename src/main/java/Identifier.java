
public class Identifier implements IdentifierInterface {
	
	char[] name;
	
	Identifier(char[] n) {
		name = n;
	}
	
	Identifier(String s) {
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
}
