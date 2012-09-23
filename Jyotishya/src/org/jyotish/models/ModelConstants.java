/**
 * 
 */
package org.jyotish.models;

import org.chandan.java.logging.LogType;

/**
 * Final class which contains all the constants associated with model of the project.
 * @author Chandan
 *
 */
public final class ModelConstants {
	
	/**
	 * Default type of logging for the entire project..
	 * i.e {@link LogType#TO_CONSOLE TO_CONSOLE}
	 */
	public static final LogType PROJECT_LOG_TYPE=LogType.TO_CONSOLE;
	
	/**
	 * Default delay after which splash screen should be switched to next screen.
	 */
	public static final short SPLASH_DELAY_IN_MILI_SEC=2000;//;5000;

	/**
	 * Value of a day expressed in terms of seconds
	 */
	public static final int A_DAY_IN_TERMS_OF_SECONDS=60*60*24;
	
	/**
	 * Value of a day expressed in terms of seconds
	 */
	public static final int AN_HOUR_IN_TERMS_OF_SECONDS=60*60;
	
	/**
	 * Value of a day expressed in terms of seconds
	 */
	public static final int A_MINUTE_IN_TERMS_OF_SECONDS=60;
	
	/**
	 * Value of a Galige expressed in terms of Vigalige
	 */
	public static final int A_GALIGE_IN_TERMS_OF_VIGALIGE=60;
	
	/**
	 * Value of an English hour expressed in terms of Galige.
	 * <p>
	 * Note, 1 HOUR = 2.5 GALIGE.
	 */
	public static final float AN_ENGLISH_HOUR_IN_TERMS_OF_GALIGE=2.5F;
	
	/**
	 * Value of an English hour expressed in terms of Galige.
	 * <p>
	 * Note, 1 HOUR = 2.5 GALIGE. 
	 * <p>
	 * => 1 Minute = 2.5 VIGALIGE [As 60 gets cancelled on either side]
	 */
	public static final float A_ENGLISH_MINUTE_IN_TERMS_OF_VIGALIGE=2.5F;
	
	
	
}
