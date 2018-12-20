package org.chandan.java.views;

import java.util.Calendar;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.models.ModelConstants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel consisting of date views. Also provides corresponding 
 * getter and setter methods. Note use of setter methods for date attributes 
 * is not expected and hence made deprecated.
 * @author chandan
 *
 */
public final class MyDatePicker extends JPanel {

	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=MyDatePicker.class.getSimpleName();

	/**
	 * DateView's width
	 */
	private static final short DATEVIEW_PANEL_WIDTH=330;
	
	/**
	 * DateView's height
	 */
	private static final short DATEVIEW_PANEL_HEIGHT=30;
	
	
	/**
	 * Generated version ID.
	 */
	private static final long serialVersionUID = 2429478144962436792L;
	
	/**
	 * Invalid selected date attributes.
	 */
	public static final int ERROR_CODE_INVALID_DATE_ATTRIBUTE=-1;
	
	/**
	 * Header position[usually -> '0']
	 */
	private static final int PICKER_HEADER_POSITION=0;
	
	/**
	 * Selected year.
	 * @see #BASE_YEAR
	 */
	private int mSelectedYear=ERROR_CODE_INVALID_DATE_ATTRIBUTE;
	
	/**
	 * Selected month.
	 */
	private int mSelectedMonth=ERROR_CODE_INVALID_DATE_ATTRIBUTE;
	
	/**
	 * Selected day.
	 */
	private int mSelectedDay=ERROR_CODE_INVALID_DATE_ATTRIBUTE;

	/**
	 * Assumed this application uses dates 
	 * starting from 1900 years and not earlier ones.
	 */
	private static final int BASE_YEAR=1900;
	
	/**
	 * enum defines all 12 months in order.
	 * @author chandan
	 *
	 */
	public static  enum MONTH{
		/** */January,/** */February,/** */March,/** */April,
		/** */May,/** */June,/** */July,/** */August,
		/** */September,/** */October,/** */November,/** */December
	}
		
	/**
	 * All months put together as array.
	 * <p>
	 * NOTE: Indexing starts from 0 to 11.
	 */
	private final String [] MONTH_NAME_ARRAY=new String[]{
		MONTH.January.name(),MONTH.February.name(),
		MONTH.March.name(),MONTH.April.name(),
		MONTH.May.name(),MONTH.June.name(),
		MONTH.July.name(),MONTH.August.name(),
		MONTH.September.name(),MONTH.October.name(),
		MONTH.November.name(),MONTH.December.name()
	};
	
	
	/**
	 * Specifies type of month to determine no of days for that month.
	 * @author chandan
	 *
	 */
	private enum MONTH_TYPE{
		/**
		 * Month of type 31 days.
		 */
		OF_31_DAYS,
		
		/**
		 * Month of type 30 days.
		 */
		OF_30_DAYS,
		
		/**
		 * Month of type 29 days.
		 */
		OF_29_DAYS,
		
		/**
		 * Month of type 28 days.
		 */
		OF_28_DAYS
		
	}
	
	/**
	 * Vector holding days
	 */
	private Vector<String> mDayVector;
	
	/**
	 * Vector holding month names.
	 */
	private Vector<String> mMonthVector;
	
	/**
	 * Vector holding year names.
	 */
	private Vector<String> mYearVector;
	
	/**
	 * Day picker
	 */
	private JComboBox<String> mDayPicker;
	
	/**
	 * Month picker
	 */
	private JComboBox<String> mMonthPicker;
	
	/**
	 * Year picker
	 */
	private JComboBox<String> mYearPicker;
	
	/**
	 * Day's model
	 */
	private DefaultComboBoxModel<String> mDayModel;
	
	/**
	 * Month's model
	 */
	private DefaultComboBoxModel<String> mMonthModel;
	
	/**
	 * Year's model
	 */
	private DefaultComboBoxModel<String> mYearModel;

	
	/**
	 * Constructor.
	 */
	public MyDatePicker(){
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating DateView..");
		
		setSize(DATEVIEW_PANEL_WIDTH, 
				DATEVIEW_PANEL_HEIGHT);
		
		initialiseModel();
		
		mDayPicker=new JComboBox<String>(mDayModel);
		mMonthPicker=new JComboBox<String>(mMonthModel);
		mYearPicker=new JComboBox<String>(mYearModel);
		
		mDayPicker.setPreferredSize(new Dimension(120, DATEVIEW_PANEL_HEIGHT-8));
		mMonthPicker.setPreferredSize(new Dimension(110, DATEVIEW_PANEL_HEIGHT-8));
		mYearPicker.setPreferredSize(new Dimension(100, DATEVIEW_PANEL_HEIGHT-8));
		
		mDayPicker.setMaximumRowCount(5);
		mMonthPicker.setMaximumRowCount(5);
		mYearPicker.setMaximumRowCount(5);
		
		/* Not required so..
		mDayPicker.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//DO nothing..
			}
		});
		*/
		
		mMonthPicker.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				updateDayModel();
			}
		});
		
		mYearPicker.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				updateMonthModel();
			}
		});
				
		//Do not change order of adding..of course you know tat ;)
		add(mYearPicker);
		add(mMonthPicker);
		add(mDayPicker);
			
	}

	
	/**
	 * Prepares data models for year,month and days.
	 * Also, sets default values for the same.
	 */
	private void initialiseModel(){
		
		mDayVector=new Vector<String>();		
		mMonthVector=new Vector<String>();
		mYearVector=new Vector<String>();
		
		mDayVector.add("Day");
		mMonthVector.add("Month");
		mYearVector.add("Year");
		
		mDayModel=new DefaultComboBoxModel<String>(mDayVector);
		mMonthModel=new DefaultComboBoxModel<String>(mMonthVector);
		mYearModel=new DefaultComboBoxModel<String>(mYearVector);
		
		//As rest of the stuffs are common as that of,
		resetDateView();

	}
	
	/**
	 * Resets combo box model information.
	 */
	public void resetDateView(){
		//As below are deprecated..,
		//setSelectedYear(PICKER_HEADER_POSITION);
		//setSelectedMonth(PICKER_HEADER_POSITION);
		//setSelectedDay(PICKER_HEADER_POSITION);
		//set directly..
		mSelectedYear=mSelectedMonth=mSelectedDay=PICKER_HEADER_POSITION;
		
		//evaluate year picker model
		updateYearModel();
		
		//evaluate month picker model
		updateMonthModel();
		
		//evaluate day picker model
		updateDayModel();
		
	}
	
	/**
	 * Updates year model.
	 */
	private void updateYearModel(){
		
		//First of all,
		mYearModel.removeAllElements();
		
		if((getCurrentCalendarYear()-BASE_YEAR)<0){			
			mYearModel.insertElementAt("Invalid System date!", PICKER_HEADER_POSITION);
		} else	{			
			mYearModel.insertElementAt("Year", PICKER_HEADER_POSITION);
			
			for(int i=BASE_YEAR;i<=getCurrentCalendarYear();i++){
				mYearModel.insertElementAt(""+i/*Value*/, (i-BASE_YEAR+1)/*position*/);
			}
		}
		
		//Finally,
		//Set first element as selected..
		mYearModel.setSelectedItem(mYearModel.getElementAt(PICKER_HEADER_POSITION));
		
	}
	
	/**
	 * Updates month model.
	 */
	private void updateMonthModel(){
		
		mMonthModel.removeAllElements();
		//First check for selected year..
		if(getSelectedYear()==ERROR_CODE_INVALID_DATE_ATTRIBUTE){
			
			mMonthModel.insertElementAt("Set year first!", PICKER_HEADER_POSITION);
		}
		else
		{
			mMonthModel.insertElementAt("Month", PICKER_HEADER_POSITION);

			//If current year, then prepare only till current month
			int maxMonth=getSelectedYear()==getCurrentCalendarYear()?
					getCurrentCalendarMonth():MONTH_NAME_ARRAY.length/*actual months i.e 12*/;
						
			for(int i=0;i<maxMonth;i++){
				mMonthModel.insertElementAt(MONTH_NAME_ARRAY[i]/*Value*/,i+1/*position*/);
			}
		}
	
		//Finally,
		//Set first element as selected..
		mMonthModel.setSelectedItem(mMonthModel.getElementAt(PICKER_HEADER_POSITION));
		
	}
	
	/**
	 * Updates day model.
	 */
	private void updateDayModel(){
		
		mDayModel.removeAllElements();
		//First check for selected year..
		if(getSelectedMonth()==ERROR_CODE_INVALID_DATE_ATTRIBUTE){
			
			mDayModel.insertElementAt("Set month first!", PICKER_HEADER_POSITION);
		}
		else
		{
			mDayModel.insertElementAt("Day", PICKER_HEADER_POSITION);

			//If current year, then prepare only till current month
			int maxDays=((getSelectedYear()==getCurrentCalendarYear()) &&
					(getSelectedMonth()==getCurrentCalendarMonth())?
							/* Current year and month, so DATE is  permitted till today's date.*/
								getCurrentCalendarDay():
							/*Maximum allowed date for this year & month combination*/
								getMaxDaysForSelectedYearAndMonth());
						
			for(int i=1;i<=maxDays;i++){
				mDayModel.insertElementAt(""+/*Value*/
						//Format for more readability..prefix '0' if i<10  :P
						(i<10?"0"+i:i),
						i/*position, note i starts from 1*/);
			}
		}
		
		//Finally,
		//Set first element as selected..
		mDayModel.setSelectedItem(mDayModel.getElementAt(PICKER_HEADER_POSITION));
	}
	
	
	/**
	 * Checks whether give year is leap or not.
	 * @param year year to be checked for leap
	 * @return true if leap else false.
	 */
	private boolean isYearLeap(int year){
		if(year<=0){
			return false;
		}
		else if(
				((year%4==0)&&(year%100!=0))/*Year must be {[divisible completely by 4] AND [not by 100]}*/
				||((year%400)==0)/*OR [divisible completely by 400.]*/)
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/******************** GETTER & SETTER ***************/
	
	/**
	 * Sets default year.
	 * @param year year to be set.
	 */
	@Deprecated
	public void setSelectedYear(int year){
		mSelectedYear=year;
	}
	
	/**
	 * Sets default month.
	 * @param month month to be set.
	 */
	@Deprecated
	public void setSelectedMonth(int month){
		mSelectedMonth=month;
	}

	/**
	 * Sets default day.
	 * @param day day to be set.
	 */
	@Deprecated
	public void setSelectedDay(int day){
		mSelectedDay=day;
	}
	
	/**
	 * Gets selected year.
	 * @return selected year.
	 * @see MyDatePicker#ERROR_CODE_INVALID_DATE_ATTRIBUTE ERROR_CODE_INVALID_DATE_ATTRIBUTE
	 */
	public int getSelectedYear(){
		
		if(mYearModel!=null) {
				if(mYearModel.getIndexOf(mYearModel.getSelectedItem())==PICKER_HEADER_POSITION){
					mSelectedYear= ERROR_CODE_INVALID_DATE_ATTRIBUTE;
				}
				else
				{
					mSelectedYear=BASE_YEAR+mYearModel.getIndexOf(mYearModel.getSelectedItem())-1;
				}			
		}
		else
		{
			mSelectedYear= ERROR_CODE_INVALID_DATE_ATTRIBUTE;
		}
		//LogManager.processLog(MY_LOG_TYPE, TAG, "getSelectedYear():"+mSelectedYear);
		return mSelectedYear;
	}
	
	/**
	 * Gets selected month.
	 * @return selected month
	 * @see MyDatePicker#ERROR_CODE_INVALID_DATE_ATTRIBUTE ERROR_CODE_INVALID_DATE_ATTRIBUTE
	 */
	public int getSelectedMonth(){
		if(mMonthModel!=null) {
			if(mMonthModel.getIndexOf(mMonthModel.getSelectedItem())==PICKER_HEADER_POSITION){
				mSelectedMonth= ERROR_CODE_INVALID_DATE_ATTRIBUTE;
			}
			else
			{
				mSelectedMonth=mMonthModel.getIndexOf(mMonthModel.getSelectedItem());
			}			
		}
		else
		{
			mSelectedMonth= ERROR_CODE_INVALID_DATE_ATTRIBUTE;
		}
		//LogManager.processLog(MY_LOG_TYPE, TAG, "getSelectedMonth():"+mSelectedMonth);
		return mSelectedMonth;
	}
	
	/**
	 * Returns the maximum permitted days for current selected month.
	 * @return Maximum permitted month.
	 */
	private int getMaxDaysForSelectedYearAndMonth(){
		
		MONTH_TYPE monthType=MONTH_TYPE.OF_31_DAYS;//By default
		
		switch(getSelectedMonth()){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				//For jan,mar,may,JULY,AUG,oct,dec:
				monthType=MONTH_TYPE.OF_31_DAYS;
				break;
				
			case 2:
				monthType=isYearLeap(getSelectedYear())?
						MONTH_TYPE.OF_29_DAYS:MONTH_TYPE.OF_28_DAYS;
				break;
				
			case 4:
			case 6:
			case 9:
			case 11:
				//For apr,jun,sep,nov:
				monthType=MONTH_TYPE.OF_30_DAYS;
				break;
		
		}
			
		return getMaxDaysFor(monthType);
	}
	
	/**
	 * Gets selected day.
	 * @return selected day.
	 * @see MyDatePicker#ERROR_CODE_INVALID_DATE_ATTRIBUTE ERROR_CODE_INVALID_DATE_ATTRIBUTE
	 */
	public int getSelectedDay(){
		if(mDayModel!=null) {
			if(mDayModel.getIndexOf(mDayModel.getSelectedItem())==PICKER_HEADER_POSITION){
				mSelectedDay= ERROR_CODE_INVALID_DATE_ATTRIBUTE;
			}
			else
			{
				mSelectedDay=mDayModel.getIndexOf(mDayModel.getSelectedItem());
			}			
		}
		else
		{
			mSelectedMonth= ERROR_CODE_INVALID_DATE_ATTRIBUTE;
		}
		//LogManager.processLog(MY_LOG_TYPE, TAG, "getSelectedDay():"+mSelectedDay);
		return mSelectedDay;
	}
	
	/**
	 * Today's calendar reference.
	 * @return year of todays date.
	 */
	private int getCurrentCalendarYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * Today's calendar reference.
	 * @return month of todays date.
	 */
	private int getCurrentCalendarMonth(){
		return (Calendar.getInstance().get(Calendar.MONTH)+1);
	}
	
	/**
	 * Today's calendar reference.
	 * @return day of todays date.
	 */
	private int getCurrentCalendarDay(){
		return (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1);
	}
	
	/**
	 * Returns maximum allowed days.
	 * @param type Type of the month 
	 * @return maximum permitted days for the type.
	 * @see MONTH_TYPE
	 */
	private int getMaxDaysFor(MONTH_TYPE type){
		switch (type) 
		{
		case OF_28_DAYS:
			return 28;

		case OF_29_DAYS:
			return 29;
			
		case OF_30_DAYS:
			return 30;
			
		case OF_31_DAYS:
		default:
			return 31;
		}
	}


	/**
	 * One door entry which checks all sub attributes and gives consolidated report.
	 * @return true If all date attributes are selected properly, else false.
	 */
	public boolean isValidDateSelected() {
		
		if(getSelectedYear()!=ERROR_CODE_INVALID_DATE_ATTRIBUTE
				&& getSelectedMonth()!=ERROR_CODE_INVALID_DATE_ATTRIBUTE
				&& getSelectedDay()!=ERROR_CODE_INVALID_DATE_ATTRIBUTE){
			return true;
		}
		else
		{
			return false;
		}		
	}
	
}
