package euclidean_algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * <p>
 * Simple tests for DiophantineSolver class <br>
 * Meant to be as a reference for future development of the class
 * </p>
 * @author Aviral Jain
 *
 */
public class DiophantineSolverTests {

	/**
	 * Tests if an exception is thrown if either of the <br>
	 * parameters to the gcdCalculator function are zero
	 */
	@Test
	public void gcdCalculatorThrowsExcept() {
		int except = 0;
		try {
			DiophantineSolver.gcdCalculator(0, 2);
		} catch (AssertionError e) {
			except++;
		}
		
		try {
			DiophantineSolver.gcdCalculator(2, 0);
		} catch (AssertionError e) {
			except++;
		}
		
		try {
			DiophantineSolver.gcdCalculator(0, 0);
		} catch (AssertionError e) {
			except++;
		}
		assertTrue(except == 3);
	}
	
	/**
	 * <p>
	 * Tests that lnrDiophantineSolver throws an assertion error <br>
	 * when the RHS value is not divisible by the gcd of the two numbers <br> 
	 * </p>
	 */
	@Test
	public void lnrDiophantineSolverThrowsExceptTest() {
		int except = 0;
		try {
			DiophantineSolver.lnrDiophantineSolver(98, 36, 3);
		} catch (AssertionError e) {
			except++;			
		}
		assertTrue(except == 1);		
	}
	
	/**
	 * <p>
	 * Simple gcd tests
	 * </p>
	 */
	@Test
	public void gcdCalculatorTests() {
		// gcd of 98 and 36 is 2
		assertTrue(2 == DiophantineSolver.gcdCalculator(98, 36));
		
		// gcd of 98 and 1 should be 1
		assertTrue(1 == DiophantineSolver.gcdCalculator(98, 1));
		
		// gcd of 1 and 1 should 1
		assertTrue(1 == DiophantineSolver.gcdCalculator(1, 1));
		
		// gcd of two prime numbers should be 1
		assertTrue(1 == DiophantineSolver.gcdCalculator(7,  11));
		
		// gcd of the same numbers should be the same number
		assertTrue(300 == DiophantineSolver.gcdCalculator(300,  300));
		
		// gcd should be the smaller number when one is the multiple of the other
		assertTrue(200 == DiophantineSolver.gcdCalculator(800, 200));
	}
	
	/**
	 * <p>
	 * Testing edge cases in Diophantine solver
	 * </p>
	 */
	@Test
	public void diophantineSolverTestEdgeCases() {
		// 98x + 1y = 1 /* Solution should be: x = 1, y = -97 */
		int[] result = DiophantineSolver.lnrDiophantineSolver(98, 1, 1);
		assertTrue(result[0] == 1 && result[1] == -97);
		
		// 1x + 1y = 1 /* Solution should be x = 1 and y = 0 */
		result = DiophantineSolver.lnrDiophantineSolver(1, 1, 1);
		assertTrue(result[0] == 1 && result[1] == 0);
		
		// 1x + 1y = 2 
		/* Various solutions are possible in this case (like x = 4, y = -2 or x = 6, y = -4) 
		   but the algorithm should output x = 2 and y = 0 */ 
		result = DiophantineSolver.lnrDiophantineSolver(1, 1, 2);
		assertTrue(result[0] == 2 && result[1] == 0);
		
		// Testing when RHS is 0, simplest solution is x = y = 0
		result = DiophantineSolver.lnrDiophantineSolver(96, 15, 0);
		assertTrue(result[0] == 0 && result[1] == 0);
	}
	
	/**
	 * <p>
	 * Simple tests
	 * </p>
	 */
	@Test
	public void diophantineSolverSimpleTests() {		
		// 98x + 36y = 2 /* Solution should be: x = 7, y = -19 */
		int[] result = DiophantineSolver.lnrDiophantineSolver(98, 36, 2);
		assertTrue(result[0] == 7 && result[1] == -19);
		
		// 98x + 36y = 4 /* Solution should be scaled compared to prev: x = 14, y = -38 */
		result = DiophantineSolver.lnrDiophantineSolver(98, 36, 4);
		assertTrue(result[0] == 14 && result[1] == -38);
		
		// Testing with prime numbers
		result = DiophantineSolver.lnrDiophantineSolver(11, 13, 2);
		assertTrue(result[0] == -10 && result[1] == 12); /* (13 * (-10)) + (11 * (12)) = 2 */
	}
	
	
	/**
	 * <p>
	 * Should throw an assertion error because the RHS (value of c) <br>
	 * is not a multiple of the gcd <br>
	 * </p>
	 */
	@Test
	public void diophantineSolverThrowsExcept() {
		int except = 0;
		int[] result;
		
		// 98x + 36y = 1 /* Should throw an assertion error since (1 % 2 != 0), where gcd = 2 */
		try {
			result = DiophantineSolver.lnrDiophantineSolver(98, 36, 1);
		} catch (AssertionError e) {
			except++;
		}
		
		// 96x + 15y = 8 /* Should throw an assertion error since (8 % 3 != 0), where gcd = 3 */ 
		try {
			result = DiophantineSolver.lnrDiophantineSolver(96, 15, 8);
		} catch (AssertionError e) {
			except++;
		}
		
		assertTrue(except == 2);
	}
	
}
