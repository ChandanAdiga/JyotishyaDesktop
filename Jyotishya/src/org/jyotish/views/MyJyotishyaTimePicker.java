package org.jyotish.views;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.models.ModelConstants;
import java.awt.Dimension;

/**
 * Panel consisting of jyotishya time views. Also provides corresponding 
 * getter and setter methods. Note use of setter methods for jyotishya time attributes 
 * is not expected and hence made deprecated.
 * @author chandan
 *
 */
public final class MyJyotishyaTimePicker extends JPanel {

	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=MyJyotishyaTimePicker.class.getSimpleName();

	
	/**
	 * Generated version ID.
	 */
	private static final long serialVersionUID = 2429478144962436792L;
	
	/**
	 * DateView's width
	 */
	private static final short DATEVIEW_PANEL_WIDTH=180;
	
	/**
	 * DateView's height
	 */
	private static final short DATEVIEW_PANEL_HEIGHT=30;
	
	/**
	 * Invalid selected time attributes.
	 */
	public static final int ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE=-1;
	
	
	/**
	 * Minimum valid time attribute value.[usually -> '0']
	 */
	private static final int MIN_TIME_ATTRIBUTE=0;
	
	/**
	 * Header position[usually -> '0']
	 */
	private static final int PICKER_HEADER_POSITION=MIN_TIME_ATTRIBUTE;
	
	/**
	 * 59.
	 */
	private final int MAX_GALIGE=59;
	
	/**
	 * 59.
	 */
	private final int MAX_VIGALIGE=MAX_GALIGE;
	
	/**
	 * Selected galige.
	 */
	private int mSelectedGalige=ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE;
	
	/**
	 * Selected vigalige.
	 */
	private int mSelectedVigalige=ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE;
	
	/**
	 * Vector holding vigalige
	 */
	private Vector<String> mVigaligeVector;
	
	/**
	 * Vector holding galige.
	 */
	private Vector<String> mGaligeVector;
	
	
	/**
	 * Vigalige picker
	 */
	private JComboBox<String> mVigaligePicker;
	
	/**
	 * Galige picker
	 */
	private JComboBox<String> mGaligePicker;
	
	/**
	 * Vigalige's model
	 */
	private DefaultComboBoxModel<String> mVigaligeModel;
	
	/**
	 * Galige's model
	 */
	private DefaultComboBoxModel<String> mGaligeModel;
	
	/**
	 * Constructor.
	 */
	public MyJyotishyaTimePicker(){
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating TimePicker..");
		
		setSize(DATEVIEW_PANEL_WIDTH, 
				DATEVIEW_PANEL_HEIGHT);
		
		initialiseModels();
		
		mVigaligePicker=new JComboBox<String>(mVigaligeModel);
		mGaligePicker=new JComboBox<String>(mGaligeModel);
		
		mVigaligePicker.setPreferredSize(new Dimension(75, DATEVIEW_PANEL_HEIGHT-8));
		mGaligePicker.setPreferredSize(new Dimension(70, DATEVIEW_PANEL_HEIGHT-8));
		
		mVigaligePicker.setMaximumRowCount(5);
		mGaligePicker.setMaximumRowCount(5);
		
		/*
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
		add(mGaligePicker);
		add(mVigaligePicker);
		
	}

	
	/**
	 * Prepares data models for year,month and days.
	 * Also, sets default values for the same.
	 */
	private void initialiseModels(){
		
		mVigaligeVector=new Vector<String>();		
		mGaligeVector=new Vector<String>();
		
		mVigaligeVector.add("Vigalige");
		mGaligeVector.add("Galige");
		
		mVigaligeModel=new DefaultComboBoxModel<String>(mVigaligeVector);
		mGaligeModel=new DefaultComboBoxModel<String>(mGaligeVector);
		
		//As rest of the stuffs are common as that of,
		resetJyotishyaTimeView();

	}
	
	/**
	 * Resets combo box model information.
	 */
	public void resetJyotishyaTimeView(){
		//As below are deprecated..,
		//mSelectedHour(PICKER_HEADER_POSITION);
		//mSelectedMinute(PICKER_HEADER_POSITION);
		//mSelectedSecond(PICKER_HEADER_POSITION);
		//set directly..
		mSelectedGalige=mSelectedVigalige=PICKER_HEADER_POSITION;
		
		//evaluate month picker model
		updateGaligeModel();
		
		//evaluate day picker model
		updateVigaligeModel();
		
	}
	
	
	/**
	 * Updates minute model.
	 */
	private void updateGaligeModel(){
		
		//First of all,
		mGaligeModel.removeAllElements();
		
		mGaligeModel.insertElementAt("Galige", PICKER_HEADER_POSITION);
		
		for(int i=0;i<=MAX_GALIGE;i++){
			mGaligeModel.insertElementAt(""+(i<10?"0"+i:i)/*Value*/,i+1/*position*/);
		}
		
		//Finally,
		//Set first element as selected..
		mGaligeModel.setSelectedItem(mGaligeModel.getElementAt(PICKER_HEADER_POSITION));
			
	}
	
	/**
	 * Updates seconds model.
	 */
	private void updateVigaligeModel(){
		
		//First of all,
		mVigaligeModel.removeAllElements();
		
		mVigaligeModel.insertElementAt("Vigalige", PICKER_HEADER_POSITION);
		
		for(int i=0;i<=MAX_VIGALIGE;i++){
			mVigaligeModel.insertElementAt(""+(i<10?"0"+i:i)/*Value*/,i+1/*position*/);
		}
		
		//Finally,
		//Set first element as selected..
		mVigaligeModel.setSelectedItem(mVigaligeModel.getElementAt(PICKER_HEADER_POSITION));
		
	}
	
	
	/******************** GETTER & SETTER ***************/
	
	/**
	 * Sets default galige.
	 * @param galige galige to be set.
	 */
	@Deprecated
	public void setSelectedGalige(int galige){
		mSelectedGalige=galige;
	}

	/**
	 * Sets default vigalige.
	 * @param vigalige vigalige to be set.
	 */
	@Deprecated
	public void setSelectedVigalige(int vigalige){
		mSelectedVigalige=vigalige;
	}

	/**
	 * Gets selected galige.
	 * @return selected galige
	 * @see MyJyotishyaTimePicker#ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE
	 */
	public int getSelectedGalige(){
		if(mGaligeModel!=null) {
			if(mGaligeModel.getIndexOf(mGaligeModel.getSelectedItem())==PICKER_HEADER_POSITION){
				mSelectedGalige= ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE;
			}
			else
			{
				mSelectedGalige=mGaligeModel
					.getIndexOf(mGaligeModel.getSelectedItem())
						-/* As value 'o' will be at index '1'*/1;;
			}			
		}
		else
		{
			mSelectedGalige= ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE;
		}
		//LogManager.processLog(MY_LOG_TYPE, TAG, "getSelectedGalige():"+mSelectedGalige);
		return mSelectedGalige;
	}
	
	/**
	 * Gets selected vigalige.
	 * @return selected vigalige
	 * @see MyJyotishyaTimePicker#ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE ERROR_CODE_INVALID_TIME_ATTRIBUTE
	 */
	public int getSelectedVigalige(){
		if(mVigaligeModel!=null) {
			if(mVigaligeModel.getIndexOf(mVigaligeModel.getSelectedItem())==PICKER_HEADER_POSITION){
				mSelectedVigalige= ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE;
			}
			else
			{
				mSelectedVigalige=mVigaligeModel
					.getIndexOf(mVigaligeModel.getSelectedItem())
						-/* As value 'o' will be at index '1'*/1;;
			}			
		}
		else
		{
			mSelectedVigalige= ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE;
		}
		//LogManager.processLog(MY_LOG_TYPE, TAG, "getSelectedVigalige():"+mSelectedVigalige);
		return mSelectedVigalige;
	}
	
	
	/**
	 * One door entry which checks all sub attributes and gives consolidated report.
	 * @return true If all time attributes are selected properly, else false.
	 */
	public boolean isValidTimeSelected() {
		
		if(getSelectedVigalige()!=ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE
				&& getSelectedGalige()!=ERROR_CODE_INVALID_JYOTISHYA_TIME_ATTRIBUTE){
			return true;
		}
		else
		{
			return false;
		}		
	}

	
}
