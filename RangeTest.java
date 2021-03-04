package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RangeTest {
	private Range exRange1;
	private Range exRange2;
	
	@Test //range 2 is within range 1
	void combine_validValues1() {
		exRange1 = new Range(2,5);
		exRange2 = new Range(1,3);
		Range actual = Range.combine(exRange1, exRange2);
		Range expected = new Range(1,5);
		
		assertEquals(expected,actual);				
	}
	@Test //range 2 lower bound is less than range 1 lower bound
	void combine_validValues2() {
		exRange1 = new Range(2,5);
		exRange2 = new Range(0,3);
		Range actual = Range.combine(exRange1, exRange2);
		Range expected = new Range(0,5);
		
		assertEquals(expected,actual);				
	}
	@Test //range 2 upper bound is greater than range 1 upper bound
	void combine_validValues3() {
		exRange1 = new Range(2,5);
		exRange2 = new Range(3,6);
		Range actual = Range.combine(exRange1, exRange2);
		Range expected = new Range(2,6);
		
		assertEquals(expected,actual);				
	}
	@Test //both ranges not intersecting
	void combine_validValues4() {
		exRange1 = new Range(2,5);
		exRange2 = new Range(7,10);
		Range actual = Range.combine(exRange1, exRange2);
		Range expected = new Range(2,10);
		
		assertEquals(expected,actual);				
	}
	@Test //both ranges identical
	void combine_validValues5() {
		exRange1 = new Range(2.21,55.99);
		exRange2 = new Range(2.21,55.99);
		Range actual = Range.combine(exRange1, exRange2);
		Range expected = new Range(2.21,55.99);
		
		assertEquals(expected,actual);				
	}
	@Test //range 2 lower bound is negative and lower than range 1 lower bound
	void combine_negativeValue() {
		exRange1 = new Range(2,5);
		exRange2 = new Range(-1,7);
		Range actual = Range.combine(exRange1, exRange2);
		Range expected = new Range(-1,7);
		
		assertEquals(expected,actual);				
	}
	@Test //all values negative, range 1 lower bound is greater than range 2 lower bound
	void combine_negativeValues2() {
		exRange1 = new Range(-5,-1);
		exRange2 = new Range(-6,-2);
		Range actual = Range.combine(exRange1, exRange2);
		Range expected = new Range(-6,-1);
		
		assertEquals(expected,actual);				
	}
	@Test //all values negative, range 1 lower bound is less than range 2 lower bound
	void combine_negativeValues2ReversedRanges() {
		exRange1 = new Range(-6,-2);
		exRange2 = new Range(-5,-1);
		Range actual = Range.combine(exRange1, exRange2);
		Range expected = new Range(-6,-1);
		
		assertEquals(expected,actual);				
	}
	@Test 
	void combine_nullValues() {
		exRange1 = new Range(2,5);
		exRange2 = null;
		
		assertAll(
				() -> assertEquals(exRange1,Range.combine(exRange1, exRange2)),
				() -> assertEquals(exRange1,Range.combine(exRange2, exRange1)),
				() -> assertNull(Range.combine(exRange2, exRange2))
				);
	}
	@Test
	void constrain_ValueInRange() {
		exRange1 = new Range(1,5);

		double expected = 3;
		double actual = exRange1.constrain(3);
		
		assertEquals(expected,actual);				
	}
	@Test //value greater than upper bound
	void constrain_valueOutsideRange1() {
		exRange1 = new Range(1,5);

		double expected = 5;
		double actual = exRange1.constrain(6);
		
		assertEquals(expected,actual);				
	}
	@Test //value less than lower bound
	void constrain_valueOutsideRange2() {
		exRange1 = new Range(1,5);

		double expected = 1;
		double actual = exRange1.constrain(0);
		
		assertEquals(expected,actual);				
	}
	@Test //range is positive
	void contains_valueInRange1() {
		exRange1 = new Range(1,5);

		boolean actual = exRange1.contains(2);
		
		assertTrue(actual);				
	}
	@Test //range is negative
	void contains_valueInRange2() {
		exRange1 = new Range(-5,-1);

		boolean actual = exRange1.contains(-2);
		
		assertTrue(actual);				
	}
	@Test //value lower than lower bound
	void contains_valueNotInRange1() {
		exRange1 = new Range(1,5);

		boolean actual = exRange1.contains(0);
		
		assertFalse(actual);				
	}
	@Test //value greater than upper bound
	void contains_valueNotInRange2() {
		exRange1 = new Range(1,5);

		boolean actual = exRange1.contains(6);
		
		assertFalse(actual);				
	}
	@Test //value is out of range, but absolute value is in range
	void contains_valueNotInRange3() {
		exRange1 = new Range(1,5);

		boolean actual = exRange1.contains(-1);
		
		assertFalse(actual);				
	}
	@Test //range is negative, value is outside of range
	void contains_valueNotInRange4() {
		exRange1 = new Range(-5,-1);

		boolean actual = exRange1.contains(1);
		
		assertFalse(actual);				
	}
	@Test //using two equal ranges
	void equals_valid1() {
		exRange1 = new Range(1,5);
		exRange2 = new Range(1,5);
		boolean actual = exRange1.equals(exRange2);
		
		assertTrue(actual);				
	}
	@Test //using two equal ranges of zero
	void equals_valid2() {
		exRange1 = new Range(0,0);
		exRange2 = new Range(0,0);
		boolean actual = exRange1.equals(exRange2);
		
		assertTrue(actual);				
	}
	@Test //using a valid range and a null
	void equals_validNulls1() {
		exRange1 = new Range(1,5);
		exRange2 = null;
		boolean actual = exRange1.equals(exRange2);
		
		assertFalse(actual);				
	}
	@Test //using two different ranges
	void equals_invalid1() {
		exRange1 = new Range(1,5);
		exRange2 = new Range(0,6);
		boolean actual = exRange1.equals(exRange2);
		
		assertFalse(actual);				
	}
	@Test //using two different ranges but equal lower bounds
	void equals_invalid2() {
		exRange1 = new Range(1,5);
		exRange2 = new Range(1,6);
		boolean actual = exRange1.equals(exRange2);
		
		assertFalse(actual);				
	}
	@Test //using two different ranges but equal upper bounds
	void equals_invalid3() {
		exRange1 = new Range(1,5);
		exRange2 = new Range(2,5);
		boolean actual = exRange1.equals(exRange2);
		
		assertFalse(actual);				
	}
	@Test //expand range so that lower bound becomes a negative number
	void expand_intoNegative() {
		exRange1 = new Range(2,10);
		
		Range newRange = Range.expand(exRange1, 0.5, 0.25);
		Range expected = new Range(-2,12);
		assertEquals(expected, newRange);				
	}
	@Test //expand range so that upper bound becomes a positive number, using positive multiplier
	void expand_intoPostive1() {
		exRange1 = new Range(-6,-2);
		
		Range newRange = Range.expand(exRange1, 0.25, 1);
		Range expected = new Range(-5,2);
		assertEquals(expected, newRange);				
	}
	@Test //expand range so that upper bound becomes a positive number, using negative multiplier
	void expand_intoPostive2() {
		exRange1 = new Range(-6,-2);
		
		Range newRange = Range.expand(exRange1, -0.25, 1);
		Range expected = new Range(-5,2);
		assertEquals(expected, newRange);				
	}
	@Test //lower bound multiplier zero
	void expand_zeroMargin1() {
		exRange1 = new Range(2,10);
		
		Range newRange = Range.expand(exRange1, 0, 0.25);
		Range expected = new Range(2,12);
		assertEquals(expected, newRange);				
	}
	@Test //upper bound multiplier zero
	void expand_zeroMargin2() {
		exRange1 = new Range(4,10);
		
		Range newRange = Range.expand(exRange1, 0.5, 0);
		Range expected = new Range(1,10);
		assertEquals(expected, newRange);				
	}
	@Test //lower bound multiplier greater than one
	void expand_marginGreaterThanOne1() {
		exRange1 = new Range(6,10);
		
		Range newRange = Range.expand(exRange1, 1.5, 0.25);
		Range expected = new Range(0,11);
		assertEquals(expected, newRange);				
	}
	@Test //both range multipliers greater than one
	void expand_marginGreaterThanOne2() {
		exRange1 = new Range(6,10);
		
		Range newRange = Range.expand(exRange1, 1.5, 1.25);
		Range expected = new Range(0,15);
		assertEquals(expected, newRange);				
	}
	@Test //lower bound multiplier negative
	void expand_marginNegative1() {
		exRange1 = new Range(6,10);
		
		Range newRange = Range.expand(exRange1, -0.5, 0.25);
		Range expected = new Range(4,11);
		assertEquals(expected, newRange);				
	}
	@Test //upper bound multiplier negative
	void expand_marginNegative2() {
		exRange1 = new Range(6,10);
		
		Range newRange = Range.expand(exRange1, 0.5, -0.25);
		Range expected = new Range(4,11);
		assertEquals(expected, newRange);				
	}
	@Test //both range multipliers negative
	void expand_marginNegative3() {
		exRange1 = new Range(6,10);
		
		Range newRange = Range.expand(exRange1, -0.5, -0.25);
		Range expected = new Range(4,11);
		assertEquals(expected, newRange);				
	}
	@Test //original range is zero
	void expand_zeroRange() {
		exRange1 = new Range(0,0);
		
		Range newRange = Range.expand(exRange1, 0.5, 0.25);
		Range expected = new Range(0,0);
		assertEquals(expected, newRange);				
	}
	@Test //new value higher than upper bound
	void expandToInclude_validValues1() {
		exRange1 = new Range(6,10);
		
		Range newRange = Range.expandToInclude(exRange1, 11);
		Range expected = new Range(6,11);
		assertEquals(expected, newRange);
	}
	@Test //new value lower than lower bound
	void expandToInclude_validValues2() {
		exRange1 = new Range(6,10);
		
		Range newRange = Range.expandToInclude(exRange1, 5);
		Range expected = new Range(5,10);
		assertEquals(expected, newRange);
	}
	@Test //new value already within bounds of range
	void expandToInclude_NewValueWithinOriginalRange() {
		exRange1 = new Range(6,10);
		
		Range newRange = Range.expandToInclude(exRange1, 7);
		Range expected = new Range(6,10);
		assertEquals(expected, newRange);				
	}
	@Test //both bounds positive, new value negative
	void expandToInclude_negativeValues1() {
		exRange1 = new Range(6,10);
		
		Range newRange = Range.expandToInclude(exRange1, -1);
		Range expected = new Range(-1,10);
		assertEquals(expected, newRange);				
	}
	@Test //lower bound negative, new value greater than upper bound
	void expandToInclude_negativeValues2() {
		exRange1 = new Range(-5,10);
		
		Range newRange = Range.expandToInclude(exRange1, 11);
		Range expected = new Range(-5,11);
		assertEquals(expected, newRange);				
	}
	@Test //both bounds negative, new value less than lower bound
	void expandToInclude_negativeValues3() {
		exRange1 = new Range(-6,-1);
		
		Range newRange = Range.expandToInclude(exRange1, -7);
		Range expected = new Range(-7,-1);
		assertEquals(expected, newRange);				
	}
	@Test //both bounds negative, new value greater than upper bound
	void expandToInclude_negativeValues4() {
		exRange1 = new Range(-6,-2);
		
		Range newRange = Range.expandToInclude(exRange1, -1);
		Range expected = new Range(-6,-1);
		assertEquals(expected, newRange);				
	}
	@Test //range is zero, new value less than lower bound
	void expandToInclude_zeroRange1() {
		exRange1 = new Range(0,0);
		
		Range newRange = Range.expandToInclude(exRange1, -7);
		Range expected = new Range(-7,0);
		assertEquals(expected, newRange);				
	}
	@Test //range is zero, new value greater than upper bound
	void expandToInclude_zeroRange2() {
		exRange1 = new Range(0,0);
		
		Range newRange = Range.expandToInclude(exRange1, 7);
		Range expected = new Range(0,7);
		assertEquals(expected, newRange);				
	}
	@Test //central value should be a whole number
	void getCentralValue_wholeNumber() {
		exRange1 = new Range(1,5);
		
		double actual = exRange1.getCentralValue();
		double expected = 3;
		assertEquals(expected, actual);				
	}
	@Test //central value should be a fraction
	void getCentralValue_fractionNumber() {
		exRange1 = new Range(1,6);
		
		double actual = exRange1.getCentralValue();
		double expected = 3.5;
		assertEquals(expected, actual);				
	}
	@Test //range is zero
	void getCentralValue_zero() {
		exRange1 = new Range(0,0);
		
		double actual = exRange1.getCentralValue();
		double expected = 0;
		assertEquals(expected, actual);				
	}
	@Test //only lower bound is negative
	void getCentralValue_negative1() {
		exRange1 = new Range(-5,5);
		
		double actual = exRange1.getCentralValue();
		double expected = 0;
		assertEquals(expected, actual);				
	}
	@Test //both bounds are negative
	void getCentralValue_negative2() {
		exRange1 = new Range(-5,-1);
		
		double actual = exRange1.getCentralValue();
		double expected = -3;
		assertEquals(expected, actual);				
	}
	@Test //
	void getLength_valid1() {
		exRange1 = new Range(1,5);
		
		double actual = exRange1.getLength();
		double expected = 4;
		assertEquals(expected, actual);				
	}
	@Test //
	void getLength_zero() {
		exRange1 = new Range(0,0);
		
		double actual = exRange1.getLength();
		double expected = 0;
		assertEquals(expected, actual);				
	}	
	@Test //both bounds negative
	void getLength_negative1() {
		exRange1 = new Range(-5,-1);
		
		double actual = exRange1.getLength();
		double expected = 4;
		assertEquals(expected, actual);				
	}
	@Test //lower bound negative
	void getLength_negative2() {
		exRange1 = new Range(-5,5);
		
		double actual = exRange1.getLength();
		double expected = 10;
		assertEquals(expected, actual);				
	}
	@Test //
	void getLowerBound_valid1() {
		exRange1 = new Range(1,5);
		
		double actual = exRange1.getLowerBound();
		double expected = 1;
		assertEquals(expected, actual);				
	}
	@Test //
	void getLowerBound_valid2() {
		exRange1 = new Range(1,1);
		
		double actual = exRange1.getLowerBound();
		double expected = 1;
		assertEquals(expected, actual);				
	}
	@Test //
	void getLowerBound_zero() {
		exRange1 = new Range(0,5);
		
		double actual = exRange1.getLowerBound();
		double expected = 0;
		assertEquals(expected, actual);				
	}
	@Test //lower bound negative
	void getLowerBound_negative1() {
		exRange1 = new Range(-11,5);
		
		double actual = exRange1.getLowerBound();
		double expected = -11;
		assertEquals(expected, actual);				
	}
	@Test //both bounds negative
	void getLowerBound_negative2() {
		exRange1 = new Range(-11,-5);
		
		double actual = exRange1.getLowerBound();
		double expected = -11;
		assertEquals(expected, actual);				
	}
	@Test
	void getLowerBound_extreme() {
		exRange1 = new Range(7.77777,7.77778);
		
		double actual = exRange1.getLowerBound();
		double expected = 7.77777;
		assertEquals(expected, actual);				
	}
	@Test //
	void getUpperBound_valid1() {
		exRange1 = new Range(1,5);
		
		double actual = exRange1.getUpperBound();
		double expected = 5;
		assertEquals(expected, actual);				
	}
	@Test //
	void getUpperBound_valid2() {
		exRange1 = new Range(1,1);
		
		double actual = exRange1.getUpperBound();
		double expected = 1;
		assertEquals(expected, actual);				
	}
	@Test //
	void getUpperBound_zero() {
		exRange1 = new Range(-5,0);
		
		double actual = exRange1.getUpperBound();
		double expected = 0;
		assertEquals(expected, actual);				
	}
	@Test //lower bound negative
	void getUpperBound_negative1() {
		exRange1 = new Range(-11,5);
		
		double actual = exRange1.getUpperBound();
		double expected = 5;
		assertEquals(expected, actual);				
	}
	@Test //both bounds negative
	void getUpperBound_negative2() {
		exRange1 = new Range(-11,-5);
		
		double actual = exRange1.getUpperBound();
		double expected = -5;
		assertEquals(expected, actual);				
	}
	@Test
	void getUpperBound_extreme() {
		exRange1 = new Range(7.77777,7.77778);
		
		double actual = exRange1.getUpperBound();
		double expected = 7.77778;
		assertEquals(expected, actual);				
	}
	@Test //
	void intersects_valid1() {
		exRange1 = new Range(5,10);
		
		boolean actual = exRange1.intersects(6, 7);
		assertTrue(actual);
	}
	@Test //upper boundries match
	void intersects_boundry1() {
		exRange1 = new Range(5,10);
		
		boolean actual = exRange1.intersects(10, 12);
		assertTrue(actual);
	}
	@Test //lower boundries match
	void intersects_boundry2() {
		exRange1 = new Range(5,10);
		
		boolean actual = exRange1.intersects(2, 5);
		assertTrue(actual);
	}
	@Test //lower boundries are negative and match
	void intersects_boundryNegative1() {
		exRange1 = new Range(-20,-10);
		
		boolean actual = exRange1.intersects(-30,-20);
		assertTrue(actual);
	}
	@Test //upper boundries are negative and match
	void intersects_boundryNegative2() {
		exRange1 = new Range(-20,-10);
		
		boolean actual = exRange1.intersects(-10,-2);
		assertTrue(actual);
	}
	@Test //one negative boundry
	void intersects_negative1() {
		exRange1 = new Range(-5,10);
		
		boolean actual = exRange1.intersects(-7,-2);
		assertTrue(actual);
	}
	@Test //both negative boundries
	void intersects_negative2() {
		exRange1 = new Range(-20,-5);
		
		boolean actual = exRange1.intersects(-8,-6);
		assertTrue(actual);
	}
	@Test //both negative boundries, intersection boundry has one negative boundry
	void intersects_negative3() {
		exRange1 = new Range(-20,-5);
		
		boolean actual = exRange1.intersects(-7,2);
		assertTrue(actual);
	}
	@Test
	void shiftNoFlag_valid1() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, 1);
		Range expected = new Range(6,11);
		assertEquals(expected,actual);
	}
	@Test
	void shiftNoFlag_negativeDelta() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, -1);
		Range expected = new Range(4,9);
		assertEquals(expected,actual);
	}
	@Test
	void shiftNoFlag_zeroDelta() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, 0);
		Range expected = new Range(5,10);
		assertEquals(expected,actual);
	}
	@Test
	void shiftNoFlag_decimalDelta() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, 0.5);
		Range expected = new Range(5.5,10.5);
		assertEquals(expected,actual);
	}
	@Test
	void shiftNoFlag_intoZero() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, -5);
		Range expected = new Range(0,5);
		assertEquals(expected,actual);
	}
	@Test //shift so that one boundry becomes negative
	void shiftNoFlag_intoNegative1() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, -6);
		Range expected = new Range(-1,4);
		assertEquals(expected,actual);
	}
	@Test //shift so that both boundries become negative
	void shiftNoFlag_intoNegative2() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, -15);
		Range expected = new Range(-10,-5);
		assertEquals(expected,actual);
	}
	@Test //initial range has one boundry negative, shift so that both boundries become positive
	void shiftNoFlag_intoPositive1() {
		exRange1 = new Range(-5,10);
		
		Range actual = Range.shift(exRange1, 6);
		Range expected = new Range(1,16);
		assertEquals(expected,actual);
	}
	@Test //initial range boundries both negative, shifting so that one boundry becomes positive
	void shiftNoFlag_intoPositive2() {
		exRange1 = new Range(-15,-10);
		
		Range actual = Range.shift(exRange1, 11);
		Range expected = new Range(-4,1);
		assertEquals(expected,actual);
	}
	@Test //initial range boundries both negative, shifting so that both boundries becomes positive
	void shiftNoFlag_intoPositive3() {
		exRange1 = new Range(-15,-10);
		
		Range actual = Range.shift(exRange1, 20);
		Range expected = new Range(5,10);
		assertEquals(expected,actual);
	}
	
	@Test //initial range is zero
	void shiftNoFlag_zeroRange() {
		exRange1 = new Range(0,0);
		
		Range actual = Range.shift(exRange1, 5);
		Range expected = new Range(5,5);
		assertEquals(expected,actual);
	}
	@Test //
	void shiftWithFlag_valid1() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, 5, false);
		Range expected = new Range(10,15);
		assertEquals(expected,actual);
	}
	@Test // starting from positive
	void shiftWithFlag_shiftToZero1() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, -5, false);
		Range expected = new Range(0,5);
		assertEquals(expected,actual);
	}
	@Test // starting from negative
	void shiftWithFlag_shiftToZero2() {
		exRange1 = new Range(-15,-10);
		
		Range actual = Range.shift(exRange1, 10, false);
		Range expected = new Range(-5,0);
		assertEquals(expected,actual);
	}
	@Test // starting from one boundry negative
	void shiftWithFlag_shiftToZero3() {
		exRange1 = new Range(-15,10);
		
		Range actual = Range.shift(exRange1, 15, false);
		Range expected = new Range(0,25);
		assertEquals(expected,actual);
	}
	@Test //starting from positive
	void shiftWithFlag_shiftBelowZero1() {
		exRange1 = new Range(5,10);
		
		Range actual = Range.shift(exRange1, -6, false);
		Range expected = new Range(0,4);
		assertEquals(expected,actual);
	}
	@Test //starting from one boundry positive
	void shiftWithFlag_shiftBelowZero2() {
		exRange1 = new Range(-5,10);
		
		Range actual = Range.shift(exRange1, -11, false);
		Range expected = new Range(-16,0);
		assertEquals(expected,actual);
	}
	@Test //starting from negative
	void shiftWithFlag_shiftAboveZero1() {
		exRange1 = new Range(-10,-5);
		
		Range actual = Range.shift(exRange1, 6, false);
		Range expected = new Range(-4,0);
		assertEquals(expected,actual);
	}
	@Test //starting with only one boundry negative
	void shiftWithFlag_shiftAboveZero2() {
		exRange1 = new Range(-10,5);
		
		Range actual = Range.shift(exRange1, 11, false);
		Range expected = new Range(0,16);
		assertEquals(expected,actual);
	}
	@Test //starting from zero range
	void shiftWithFlag_startAtZero1() {
		exRange1 = new Range(0,0);
		
		Range actual = Range.shift(exRange1, 5, false);
		Range expected = new Range(5,5);
		assertEquals(expected,actual);
	}
	@Test //starting from zero range
	void shiftWithFlag_startAtZero2() {
		exRange1 = new Range(0,0);
		
		Range actual = Range.shift(exRange1, -5, false);
		Range expected = new Range(-5,-5);
		assertEquals(expected,actual);
	}
	@Test
	void toString_valid1() {
		exRange1 = new Range(5,10);
		String actual = exRange1.toString();
		String expected = "Range[5.0,10.0]";
		
		assertEquals(expected,actual);
	}
	@Test
	void toString_valid2() {
		//exRange1 = new Range(0,10);
		String actual = new Range(0,10).toString();
		String expected = "Range[0.0,10.0]";
		
		assertEquals(expected,actual);
	}
	@Test
	void toString_zero() {
		exRange1 = new Range(0,0);
		String actual = exRange1.toString();
		String expected = "Range[0.0,0.0]";
		
		assertEquals(expected,actual);
	}
	@Test //one boundry negative
	void toString_negative1() {
		exRange1 = new Range(-5,10);
		String actual = exRange1.toString();
		String expected = "Range[-5.0,10.0]";
		
		assertEquals(expected,actual);
	}
	@Test //both boundries negative
	void toString_negative2() {
		exRange1 = new Range(-15,-10);
		String actual = exRange1.toString();
		String expected = "Range[-15.0,-10.0]";
		
		assertEquals(expected,actual);
	}
	@Test
	void toString_decimal1() {
		exRange1 = new Range(15.666,16.001);
		String actual = exRange1.toString();
		String expected = "Range[15.666,16.001]";
		
		assertEquals(expected,actual);
	}
}
