package 面向接口编程;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {
	private static Calculator calculator = new Calculator();
	@Before
	public void setUp() throws Exception {
		calculator.clear();
	}

	@Test
	public void testAdd() {
		calculator.add(2);
        calculator.add(3);
        assertEquals(5, calculator.getResult());
	}

	@Test
	public void testSubstract() {
		calculator.add(10);
        calculator.substract(2);
        assertEquals(8, calculator.getResult());
	}
	@Ignore("Multiply() Not yet implemented")
	@Test
	public void testMultiply() {
		fail("Not yet implemented");
	}

	@Test
	public void testDivide() {
		System.out.println("aaaaaaaaaaaaaaaaaaa");
		calculator.add(8);
        calculator.divide(2);
        assertEquals(4, calculator.getResult());
	}

}
