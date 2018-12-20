package org.jyotish.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.chandan.java.views.MyAlphabetTextField;
import org.chandan.java.views.MyDatePicker;
import org.chandan.java.views.MyTimePicker;
import org.chandan.java.views.MyTimePicker.TIME_FORMAT;
import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.chandan.utils.StringUtils;
import org.jyotish.controllers.DataTransportKeyManager.Key;
import org.jyotish.controllers.DataTransporter;
import org.jyotish.exceptions.CustomDateException;
import org.jyotish.exceptions.EnglishTimeException;
import org.jyotish.exceptions.TransporterException;
import org.jyotish.logic.LogicManager;
import org.jyotish.models.CustomDate;
import org.jyotish.models.EnglishTime;
import org.jyotish.models.ModelConstants;
import org.jyotish.observers.ActionListener;
import org.jyotish.views.Dialog.ApplicatinDialog;
import org.jyotish.views.ViewConstants.BUTTON_NAMES;
import org.jyotish.views.ViewConstants.LABEL_NAMES;

/**
 * Panel containing views related to accepting user inputs on client's
 * name,birth date, birth time and sun rise time.
 * <p>
 * {@link LogicManager#calculateSuryaUdayadiGhati() calculateSuryaUdayadiGhati()}
 * will be performed after reading input from this panel.
 * <p>
 * NOTE: Corresponding view type: {@link ViewType#CALCULATIONS_TAB_BIRTH_TIME_INPUT_PANEL
 * CALCULATIONS_TAB_BIRTH_TIME_INPUT_PANEL}
 * @author Chandan
 *
 */
final class PanelCalculationTabBirthTimeReader extends JPanel implements ActionListener {
	
	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=PanelCalculationTabBirthTimeReader.class.getSimpleName();

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = -5435383688902402244L;
	
	/**
	 * Standard label width 
	 */
	private static final int LABEL_WIDTH=150;
	
	/**
	 * Standard label height
	 */
	private static final int LABEL_HEIGHT=30;
	
	/**
	 * Reference for application's alert dialog. 
	 */
	private static ApplicatinDialog mAlertDialog;
	
	/**
	 * Error message for pop up.
	 */
	private static String ERROR_TITLE=LABEL_NAMES.INVALID_INPUT;
	
	/**
	 * Error message for pop up.
	 */
	private static String ERROR_MESSAGE=BUTTON_NAMES.EMPTY;
	
	/**
	 * Control button views manager.
	 */
	private PanelControlButtons mControlButtons;
	
	/**
	 * Observer for mouse clicks.
	 */
	private ActionListener mActionListener;
	
	/**
	 * Label for description of this panel.
	 */
	private JLabel mLabelAbout;
	
	/**
	 * Label for client name field
	 */
	private JLabel mLabelClientName;
	
	/**
	 * Label for client's birth date.
	 */
	private JLabel mLabelClientBirthDate;
	
	/**
	 * Label for client birth time.
	 */
	private JLabel mLabelClientBirthTime;
	
	/**
	 * Label for sun rise time.
	 */
	private JLabel mLabelSunRiseTime;
	
	/**
	 * Input field for client name.
	 */
	private MyAlphabetTextField mTextFieldClientName;
	
	/**
	 * Date picker for client's birth date.
	 */
	private MyDatePicker mDatePickerClientBirthDate;
	
	/**
	 * Time picker for client's birth time.
	 */
	private MyTimePicker mTimePickerClientBirthTime;
	
	/**
	 * Time picker for sun rise time.
	 */
	private MyTimePicker mTimePickerSunRiseTime;
	
	
	/**
	 * Create the panel.
	 * @param mouseClickListener Observer for mouse click events
	 * @param dialog Reference for application alert dialog
	 */
	PanelCalculationTabBirthTimeReader(final ActionListener mouseClickListener,final ApplicatinDialog dialog) {
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating birth time reader panel..");
		
		setSize(ViewConstants.TAB_COMPONENT_WIDTH, 
				ViewConstants.TAB_COMPONENT_HEIGHT);
		
		setLayout(new BorderLayout(5,5));
		
		/*
		setBounds(ViewConstants.RESULT_SCREEN_FRAME_X,
				ViewConstan
				ts.RESULT_SCREEN_FRAME_Y,
				ViewConstants.RESULT_SCREEN_FRAME_WIDTH,
				ViewConstants.RESULT_SCREEN_FRAME_HEIGHT);
		*/
		
		mActionListener=mouseClickListener;
		mAlertDialog=dialog;
		
		initViews();
		
		//Reset all
		resetCurrentPanelViews();
		resetControleButtons();
		
	
	}
	
	/**
	 * Requests,prepares and displays application's alert dialog.
	 * Set necessary attributes @ #ERROR_TITLE {@link #ERROR_MESSAGE}
	 */
	private void showAlertDialog(){
		//Oops..Do not be afraid of seeing lengthy stuffs... :P
		mAlertDialog.prepareDialog(ERROR_TITLE,
				ERROR_MESSAGE,
				BUTTON_NAMES.OK, 
				BUTTON_NAMES.CANCEL, null,null,	null);
	}

	/**
	 * Only instantiate views for this screen.
	 * For setting up initial/default values refer {@link #resetCurrentPanelViews()}
	 */
	private void initViews(){
		JPanel parentPanel=new JPanel();
		parentPanel.setSize(ViewConstants.TAB_COMPONENT_WIDTH, 
				ViewConstants.TAB_COMPONENT_HEIGHT-50);
		
		//SpringLayout springLayout = new SpringLayout();
		//parentPanel.setLayout(springLayout);	
	
				
		/* About this panel */
		JPanel panelAbout=new JPanel();
		SpringLayout springLayout1 = new SpringLayout();
			mLabelAbout=new JLabel();	
			mLabelAbout.setEnabled(false);
			springLayout1.putConstraint(SpringLayout.NORTH, mLabelAbout, 10, SpringLayout.NORTH, panelAbout);
			springLayout1.putConstraint(SpringLayout.WEST, mLabelAbout, 10, SpringLayout.WEST, panelAbout);
		panelAbout.setLayout(springLayout1);	
		panelAbout.add(mLabelAbout);
		
		/* Views which are related to client name input*/
		JPanel panelClientNameInput=new JPanel();
		SpringLayout springLayout2 = new SpringLayout();
			mLabelClientName=new JLabel();
			mLabelClientName.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			springLayout2.putConstraint(SpringLayout.WEST, mLabelClientName, 10, SpringLayout.WEST, panelClientNameInput);
			springLayout2.putConstraint(SpringLayout.SOUTH, mLabelClientName, -11, SpringLayout.SOUTH, panelClientNameInput);
			mTextFieldClientName=new MyAlphabetTextField();
			mTextFieldClientName.setPreferredSize(new Dimension(LABEL_WIDTH,20));
			springLayout2.putConstraint(SpringLayout.NORTH, mTextFieldClientName, 10, SpringLayout.NORTH, panelClientNameInput);
			springLayout2.putConstraint(SpringLayout.WEST, mTextFieldClientName, 72, SpringLayout.EAST, mLabelClientName);
		panelClientNameInput.setLayout(springLayout2);
		panelClientNameInput.add(mLabelClientName);
		panelClientNameInput.add(mTextFieldClientName);
		
		/* Views which are related to client birth date input*/
		JPanel panelClientBirthDateInput=new JPanel();
		SpringLayout springLayout3 = new SpringLayout();
			mLabelClientBirthDate=new JLabel();
			mLabelClientBirthDate.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			springLayout3.putConstraint(SpringLayout.WEST, mLabelClientBirthDate, 10, SpringLayout.WEST, panelClientBirthDateInput);
			springLayout3.putConstraint(SpringLayout.SOUTH, mLabelClientBirthDate, -9, SpringLayout.SOUTH, panelClientBirthDateInput);
			mDatePickerClientBirthDate=new MyDatePicker();
			springLayout3.putConstraint(SpringLayout.WEST, mDatePickerClientBirthDate, 72, SpringLayout.EAST, mLabelClientBirthDate);
			springLayout3.putConstraint(SpringLayout.SOUTH, mDatePickerClientBirthDate, 0, SpringLayout.SOUTH, mLabelClientBirthDate);
		panelClientBirthDateInput.setLayout(springLayout3);
		panelClientBirthDateInput.add(mLabelClientBirthDate);
		panelClientBirthDateInput.add(mDatePickerClientBirthDate);
		
			
		/* Views which are related to client birth time input*/
		JPanel panelClientBirthTimeInput=new JPanel();
		SpringLayout springLayout4 = new SpringLayout();
			mLabelClientBirthTime=new JLabel();
			mLabelClientBirthTime.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			springLayout4.putConstraint(SpringLayout.NORTH, mLabelClientBirthTime, 10, SpringLayout.NORTH, panelClientBirthTimeInput);
			springLayout4.putConstraint(SpringLayout.WEST, mLabelClientBirthTime, 10, SpringLayout.WEST, panelClientBirthTimeInput);
			mTimePickerClientBirthTime=new MyTimePicker(TIME_FORMAT.HOUR_12);
			springLayout4.putConstraint(SpringLayout.NORTH, mTimePickerClientBirthTime, 0, SpringLayout.NORTH, panelClientBirthTimeInput);
			springLayout4.putConstraint(SpringLayout.WEST, mTimePickerClientBirthTime, 70, SpringLayout.EAST, mLabelClientBirthTime);
			panelClientBirthTimeInput.setLayout(springLayout4);
			panelClientBirthTimeInput.add(mLabelClientBirthTime);
			panelClientBirthTimeInput.add(mTimePickerClientBirthTime);
		
		/* Views which are related to Sun rise time input*/
		JPanel panelSunRiseTimeInput=new JPanel();	
		SpringLayout springLayout5 = new SpringLayout();
			mLabelSunRiseTime=new JLabel();
			mLabelSunRiseTime.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			springLayout5.putConstraint(SpringLayout.NORTH, mLabelSunRiseTime, 10, SpringLayout.NORTH, panelSunRiseTimeInput);
			springLayout5.putConstraint(SpringLayout.WEST, mLabelSunRiseTime, 10, SpringLayout.WEST, panelSunRiseTimeInput);
			mTimePickerSunRiseTime=new MyTimePicker(TIME_FORMAT.HOUR_12);
			mTimePickerSunRiseTime.enableAMPMPicker(false);//Force it to AM..
			springLayout5.putConstraint(SpringLayout.NORTH, mTimePickerSunRiseTime, 0, SpringLayout.NORTH, panelSunRiseTimeInput);
			springLayout5.putConstraint(SpringLayout.WEST, mTimePickerSunRiseTime, 71, SpringLayout.EAST, mLabelSunRiseTime);
			
		panelSunRiseTimeInput.setLayout(springLayout5);
		panelSunRiseTimeInput.add(mLabelSunRiseTime);
		panelSunRiseTimeInput.add(mTimePickerSunRiseTime);
		
		//ALL sub panels are initialized.
		//set sub panels to main holder...
		
		
		//Add each compound input panel to parent panel...
			
		GridLayout gridLayout=new GridLayout(10,0);//How many children you are goint to add..
		parentPanel.setLayout(gridLayout);
		parentPanel.add(panelAbout);//1
		parentPanel.add(panelClientNameInput);//2
		parentPanel.add(panelClientBirthDateInput);//3
		parentPanel.add(panelClientBirthTimeInput);//4
		parentPanel.add(panelSunRiseTimeInput);//5

		//Set up control panel..
		mControlButtons=new PanelControlButtons(this);
		
		//Finally add in order..
		add(parentPanel);
		add(mControlButtons,BorderLayout.CENTER);
	}
	
	/**
	 * Resets all control buttons to initial behavior.
	 * <p>
	 * For initializing views, see {@link #initViews()}
	 * 
	 */
	private void resetControleButtons(){
		
		mControlButtons.enablePositiveButton(true);
		mControlButtons.setPositiveButtonName(BUTTON_NAMES.NEXT);
		
		mControlButtons.enableNeutralButton(true);
		mControlButtons.setNeutralButtonName(BUTTON_NAMES.RESET);
		
		mControlButtons.enableNegativeButton(false);
		mControlButtons.setNegativeButtonName(BUTTON_NAMES.BACK);
	}
	
	/**
	 * Resets all input fields/flags. Use this only after initializing views via {@link #initViews()}
	 */
	private void resetCurrentPanelViews(){
		
		//Reset labels
		mLabelAbout.setText(LABEL_NAMES.ABOUT_PANEL_BIRTH_DAY_INPUT);
		mLabelClientName.setText(LABEL_NAMES.ENTER_CLIENT_NAME);
		mLabelClientBirthDate.setText(LABEL_NAMES.PICK_CLIENT_BIRTH_DATE);
		mLabelClientBirthTime.setText(LABEL_NAMES.PICK_CLIENT_BIRTH_TIME);
		mLabelSunRiseTime.setText(LABEL_NAMES.PICK_SUN_RISE_TIME);
		
		
		//Reset input fields
		mTextFieldClientName.setText(BUTTON_NAMES.EMPTY);
		mTextFieldClientName.setToolTipText(LABEL_NAMES.ENTER_CLIENT_NAME);
		
		mDatePickerClientBirthDate.resetDateView();
		mTimePickerClientBirthTime.resetTimeView();
		mTimePickerSunRiseTime.resetTimeView();
		
		//Reset hints if any..
		
	}

	/* ************  CONTROL BUTTON METHODS *******************/
	@Override
	public void onNegativeButtonClick() {
		//[BACK]
		//Validate and then only pass..
		//mActionListener.onNegativeButtonClick();
		
		//Control,Not expected you here! ;)
	}

	@Override
	public void onNeutralButtonClick() {
		//[RESET]
		//Validate and then only pass..
		//Only reset all fields..No need to pass to super/manager..
		//mActionListener.onNeutralButtonClick();
		resetCurrentPanelViews();
	}
	
	@Override
	public void onPositiveButtonClick() {
		//[NEXT]
		//Validate and then only pass..
		
		//TESTING Remove below line and comments as well..
		//mActionListener.onPositiveButtonClick();
		///*
		if(isAllFieldsFilledWithValidData())
		{
			//Dump all values in transporter..
			try {
				transportSubmittedData();
			} catch (Exception e) {
				LogManager.processException(MY_LOG_TYPE, TAG, e);
				ERROR_MESSAGE=BUTTON_NAMES.EMPTY;
				ERROR_MESSAGE+="Exception while transporting!"+
					LABEL_NAMES.LINE_FEED_SINGLE+
					LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+e.toString();
				showAlertDialog();
				return;
			} 
			//Then,
			mActionListener.onPositiveButtonClick();
		}
		else
		{
			//Probably show alert..As message and title will be updated prior..
			showAlertDialog();
			//Don't call reset, as user will correct himself..
		}
		//TESTING comments
		//*/
	}
	
	/**
	 * Checks whether all input fields are filled up properly or not.
	 * <p>
	 * NOTE: Up on invalid data submission, corresponding error message will be 
	 * updated to {@link #ERROR_MESSAGE}
	 * @return true if all fields are filled up properly else false.
	 */
	private boolean isAllFieldsFilledWithValidData(){
		boolean isAllFieldsFilledWithValidData=false;
		int errorCount=0;
		
		//Clear previous error messages if any..
		ERROR_MESSAGE=BUTTON_NAMES.EMPTY;
		ERROR_MESSAGE="Please correct below errors.."+LABEL_NAMES.LINE_FEED_SINGLE;
		
		//Lets begin validation..
		//Client name
		if(StringUtils.isEmpty(mTextFieldClientName.getText())){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount+". Client's name can not be empty!"+LABEL_NAMES.LINE_FEED_SINGLE;
		}
		
		//Client birth date
		if(!mDatePickerClientBirthDate.isValidDateSelected()){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount+". Client's birth date is invalid!"+LABEL_NAMES.LINE_FEED_SINGLE;
		}
		
		//Client birth time
		if(!mTimePickerClientBirthTime.isValidTimeSelected()){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount+". Client's birth time is invalid!"+LABEL_NAMES.LINE_FEED_SINGLE;
		}
		
		//Sun rise time
		if(!mTimePickerSunRiseTime.isValidTimeSelected()){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount+". Sun rise time is invalid!"+LABEL_NAMES.LINE_FEED_SINGLE;
		}
		
		//All is set..
		//So,		
		if(errorCount==0){
			isAllFieldsFilledWithValidData=true;
		}
		
		return isAllFieldsFilledWithValidData;
	}
	
	/**
	 * Dumps all submitted data on to {@link DataTransporter}
	 * First check {@link #isAllFieldsFilledWithValidData()}. If all is well, then  only proceed 
	 * with this method.
	 * @throws CustomDateException Invalid CustomDate.
	 * @throws EnglishTimeException Invalid EnglishTime.
	 * @throws TransporterException Exception during transportation.
	 */
	private void transportSubmittedData() throws 
		CustomDateException, EnglishTimeException, TransporterException{

		//Prepare data
		CustomDate clientBirthDate=new CustomDate(
					mDatePickerClientBirthDate.getSelectedDay(),
					mDatePickerClientBirthDate.getSelectedMonth(),
					mDatePickerClientBirthDate.getSelectedYear());
		
		EnglishTime clientBirthTime=new EnglishTime(
					mTimePickerClientBirthTime.getSelectedHour(),
					mTimePickerClientBirthTime.getSelectedMinute(),
					mTimePickerClientBirthTime.getSelectedSeconds());
		
		EnglishTime sunRiseTime=new EnglishTime(
				mTimePickerSunRiseTime.getSelectedHour(),
				mTimePickerSunRiseTime.getSelectedMinute(),
				mTimePickerSunRiseTime.getSelectedSeconds());
		
		
		//Get static instance..
		DataTransporter dataTransporter=DataTransporter.getInstance();
		dataTransporter.putString(Key.CLIENT_NAME, mTextFieldClientName.getText());
		dataTransporter.putCustomDate(Key.CLIENT_BIRTH_DATE, clientBirthDate);
		dataTransporter.putEnglishTime(Key.CLIENT_BIRTH_TIME, clientBirthTime);
		dataTransporter.putEnglishTime(Key.SUN_RISE_TIME,sunRiseTime);
		
		//Ooooofff :)
	}


	@Override
	public void refresh() {
		//mActionListener.refresh();
	}

	@Override
	public void onMouseClick(ClickedView clickedView) {
		//mActionListener.onMouseClick(clickedView);
	}
}
