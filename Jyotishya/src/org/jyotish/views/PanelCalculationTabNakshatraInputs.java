package org.jyotish.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.chandan.java.views.MyNumericTextField;
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
 * for nakshatra calculation.
 * <p>
 * {@link LogicManager#performNakshatraCalculations() performNakshatraCalculations()}
 * will be performed after reading input from this panel. 
 * <p>
 *  Also, {@link LogicManager#performDashBhuktiCalculations() performDashBhuktiCalculations()}
 *  performed after Nakshatra calculations.
 * <p>
 *  NOTE: Corresponding view type: {@link ViewType#CALCULATIONS_TAB_NAKSHATRA_DASHABHUKTI_INPUT_PANEL
 * CALCULATIONS_TAB_NAKSHATRA_INPUT_PANEL}
 * @author Chandan
 *
 */
final class PanelCalculationTabNakshatraInputs extends JPanel implements ActionListener{
	
	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=PanelCalculationTabNakshatraInputs.class.getSimpleName();

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
	 * Invalid selected operation attributes.
	 */
	public static final int ERROR_CODE_INVALID_OPERATION_ATTRIBUTE=-1;
	
	/**
	 * Header position[usually -> '0']
	 */
	private static final int PICKER_HEADER_POSITION=0;
	
	/**
	 * Array for updating UI of ADD/SUBTRACT
	 */
	private final String[] TYPE_ADD_SUBTRACT=new String[] {
			BUTTON_NAMES.OPERATION,
			BUTTON_NAMES.ADD,
			BUTTON_NAMES.SUBTRACT
	};
	
	/**
	 * Position of AM in {@link #TYPE_ADD_SUBTRACT}
	 */
	public static final int POS_ADD=1;
	
	/**
	 * Position of PM in {@link #TYPE_ADD_SUBTRACT}
	 */
	public static final int POS_SUBTRACT=2;
	
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
	 * Label for Previous star pramana
	 */
	private JLabel mLabelPreviousStarPramana;
	
	/**
	 * Label for Born star pramana
	 */
	private JLabel mLabelBornStarPramana;
	
	/**
	 * Label for ADD/SUBTRACT operation.
	 */
	private JLabel mLabelOperation;
	
	/**
	 * Label for Dasha varsha input.
	 */
	private JLabel mLabelDashaVarshaInput;
	
	/**
	 * Label for ADD/SUBTRACT operation description.
	 */
	private JTextArea mTextAreaOperationDescription;
	
	/**
	 * Label for dasha bhukti description.
	 */
	private JTextArea mTextAreaDashaBhuktiDescription;
	
	/**
	 * Jyotishya Time picker for Previous star pramana
	 * <p>
	 * NOTE: Type is {@link JyotishyaTime}
	 */
	private MyJyotishyaTimePicker mJyotishyaTimePickerPrevStarPramana;
	
	/**
	 * Jyotishya Time picker for Born star pramana
	 * <p>
	 * NOTE: Type is {@link JyotishyaTime}
	 */
	private MyJyotishyaTimePicker mJyotishyaTimePickerBornStarPramana;
	
	/**
	 * Operation picker.
	 */
	private JComboBox<String> mOperationPicker;
	
	/**
	 * Operation's model
	 */
	private DefaultComboBoxModel<String> mOperationModel;

	/**
	 * Textfield for reading dasha varsha.
	 * <p>
	 * NOTE: Type is {@link MyNumericTextField}
	 */
	private MyNumericTextField mTextFieldDashaVarshaInput;
	
	/**
	 * Create the panel.
	 * @param mouseClickListener Observer for mouse click events
	 * @param dialog Reference for application alert dialog
	 */
	PanelCalculationTabNakshatraInputs(final ActionListener mouseClickListener,
			final ApplicatinDialog dialog) {
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating PanelCalculationTabNakshatraInputs panel..");
		
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
		
		
		/* Views which are related to Previous star Pramana input*/
		JPanel panelPrevStarPramanaInput=new JPanel();
		SpringLayout springLayout2 = new SpringLayout();
			mLabelPreviousStarPramana=new JLabel();
			springLayout2.putConstraint(SpringLayout.NORTH, mLabelPreviousStarPramana, 0, SpringLayout.NORTH, panelPrevStarPramanaInput);
			springLayout2.putConstraint(SpringLayout.WEST, mLabelPreviousStarPramana, 10, SpringLayout.WEST, panelPrevStarPramanaInput);
			mLabelPreviousStarPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mJyotishyaTimePickerPrevStarPramana=new MyJyotishyaTimePicker();
			springLayout2.putConstraint(SpringLayout.NORTH, mJyotishyaTimePickerPrevStarPramana, 0, SpringLayout.NORTH, mLabelPreviousStarPramana);
			springLayout2.putConstraint(SpringLayout.WEST, mJyotishyaTimePickerPrevStarPramana, 69, SpringLayout.EAST, mLabelPreviousStarPramana);
		panelPrevStarPramanaInput.setLayout(springLayout2);
		panelPrevStarPramanaInput.add(mLabelPreviousStarPramana);
		panelPrevStarPramanaInput.add(mJyotishyaTimePickerPrevStarPramana);
		
		/* Views which are related to Born star Pramana input*/
		JPanel panelBornStarPramanaInput=new JPanel();
		SpringLayout springLayout3 = new SpringLayout();
			mLabelBornStarPramana=new JLabel();
			springLayout3.putConstraint(SpringLayout.WEST, mLabelBornStarPramana, 10, SpringLayout.WEST, panelBornStarPramanaInput);
			mLabelBornStarPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mJyotishyaTimePickerBornStarPramana=new MyJyotishyaTimePicker();
			springLayout3.putConstraint(SpringLayout.NORTH, mJyotishyaTimePickerBornStarPramana, 0, SpringLayout.NORTH, panelBornStarPramanaInput);
			springLayout3.putConstraint(SpringLayout.WEST, mJyotishyaTimePickerBornStarPramana, 70, SpringLayout.EAST, mLabelBornStarPramana);
			springLayout3.putConstraint(SpringLayout.NORTH, mLabelBornStarPramana, 0, SpringLayout.NORTH, mJyotishyaTimePickerBornStarPramana);
		panelBornStarPramanaInput.setLayout(springLayout3);
		panelBornStarPramanaInput.add(mLabelBornStarPramana);
		panelBornStarPramanaInput.add(mJyotishyaTimePickerBornStarPramana);
			
		
		/* Views which are related operation input */
		JPanel panelOperationDescription=new JPanel();
		SpringLayout springLayout4 = new SpringLayout();
			mTextAreaOperationDescription=new JTextArea();
			springLayout4.putConstraint(SpringLayout.NORTH, mTextAreaOperationDescription, 0, SpringLayout.NORTH, panelOperationDescription);
			springLayout4.putConstraint(SpringLayout.EAST, mTextAreaOperationDescription, 0, SpringLayout.EAST, panelOperationDescription);
			mTextAreaOperationDescription.setPreferredSize(new Dimension((5*LABEL_WIDTH),LABEL_HEIGHT));
			mTextAreaOperationDescription.setOpaque(false);
			mTextAreaOperationDescription.setEditable(false);
			mTextAreaOperationDescription.setLineWrap(true);
			mTextAreaOperationDescription.setWrapStyleWord(true);
			mTextAreaOperationDescription.setEnabled(false);
			mTextAreaOperationDescription.setRows(2);
			mTextAreaOperationDescription.setColumns(10);
		panelOperationDescription.setLayout(springLayout4);
		panelOperationDescription.add(mTextAreaOperationDescription);
		
		/* Views which are related operation input*/
		JPanel panelOperationInput=new JPanel();
		SpringLayout springLayout5 = new SpringLayout();
			mLabelOperation=new JLabel();
			springLayout5.putConstraint(SpringLayout.WEST, mLabelOperation, 10, SpringLayout.WEST, panelOperationInput);
			mLabelOperation.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mOperationModel=new DefaultComboBoxModel<String>(TYPE_ADD_SUBTRACT);
			mOperationModel.setSelectedItem(mOperationModel.getElementAt(PICKER_HEADER_POSITION));
			mOperationPicker=new JComboBox<String>(mOperationModel);
			springLayout5.putConstraint(SpringLayout.NORTH, mOperationPicker, 4, SpringLayout.NORTH, panelOperationInput);
			springLayout5.putConstraint(SpringLayout.WEST, mOperationPicker, 76, SpringLayout.EAST, mLabelOperation);
			springLayout5.putConstraint(SpringLayout.NORTH, mLabelOperation, -4, SpringLayout.NORTH, mOperationPicker);
			mOperationPicker.setPreferredSize(new Dimension(75, 22));
		panelOperationInput.setLayout(springLayout5);
		panelOperationInput.add(mLabelOperation);
		panelOperationInput.add(mOperationPicker);
		
		/* Views which are related operation input */
		JPanel panelDashaBhuktiDescription=new JPanel();
		SpringLayout springLayout6 = new SpringLayout();
			mTextAreaDashaBhuktiDescription=new JTextArea();
			springLayout6.putConstraint(SpringLayout.NORTH, mTextAreaDashaBhuktiDescription, 0, SpringLayout.NORTH, panelDashaBhuktiDescription);
			springLayout6.putConstraint(SpringLayout.EAST, mTextAreaDashaBhuktiDescription, 0, SpringLayout.EAST, panelDashaBhuktiDescription);
			mTextAreaDashaBhuktiDescription.setPreferredSize(new Dimension((5*LABEL_WIDTH),LABEL_HEIGHT));
			mTextAreaDashaBhuktiDescription.setOpaque(false);
			mTextAreaDashaBhuktiDescription.setEditable(false);
			mTextAreaDashaBhuktiDescription.setLineWrap(true);
			mTextAreaDashaBhuktiDescription.setWrapStyleWord(true);
			mTextAreaDashaBhuktiDescription.setEnabled(false);
			mTextAreaDashaBhuktiDescription.setRows(2);
			mTextAreaDashaBhuktiDescription.setColumns(10);
		panelDashaBhuktiDescription.setLayout(springLayout6);
		panelDashaBhuktiDescription.add(mTextAreaDashaBhuktiDescription);
		
		/* Views which are related to Previous star Pramana input*/
		JPanel panelDashaVarshaInput=new JPanel();
		SpringLayout springLayout7 = new SpringLayout();
			mLabelDashaVarshaInput=new JLabel();
			springLayout7.putConstraint(SpringLayout.NORTH, mLabelDashaVarshaInput, 0, SpringLayout.NORTH, panelDashaVarshaInput);
			springLayout7.putConstraint(SpringLayout.WEST, mLabelDashaVarshaInput, 10, SpringLayout.WEST, panelDashaVarshaInput);
			mLabelDashaVarshaInput.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mTextFieldDashaVarshaInput=new MyNumericTextField();
			springLayout7.putConstraint(SpringLayout.NORTH, mTextFieldDashaVarshaInput, 4, SpringLayout.NORTH, mLabelDashaVarshaInput);
			springLayout7.putConstraint(SpringLayout.WEST, mTextFieldDashaVarshaInput, 75, SpringLayout.EAST, mLabelDashaVarshaInput);
			mTextFieldDashaVarshaInput.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT-8));
		panelDashaVarshaInput.setLayout(springLayout7);
		panelDashaVarshaInput.add(mLabelDashaVarshaInput);
		panelDashaVarshaInput.add(mTextFieldDashaVarshaInput);
		
		
		//ALL sub panels are initialized.
		//set sub panels to main holder...
				
		//Add each compound input panel to parent panel...
			
		GridLayout gridLayout=new GridLayout(10,0);//How many children you are goint to add..
		parentPanel.setLayout(gridLayout);
		parentPanel.add(panelAbout);//1
		parentPanel.add(panelPrevStarPramanaInput);//2
		parentPanel.add(panelBornStarPramanaInput);//3
		parentPanel.add(panelOperationDescription);//4
		parentPanel.add(panelOperationInput);//5
		parentPanel.add(panelDashaBhuktiDescription);//6
		parentPanel.add(panelDashaVarshaInput);//7
		
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
		mLabelAbout.setText(LABEL_NAMES.ABOUT_PANEL_NAKSHATRA);
		mLabelPreviousStarPramana.
			setText(LABEL_NAMES.PICK_PREVIOUS_STAR_PRAMANA);
		mLabelBornStarPramana.setText(LABEL_NAMES.PICK_BORN_STAR_PRAMANA);
		mLabelOperation.setText(LABEL_NAMES.PICK_ADD_OR_SUBTRACT);
		mTextAreaOperationDescription.setText(LABEL_NAMES.NAKSHATRA_ADD_OR_SUB_DESCRIPTION);
		
		mTextAreaDashaBhuktiDescription.setText(LABEL_NAMES.DASHABHUKTI_DESCRIPTION);
		mLabelDashaVarshaInput.setText(LABEL_NAMES.ENTER_DASHA_VARSHA);
		
		//Reset input fields		
		mJyotishyaTimePickerPrevStarPramana.resetJyotishyaTimeView();
		mJyotishyaTimePickerBornStarPramana.resetJyotishyaTimeView();
		mOperationModel.setSelectedItem(mOperationModel.getElementAt(PICKER_HEADER_POSITION));
		
		//Reset hints if any..
		
	}

	/* ************  CONTROL BUTTON METHODS *******************/
	@Override
	public void onNegativeButtonClick() {
		//[BACK]
		//disabled..
		//mActionListener.onNegativeButtonClick();
	}

	@Override
	public void onNeutralButtonClick() {
		//[RESET]
		resetCurrentPanelViews();
	}
	
	@Override
	public void onPositiveButtonClick() {
		//on [NEXT]
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
		
		//Previous star pramana..
		if(!mJyotishyaTimePickerPrevStarPramana.isValidTimeSelected()){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount+". Previous star pramana is invalid!"+LABEL_NAMES.LINE_FEED_SINGLE;
		}
		
		//Previous star pramana..
		if(!mJyotishyaTimePickerBornStarPramana.isValidTimeSelected()){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount+". Born star pramana is invalid!"+LABEL_NAMES.LINE_FEED_SINGLE;
		}
		
		if(mOperationModel.getIndexOf(mOperationModel.getSelectedItem())
				==PICKER_HEADER_POSITION){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount+". Should add/subtract ?"+LABEL_NAMES.LINE_FEED_SINGLE;
		}
		
		if(StringUtils.isEmpty(mTextFieldDashaVarshaInput.getText())){
			errorCount++;
			ERROR_MESSAGE+=LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+errorCount+". Dasha varsha can not be empty!"+LABEL_NAMES.LINE_FEED_SINGLE;		}
		
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

		JyotishyaTime jyotishyaTimePrevStarPramana=new JyotishyaTime(
					mJyotishyaTimePickerPrevStarPramana.getSelectedGalige(),
					mJyotishyaTimePickerPrevStarPramana.getSelectedVigalige());
		//[CORRECTED : As you do not know how to copy and where to paste :P
		// mJyotishyaTimePickerPrevStarPramana was set below also!!
		//Now, corrected :)
		JyotishyaTime jyotishyaTimeBornStarPramana=new JyotishyaTime(
				mJyotishyaTimePickerBornStarPramana.getSelectedGalige(),
				mJyotishyaTimePickerBornStarPramana.getSelectedVigalige());
		//CORRECTED]
		Boolean shouldAdd=true;
				if(mOperationModel.getIndexOf(mOperationModel.getSelectedItem())
					==POS_SUBTRACT){
					shouldAdd=false;
				}
				
		Integer dashaVarsha=Integer.parseInt(
				mTextFieldDashaVarshaInput.getText());
		
		//Get static instance..
		DataTransporter dataTransporter=DataTransporter.getInstance();
		dataTransporter.putJyotishyaTime(Key.PREV_STAR_PRAMANA_FOR_NAVAMSHA, jyotishyaTimePrevStarPramana);
		dataTransporter.putJyotishyaTime(Key.BORN_STAR_PRAMANA_FOR_NAVAMSHA, jyotishyaTimeBornStarPramana);
		dataTransporter.putBoolean(Key.SHOULD_SUM_FOR_NAKSHATRA_CALCULATION, shouldAdd);
		dataTransporter.putInteger(Key.DASHA_VARSHA, dashaVarsha);
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
