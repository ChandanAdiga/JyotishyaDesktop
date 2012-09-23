
package org.jyotish.models;

import org.jyotish.exceptions.BaseTimeException;

/**
 * @author Chandan
 *
 */
abstract class BaseTime {
	
	/**
	 * Method which verifies its attributes are valid or not.
	 * @throws BaseTimeException Exception due to invalid Time attributes
	 */
	abstract void selfVerify() throws BaseTimeException;

	/**
	 * Method which validates its attributes. 
	 * @throws BaseTimeException Exception due to invalid Time attributes
	 */
	public abstract void selfCheck() throws BaseTimeException;
	
	/**
	 * Returns formated readable string representation.
	 */
	public abstract String toString();
	
	/**
	 * Represents all attributes summed up in its fundamental attribute. Say for time, second
	 * is common fundamental attribute. So this method may return entire time(hour,minute & sec) 
	 * represented in terms of seconds.
	 * @return Fundamental representation of the type.
	 */
	public abstract double getInFundamentalAttribute();
	
}
