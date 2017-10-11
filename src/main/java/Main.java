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
	
	/*private boolean nextCharIs(char c, char compareTo) {
		return (c == compareTo);
	}
	
	private boolean nextCharIsDigit(char c) {
		return (c >= 0 && c <= 9);
	}
	
	private boolean nextCharIsLetter(char c) {
		return (c >= 'A' && c <='Z');
	}*/
	
    private void start() {
        // Create a scanner on System.in
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        
        Scanner lineScanner = new Scanner(line);
		in.useDelimiter("");
		if (nextCharIs(lineScanner, COMMENT)) {
			
		} else if (nextCharIs(lineScanner, PRINT_STATEMENT)) {
			
		} else if (nextCharIsLetter(lineScanner)) {
			
		}
        // While there is input, read line and parse it.
    	//HashMap<Identifier, Set<Integer>> hmap = new HashMap<Identifier, Set<Integer>>();
    	
    }

    public static void main(String[] argv) {
        new Main().start();
    }
}
