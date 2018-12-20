package org.chandan.java.logging;

/**
 * Helper class to process logging.
 * @author Chandan
 *
 */
public final class LogManager {
	//TODO Make jar of this once stabilized. And use as lib
	
	/** 
	 * Processes log dumped by exceptions. Note that complete exception stack trace 
	 * will be dumped.
	 * @param type Type, how log should be processed.
	 * @param tag  Title of the log
	 * @param e Exception object caught, whose stack trace has to be logged.
	 */
	public static void processException(LogType type,String tag,Exception e){
		//Begining..
		logLineSeperator(type);
		processLog(type,tag, e.toString());
		
		//Next, print stack trace..		
		for(StackTraceElement element:e.getStackTrace()){
			processLog(type, Constants.EXCEPTION_TAG, element.toString());
		}
		//Finally,
		logLineSeperator(type);
			
	}

	/**
	 * @param type How log should be processed. See {@link LogType}
	 * @param tag Heading of the log
	 * @param message Body of the log.
	 */
	public static void processLog(LogType type,String tag,String message){
		//First..,
		if(type==null)
			type=LogType.TO_CONSOLE;
		
		/*
		 * If 'tag' is null, it will be handled in formatTag() method.
		 * if 'message' is null, then we must process just like any other
		 * message[which prints 'null'. As user may be checking value 
		 * of some attributes.
		 */
		
		//All is fine, So..
		formatTag(tag);
			
		//Finally..,
		switch (type) {
		
		case NONE:
			//Do nothing..
			break;
		
		case TO_CONSOLE:
			processConsoleLog(tag, message);
			break;
		/* To be defined shortly..
		case TO_FILE:
			processFileLog(tag, message);
			break;
			
		case TO_DATABASE:
			processDatabaseLog(tag, message);
			break;
 		*/
		default:
			logLineSeperator(LogType.TO_CONSOLE);			
			processConsoleLog(tag, message);
			logLineSeperator(LogType.TO_CONSOLE);
			break;
		}
		
	}
	
	/**
	 * Simply prints a line. This could be used to group similar logs and hence
	 * organize log for more readability.
	 * @param type type, how log should be processed.
	 */
	public static void logLineSeperator(LogType type){
		
		switch (type) {		
		case TO_CONSOLE:
			processConsoleLog(Constants.LINE_FEED_TAG, Constants.LINE_FEED_MESSAGE);
			break;
		/* To be defined shortly..
		case TO_FILE:
			processFileLog(Constants.LINE_FEED_TAG, Constants.LINE_FEED_MESSAGE);
			break;
			
		case TO_DATABASE:
			processDatabaseLog(Constants.LINE_FEED_TAG, Constants.LINE_FEED_MESSAGE);
			break;
 		*/
		default:			
			processConsoleLog(Constants.LINE_FEED_TAG, Constants.LINE_FEED_MESSAGE);
			break;
		}
	}
	
	/**
	 * @param tag Heading of the log
	 * @param message Body of the log
	 */
	private static void processConsoleLog(String tag,String message){
		System.out.println(tag+":\t"+message);
	}

	
	/**
	 * @param tag Heading of the log
	 * @param message Body of the log
	 */
	@SuppressWarnings("unused")
	private static void processFileLog(String tag,String message){
		//Remember to update line feed urself over here..
	}
	
	/**
	 * @param tag Heading of the log
	 * @param message Body of the log
	 */
	@SuppressWarnings("unused")
	private static void processDatabaseLog(String tag,String message){
	}
	
	
	/**
	 * Formats the tag for more readability..
	 * @param tag Tag of the log
	 * @return Formated tag.
	 */
	private static String formatTag(String tag){
		/**
		 * Round off tag if its length is more than Constansts.LINE_FEED_TAG's characters..
		 */
		if(tag!=null)
		{
			final int tagLength=tag.length();
			final int maxTagLength=Constants.LINE_FEED_TAG.length();
			
			if(tagLength>=maxTagLength){
				//Trim the tag..
				tag=tag.substring(0, maxTagLength-1);
			}
			else{
				//Uppend white spaces to the tag..
				while(tag.length()>=maxTagLength)
				{
					tag+=" ";
				}
			}
		}
		else
		{
			tag=Constants.LINE_FEED_TAG;
		}
		
		return tag;
	}
	
	
}
