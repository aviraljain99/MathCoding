package euclidean_algorithm;


/**
 * TODO Build GUI
 * @author Aviral
 *
 */

public class DiophantineSolver {
	
	public static void main(String[] args) {		
		// System.out.println(DiophantineSolver.gcdCalculator(98, 36));
		
		int[] result = DiophantineSolver.lnrDiophantineSolver(210, 209, 1);
 		
		System.out.println("coefficient for smaller number: " + result[1] + " coefficient for larger number: " + result[0]);
	}
	
	/**
	 * Calculates the greatest common divisor of two numbers x and y
	 * 
	 * Do an algorithm correctness proof for this!
	 * 
	 * @param x
	 * @param y
	 * 
	 * @return
	 */
	public static int gcdCalculator(int x, int y) {
		int a, b;
		if (x > y) {
			a = x;
			b = y;
		} else {
			a = y;
			b = x;
		}
		int remainder = 1; // random value (better way to do this??)
		int result = b;
		while (remainder != 0) {
			remainder = a % b;
			System.out.println("a = " + a + " b = " + b + " remainder = " + remainder);
			a = b;
			result = b;
			b = remainder;
		}
		return result;
	}
	
	/**
	 * 
	 * 
	 * @param x
	 * @param y
	 * @param c
	 * @return
	 */
	public static int[] lnrDiophantineSolver(int x, int y, int c) {
		int gcd = DiophantineSolver.gcdCalculator(x, y);
		if (c % gcd != 0) {
			return null;
		} else {
			int a, b;
			if (x > y) {
				a = x;
				b = y;
			} else {
				a = y;
				b = x;
			}
			
			int[] result = recursiveSolver(a, b, c, gcd);
			int factor = c/gcd;
			result[0] = result[0] * factor;
			result[1] = result[1] * factor;
			return result;
		}
	}
	
	private static int[] recursiveSolver(int a, int b, int c, int gcd) {
		int[] result = new int[2];
		int rem = a % b;
		if (rem == gcd) {
			result[0] = 1; // default, always the case
			result[1] = (int)(-a/b);
			return result;
		} else {
			result = recursiveSolver(b, rem, c, gcd);
			int r0 = result[0];
			int r1 = result[1];
			result[0] = r1; // coefficient for b in the upper stack
			result[1] = r0 - (r1 * ((int)a/b)); // coefficient for rem in the upper stack
			return result;
		}		
	}
	
	

}
