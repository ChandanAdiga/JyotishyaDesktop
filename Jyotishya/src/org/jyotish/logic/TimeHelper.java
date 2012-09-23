package org.jyotish.logic;

import org.chandan.java.logging.LogType;
import org.jyotish.exceptions.EnglishTimeException;
import org.jyotish.exceptions.JyotishyaTimeException;
import org.jyotish.models.EnglishTime;
import org.jyotish.models.JyotishyaTime;
import org.jyotish.models.ModelConstants;

/**
 * Provides both {@link EnglishTime} and {@link JyotishyaTime} related processing.
 * @author Chandan
 *
 */
final class TimeHelper{
	
	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	@SuppressWarnings("unused")
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	/**
	 * Tag value for logging.
	 */
	@SuppressWarnings("unused")
	private final static String TAG=TimeHelper.class.getSimpleName();
	
	
	
	/************************* ENGLISH TIME ****************************************/
	

	/**Checks whether first parameter is relatively older than second.
	 * @param oldTime Relatively old time
	 * @param recentTime Relatively recent time.
	 * @return True if first parameter is relatively older than second. False if 
	 * 		they don't mean their names.
	 */
	public boolean isFirstTimeParamIsOlder(EnglishTime oldTime,EnglishTime recentTime){
		
		//Assume oldDate is older.
		boolean isFirstParamIsOlder=true;
		//Verify weather old and recent dates are relatively old and recent.
		if(recentTime.getDays()<oldTime.getDays()){
			isFirstParamIsOlder=false;
		}else if(recentTime.getDays()==oldTime.getDays()){
			if(recentTime.getHour()<oldTime.getHour()){
				isFirstParamIsOlder=false;
			} else if(recentTime.getHour()==oldTime.getHour()){
				if(recentTime.getMinute()<oldTime.getMinute()){
					isFirstParamIsOlder=false;
				}else if(recentTime.getMinute()==oldTime.getMinute()){
					 if(recentTime.getSecond()<oldTime.getSecond()){
						 isFirstParamIsOlder=false;
					 }//else no need to change as both time are same or they may mean their names..	
				}
			}		
		}
		
		return isFirstParamIsOlder;
	}
	
	
	
	/**
	 * Processes the difference between two {@link EnglishTime} instances.
	 * <p>
	 * This method assumes time variables mean their names. i.e old and recent. If you want 
	 * to validate time values to mean their names peep in to 
	 * {@link #isFirstTimeParamIsOlder(EnglishTime, EnglishTime) isFirstTimeParamIsOlder()}
	 * 
	 * @see EnglishTime#compareTo(EnglishTime) compareTo()
	 * 
	 * @param oldTime Relatively older time.
	 * @param recentTime Relatively recent time.
	 * @return Difference between two times.
	 * @throws EnglishTimeException If invalid difference
	 * 
	 * 
	 */
	public EnglishTime getDifference(EnglishTime oldTime,EnglishTime recentTime) 
							throws EnglishTimeException{
		
		EnglishTime timeDifference=new EnglishTime(0, 0, 0);
		
		//All is set. So, proceed with difference..
		
		timeDifference.setSecond((int)
				(recentTime.getInFundamentalAttribute()
						-oldTime.getInFundamentalAttribute()));
		/*MALLFUNCTIONING CODE..
		timeDifference.setDays(recentTime.getDays()-oldTime.getDays());
		timeDifference.setHour(recentTime.getHour()-oldTime.getHour());
		timeDifference.setMinute(recentTime.getMinute()-oldTime.getMinute());
		timeDifference.setSecond(recentTime.getSecond()-oldTime.getSecond());
		*/
		
		//Finally,
		timeDifference.selfCheck();
		
		return timeDifference;		
	}
	
	/**
	 * Processes the sum of two {@link EnglishTime} instances.
	 * @param oldTime Relatively older time.
	 * @param recentTime Relatively recent time.
	 * @return Sum of two times.
	 * @throws EnglishTimeException If invalid sum
	 */
	public EnglishTime getSum(EnglishTime oldTime,EnglishTime recentTime)
							throws EnglishTimeException{
		
		EnglishTime timeSum=new EnglishTime(0, 0, 0);
		
		timeSum.setSecond((int)
				(recentTime.getInFundamentalAttribute()
				+oldTime.getInFundamentalAttribute()));
		
		/*//MALLFUNCTIONING CODE
		//All is set. So, proceed with summing up..
		timeSum.setDays(recentTime.getDays()+oldTime.getDays());
		timeSum.setHour(recentTime.getHour()+oldTime.getHour());
		timeSum.setMinute(recentTime.getMinute()+oldTime.getMinute());
		timeSum.setSecond(recentTime.getSecond()+oldTime.getSecond());*/
		
		//Finally,
		timeSum.selfCheck();
		
		
		return timeSum;		
	}
	
	
	
	/******************************* JYOTISHYA TIME ***************************************/
	
	
	/**Checks whether first parameter is relatively older than second.
	 * @param oldTime Relatively old time
	 * @param recentTime Relatively recent time.
	 * @return True if first parameter is relatively older than second. False if 
	 * 		they don't mean their names.
	 */
	public boolean isFirstTimeParamIsOlder(JyotishyaTime oldTime,JyotishyaTime recentTime){
		
		//Assume oldDate is older.
		boolean isFirstParamIsOlder=true;
		//Verify weather old and recent dates are relatively old and recent.
		if(recentTime.getGalige()<oldTime.getGalige()){
			isFirstParamIsOlder=false;
		}else if(recentTime.getGalige()==oldTime.getGalige()){
			if(recentTime.getVigalige()<oldTime.getVigalige()){
				isFirstParamIsOlder=false;
			} //else no need to change as both time are same or they may mean their names..
						
		}
		
		return isFirstParamIsOlder;
	}
	
	
	/**
	 * Processes the difference between two {@link JyotishyaTime} instances.
	 * <p>
	 * This method assumes time variables mean their names. i.e old and recent. If you want 
	 * to validate time values to mean their names peep in to 
	 * {@link #isFirstTimeParamIsOlder(JyotishyaTime, JyotishyaTime) isFirstTimeParamIsOlder()}
	 * 
	 * @see JyotishyaTime#compareTo(JyotishyaTime) compareTo()
	 * 
	 * Processes the difference between two {@link JyotishyaTime} instances.
	 * @param oldTime Relatively older time.
	 * @param recentTime Relatively recent time.
	 * @return Difference between two times.
	 * @throws JyotishyaTimeException If invalid difference
	 */
	public JyotishyaTime getDifference(JyotishyaTime oldTime,JyotishyaTime recentTime)
							throws JyotishyaTimeException{
		
		JyotishyaTime timeDifference=new JyotishyaTime(0, 0);
		
		timeDifference.setVigalige((int)
				(recentTime.getInFundamentalAttribute()
						-oldTime.getInFundamentalAttribute()));
		
		/*MALLFUNCTIONING CODE
		timeDifference.setGalige(recentTime.getGalige()-oldTime.getGalige());
		timeDifference.setGalige(recentTime.getGalige()-recentTime.getGalige());*/
		
		//Finally,
		timeDifference.selfCheck();
		
		return timeDifference;		
	}
	
	
	/**
	 * Processes the sum of two {@link JyotishyaTime} instances.
	 * @param oldTime Relatively older time.
	 * @param recentTime Relatively recent time.
	 * @return Sum of two times.
	 * @throws JyotishyaTimeException If invalid sum
	 */
	public JyotishyaTime getSum(JyotishyaTime oldTime,JyotishyaTime recentTime)
							throws JyotishyaTimeException{
		
		JyotishyaTime timeSum=new JyotishyaTime(0, 0);
		
		timeSum.setVigalige((int)
				(recentTime.getInFundamentalAttribute()
						+oldTime.getInFundamentalAttribute()));
		
		/*//MALLFUNCIONING CODE..
		timeSum.setGalige(recentTime.getGalige()+oldTime.getGalige());
		timeSum.setGalige(recentTime.getGalige()+recentTime.getGalige());
		*/
		
		//Finally..,
		timeSum.selfCheck();
		
		return timeSum;		
	}
	
	
	/*************************** ENGLISH & JYOTISHYA CONVERTIONS **********************/
	
	
	/** Converts given time from Galige-Vigalige format into Hours-Minute-Second format.
	 * As conversion mostly affects only minutes and hours, second will be mostly 0.
	 * @param jyotishyaTime Time in Galige-Vigalige format which needs to be converted.
	 * @return Time in Hours-Minutes-Seconds format.
	 * @throws EnglishTimeException Invalid attributes due to conversion.
	 */
	public EnglishTime convertToEnglishTime(JyotishyaTime jyotishyaTime)
							throws EnglishTimeException{
		
		EnglishTime englishTime=new EnglishTime(0, 0, 0);;
				
		// 2.5 Galige = 1 Hour.
		// 2.5 * 60 Vigalige = 1 * 60 Minutes.
		// => 2.5 Vigalige =1 minute.
		
		englishTime.setMinute(
				(int)(jyotishyaTime.getInFundamentalAttribute()
					/(float)ModelConstants.A_ENGLISH_MINUTE_IN_TERMS_OF_VIGALIGE)
				);
		
		//As selfCheck() will do the rest of the formatting work..
		englishTime.selfCheck();
				
		return englishTime;
	}
	
	/** Converts given English time into Galige-Vigalige format. As conversion hardly 
	 * depends on seconds, it is mostly ignored.
	 * @param englishTime Time in English format which needs to
	 * 						 be represented in Galige-Vigalige format
	 * @return Time in Galige-Vigalige format.
	 * @throws JyotishyaTimeException If invalid attributes due to conversion
	 */
	public JyotishyaTime convertToJyotishyaTime(EnglishTime englishTime)
							throws JyotishyaTimeException{
		
		JyotishyaTime jyotishyaTime=new JyotishyaTime(0, 0);
		
		// 2.5 Galige = 1 Hour.
		// 2.5 * 60 Vigalige = 1 * 60 Minutes.
		// => 2.5 Vigalige =1 minute.
		
		//DEBUGGED '/' changed to '*' to correct conversion bug!
		jyotishyaTime.setVigalige((int)
				((englishTime.getInFundamentalAttribute()/60)//get in terms of minutes.
						*(float)ModelConstants.A_ENGLISH_MINUTE_IN_TERMS_OF_VIGALIGE)
				);
		
		//[FIXED Surya udayadi ghati was expressed only in terms of Vigalige.
		jyotishyaTime.selfCheck();
		//]
		
		return jyotishyaTime;
	}
	
	
}
