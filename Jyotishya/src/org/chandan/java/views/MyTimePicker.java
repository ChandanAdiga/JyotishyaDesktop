package org.chandan.java.views;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.models.ModelConstants;
import java.awt.Dimension;

/**
 * Panel consisting of time views. Also provides corresponding 
 * getter and setter methods. Note use of setter methods for time attributes 
 * is not expected and hence made deprecated.
 * @author chandan
 *
 */
public final class MyTimePicker extends JPanel {

	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=MyTimePicker.class.getSimpleName();

	
	/**
	 * Generated version ID.
	 */
	private static final long serialVersionUID = 2429478144962436792L;
	
	/**
	 * DateView's width
	 */
	private static final short DATEVIEW_PANEL_WIDTH=400;
	
	/**
	 * DateView's height
	 */
	private static final short DATEVIEW_PANEL_HEIGHT=30;
	
	/**
	 * Invalid selected time attributes.
	 */
	public static final int ERROR_CODE_INVALID_TIME_ATTRIBUTE=-1;
	
	/**
	 * Specifies time format. either 12 or 24 hour format.
	 * @author chandan
	 *
	 */
	public static enum TIME_FORMAT{
		/**
		 * 24 hour format.
		 */
		HOUR_24,
		
		/**
		 * 12 hour format.
		 */
		HOUR_12,
		
	}
	
	/**
	 * Array for updating UI of AM/PM
	 */
	private final String[] TYPE_AM_PM=new String[] {
		"AM", "PM"
	};
	
	/**
	 * Position of AM in {@link #TYPE_AM_PM}
	 */
	public static final int POS_AM=0;
	
	/**
	 * Position of PM in {@link #TYPE_AM_PM}
	 */
	public static final int POS_PM=1;
	
	/**
	 * Minimum valid time attribute value.[usually -> '0']
	 */
	private static final int MIN_TIME_ATTRIBUTE=0;
	
	/**
	 * Header position[usually -> '0']
	 */
	private static final int PICKER_HEADER_POSITION=MIN_TIME_ATTRIBUTE;
	
	/**
	 * 24.
	 */
	private final int MAX_HOUR_FOR_24_HOUR_FORMAT=24;
	
	/**
	 * 12.
	 */
	private final int MAX_HOUR_FOR_12_HOUR_FORMAT=12;
	
	/**
	 * 60.
	 */
	private final int MAX_MINUTES=60;
	
	/**
	 * 60.
	 */
	private final int MAX_SECONDS=MAX_MINUTES;
	
	/**
	 * Selected hour.
	 */
	private int mSelectedHour=ERROR_CODE_INVALID_TIME_ATTRIBUTE;
	
	/**
	 * Selected minute.
	 */
	private int mSelectedMinute=ERROR_CODE_INVALID_TIME_ATTRIBUTE;
	
	/**
	 * Selected second.
	 */
	private int mSelectedSecond=ERROR_CODE_INVALID_TIME_ATTRIBUTE;
		
	/**
	 * Time format to be prepared/used.
	 */
	private TIME_FORMAT mTimeFormat;
	
	/**
	 * Vector holding seconds
	 */
	private Vector<String> mSecondsVector;
	
	/**
	 * Vector holding minutes.
	 */
	private Vector<String> mMinuteVector;
	
	/**
	 * Vector holding hours.
	 */
	private Vector<String> mHourVector;
	
	/**
	 * Seconds picker
	 */
	private JComboBox<String> mSeondsPicker;
	
	/**
	 * Minutes picker
	 */
	private JComboBox<String> mMinutePicker;
	
	/**
	 * Hour picker
	 */
	private JComboBox<String> mHourPicker;
	
	/**
	 * AM-PM picker
	 */
	private JComboBox<String> mAmPmPicker;
	
	/**
	 * Second's model
	 */
	private DefaultComboBoxModel<String> mSecondsModel;
	
	/**
	 * Minutes model
	 */
	private DefaultComboBoxModel<String> mMinutesModel;
	
	/**
	 * Hour's model
	 */
	private DefaultComboBoxModel<String> mHourModel;
	
	/**
	 * AM-PM's model
	 */
	private DefaultComboBoxModel<String> mAmPmModel;

	
	/**
	 * Constructor.
	 * @param timeFormat {@link TIME_FORMAT}
	 */
	public MyTimePicker(TIME_FORMAT timeFormat){
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating TimePicker..");
		
		setSize(DATEVIEW_PANEL_WIDTH, 
				DATEVIEW_PANEL_HEIGHT);
		
		mTimeFormat=TIME_FORMAT.HOUR_12;//timeFormat;		
		
		initialiseModels();
		
		mSeondsPicker=new JComboBox<String>(mSecondsModel);
		mMinutePicker=new JComboBox<String>(mMinutesModel);
		mHourPicker=new JComboBox<String>(mHourModel);
		mAmPmPicker=new JComboBox<String>(mAmPmModel);
		
		mSeondsPicker.setPreferredSize(new Dimension(100, DATEVIEW_PANEL_HEIGHT-8));
		mMinutePicker.setPreferredSize(new Dimension(100, DATEVIEW_PANEL_HEIGHT-8));
		mHourPicker.setPreferredSize(new Dimension(100, DATEVIEW_PANEL_HEIGHT-8));
		mAmPmPicker.setPreferredSize(new Dimension(80, DATEVIEW_PANEL_HEIGHT-8));
		
		mSeondsPicker.setMaximumRowCount(5);
		mMinutePicker.setMaximumRowCount(5);
		mHourPicker.setMaximumRowCount(5);
		mAmPmPicker.setMaximumRowCount(2);
		
		/* Not required so..
		mDayPicker.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//DO nothing..
			}
		});		
		
		mMinutePicker.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				updateMinutesModel();
			}
		});
		
		mHourPicker.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				updateMinutesModel();
			}
		});
		*/
				
		//Do not change order of adding..of course you know that ;)
		add(mHourPicker);
		add(mMinutePicker);
		add(mSeondsPicker);
		add(mAmPmPicker);
			
		if(timeFormat.equals(TIME_FORMAT.HOUR_24))
			setAMPMPickerVisibility(false);
	}

	/**
	 * Sets visibility of AM-PM picker view.
	 * @param enable if true sets visible else invisible.
	 */
	public void enableAMPMPicker(boolean enable){
		if(mAmPmPicker!=null)
			mAmPmPicker.setEnabled(enable);
	}
	
	/**
	 * Sets visibility of AM-PM picker view.
	 * @param visible if true sets visible else invisible.
	 */
	private void setAMPMPickerVisibility(boolean visible){
		if(mAmPmPicker!=null)
			mAmPmPicker.setVisible(visible);
	}
	
	/**
	 * Prepares data models for year,month and days.
	 * Also, sets default values for the same.
	 */
	private void initialiseModels(){
		
		mSecondsVector=new Vector<String>();		
		mMinuteVector=new Vector<String>();
		mHourVector=new Vector<String>();
		
		mSecondsVector.add("Seconds");
		mMinuteVector.add("Minutes");
		mHourVector.add("Hours");
		
		mSecondsModel=new DefaultComboBoxModel<String>(mSecondsVector);
		mMinutesModel=new DefaultComboBoxModel<String>(mMinuteVector);
		mHourModel=new DefaultComboBoxModel<String>(mHourVector);
		mAmPmModel=new DefaultComboBoxModel<String>(TYPE_AM_PM);
		
		//As rest of the stuffs are common as that of,
		resetTimeView();

	}
	
	/**
	 * Resets combo box model information.
	 */
	public void resetTimeView(){
		//As below are deprecated..,
		//mSelectedHour(PICKER_HEADER_POSITION);
		//mSelectedMinute(PICKER_HEADER_POSITION);
		//mSelectedSecond(PICKER_HEADER_POSITION);
		//set directly..
		mSelectedHour=mSelectedMinute=mSelectedSecond=PICKER_HEADER_POSITION;
		
		//evaluate year picker model
		updateHourModel();
		
		//evaluate month picker model
		updateMinutesModel();
		
		//evaluate day picker model
		updateSecondsModel();
		
		updateAmPmModel();
		
	}
	
	/**
	 * Updates Hour model.
	 */
	private void updateHourModel(){
		
		//First of all,
		mHourModel.removeAllElements();
		
		mHourModel.insertElementAt("Hours", PICKER_HEADER_POSITION);
		
		int maxHours=mTimeFormat.equals(TIME_FORMAT.HOUR_12)?
				MAX_HOUR_FOR_12_HOUR_FORMAT:MAX_HOUR_FOR_24_HOUR_FORMAT;
		
		for(int i=0;i<maxHours;i++){
			mHourModel.insertElementAt(""+(i<10?"0"+i:i)/*Value*/,i+1/*position*/);
		}
		
		//Finally,
		//Set first element as selected..
		mHourModel.setSelectedItem(mHourModel.getElementAt(PICKER_HEADER_POSITION));
		
	}
	
	/**
	 * Updates minute model.
	 */
	private void updateMinutesModel(){
		
		//First of all,
		mMinutesModel.removeAllElements();
		
		mMinutesModel.insertElementAt("Minutes", PICKER_HEADER_POSITION);
		
		for(int i=0;i<MAX_MINUTES;i++){
			mMinutesModel.insertElementAt(""+(i<10?"0"+i:i)/*Value*/,i+1/*position*/);
		}
		
		//Finally,
		//Set first element as selected..
		mMinutesModel.setSelectedItem(mMinutesModel.getElementAt(PICKER_HEADER_POSITION));
			
	}
	
	/**
	 * Updates seconds model.
	 */
	private void updateSecondsModel(){
		
		//First of all,
		mSecondsModel.removeAllElements();
		
		mSecondsModel.insertElementAt("Seconds", PICKER_HEADER_POSITION);
		
		for(int i=0;i<MAX_SECONDS;i++){
			mSecondsModel.insertElementAt(""+(i<10?"0"+i:i)/*Value*/,i+1/*position*/);
		}
		
		//Finally,
		//Set first element as selected..
		mSecondsModel.setSelectedItem(mSecondsModel.getElementAt(PICKER_HEADER_POSITION));
		
	}
	
	/**
	 * Updates AM-PM picker model..
	 * <p>
	 * Also sets visibility of the same depending on time format.
	 */
	private void updateAmPmModel(){
		
		//First of all check time format..
		if(mTimeFormat.equals(TIME_FORMAT.HOUR_12))
		{
			//Proceed..
			setAMPMPickerVisibility(true);
			//As AM-PM model is static array, no need to insert for reset,
			//Just set first item as selected item ..
			mAmPmModel.setSelectedItem(mAmPmModel.getElementAt(PICKER_HEADER_POSITION));			
		}
		else
		{
			setAMPMPickerVisibility(false);
		}
	}
	

	/******************** GETTER & SETTER ***************/
	
	/**
	 * Sets default hour.
	 * @param hour hour to be set.
	 */
	@Deprecated
	public void setSelectedHour(int hour){
		mSelectedHour=hour;
	}
	
	/**
	 * Sets default minute.
	 * @param minute minute to be set.
	 */
	@Deprecated
	public void setSelectedMinute(int minute){
		mSelectedMinute=minute;
	}

	/**
	 * Sets default seconds.
	 * @param seconds seconds to be set.
	 */
	@Deprecated
	public void setSelectedSeconds(int seconds){
		mSelectedSecond=seconds;
	}
	
	/**
	 * Gets selected hour in 24 hour format.
	 * <p>
	 * NOTE: Though time format of UI is {@link TIME_FORMAT#HOUR_12}, it will 
	 * be updated to {@link TIME_FORMAT#HOUR_24} and corresponding time will be
	 * returned.
	 * @return selected hour in 24 hour format.
	 * @see MyTimePicker#ERROR_CODE_INVALID_TIME_ATTRIBUTE ERROR_CODE_INVALID_TIME_ATTRIBUTE
	 */
	public int getSelectedHour(){
		
		if(mHourModel!=null) {
				if(mHourModel.getIndexOf(mHourModel.getSelectedItem())==PICKER_HEADER_POSITION){
					mSelectedHour= ERROR_CODE_INVALID_TIME_ATTRIBUTE;
				}
				else
				{
					mSelectedHour=mHourModel.getIndexOf(mHourModel.getSelectedItem())
							-/* As value 'o' will be at index '1'*/1;
					//Check for 12 hour format..
					if(mTimeFormat.equals(TIME_FORMAT.HOUR_12))
					{
						mSelectedHour+=isPmSelected()?MAX_HOUR_FOR_12_HOUR_FORMAT:MIN_TIME_ATTRIBUTE;
					}
					//else do nothing..
				
				}			
		}
		else
		{
			mSelectedHour= ERROR_CODE_INVALID_TIME_ATTRIBUTE;
		}
		//LogManager.processLog(MY_LOG_TYPE, TAG, "getSelectedHour():"+mSelectedHour);
		return mSelectedHour;
	}
	
	/**
	 * Gets selected minutes.
	 * @return selected minutes
	 * @see MyTimePicker#ERROR_CODE_INVALID_TIME_ATTRIBUTE ERROR_CODE_INVALID_TIME_ATTRIBUTE
	 */
	public int getSelectedMinute(){
		if(mMinutesModel!=null) {
			if(mMinutesModel.getIndexOf(mMinutesModel.getSelectedItem())==PICKER_HEADER_POSITION){
				mSelectedMinute= ERROR_CODE_INVALID_TIME_ATTRIBUTE;
			}
			else
			{
				mSelectedMinute=mMinutesModel
					.getIndexOf(mMinutesModel.getSelectedItem())
						-/* As value 'o' will be at index '1'*/1;;
			}			
		}
		else
		{
			mSelectedMinute= ERROR_CODE_INVALID_TIME_ATTRIBUTE;
		}
		//LogManager.processLog(MY_LOG_TYPE, TAG, "getSelectedMinute():"+mSelectedMinute);
		return mSelectedMinute;
	}
	
	/**
	 * Gets selected seconds.
	 * @return selected seconds
	 * @see MyTimePicker#ERROR_CODE_INVALID_TIME_ATTRIBUTE ERROR_CODE_INVALID_TIME_ATTRIBUTE
	 */
	public int getSelectedSeconds(){
		if(mSecondsModel!=null) {
			if(mSecondsModel.getIndexOf(mSecondsModel.getSelectedItem())==PICKER_HEADER_POSITION){
				mSelectedSecond= ERROR_CODE_INVALID_TIME_ATTRIBUTE;
			}
			else
			{
				mSelectedSecond=mSecondsModel
					.getIndexOf(mSecondsModel.getSelectedItem())
						-/* As value 'o' will be at index '1'*/1;;
			}			
		}
		else
		{
			mSelectedSecond= ERROR_CODE_INVALID_TIME_ATTRIBUTE;
		}
		//LogManager.processLog(MY_LOG_TYPE, TAG, "getSelectedSeconds():"+mSelectedSecond);
		return mSelectedSecond;
	}
	
	/**
	 * Checks if PM is selected in AM-PM picker dialog.
	 * <p>
	 * NOTE: For this time format must be 12 hour.Else it simply returns false.
	 * @return true if PM is selected, false otherwise.
	 */
	private boolean isPmSelected(){
		boolean  isPmSelected=false;
		if(mTimeFormat.equals(TIME_FORMAT.HOUR_12)){
			if(mAmPmModel!=null &&
					mAmPmModel.getSelectedItem().equals(TYPE_AM_PM[POS_PM])){
				isPmSelected=true;
			}
		}
		//else simply return..
		
		return isPmSelected;
	}
	
	/**
	 * One door entry which checks all sub attributes and gives consolidated report.
	 * @return true If all time attributes are selected properly, else false.
	 */
	public boolean isValidTimeSelected() {
		
		if(getSelectedSeconds()!=ERROR_CODE_INVALID_TIME_ATTRIBUTE
				&& getSelectedMinute()!=ERROR_CODE_INVALID_TIME_ATTRIBUTE
				&& getSelectedHour()!=ERROR_CODE_INVALID_TIME_ATTRIBUTE){
			return true;
		}
		else
		{
			return false;
		}		
	}

	
}
