package org.jfree.data;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class DataUtilitiesTest {
	private Values2D value;
	private KeyedValues keyedValues;
	
	@BeforeEach
	void setUp() throws Exception {
		value = mock(Values2D.class);
		when(value.getColumnCount()).thenReturn(4);
		when(value.getRowCount()).thenReturn(3);
		when(value.getValue(0, 2)).thenReturn(5);
		when(value.getValue(1, 2)).thenReturn(7);
		when(value.getValue(2, 2)).thenReturn(1);
		
		when(value.getValue(2, 0)).thenReturn(3);
		when(value.getValue(2, 1)).thenReturn(6);
		when(value.getValue(2, 2)).thenReturn(1);
		when(value.getValue(2, 3)).thenReturn(2);
	
		keyedValues = mock(KeyedValues.class);
		when(keyedValues.getItemCount()).thenReturn(3);
		
		when(keyedValues.getValue(0)).thenReturn(5);
		when(keyedValues.getValue(1)).thenReturn(9);
		when(keyedValues.getValue(2)).thenReturn(2);
		
		when(keyedValues.getKey(0)).thenReturn(0);
		when(keyedValues.getKey(1)).thenReturn(1);
		when(keyedValues.getKey(2)).thenReturn(2);
		
		
	}
	
	@Test
	void getCumilitivePercentages_test() {
		ArrayList keyList = new ArrayList<>();
		keyList.add(0);
		keyList.add(1);
		keyList.add(2);
		when(keyedValues.getKeys()).thenReturn(keyList);
		
		KeyedValues actual = DataUtilities.getCumulativePercentages(keyedValues);
		assertAll(
				() -> assertEquals(0.3125, actual.getValue(0).doubleValue(),0.1d),
				() -> assertEquals(0.875, actual.getValue(1).doubleValue(),0.1d),
				() -> assertEquals(1.0, actual.getValue(2).doubleValue(),0.1d)
				
				);	
	}
	
	@Test
	void calculateColumnTotal_test() {
		double actual = DataUtilities.calculateColumnTotal(value, 2);
		double expected = 13;
		assertEquals(expected,actual);
	}
	
	@Test
	void calculateRowTotal_test() {
		double actual = DataUtilities.calculateRowTotal(value, 2);
		double expected = 12;
		assertEquals(expected,actual);
	}
	
	@Test
	void createNumberArray_test() {
		double[] testArray = {1.0,2.0,3.0,4.0,5.0,6.0,7.0};
		Number[] actual = DataUtilities.createNumberArray(testArray);
		Number[] expected = {1.0,2.0,3.0,4.0,5.0,6.0,7.0};
		
		//System.out.print(testArray[0]);
		//System.out.print(actual[0]);
		
		assertAll(
				() -> assertEquals(expected[0], actual[0]),
				() -> assertEquals(expected[1], actual[1]),
				() -> assertEquals(expected[2], actual[2]),
				() -> assertEquals(expected[3], actual[3]),
				() -> assertEquals(expected[4], actual[4]),
				() -> assertEquals(expected[5], actual[5]),
				() -> assertEquals(expected[6], actual[6])
				);

	}

}
