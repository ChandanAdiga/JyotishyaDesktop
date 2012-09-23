package org.jyotish.logic;

import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.exceptions.CustomDateException;
import org.jyotish.models.CustomDate;
import org.jyotish.models.ModelConstants;

/**
 * @author Chandan
 *
 */
public final class CustomDateHelper {

	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	/**
	 * Tag used for logging..
	 */
	private static final String TAG=CustomDateHelper.class.getSimpleName();
	
	
	/**
	 * Process the difference between two dates. Ensure oldDate is older than recentDate,
	 * 	though they will be internally evaluated.
	 * @param oldDate Relatively older date.
	 * @param recentDate Relatively recent date.
	 * @return difference between oldDate and recentDate expressed as days.
	 */
	@Deprecated
	public long getDifference(CustomDate oldDate,CustomDate recentDate){
		
		long dateDifference=0;
		
		if(oldDate.getYear()-recentDate.getYear()
				+oldDate.getMonth()-recentDate.getMonth()
				+oldDate.getDay()-recentDate.getDay()!=0){
			//Dates are different..So proceed to find the difference..
			//..
			//..
			//..
			
			
		}//else both dates are same -->dateDifference is already '0'.. So just return.
			
		
		return  dateDifference;
	}
	
	
	
	
	
	/**Checks whether first parameter is relatively older than second.
	 * @param oldDate Relatively old time
	 * @param recentDate Relatively recent time.
	 * @return True if first parameter is relatively older than second. False if 
	 * 		they don't mean their names.
	 */
	public boolean isFirstDateParamIsOlder(CustomDate oldDate,CustomDate recentDate){
		
		//Assume oldDate is old.
		boolean isFirstParamIsOlder=true;
		//Verify weather old and recent dates are relatively old and recent.
		if(recentDate.getYear()<oldDate.getYear()){
			isFirstParamIsOlder=false;
		}else if(recentDate.getYear()==oldDate.getYear()){
			if(recentDate.getMonth()<oldDate.getMonth()){
				isFirstParamIsOlder=false;
			} else if(recentDate.getMonth()==oldDate.getMonth()){
				if(recentDate.getDay()<oldDate.getDay()){
					isFirstParamIsOlder=false;
				}//else no need to change as both dates are same or they may mean their names..
			}		
		}
		
		return isFirstParamIsOlder;
	}
	
	/**
	 * Processes summing up of two dates.
	 * @param oldDate Relatively old date.
	 * @param recentDate Relatively recent date.
	 * @return Sum of above two dates.
	 */
	@Deprecated
	public CustomDate getSum(CustomDate oldDate,CustomDate recentDate){
		CustomDate dateSum=null;
		try {
			dateSum=new CustomDate(0, 0, 0);
		} catch (CustomDateException e) {
			// TODO Yet to write code for sum of two dates
			LogManager.processException(MY_LOG_TYPE,TAG, e);
		}
		
		//...
		//...
		//...
		
		return dateSum;
	}
	
	
	
	
}
