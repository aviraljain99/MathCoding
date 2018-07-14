package euclidean_algorithm;


/**
 * @author Aviral
 *
 */

public class DiophantineSolver {
	
	public static void main(String[] args) {		
		int[] result = DiophantineSolver.lnrDiophantineSolver(98, 36, gcdCalculator(98, 36)); 		
		System.out.println("coefficient for smaller number: " + result[1] + " coefficient for larger number: " + result[0]);
	}
	
	/**
	 * Calculates the greatest common divisor (gcd) of two numbers x and y
	 *  
	 * @param x,y
	 * 		the numbers whose gcd is to be calculated
	 * @return
	 * 		the gcd of the two numbers
	 * @throws AssertionError
	 * 		Thrown if x == 0 || y == 0 
	 */
	public static int gcdCalculator(int x, int y) {
		assert(x != 0 && y != 0);
		/* Choosing the largest and the smallest */
		int a = (x < y) ? y : x;
		int b = (x > y) ? y : x;
		int remainder, result;
		
		do {
			remainder = a % b;
			result = b;
			a = b;
			b = remainder;
		} while (remainder != 0);
		
		return result;
	}
	
	/**
	 * <p>
	 * Solves for values of x and y in the following Diophantine equation: <br>
	 * 		(a1 * x) + (a2 * y) = c 
	 * </p>
	 * 
	 * <p>
	 * i.e. it attempts to find integer solutions to x and y
	 * for the above equation.
	 * </p>
	 * 
	 * This fails if <br>
	 * <pre>
	 * 		(c % gcd(a1, a2) != 0)
	 * </pre>
	 * 
	 * @param a1,a2,c
	 * 		inputs to the above equation
	 * @return
	 * 		An array of size 2 containing solutions to x and y
	 * 
	 * @throws AssertionError
	 * 		Thrown if (c % gcd(a1, a2)) != 0
	 */
	public static int[] lnrDiophantineSolver(int a1, int a2, int c) {
		/* Calculates GCD to perform checks */
		int gcd = DiophantineSolver.gcdCalculator(a1, a2);
		assert(c % gcd == 0);
		
		int a = (a1 < a2) ? a2 : a1;
		int b = (a1 > a2) ? a2 : a1;
		
		int[] result = recursiveSolver(a, b);
		int factor = c/gcd;
		result[0] = result[0] * factor;
		result[1] = result[1] * factor;
		return result;
	}
	
	/**
	 * <p>
	 * Called in lnrDiophantineSolver and recursively performs the <br>
	 * the extended euclidean algorithm
	 * </p>
	 * 
	 * <p>
	 * result[0] is the coefficient of a and <br>
	 * result[1] is the coefficient of b in the following condition <br>
	 * </p>
	 * 
	 * condition on the result returned by the function: <br>
	 * <pre>
	 * 		{@code gcd(a,b) = (a*result[0]) + (b*result[1])}
	 * </pre>
	 * 
	 * @param a,b
	 * 		coefficients of x and y in the diophantine equation
	 * @return
	 * 		An array of size 2, containing coefficients to a and b
	 */
	private static int[] recursiveSolver(int a, int b) {
		int[] result = new int[2];
		int rem = a % b;
		if ((b % rem) == 0) {
			/* In this base case:
			 * 
			 * 		a = bq + r, where r is the remainder
			 * 
			 * and since b is divisible by r, r is the gcd.
			 * So we get,
			 * 		
			 * 		a + (( -q ) * b) = r = gcd
			 * 
			 * Also note 
			 * 		q = (int)( a / b ) - integer division
			 * 
			 * And so we set result[0] = 1 and result[1] = -q
			 * and these are the coefficients of a and b respectively
			 * */
			result[0] = 1; 
			result[1] = (int)(-a/b); // so, a + ((-q)*b) = gcd
			return result;
		} else {
			result = recursiveSolver(b, rem); 
			/* result contains values such that:
			 * 		(result[0] * b) + (result[1] * rem) = gcd  ---- |1|
			 * 
			 * where,
			 * 		gcd = gcd(a,b) 
			 * 
			 * Also note,
			 * 		rem = a - (( a / b ) * b)  ---- |2|   (using integer division)
			 * Also let,
			 * 		q = (a / b)
			 * 
			 * So we can substitute |2| into |1| and we get the following sequence of eqns:
			 * 		(result[0] * b) + (result[1] * (a - (q * b))) = gcd
			 * 
			 * And this can be re-arranged to get the following equation:
			 * 		( (result[0] - (result[1] * (a / b))) * b ) + ( result[1] * a ) = gcd
			 * 
			 * And so the new result[0] and result[1] are modified accordingly to match
			 * the coefficients of a and b
			 * */
			int r0 = result[0];
			int r1 = result[1];
			result[0] = r1; // coefficient for b in the upper stack
			result[1] = r0 - (r1 * ( (int) a / b )); // coefficient for rem in the upper stack
			return result;
		}		
	}
}
