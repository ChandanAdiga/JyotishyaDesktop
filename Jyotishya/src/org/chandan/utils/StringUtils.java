package org.chandan.utils;

/**
 * Provides utilities to work around {@link String}
 * @author chandan
 *
 */
public final class StringUtils {

	/**
	 * Checks whether the string value is null or if size is 0.
	 * @param value {@link String} value to be checked.
	 * @return true if null/size is zero. Else false.
	 */
	public static boolean isEmpty(String value){
		return (value==null|value.length()==0);
	}
	
	//TODO Update utility methods which might be useful. So that this class can be jarred :P
	
}
