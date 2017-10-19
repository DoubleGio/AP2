import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Pattern;
public class Main {

	private static final char COMMENT = '/',
			PRINT_STATEMENT = '?',
			ASSIGNMENT = '=',
			UNION = '+',
			INTERSECTION = '*',
			COMPLEMENT = '-',
			SYM_DIFFERENCE = '|';
	private int lineNumber = 0;
	private HashMap<Identifier, Set<BigInteger>> hmap;
	
	
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
	
	private Set<BigInteger> checkFactor(Scanner in) throws APException {
		Set<BigInteger> temp = new Set<BigInteger>();
		skipSpaces(in);
		if (nextCharIs(in, '{')) {						//Set
			readChar(in);
			temp = readSet(in);
		} else if (nextCharIs(in, '(')) {				//Complex_factor, i.e.: (A+B)
			readChar(in);
			checkExpression(in);
			skipSpaces(in);
			if (!nextCharIs(in, ')')) {
				throw new APException("No closing brackets\n");
			}
		} else if (nextCharIsLetter(in)) {				//Identifier
			Identifier id = readIdentifier(in);
			if (hmap.containsKey("Aap")) {
				System.out.println("yes");
			}
			temp = hmap.get(id);
		} else {
			throw new APException("Wrong print syntax\n");
		}
		return temp;
	}
	
	private Set<BigInteger> checkExpression(Scanner in) throws APException {
		Set<BigInteger> temp = checkTerm(in);
		skipSpaces(in);
		if (nextCharIs(in, UNION)) {
			readChar(in);
			skipSpaces(in);
			temp.union(checkTerm(in));
		} else if (nextCharIs(in,  COMPLEMENT)) {
			readChar(in);
			skipSpaces(in);
			temp.complement(checkTerm(in));
		} else if (nextCharIs(in, SYM_DIFFERENCE)) {
			readChar(in);
			skipSpaces(in);
			temp.symmetricDifference(checkTerm(in));
		}
		return temp;
	}
	
	private Set<BigInteger> checkTerm(Scanner in) throws APException {
		Set<BigInteger> temp = checkFactor(in);
		skipSpaces(in);
		if (nextCharIs(in, INTERSECTION)) {
			readChar(in);
			skipSpaces(in);
			temp.intersection(checkFactor(in));
		}
		return temp;
	}
	
	private void printSet(Set<BigInteger> s) {
		PrintStream out = new PrintStream(System.out);
		out.printf("%d:", lineNumber);
		if (!s.isEmpty()) {
			out.printf("%s\n", s.print());
		} else {
			out.println();
		}
	}
	
	private Identifier readIdentifier(Scanner in) throws APException {
		String s = "";
		while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
			s += readChar(in);
		}
		return new Identifier(s);
	}
	
	private Set<BigInteger> readSet(Scanner in) throws APException {
		Set<BigInteger> result = new Set<BigInteger>();
		skipSpaces(in);
		if (nextCharIs(in, '}')) {
			return result;
		}
		while (!nextCharIs(in, '}')) {
			skipSpaces(in);
			String number = "";
			if (!nextCharIsDigit(in)) {
				throw new APException("Incorrect character in set\n");
			}
			while (nextCharIsDigit(in)) {
				number += readChar(in);
			}
			skipSpaces(in);
			if (nextCharIsDigit(in)) {
				throw new APException("Space in between digits\n");
			} else if (nextCharIs(in, ',')) {
				readChar(in);
				result.add(new BigInteger(number));
			} else if (nextCharIs(in, '}')) {
				result.add(new BigInteger(number));
			} else {
				throw new APException("Incorrect character in set\n");
			}
		}
		return result;
	}
		
	private void start() throws APException {
        // Create a scanner on System.in
        Scanner in = new Scanner(System.in);
        hmap = new HashMap<Identifier, Set<BigInteger>>();
        
        // While there is input, read line and parse it.
        while (in.hasNextLine()) {
        	lineNumber++;
	        String line = in.nextLine();
	        
	        Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter("");
			skipSpaces(lineScanner);
			if (nextCharIs(lineScanner, COMMENT)) {
				//do nothing
			} else if (nextCharIs(lineScanner, PRINT_STATEMENT)) {
				readChar(lineScanner);
				printSet(checkExpression(lineScanner));
			} else if (nextCharIsLetter(lineScanner)) {
				Identifier id = readIdentifier(lineScanner);
				skipSpaces(lineScanner);
				if (!nextCharIs(lineScanner, ASSIGNMENT)) {
					throw new APException("'=' expected in assignment");
				} else {
					readChar(lineScanner);
					skipSpaces(lineScanner);
					hmap.put(id, checkExpression(lineScanner));
				}
			} else {
				throw new APException("Empty line\n");
			}
        }
        in.close();
    }

    public static void main(String[] argv) throws APException {
        new Main().start();
    }
}
