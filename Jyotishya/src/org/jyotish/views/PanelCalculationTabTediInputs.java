package org.jyotish.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import org.chandan.java.views.MyNumericTextField;
import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.chandan.utils.StringUtils;
import org.jyotish.controllers.DataTransportKeyManager.Key;
import org.jyotish.controllers.DataTransporter;
import org.jyotish.exceptions.JyotishyaTimeException;
import org.jyotish.exceptions.TransporterException;
import org.jyotish.logic.LogicManager;
import org.jyotish.models.JyotishyaTime;
import org.jyotish.models.ModelConstants;
import org.jyotish.observers.ActionListener;
import org.jyotish.views.Dialog.ApplicatinDialog;
import org.jyotish.views.ViewConstants.BUTTON_NAMES;
import org.jyotish.views.ViewConstants.LABEL_NAMES;

/**
 * Panel containing views related to reading inputs from user
 * for divamana, bhukti and lagna pramana.
 * <p>
 * {@link LogicManager#calculateTedi() calculateTedi()}
 * will be performed after reading input from this panel. 
 * <p>
 *  NOTE: Corresponding view type: {@link ViewType#CALCULATIONS_TAB_DIVAMANA_BHUKTI_LAGNA_INPUT_PANEL
 * CALCULATIONS_TAB_DIVAMANA_BHUKTI_LAGNA_INPUT_PANEL}
 * @author Chandan
 *
 */
final class PanelCalculationTabTediInputs extends JPanel implements ActionListener{
	
	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=PanelCalculationTabTediInputs.class.getSimpleName();

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = -5439383688902402244L;
	
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
	 * Label for divamana field.
	 */
	private JLabel mLabelDivamana;
	
	/**
	 * Label for Bhukti
	 */
	private JLabel mLabelBhukti;
	
	/**
	 * Label for Lagna Pramana.
	 */
	private JLabel mLabelLagnaPramana;
	
	
	/**
	 * Input field for divamana.
	 */
	private MyNumericTextField mTextFieldDivamana;
	
	/**
	 * Input field for Bhukti.
	 */
	private MyNumericTextField mTextFieldBhukti;
	
	
	/**
	 * Jyotishya Time picker for Lagna Pramana.
	 * <p>
	 * NOTE: Type is {@link JyotishyaTime}
	 */
	private MyJyotishyaTimePicker mJyotishyaTimePickerLagnaPramana;
	
	
	/**
	 * Create the panel.
	 * @param mouseClickListener Observer for mouse click events
	 * @param dialog Reference for application alert dialog
	 */
	PanelCalculationTabTediInputs(final ActionListener mouseClickListener,final ApplicatinDialog dialog) {
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating PanelCalculationTabTediInputs..");
		
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
				BUTTON_NAMES.CANCEL, null,null,null);
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
		
		/* Views which are related to divamana input*/
		JPanel panelDivamanaInput=new JPanel();
		SpringLayout springLayout2 = new SpringLayout();
			mLabelDivamana=new JLabel();
			mLabelDivamana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			springLayout2.putConstraint(SpringLayout.WEST, mLabelDivamana, 10, SpringLayout.WEST, panelDivamanaInput);
			springLayout2.putConstraint(SpringLayout.SOUTH, mLabelDivamana, -11, SpringLayout.SOUTH, panelDivamanaInput);
			mTextFieldDivamana=new MyNumericTextField();
			mTextFieldDivamana.setPreferredSize(new Dimension(LABEL_WIDTH,20));
			springLayout2.putConstraint(SpringLayout.NORTH, mTextFieldDivamana, 10, SpringLayout.NORTH, panelDivamanaInput);
			springLayout2.putConstraint(SpringLayout.WEST, mTextFieldDivamana, 72, SpringLayout.EAST, mLabelDivamana);
		panelDivamanaInput.setLayout(springLayout2);
		panelDivamanaInput.add(mLabelDivamana);
		panelDivamanaInput.add(mTextFieldDivamana);
		
		/* Views which are related to Bhukti input*/
		JPanel panelBhuktiInput=new JPanel();
		SpringLayout springLayout3 = new SpringLayout();
			mLabelBhukti=new JLabel();
			mLabelBhukti.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			springLayout3.putConstraint(SpringLayout.WEST, mLabelBhukti, 10, SpringLayout.WEST, panelBhuktiInput);
			springLayout3.putConstraint(SpringLayout.SOUTH, mLabelBhukti, -9, SpringLayout.SOUTH, panelBhuktiInput);
			mTextFieldBhukti=new MyNumericTextField();
			mTextFieldBhukti.setPreferredSize(new Dimension(LABEL_WIDTH,20));
			springLayout3.putConstraint(SpringLayout.WEST, mTextFieldBhukti, 72, SpringLayout.EAST, mLabelBhukti);
			springLayout3.putConstraint(SpringLayout.SOUTH, mTextFieldBhukti, 0, SpringLayout.SOUTH, mLabelBhukti);
		panelBhuktiInput.setLayout(springLayout3);
		panelBhuktiInput.add(mLabelBhukti);
		panelBhuktiInput.add(mTextFieldBhukti);
		
			
		/* Views which are related to Lagna Pramana input*/
		JPanel panelLagnaPramanaInput=new JPanel();
		SpringLayout springLayout4 = new SpringLayout();
			mLabelLagnaPramana=new JLabel();
			mLabelLagnaPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			springLayout4.putConstraint(SpringLayout.NORTH, mLabelLagnaPramana, 10, SpringLayout.NORTH, panelLagnaPramanaInput);
			springLayout4.putConstraint(SpringLayout.WEST, mLabelLagnaPramana, 10, SpringLayout.WEST, panelLagnaPramanaInput);
			mJyotishyaTimePickerLagnaPramana=new MyJyotishyaTimePicker();
			springLayout4.putConstraint(SpringLayout.NORTH, mJyotishyaTimePickerLagnaPramana, 0, SpringLayout.NORTH, panelLagnaPramanaInput);
			springLayout4.putConstraint(SpringLayout.WEST, mJyotishyaTimePickerLagnaPramana, 70, SpringLayout.EAST, mLabelLagnaPramana);
			panelLagnaPramanaInput.setLayout(springLayout4);
			panelLagnaPramanaInput.add(mLabelLagnaPramana);
			panelLagnaPramanaInput.add(mJyotishyaTimePickerLagnaPramana);
		
		
		//ALL sub panels are initialized.
		//set sub panels to main holder...
		
		
		//Add each compound input panel to parent panel...
			
		GridLayout gridLayout=new GridLayout(10,0);//How many children you are goint to add..
		parentPanel.setLayout(gridLayout);
		parentPanel.add(panelAbout);//1
		parentPanel.add(panelDivamanaInput);//2
		parentPanel.add(panelBhuktiInput);//3
		parentPanel.add(panelLagnaPramanaInput);//4

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
		mLabelAbout.setText(LABEL_NAMES.ABOUT_PANEL_TEDI_INPUT);
		mLabelDivamana.setText(LABEL_NAMES.ENTER_DIVAMANA);
		mLabelBhukti.setText(LABEL_NAMES.ENTER_BHUKTI);
		mLabelLagnaPramana.setText(LABEL_NAMES.PICK_LAGNA_PRAMANA);
		
		//Reset input fields
		mTextFieldDivamana.setText(BUTTON_NAMES.EMPTY);
		mTextFieldBhukti.setText(BUTTON_NAMES.EMPTY);
		mTextFieldDivamana.setToolTipText(LABEL_NAMES.ENTER_DIVAMANA);
		mLabelBhukti.setToolTipText(LABEL_NAMES.ENTER_BHUKTI);
		
		mJyotishyaTimePickerLagnaPramana.resetJyotishyaTimeView();
		
		//Reset hints if any..
		
	}

	/* ************  CONTROL BUTTON METHODS *******************/
	@Override
	public void onNegativeButtonClick() {
		//[BACK]
		//disabled :P
		//mActionListener.onNegativeButtonClick();
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
		//Divamana
		if(StringUtils.isEmpty(mTextFieldDivamana.getText())){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount
				+". Divamana is not set!"+LABEL_NAMES.LINE_FEED_SINGLE;
		}
		
		//Bhukti
		if(StringUtils.isEmpty(mTextFieldBhukti.getText())){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount
				+". Bhukti is not set!"+LABEL_NAMES.LINE_FEED_SINGLE;
		}
		
		//Lagna pramana
		if(!mJyotishyaTimePickerLagnaPramana.isValidTimeSelected()){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount
				+". Lagna Pramana is invalid!"+LABEL_NAMES.LINE_FEED_SINGLE;
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
	 * @throws TransporterException Exception during transportation.
	 * @throws JyotishyaTimeException  Invalid JyotishyaTime.
	 */
	private void transportSubmittedData() throws 
		 TransporterException, JyotishyaTimeException{

		//Prepare data
		int divamana=Integer.parseInt(mTextFieldDivamana.getText());
		
		int bhukti=Integer.parseInt(mTextFieldBhukti.getText());
		
		JyotishyaTime jyotishyaTimeLagnaPramana=new JyotishyaTime(
					mJyotishyaTimePickerLagnaPramana.getSelectedGalige(),
					mJyotishyaTimePickerLagnaPramana.getSelectedVigalige());
		
		//Get static instance..
		DataTransporter dataTransporter=DataTransporter.getInstance();
		dataTransporter.putInteger(Key.DIVAMANA, divamana);
		dataTransporter.putInteger(Key.BHUKTI, bhukti);
		dataTransporter.putJyotishyaTime(Key.LAGNA_PRAMANA_FOR_TEDI, jyotishyaTimeLagnaPramana);
		
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
