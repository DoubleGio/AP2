import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Main {

	private static final char COMMENT = '/',
			PRINT_STATEMENT = '?',
			ASSIGNMENT = '=',
			UNION = '+',
			INTERSECTION = '*',
			COMPLEMENT = '-',
			SYM_DIFFERENCE = '|';
	private static final int STANDARD_LENGTH = 100;
	
	private char readChar(Scanner in) {
		return in.next().charAt(0);
	}
	
	private boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c+""));
	}
	
	private boolean nextCharIsDigit(Scanner in) {
		return in.hasNext("[0-9]");
	}
	
	private boolean nextCharIsLetter(Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}
	
	private void print(Scanner in) throws APException {
		readChar(in);
		if (nextCharIs(in, ' ')) {
			readChar(in);
		} else if (nextCharIs(in, '{')) {
			readChar(in);
			Set<Integer> s = readSet(in);
		} else if (nextCharIs(in, '(')) {
			
		} else if (nextCharIsLetter(in)) {
			
		} else {
			throw new APException("Wrong print syntax/n");
		}
	}
	
	private Set<Integer> readSet(Scanner in) throws APException {
		Set<Integer> result = new Set<Integer>();
		char[] digits = new char[STANDARD_LENGTH];
		int index = -1;
		while (!nextCharIs(in, '}')) {
			if (nextCharIsDigit(in)) {
				index++;
				if (index == digits.length) {
					char[] tempStack = new char[digits.length * 2];
		    			System.arraycopy(digits, 0, tempStack, 0, digits.length);
		    			digits = tempStack;
				}
				digits[index] = readChar(in);
			} else if (nextCharIs(in, ',')) {
				result.add(Integer.parseInt(new String(digits)));
			} else if (nextCharIs(in, ' ')){
				readChar(in);
				if (nextCharIsDigit(in)) {
					throw new APException("Space in between digits");
				}
			} else {
				throw new APException("Incorrect character in set");
			}
		}
		return result;
	}
	
	
    private void start() throws APException {
        // Create a scanner on System.in
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
	        String line = in.nextLine();
	        
	        Scanner lineScanner = new Scanner(line);
			in.useDelimiter("");
			if (nextCharIs(lineScanner, COMMENT)) {
				
			} else if (nextCharIs(lineScanner, PRINT_STATEMENT)) {
				print(lineScanner);
				
			} else if (nextCharIsLetter(lineScanner)) {
				
			}
	        // While there is input, read line and parse it.
	    	//HashMap<Identifier, Set<Integer>> hmap = new HashMap<Identifier, Set<Integer>>();
        }
        in.close();
    }

    public static void main(String[] argv) throws APException {
        new Main().start();
    }
}
