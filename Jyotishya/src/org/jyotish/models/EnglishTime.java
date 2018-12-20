package org.jyotish.models;

import org.jyotish.exceptions.EnglishTimeException;

/**
 * Class representation of English time. Time format is 24 hours only.
 * <p>
 * If time exceeds maximum time of the day, then day variable will be updated and rest
 * of time attributes updated accordingly.
 * @author Chandan
 *
 */
public final class EnglishTime extends BaseTime implements Comparable<EnglishTime>{
	
	/**
	 * 
	 */
	private int mDay;

	/**
	 * 
	 */
	private int mHour;
	
	/**
	 * 
	 */
	private int mMinute;
	
	/**
	 * 
	 */
	private int mSecond;
	
	/**
	 * Remember this instantiate 2400 hour format.
	 * @param hour Hour
	 * @param minute Minute
	 * @param second Second
	 * @throws EnglishTimeException Exception due to invalid attributes
	 */
	public EnglishTime(int hour,int minute,int second)
			throws EnglishTimeException
	{
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		
		selfVerify();
	}

	
	@Override
	void selfVerify() 
		throws EnglishTimeException
	{
		if(mHour>23|mMinute>59|mSecond>59)
			throw new EnglishTimeException();
		//No limit on value of mDay
	}
	
	/**
	 * Say, seconds are rounded off and which in turn
	 * updates minutes. And minutes are rounded off and 
	 * which in turn update hours and so on..
	 */
	/* (non-Javadoc)
	 * @see org.jyotish.models.BaseTime#selfValidate()
	 */
	@Override
	public void selfCheck() throws EnglishTimeException{
		
		double totalTimeAsSeconds=getInFundamentalAttribute();
		//Determine day.
		mDay=(int)totalTimeAsSeconds/ModelConstants.A_DAY_IN_TERMS_OF_SECONDS;
		totalTimeAsSeconds-=(mDay*ModelConstants.A_DAY_IN_TERMS_OF_SECONDS);
		
		//Determine mHour,
		mHour=(int)totalTimeAsSeconds/ModelConstants.AN_HOUR_IN_TERMS_OF_SECONDS;
		totalTimeAsSeconds-=(mHour*ModelConstants.AN_HOUR_IN_TERMS_OF_SECONDS);
		
		//Determine mMinute,
		mMinute=(int)totalTimeAsSeconds/ModelConstants.A_MINUTE_IN_TERMS_OF_SECONDS;
		totalTimeAsSeconds-=(mMinute*ModelConstants.A_MINUTE_IN_TERMS_OF_SECONDS);
		
		//Determine mSecond,
		mSecond=(int)totalTimeAsSeconds;
		
		//Finally..
		selfVerify();
	}
	
	
	
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.mDay = days;
	}


	/**
	 * @return the mDays
	 */
	public int getDays() {
		return mDay;
	}


	/**
	 * @param hour the hour to set
	 * @see #selfCheck()
	 */
	public void setHour(int hour) {
		this.mHour = hour;
	}

	/**
	 * @return the mHour
	 */
	public int getHour() {
		return mHour;
	}

	/**
	 * @param minute the minute to set
	 *  @see #selfCheck()
	 */
	public void setMinute(int minute) {
		this.mMinute = minute;
	}

	/**
	 * @return the mMinute
	 */
	public int getMinute() {
		return mMinute;
	}

	/**
	 * @param second the second to set
	 *  @see #selfCheck()
	 */
	public void setSecond(int second) {
		this.mSecond = second;
	}

	/**
	 * @return the mSecond
	 */
	public int getSecond() {
		return mSecond;
	}


	@Override
	public String toString() {
		
		return "[HH:MM:SS] "/*+getDays()+":"*/
			+(getHour()<10?"0"+getHour():getHour())
			+":"
			+(getMinute()<10?"0"+getMinute():getMinute())
			+":"
			+(getSecond()<10?"0"+getSecond():getSecond());
	}

	/**
	 * Represents instance in terms of seconds.
	 * @return All attributes together expressed in terms of seconds 
	 */
	@Override
	public double getInFundamentalAttribute() {
		
		return (getDays()*24*60*60*60)+(getHour()*60*60)+(getMinute()*60)+getSecond();
	}


	/**
	 * Implementation of {@link Comparable}.
	 * @param englishTime instance which has to be compared against.
	 * @return int -ve/0/+ve value. 
	 * <p> -ve if invoking {@link EnglishTime} instance is older 
	 * 			than parameter {@link EnglishTime} instance
	 * <p> 0 if invoking {@link EnglishTime} instance has same time attributes 
	 * 			that of parameter {@link EnglishTime} instance
	 * <p> +ve if invoking {@link EnglishTime} instance is recent 
	 * 			than parameter {@link EnglishTime} instance
	 */
	@Override
	public int compareTo(EnglishTime englishTime) {

		//Assume oldDate is older.
		double isInvokerIsOlder=0;
		
		/* 'm I a donkey  to do such a long procedure?
		int isInvokerIsOlder=-1;
		//Verify weather old and recent dates are relatively old and recent.
		if(englishTime.getDays()<this.getDays()){
			isInvokerIsOlder=1;
		}else if(englishTime.getDays()==this.getDays()){
			if(englishTime.getHour()<this.getHour()){
				isInvokerIsOlder=1;
			} else if(englishTime.getHour()==this.getHour()){
				if(englishTime.getMinute()<this.getMinute()){
					isInvokerIsOlder=1;
				}else if(englishTime.getMinute()==this.getMinute()){
					 if(englishTime.getSecond()<this.getSecond()){
						 isInvokerIsOlder=1;
					 }else if(englishTime.getSecond()==this.getSecond()){
						 //no need to change as both time are same..
						 isInvokerIsOlder=0;
					 }
				}
			}		
		}
		
		return isInvokerIsOlder;
		*/
		
		//No way!, I am smart :)
		isInvokerIsOlder=this.getInFundamentalAttribute()-englishTime.getInFundamentalAttribute();
		
		return (isInvokerIsOlder==0?0:(isInvokerIsOlder<0?-1:1));
	}


}
