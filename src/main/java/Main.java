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
	private HashMap<Identifier, Set<BigInteger>> hmap;
	private static PrintStream out;
	
	Main() {
		out = new PrintStream(System.out);
	}
	
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
			temp = checkExpression(in);
			skipSpaces(in);
			if (!nextCharIs(in, ')')) {
				throw new APException("No closing brackets");
			}
			readChar(in);
		} else if (nextCharIsLetter(in)) {				//Identifier
			Identifier id = readIdentifier(in);
			if (hmap.containsKey(id)) {
				temp = hmap.get(id).copy();
			} else {
				throw new APException("Unknown Identifier");
			}
			
		} else {
			throw new APException("Wrong print syntax");
		}
		return temp;
	}
	
	private Set<BigInteger> checkExpression(Scanner in) throws APException {
		Set<BigInteger> temp = checkTerm(in);
		skipSpaces(in);
		while (nextCharIs(in, UNION) || nextCharIs(in,  COMPLEMENT) || nextCharIs(in, SYM_DIFFERENCE)) {
			if (nextCharIs(in, UNION)) {
				readChar(in);
				skipSpaces(in);
				temp = temp.union(checkTerm(in));
			} else if (nextCharIs(in,  COMPLEMENT)) {
				readChar(in);
				skipSpaces(in);
				temp = temp.complement(checkTerm(in));
			} else if (nextCharIs(in, SYM_DIFFERENCE)) {
				readChar(in);
				skipSpaces(in);
				temp = temp.symmetricDifference(checkTerm(in));
			}
			skipSpaces(in);
		}
		return temp;
	}
	
	private Set<BigInteger> checkTerm(Scanner in) throws APException {
		Set<BigInteger> temp = checkFactor(in);
		skipSpaces(in);
		while (nextCharIs(in, INTERSECTION)) {
			readChar(in);
			skipSpaces(in);
			temp = temp.intersection(checkFactor(in));
			skipSpaces(in);
		}
		return temp;
	}
	
	private void printSet(Set<BigInteger> s) {
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
			readChar(in);
			return result;
		}
		while (!nextCharIs(in, '}')) {
			skipSpaces(in);
			String number = "";
			if (!nextCharIsDigit(in)) {
				throw new APException("Incorrect character in set");
			}
			while (nextCharIsDigit(in)) {
				if (number.isEmpty() && nextCharIs(in, '0')) {
					char c = readChar(in);
					if (nextCharIsDigit(in)) {
						throw new APException("Character other than '}' or ',' after a 0 not allowed");
					} else {
						number += c;
					}
				} else {
					number += readChar(in);
				}
			}
			skipSpaces(in);
			if (nextCharIsDigit(in)) {
				throw new APException("Space in between digits");
			} else if (nextCharIs(in, ',')) {
				readChar(in);
				skipSpaces(in);
				if (!nextCharIsDigit(in)) {
					throw new APException("Digit expected after ','");
				}
				result.add(new BigInteger(number));
			} else if (nextCharIs(in, '}')) {
				result.add(new BigInteger(number));
			} else {
				throw new APException("Incorrect character in set");
			}
		}
		readChar(in);
		return result;
	}
		
	private void start() throws APException {
        // Create a scanner on System.in
        Scanner in = new Scanner(System.in);
        hmap = new HashMap<Identifier, Set<BigInteger>>();
        
        // While there is input, read line and parse it.
        while (in.hasNextLine()) {
        	try {
		        String line = in.nextLine(); 
		        Scanner lineScanner = new Scanner(line);
		        
				
		        lineScanner.useDelimiter("");
				skipSpaces(lineScanner);
				if (nextCharIs(lineScanner, COMMENT)) {
					//do nothing
				} else if (nextCharIs(lineScanner, PRINT_STATEMENT)) {
					readChar(lineScanner);
					Set<BigInteger> s = checkExpression(lineScanner);
					skipSpaces(lineScanner);
					if (lineScanner.hasNext()) {
						throw new APException("Incorrect Syntax");
					}
					printSet(s);
				} else if (nextCharIsLetter(lineScanner)) {
					Identifier id = readIdentifier(lineScanner);
					skipSpaces(lineScanner);
					if (!nextCharIs(lineScanner, ASSIGNMENT)) {
						throw new APException("'=' expected in assignment");
					} else {
						readChar(lineScanner);
						skipSpaces(lineScanner);
						Set<BigInteger> s = checkExpression(lineScanner);
						skipSpaces(lineScanner);
						if (lineScanner.hasNext()) {
							throw new APException("Incorrect Syntax");
						}
						hmap.put(id, s);
					}
				} else {
					throw new APException("Empty line");
				}
        	} catch (APException e) {
        		out.println(e);
        	}
        
        }
        in.close();
    }

    public static void main(String[] argv) throws APException {
    	new Main().start();
    }
}
