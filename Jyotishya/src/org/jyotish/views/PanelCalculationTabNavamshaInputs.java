package org.jyotish.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
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
 * for navamsha calculation.
 * <p>
 * {@link LogicManager#calculateNavamsha() calculateNavamsha()}
 * will be performed after reading input from this panel. 
 * <p>
 *  NOTE: Corresponding view type: {@link ViewType#CALCULATIONS_TAB_NAVAMSHA_INPUT_PANEL
 * CALCULATIONS_TAB_NAVAMSHA_INPUT_PANEL}
 * @author Chandan
 *
 */
final class PanelCalculationTabNavamshaInputs extends JPanel implements ActionListener{
	
	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=PanelCalculationTabNavamshaInputs.class.getSimpleName();

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
	 * Label for Lagna Pramana.
	 */
	private JLabel mLabelLagnaPramana;
	
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
	PanelCalculationTabNavamshaInputs(final ActionListener mouseClickListener,
			final ApplicatinDialog dialog) {
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating PanelCalculationTabNavamshaInputs panel..");
		
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
		
		
		/* Views which are related to Lagna Pramana input*/
		JPanel panelLagnaPramanaInput=new JPanel();
		SpringLayout springLayout2 = new SpringLayout();
			mLabelLagnaPramana=new JLabel();
			springLayout2.putConstraint(SpringLayout.NORTH, mLabelLagnaPramana, 0, SpringLayout.NORTH, panelLagnaPramanaInput);
			springLayout2.putConstraint(SpringLayout.WEST, mLabelLagnaPramana, 10, SpringLayout.WEST, panelLagnaPramanaInput);
			mLabelLagnaPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mJyotishyaTimePickerLagnaPramana=new MyJyotishyaTimePicker();
			springLayout2.putConstraint(SpringLayout.NORTH, mJyotishyaTimePickerLagnaPramana, 0, SpringLayout.NORTH, mLabelLagnaPramana);
			springLayout2.putConstraint(SpringLayout.WEST, mJyotishyaTimePickerLagnaPramana, 69, SpringLayout.EAST, mLabelLagnaPramana);
		panelLagnaPramanaInput.setLayout(springLayout2);
		panelLagnaPramanaInput.add(mLabelLagnaPramana);
		panelLagnaPramanaInput.add(mJyotishyaTimePickerLagnaPramana);
		
		//ALL sub panels are initialized.
		//set sub panels to main holder...
				
		//Add each compound input panel to parent panel...
			
		GridLayout gridLayout=new GridLayout(10,0);//How many children you are goint to add..
		parentPanel.setLayout(gridLayout);
		parentPanel.add(panelAbout);//1
		parentPanel.add(panelLagnaPramanaInput);//2

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
		mLabelAbout.setText(LABEL_NAMES.ABOUT_PANEL_NAVAMSHA);
		mLabelLagnaPramana.
			setText(LABEL_NAMES.REMAINING_SURYA_UDAYADI_GHATI);
		mLabelLagnaPramana.setText(LABEL_NAMES.PICK_LAGNA_PRAMANA);
		
		//Reset input fields		
		mJyotishyaTimePickerLagnaPramana.resetJyotishyaTimeView();
		
		//Reset hints if any..
		
	}

	/* ************  CONTROL BUTTON METHODS *******************/
	@Override
	public void onNegativeButtonClick() {
		//[BACK]
		//Disabled for this panel..mActionListener.onNegativeButtonClick();
	}

	@Override
	public void onNeutralButtonClick() {
		//[RESET]
		resetCurrentPanelViews();
	}
	
	@Override
	public void onPositiveButtonClick() {
		//on [NEXT]
		//TESTING Need to comment for testing..
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
		//DO VALIDATE ONLY WHEN CONTNUE[neutral] IS CLICKED
		//Clear previous error messages if any..
		ERROR_MESSAGE=BUTTON_NAMES.EMPTY;
		ERROR_MESSAGE="Please correct below errors.."+LABEL_NAMES.LINE_FEED_SINGLE;
		
		//Lets begin validation..
		
		//Lagna pramana
		if(!mJyotishyaTimePickerLagnaPramana.isValidTimeSelected()){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount+". Lagna Pramana is invalid!"+LABEL_NAMES.LINE_FEED_SINGLE;
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

		JyotishyaTime jyotishyaTimeLagnaPramana=new JyotishyaTime(
					mJyotishyaTimePickerLagnaPramana.getSelectedGalige(),
					mJyotishyaTimePickerLagnaPramana.getSelectedVigalige());
		
		//Get static instance..
		DataTransporter dataTransporter=DataTransporter.getInstance();
		dataTransporter.putJyotishyaTime(Key.LAGNA_PRAMANA_FOR_NAVAMSHA, jyotishyaTimeLagnaPramana);
		
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
