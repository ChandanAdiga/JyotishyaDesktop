package org.jyotish.models;

import org.jyotish.exceptions.CustomDateException;

/**
 * @author Chandan
 *
 */
public final class CustomDate implements Comparable<CustomDate>{
	
	/**
	 * 
	 */
	private int mDay;
	
	/**
	 * 
	 */
	private int mMonth;
	
	/**
	 * 
	 */
	private int mYear;
	

	/** Note that each attribute can not exceed their maximum value.
	 * @param day day to set
	 * @param month month to set
	 * @param year year to set
	 * @throws CustomDateException Exception due to invalid attributes.
	 */
	public CustomDate(int day, int month, int year)
			throws CustomDateException 
	{		
		setDay(day);
		setMonth(month);
		setYear(year);
		selfVerify();
	}
	
	/**
	 * Verifies weather all the attributes are valid or not.
	 * @throws CustomDateException Exception due to Invalid Date attributes.
	 */
	private void selfVerify() 
		throws CustomDateException{
		boolean flag=false;
		
		//Check for leap year..
		flag=((mYear/4==0)|(mYear/100==0)|(mYear/400==0));
		if(flag)
		{
			//It's a leap!
			flag=false;
			flag=mDay>29;
		}
		else
		{
			//No..It's not a leap..
			flag=mDay>28;
		}
		//Verify mMonth..
		flag|=(mMonth>12);
		
		//Finally..
		if(flag)
			throw new CustomDateException();
			
	}
	
	/**
	 * Validates the attributes. Say, if day is more than the threshold, month 
	 * is updated accordingly. And if month is more than threshold, year is 
	 * updated accordingly.
	 * @throws CustomDateException Exception due to invalid Date attributes
	 */
	@Deprecated 
	public void selfValidate()throws CustomDateException{
		
		//boolean isLeapYear=false;		
		//Check for leap year..
		//isLeapYear=((mYear/4==0)|(mYear/100==0)|(mYear/400==0));
		
		//if(isLeapYear)
		//{
		//...	
		//}
		throw new CustomDateException();
		
	}
	
	/**
	 * Setter method for day.
	 * @param day day to set.
	 */
	public void setDay(int day)
	{
		mDay=day;
	}
	
	/**
	 * Getter method for day
	 * @return day of the month
	 */
	public int getDay()
	{
		return mDay;
	}
	
	/**
	 * Setter method for month
	 * @param month month to set
	 */
	public void setMonth(int month)
	{
		mMonth=month;
	}
	
	/**
	 * Getter method for month
	 * @return month
	 */
	public int getMonth()
	{
		return mMonth;
	}
	
	/**
	 * Setter method for year
	 * @param year year to set
	 */
	public void setYear(int year)
	{
		mYear=year;
	}
	
	/**
	 * getter method for year
	 * @return year
	 */
	public int getYear()
	{
		return mYear;
	}
	
	/**
	 * Implementation of {@link Comparable}.
	 * @param customDate instance which has to be compared against.
	 * @return int -ve/0/+ve value. 
	 * <p> -ve if invoking {@link CustomDate} instance is older 
	 * 			than parameter {@link EnglishTime} instance
	 * <p> 0 if invoking {@link CustomDate} instance has same date attributes 
	 * 			that of parameter {@link CustomDate} instance
	 * <p> +ve if invoking {@link CustomDate} instance is recent 
	 * 			than parameter {@link CustomDate} instance
	 */
	@Override
	public int compareTo(CustomDate customDate) {
		//Assume oldDate is old.
		int isFirstParamIsOlder=-1;
		//Verify weather old and recent dates are relatively old and recent.
		if(customDate.getYear()<this.getYear()){
			isFirstParamIsOlder=1;
		}else if(customDate.getYear()==this.getYear()){
			if(customDate.getMonth()<this.getMonth()){
				isFirstParamIsOlder=1;
			} else if(customDate.getMonth()==this.getMonth()){
				if(customDate.getDay()<this.getDay()){
					isFirstParamIsOlder=1;
				}else if(customDate.getDay()==this.getDay()){
					// no need to change as both dates are same..
					isFirstParamIsOlder=0;
				}
			}		
		}
		
		return isFirstParamIsOlder;
	}
	
	/**
	 * Returns month  in readable string format.
	 * @param month Index of the month, starting from '1'.
	 * @return Name of the month
	 */
	public static String getMonthName(int month){
		String monthName="Unknown month:"+month;
		
		switch (month)
		{
			case 1:
				monthName="January";
				break;
				
			case 2:
				monthName="February";
				break;
				
			case 3:
				monthName="March";
				break;
				
			case 4:
				monthName="April";
				break;
				
			case 5:
				monthName="May";
				break;
				
			case 6:
				monthName="June";
				break;
				
			case 7:
				monthName="July";
				break;
				
			case 8:
				monthName="August";
				break;
				
			case 9:
				monthName="September";
				break;
				
			case 10:
				monthName="October";
				break;
				
			case 11:
				monthName="November";
				
			case 12:
				monthName="December";
				break;
				
		}
		
		return monthName;
	}
	

	@Override
	public String toString()
	{
		return ""+(getDay()<10?"0"+getDay():getDay())
			+" "
			+getMonthName(getMonth())
			+" "
			+getYear();
	}

}
