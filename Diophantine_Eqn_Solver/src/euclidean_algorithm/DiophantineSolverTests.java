package euclidean_algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * <p>
 * Simple tests for DiophantineSolver class <br>
 * Meant to be as a reference for future development of the class
 * </p>
 * @author Aviral
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
	
	

}
