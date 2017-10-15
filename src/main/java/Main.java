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
	private HashMap<Identifier, Set<Integer>> hmap;
	
	private void skipSpaces(Scanner in) {
		while(nextCharIs(in, ' ')) {
			readChar(in);
		}
	}
	
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
	
	private void checkFactor(Scanner in) throws APException {
		skipSpaces(in);
		if (nextCharIs(in, '{')) {	//Set
			readChar(in);
			Set<Integer> s = readSet(in);
			/*if (print) {
				printSet(s);
			}*/
		} else if (nextCharIs(in, '(')) {	//Complex_factor, i.e.: (A+B)
			readChar(in);
			checkExpression(in, false);
			skipSpaces(in);
			if (!nextCharIs(in, ')')) {
				throw new APException("No closing brackets/n");
			}
		} else if (nextCharIsLetter(in)) {	//Identifier
			char[] name = new char[STANDARD_LENGTH];
			int index = -1;
			while(nextCharIsLetter(in) | nextCharIsDigit(in)) {
				index++;
				name[index] = readChar(in);
			}
			Identifier id = new Identifier(name);
			
		} else {
			throw new APException("Wrong print syntax/n");
		}
	}
	
	private void checkExpression(Scanner in, boolean print) throws APException {
		checkTerm(in);
		skipSpaces(in);
		if (nextCharIs(in, UNION)) {
			readChar(in);
			checkTerm(in);
			//TODO: union method
		} else if (nextCharIs(in,  COMPLEMENT)) {
			readChar(in);
			checkTerm(in);
			//TODO: complement method
		} else if (nextCharIs(in, SYM_DIFFERENCE)) {
			readChar(in);
			checkTerm(in);
			//TODO: sym difference method
		}
		if (print) {
			//TODO: print a thing
		}
	}
	
	private void checkTerm(Scanner in) throws APException {
		checkFactor(in);
		skipSpaces(in);
		if (nextCharIs(in, INTERSECTION)) {
			readChar(in);
			skipSpaces(in);
			checkFactor(in);
			//TODO: intersection method
		}
	}
	
	private void printSet(Set<Integer> s) {
		
	}
	
	private void printIdentifier(Identifier id) {
		
	}
	
	private void checkForSpacesBetweenNumber(Scanner in) throws APException {
		if (nextCharIs(in, ' ')) {
			readChar(in);
			if (nextCharIsDigit(in)) {
				throw new APException("Space in between digits");
			} else {
				checkForSpacesBetweenNumber(in);
			}
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
				checkForSpacesBetweenNumber(in);
			} else if (nextCharIs(in, ',')) {
				result.add(Integer.parseInt(new String(digits)));
			} else if (nextCharIs(in, ' ')) {
				readChar(in);
			} else {
				throw new APException("Incorrect character in set");
			}
		}
		return result;
	}
	
    private void start() throws APException {
        // Create a scanner on System.in
        Scanner in = new Scanner(System.in);
        hmap = new HashMap<Identifier, Set<Integer>>();
        
        // While there is input, read line and parse it.
        while (in.hasNextLine()) {
	        String line = in.nextLine();
	        
	        Scanner lineScanner = new Scanner(line);
			in.useDelimiter("");
			if (nextCharIs(lineScanner, COMMENT)) {
				//do nothing
			} else if (nextCharIs(lineScanner, PRINT_STATEMENT)) {
				readChar(lineScanner);
				checkExpression(lineScanner, true);
			} else if (nextCharIsLetter(lineScanner)) {
				checkFactor(lineScanner);
				skipSpaces(lineScanner);
				if (!nextCharIs(lineScanner, ASSIGNMENT)) {
					throw new APException("'=' expected in assignment");
				} else {
					readChar(lineScanner);
					skipSpaces(lineScanner);
					checkExpression(lineScanner, false);
					//TODO: assignment method
				}
			} else {
				throw new APException("Empty line/n");
			}
	    	//HashMap<Identifier, Set<Integer>> hmap = new HashMap<Identifier, Set<Integer>>();
        }
        in.close();
    }

    public static void main(String[] argv) throws APException {
        new Main().start();
    }
}
