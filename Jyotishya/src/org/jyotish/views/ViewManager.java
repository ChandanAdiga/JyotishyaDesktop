package org.jyotish.views;

import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.controllers.DataTransporter;
import org.jyotish.models.ModelConstants;
import org.jyotish.observers.ActionListener;
import org.jyotish.observers.ApplicationExitListener;
import org.jyotish.observers.ModelUpdateListener;
import org.jyotish.views.ViewConstants.TAB_INDEX;

/**
 * Manager class which controls flow of Jyotishya views.
 * @author Chandan
 *
 */
public final class ViewManager  implements ActionListener,ApplicationExitListener {

	
	/**
	 * By default,Log type of the project. However you can customize for debugging
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;


	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=ViewManager.class.getSimpleName();

	/**
	 * Holds current screen category of the flow.
	 */
	private ViewType mCurrentViewType=null;
	
	/**
	 * singleton instance of this class.
	 */
	private static ViewManager self=null;
	
	/**
	 * Instance of Application main Window.
	 */
	private FrameBaseWindow mBaseWindow=null;
	
	/**
	 * Observer for updating model up on change in view.
	 */
	private ModelUpdateListener mModelUpdateListener=null;
	
	/**
	 * Observer for application exit action. There will be two actions performed by 
	 * this single listener. i.e exit alert and exit confirmation.
	 */
	private ApplicationExitListener mApplicationExitListener=null;
	
	
	/**
	 * Hidden default constructor.
	 */
	private ViewManager() {

		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating ViewManager..");
	}
	
	
	
	/**
	 * Method which provides singleton instance for this class.
	 * @param modelListener Observer for view change.
	 * @param exitListener Observer for exit action.
	 * @return singleton instance of this class.
	 */
	public static ViewManager getInstance(ModelUpdateListener modelListener
			,ApplicationExitListener exitListener){
		if(self==null){
			self=new ViewManager();
			self.mBaseWindow=FrameBaseWindow.getInstance(self,self);
			self.mModelUpdateListener=modelListener;
			self.mApplicationExitListener=exitListener;
		}
		
		return self;
	}

	/**
	 * Brings or hides application window.
	 * @param visible if true bring window to front else push it back.
	 */
	public void setApplicationWindowVisibility(boolean visible){
		self.mBaseWindow.requestWindowVisibility(visible);
	}

	/**
	 * @param currentView the mCurrentView to set
	 */
	public void setCurrentView(ViewType currentView) {
		self.mCurrentViewType = currentView;
		self.refresh();
	}
	
	/**
	 * @return the mCurrentView
	 */
	public ViewType getCurrentView() {
		return self.mCurrentViewType;
	}



	@Override
	public void onNegativeButtonClick() {
		
		//Trigger view changed..with nexChangeview type set to prev screen..
		switch (self.mCurrentViewType) {
			case CALCULATIONS_TAB_BIRTH_TIME_INPUT_PANEL:
				//Not expected you here! as it is disabled :P
				break;
				
			case CALCULATIONS_TAB_DIVAMANA_BHUKTI_LAGNA_INPUT_PANEL:
				//Not expected you here! as it si disabled ;P
				break;
				
				//same as above for all cases.. :P
			
			default:
				//Do nothing.. :P
				break;
		}
	}

	@Override
	public void onNeutralButtonClick() {
		
		switch (self.mCurrentViewType) {
			case CALCULATIONS_TAB_LAGNA_RECURSIVE_INPUT_PANEL:
				self.mModelUpdateListener.onViewChange(
						self.mCurrentViewType,null);	
				//2nd parameter s neglected as flag is set in originator
				break;
			
			default:
				//Do nothing.. :P
				break;
		}
	}

	@Override
	public void onPositiveButtonClick() {
		
		switch (self.mCurrentViewType) {
			case CALCULATIONS_TAB_BIRTH_TIME_INPUT_PANEL:
				self.mModelUpdateListener.onViewChange(
						self.mCurrentViewType,null);	//2nd parameter s neglected		
				break;
			case CALCULATIONS_TAB_DIVAMANA_BHUKTI_LAGNA_INPUT_PANEL:
				self.mModelUpdateListener.onViewChange(
						self.mCurrentViewType,null);	//2nd parameter s neglected			
				break;
				
			case CALCULATIONS_TAB_LAGNA_RECURSIVE_INPUT_PANEL:
				self.mModelUpdateListener.onViewChange(self.mCurrentViewType,
						null);//2nd parameter s neglected as flag is set in originator	
				break;
				
			case CALCULATIONS_TAB_NAVAMSHA_INPUT_PANEL:
				/**
				 * User clicked on NEXT from #PanelCalculationTabNavamshaInputs
				 * which is positive  button.
				 */
				self.mModelUpdateListener.onViewChange(
						self.mCurrentViewType,null);//2nd parameter s neglected!!			
				break;
				
			case CALCULATIONS_TAB_NAKSHATRA_DASHABHUKTI_INPUT_PANEL:
				/**
				 * User clicked on NEXT from #PanelCalculationTabNakshatraInputs
				 * which is positive  button.
				 */
				self.mModelUpdateListener.onViewChange(
						self.mCurrentViewType,null);//2nd parameter s neglected!!			
				break;

			default:
				//Do nothing.. :P
				break;
		}
	}
	

	@Override
	public void setEnabled(boolean enable) {
		LogManager.processLog(MY_LOG_TYPE, TAG, "setEnabled("+enable+")");
		self.mBaseWindow.setUserInteractionPermitted(enable);
	}
	

	@Override
	public void onMouseClick(ClickedView clickedView) {
		
		/**
		 * NOTE: refresh() can not be generalized. Hence made case specific and
		 * not moved out of block.
		 * 
		 * Same reason for model change as well.
		 */
		switch(clickedView){
		
		case BUTTON_CACLULATION_SCREEN_IN_MENUSCREEN:
				self.mCurrentViewType=ViewType.TAB_SCREEN_CALCULATION_RESULT;
				self.refresh();
				break;
				
		case BUTTON_READ_BIRTH_TIME_DETAILS_FOR_CACLULATION_TAB:
				self.mModelUpdateListener.onViewChange(
					ViewType.TAB_CALCULATION_DEFAULT_INIT,null);
				break;				
		}
		
	}
	
	/**
	 * Switch to result tab.
	 */
	public void switchToResultTab(){
		//Not a good design :(
		//but for now, no other way... :P
		self.mBaseWindow.switchToTab(TAB_INDEX.TAB_RESULT);
	}


	@Override
	public void refresh() {
		
		switch (self.mCurrentViewType) {
		case SPLASH_SCREEN:
			self.mBaseWindow.setSplashScreen();
			break;
			
		case MENU_SCREEN:
			self.mBaseWindow.setMainMenuScreen();
			break;
			
		case TAB_SCREEN_CALCULATION_RESULT:
			DataTransporter.resetTransporter();
			self.mBaseWindow.setCalculationAndResultTabScreen();
			break;
			//TODO In rest of the cases need to check whether tab screen set or not.
			//As rest of calculation panels needs above tab panel to be set in prior.

		case CALCULATIONS_TAB_BIRTH_TIME_INPUT_PANEL:		
			//Just pass on..
			self.mBaseWindow.setCalculationPanel(self.mCurrentViewType);//Same as that of this CASE.
			break;
			
		case CALCULATIONS_TAB_DIVAMANA_BHUKTI_LAGNA_INPUT_PANEL:		
			//Just pass on..
			self.mBaseWindow.setCalculationPanel(self.mCurrentViewType);//Same as that of this CASE.
			break;
			
		case CALCULATIONS_TAB_LAGNA_RECURSIVE_INPUT_PANEL:
			self.mBaseWindow.setCalculationPanel(self.mCurrentViewType);//Same as that of this CASE.
			break;
			
		case CALCULATIONS_TAB_NAVAMSHA_INPUT_PANEL:
			self.mBaseWindow.setCalculationPanel(self.mCurrentViewType);//Same as that of this CASE.
			break;
			
		case CALCULATIONS_TAB_NAKSHATRA_DASHABHUKTI_INPUT_PANEL:
			self.mBaseWindow.setCalculationPanel(self.mCurrentViewType);//Same as that of this CASE.
			break;
			
			
		default:
			self.mBaseWindow.setMainMenuScreen();
			break;
		}
		
	}


	@Override
	public void onExitRequest(ViewType viewType) {		
		self.mApplicationExitListener.onExitRequest(self.mCurrentViewType);	
	}
	
	@Override
	public void onExitConfirmRequest() {
		//Show alert to confirm..
		self.mBaseWindow.showExitAlert(self);
	}
	
	@Override
	public void onExitConfirmed() {
		//Fire exit confirm event for chief controller..
		self.mApplicationExitListener.onExitConfirmed();
	}

}