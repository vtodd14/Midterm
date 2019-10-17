//Victoria Todd
//CISC 181 Midterm coding exercise
package pkgGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import pkgEnum.ePuzzleViolation;

public class LatinSquare implements iLatinSquare {

	/**
	 * LatinSquare - two dimensional array that's the core of the puzzle
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 */
	private int[][] LatinSquare;

	/**
	 * bIgnoreZero - should I ignore zero when doing checks...?
	 * @version 1.0
	 * @since Midterm #1
	 */
	private boolean bIgnoreZero;

	/**
	 * PV - ArrayList to collect PuzzleViolations
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 */
	private ArrayList<PuzzleViolation> PV = new ArrayList<PuzzleViolation>();
	
	/**
	 * No-arg constructor, make it public, don't do anything in the constructor
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 */
	public LatinSquare() {

	}

	/**
	 * Pass in a given puzzle, set the LatinSquare
	 * 
	 * @param puzzle Pass in given LatinSquare puzzle (probably for testing)
	 * @version 1.0
	 * @since Midterm #1
	 */

	public LatinSquare(int[][] puzzle) {
		this.LatinSquare = puzzle;
	}

	/**
	 * return LatinSquare instance attribute
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @return - returns the instance of the LatinSquare
	 */
	public int[][] getLatinSquare() {
		return LatinSquare;
	}

	/**
	 * set LatinSquare instance attribute
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param latinSquare - pass in an instance of a possible LatinSquare
	 */

	public void setLatinSquare(int[][] latinSquare) {
		LatinSquare = latinSquare;
	}

	/**
	 * Should the processing ignore zeros (hasDuplicates, etc)
	 * @version 1.0
	 * @since Midterm #1
	 * @return
	 */
	public boolean isbIgnoreZero() {
		return bIgnoreZero;
	}

	/**
	 * Should the processing ignore zeros (hasDuplicates, etc)
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param bIgnoreZero
	 */
	protected void setbIgnoreZero(boolean bIgnoreZero) {
		this.bIgnoreZero = bIgnoreZero;
	}

	/**
	 * getColumn - return a one-dimension array from a given column in the
	 * LatinSquare <br>
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 *
	 *
	 * In this example, getColumn(1) would return [1],[2],[4],[1]
	 *
	 * @version 1.0
	 * @since Midterm #1
	 * @param iCol The column you want returned from the two-dimensional array
	 * @return one dimensional array of values for the given column
	 */
	public int[] getColumn(int iCol) {
		
		
		int [] column = new int [LatinSquare.length];
		
		for (int i = 0; i < LatinSquare.length; i++) {
			column [i] = LatinSquare [i][iCol];
		}
	
		return column;
	}

	/**
	 * getRow - return a one-dimension array from a given row in the LatinSquare<br>
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * In this example, getRow(1) would return [1],[2],[3],[4]
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param iRow given row of the two dimensional array to return
	 * @return one dimensional array of the given row
	 */
	public int[] getRow(int iRow) {
		
		return LatinSquare [iRow];
		
	}

	/**
	 * Pass in a one-dimension array, pass back true if there is a duplicate value
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param arr - one dimensional array to check for duplicates
	 * @return - returns 'true' if any of the elements are duplicate
	 */
	public boolean hasDuplicates(int[] arr) {

		
		boolean hasDuplicates = false;	
		//Set<Integer> DuplicateSet = new HashSet<Integer>(); 
		
		for (int i = 0; i < arr.length; i++) {
			for (int j= i+1; j < arr.length; j++) {
				if (arr[i] ==0 || arr[j] ==0 && bIgnoreZero){
					continue;
				}
				if (arr[i]==(arr[j])) {
					hasDuplicates = true;
				}
			}
			}
			
		return hasDuplicates;
	}

	/**
	 * hasDuplicates - overloaded, checking the entire puzzle for a duplicate
	 * @version 1.0
	 * @since Midterm #1
	 * @return Return 'true' if any element in column or row is duplicate
	 */
	protected boolean hasDuplicates() {
		

		boolean dup = false;
		for (int i = 0; i < LatinSquare.length; i++) {
			if (hasDuplicates(getRow(i))) 
				AddPuzzleViolation(new PuzzleViolation(ePuzzleViolation.DupRow, i));
		}


		for (int i = 0; i < LatinSquare.length; i++) {
			if (hasDuplicates(getColumn(i)))
				AddPuzzleViolation(new PuzzleViolation(ePuzzleViolation.DupCol, i));
		}
		
		if (PV.size()>0)
			return true;
		else 
			return false;
	}

	/**
	 * Remove any zeros in an array
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param arr
	 * @return
	 */
	private int[] RemoveZeros(int[] arr) {
		while (ArrayUtils.contains(arr, 0))
			arr = ArrayUtils.removeElement(arr, 0);
		return arr;
	}

	/**
	 * doesElementExist - pass in one-dimension array and a value, if value exists
	 * in array... then return true
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param arr    - one dimensional array to check
	 * @param iValue - value to check against one dimensional array
	 * @return - return 'true' if iValue exists in arr
	 */
	public boolean doesElementExist(int[] arr, int iValue) {
		
		
		boolean doesElementExist = false;
		
		for (int element : arr) {
		    if (element == iValue) {
		        doesElementExist = true;
		    }
		}
		return doesElementExist;

	}

	/**
	 * hasAllValues - return 'true' if every element from arr2 is in arr1
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param arr1 target array
	 * @param arr2 source array
	 * @return return 'true' if every element from source array is in target array
	 */
	public boolean hasAllValues(int[] arr1, int[] arr2) {
		
		
		boolean hasAllValues = true;
		if (arr1.length == arr2.length) {
			Arrays.sort(arr1);
			Arrays.sort(arr2);
			for (int i = 0; i < arr1.length; i++) {
				if (arr1[i] != arr2[i])
					hasAllValues = false;
			}
		} else {
			hasAllValues = false;
		}

		return hasAllValues;

	}

	/**
	 * isLatinSquare - return 'true' if: Each row and column doesn't contain
	 * duplicates If each element in the first row is found in every other row If
	 * each element in the first coumn is found in every other column
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @return returns 'true' if the two-dimensional array is a LatinSquare
	 */
	public boolean isLatinSquare() {

		boolean isLatinSquare = true;

		for (int i = 0; i < LatinSquare.length; i++) {
			if (hasDuplicates(getRow(i)))
				return false;
		}

		for (int j = 0; j < LatinSquare.length; j++) {
			if (hasDuplicates(getColumn(j)))
				return false;
		}

		for (int i = 1; i < LatinSquare.length; i++) {

			if (!hasAllValues(getRow(0), getRow(i))) {
				return false;
			}
		}

		for (int j = 1; j < LatinSquare.length; j++) {

			if (!hasAllValues(getColumn(0), getColumn(j))) {
				return false;
			}
		}

		return isLatinSquare;
	}
	
	public static boolean isLatinSquare (int[][] arr) {
		
		boolean isLatinSquare = true;
		boolean hasDuplicates = false;
		Set<Integer> DuplicateSet = new HashSet<Integer>(); 

		for (int i = 0; i < arr.length; i++) {
			DuplicateSet.clear();
			
			for (int j : arr[i]) {
				if (DuplicateSet.add(j) == false) {
					if (j == 0 )
						return false;
				}
			}
		}

		for (int j = 0; j < arr.length; j++) {
			
			DuplicateSet.clear();
			
			int [] column = new int [arr.length];
			
			for (int i = 0; i < arr.length; i++) {
				column [i] = arr [i][j];
			}
			for (int i : column) {
				if (DuplicateSet.add(j) == false) {
					if (j == 0 )
						return false;
				}
			}
		}

		for (int i = 1; i < arr.length; i++) {

			//if (!hasAllValues(getRow(0), getRow(i))) {
			//	return false;
			//}
			
			boolean hasAllValues = true;
			if (arr[0].length == arr[i].length) {
				Arrays.sort(arr[0]);
				Arrays.sort(arr[i]);
				for (int j = 0; j < arr[0].length; i++) {
					if (arr[j] != arr[j])
						return false;
				}
			} else {
				return false;
			}
		}
		int [] column0 = new int [arr.length];
		for (int i = 0; i < arr.length; i++) {
			column0 [i] = arr [i][0];
		}
	
		
		for (int j = 1; j < arr.length; j++) {

			//if (!hasAllValues(getColumn(0), getColumn(j))) {
			//	return false;
			//}
			int [] column = new int [arr.length];
			
			for (int i = 0; i < arr.length; i++) {
				column [i] = arr [i][j];
			}
			boolean hasAllValues = true;
			if (arr[0].length == arr[j].length) {
				Arrays.sort(column0);
				Arrays.sort(column);
				for (int i = 0; i < arr[0].length; i++) {
					if (column0[i] != column[i])
						return false;
				}
			} else {
				return false;
			}
		}

	
			
		return true;
	}

	/**
	 * ContainsZero - return 'true' if any element in the LatinSquare is a zero
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @return - returns 'true' if any element in the LatinSquare is a zero
	 */
	public boolean ContainsZero() {
		
		
	for ( int i = 0; i < LatinSquare.length; i++) {
			for (int j : getRow(i)) {
				if (j == 0)
				return true;
				
			}
		
	}
	return false;
	}

	/**
	 * getPV - Return the collection of PuzzleViolations
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @return
	 */
	protected ArrayList<PuzzleViolation> getPV() {
		return PV;
	}
	
	/**
	 * getPV - Get all puzzle violations by type
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param ePV - given puzzle violation type
	 */	
	protected ArrayList<PuzzleViolation> getPV(ePuzzleViolation ePV)
	{
		ArrayList<PuzzleViolation> PuzzleV = new ArrayList<PuzzleViolation>();
		
		for (PuzzleViolation violation : PV) {
			if (violation.getePuzzleViolation()== ePV) {
				PuzzleV.add(violation);
			}
		}
		 
		if (PuzzleV.size() > 0) {
			return PuzzleV;
		}
		return null;
		
	}
	

	/**
	 * ClearPuzzleViolation - clear the collection of PuzzleViolations
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 */
	protected void ClearPuzzleViolation() {
		PV.clear();
	}

	/**
	 * AddPuzzleViolation - Add a PuzzleViolation to the collection
	 * 
	 * @version 1.0
	 * @since Midterm #1
	 * @param pv
	 */
	protected void AddPuzzleViolation(PuzzleViolation pv) {
		PV.add(pv);
	}
	

}
