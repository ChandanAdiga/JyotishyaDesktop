/**
 * 
 */
package org.chandan.java.logging;

/**
 * Specifies how logging should be processed.
 * @author Chandan
 *
 */
public enum LogType {
	
	/**
	 * Holds logging. i.e Nothing will be logged..
	 */
	NONE,

	/**
	 * Writes all log to console
	 */
	TO_CONSOLE,
	
	/**
	 * Writes all log to a file
	 */
	@Deprecated
	TO_FILE,
	
	/**
	 * Write all log to database.
	 */
	@Deprecated
	TO_DATABASE
}
