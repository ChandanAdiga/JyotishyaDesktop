package org.jyotish.exceptions;

/**
 * @author Chandan
 *
 */
public final class JyotishyaTimeException extends BaseTimeException {

	/**
	 * 
	 */
	private final String mInvalidJyotishyaTimeAttributesException="Invalid Jyotishya Time attributes!";
	
	/**
	 * System generated ID.
	 */
	private static final long serialVersionUID = 2907639156950378140L;
	
	@Override
	public String toString()
	{		
		return mInvalidJyotishyaTimeAttributesException;
	}
}
