package org.jyotish.exceptions;

/**
 * @author Chandan
 *
 */
public final class EnglishTimeException extends BaseTimeException {

	/**
	 * 
	 */
	private final String mInvalidEnglishTimeAttributesException="Invalid English Time attributes!";
	
	/**
	 * System generated ID.
	 */
	private static final long serialVersionUID = 2907639156950378140L;
	
	@Override
	public String toString()
	{		
		return mInvalidEnglishTimeAttributesException;
	}
}
