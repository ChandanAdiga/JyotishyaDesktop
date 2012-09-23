package org.jyotish.exceptions;


/**
 * Customized {@link Exception} which represents invalid Date attributes.
 * @author Chandan
 *
 */
public final class CustomDateException extends Exception {

	/**
	 * 
	 */
	private final String mGeneralDateException="Invalid Date attributes!";
	/**
	 * System generated ID.
	 */
	private static final long serialVersionUID = 2907639156950378140L;
	
	@Override
	public String toString()
	{		
		return mGeneralDateException;
	}
	
}
