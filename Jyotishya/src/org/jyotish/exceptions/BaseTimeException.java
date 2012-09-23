package org.jyotish.exceptions;

/**
 * @author Chandan
 *
 */
public class BaseTimeException extends Exception {

	
	/**
	 * 
	 */
	private final String mGeneralTimeException="Invalid Time attributes!";
	
	/**
	 * System generated ID.
	 */
	private static final long serialVersionUID = 2907639156950378140L;
	
	@Override
	public String toString()
	{		
		return mGeneralTimeException;
	}
}
