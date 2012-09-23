package org.jyotish.controllers;

import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.logic.LogicManager;
import org.jyotish.logic.ModelLogicType;
import org.jyotish.models.ModelConstants;
import org.jyotish.observers.ApplicationExitListener;
import org.jyotish.observers.ModelUpdateListener;
import org.jyotish.observers.ViewUpdateListner;
import org.jyotish.views.ViewManager;
import org.jyotish.views.ViewType;

/**
 * Main controller of whole application. This class controls and coordinates entire
 * application's flow by taking help of rest of manager classes.
 * <p>
 * main() method is defined by this class as this is the chief controller of 
 * whole application. 
 * @author Chandan
 *
 */
public final class ChiefManager implements ModelUpdateListener,ViewUpdateListner,
		ApplicationExitListener{
	

	/**
	 * By default,Log type of the project. However you can customize for debugging
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;


	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=ChiefManager.class.getSimpleName();

	
	/**
	 * Variable used to control no of application instance being started. 
	 * <p>
	 * i.e
	 * if an application instance is already running, and user tried to create another 
	 * instance. In this case, count is incremented and corresponding action will be
	 * handled. 
	 */
	private static short APP_VALID_INITIALIZATION_COUNT;
	
	/**
	 * Singleton instance of this class.
	 */
	private static ChiefManager self=null;
	
	/**
	 * singleton instance of {@link LogicManager}
	 */
	private LogicManager mLogicManager=null;
	
	/**
	 * singleton instance of {@link ViewManager}
	 */
	private ViewManager mViewManager=null;
	
	
	/**
	 * Hidden default constructor.
	 */
	private ChiefManager(){
		APP_VALID_INITIALIZATION_COUNT++;
		LogManager.processLog(MY_LOG_TYPE, TAG,	"appValidInitiationCount="+APP_VALID_INITIALIZATION_COUNT);
	}
	
	/**
	 * Method which returns singleton instance of this class.
	 * @return singleton instance of this class
	 */
	private static ChiefManager getInstance(){
		if(self==null){
			self=new ChiefManager();
		}
		
		return self;			
	}
	
	
	/**
	 * main method for entire application.
	 * @param args command line arguments.
	 */
	public static void main(String args[]){
		
		LogManager.logLineSeperator(MY_LOG_TYPE);
		LogManager.processLog(MY_LOG_TYPE, TAG,	"Starting the application");
		
		ChiefManager.getInstance();
		
		if(APP_VALID_INITIALIZATION_COUNT<=1){
			//Initialize Logic & View controllers..
			self.mLogicManager=LogicManager.getInstance(self);
			self.mViewManager=ViewManager.getInstance(self,self);
			
			//Set view type to be shown initially..
			self.mViewManager.setCurrentView(ViewType.SPLASH_SCREEN);
			self.mViewManager.setApplicationWindowVisibility(true);
			//Start splash timer..
			self.mLogicManager.startSplashTimer();
		}else{
			
			LogManager.logLineSeperator(MY_LOG_TYPE);
			LogManager.processLog(MY_LOG_TYPE, TAG,
				"Trying to start another instance of the application!");
			LogManager.processLog(MY_LOG_TYPE, TAG,
				"So, bringing existing instance into front..");

			//To be on safer side..
			if(self.mViewManager.getCurrentView()==null){
				self.mViewManager.setCurrentView(ViewType.SPLASH_SCREEN);
			}
			
			//Now, bring view to front..			
					
			LogManager.logLineSeperator(MY_LOG_TYPE);
		}
	}
	

	@Override
	public void onModelChange(ModelLogicType changedLogicModuleType) {
		//Update UI accordingly, here..	
		//viewType corresponds to the view which should be updated..
		LogManager.processLog(MY_LOG_TYPE, TAG, "Updating view for change of logic "
				+changedLogicModuleType.name());
		
		switch (changedLogicModuleType) {
		case SPLASH_TIMER_STARTED:
			self.mViewManager.setCurrentView(
					ViewType.SPLASH_SCREEN);
			break;
			
		case SPLASH_TIMER_ENDED:
			self.mViewManager.setCurrentView(
					ViewType.MENU_SCREEN);
			break;
			
		case SURYA_UDAYADI_GHATI:
			self.mViewManager.setCurrentView(
					ViewType.CALCULATIONS_TAB_DIVAMANA_BHUKTI_LAGNA_INPUT_PANEL);
			break;
		
		case TEDI:
			self.mViewManager.setCurrentView(
				ViewType.CALCULATIONS_TAB_LAGNA_RECURSIVE_INPUT_PANEL);
			break;
			
		case LAGNA_TEDI_CONTINUE_RECURSION:
			//Go to same panel again..
			self.mViewManager.setCurrentView(
				ViewType.CALCULATIONS_TAB_LAGNA_RECURSIVE_INPUT_PANEL);			
			break;
			
		case LAGNA_SKIP_RECURSION:
			//Go to next panel..
			self.mViewManager.setCurrentView(
					ViewType.CALCULATIONS_TAB_NAVAMSHA_INPUT_PANEL);
			break;
			
		case NAVAMSHA:
			self.mViewManager.setCurrentView(
					ViewType.CALCULATIONS_TAB_NAKSHATRA_DASHABHUKTI_INPUT_PANEL);
			break;
			
		case NAKSHATRA:
			//Only perform dasha bhukti calculations..
			//As necessary data is already read..
			try {								
				self.mLogicManager.performDashBhuktiCalculations();			
			} catch (Exception e) {
				LogManager.processException(MY_LOG_TYPE, TAG, e);
			} 			
			break;
		
		case DASHABHUKTI :
			//For time being..
			
			//FIXME : Set flag[via DataTransporter] to indicate
			//all calculations completed. which is used to disable calculation TAB.
			self.mViewManager.switchToResultTab();
			break;
			
		default:
			LogManager.processLog(MY_LOG_TYPE, TAG, 
				"Something went wrong while updating view on change of logic = "
					+changedLogicModuleType.name());
			break;
		}
			
	}

	@Override
	public void onViewChange(ViewType changedViewType,ViewType nextChangeViewType) {
		
		//Update model accordingly, here..
		//viewType corresponds to the view which is just changed..
		//nextChangeViewType corresponds to the next view to be shown/updated.
		
		//[IMP:
		//The two parameters used: Useful for screen with BACK & NEXT options.
		//So, for very changedViewType, nextChangeViewType will be having
		// either of two view references i.e either for BACK or for NEXT.
		//]
		
		switch (changedViewType) {
		case SPLASH_SCREEN:
			//Not expected !
			break;
			
		case MENU_SCREEN:
			//Hard coded for time being..
			//Though this is how it should be.
			//Because, no back end updations involved here..
			if(nextChangeViewType!=null)
				self.mViewManager.setCurrentView(nextChangeViewType);
			break;
			
		case TAB_SCREEN_CALCULATION_RESULT:
			//Not expected here !
			break;
		
		case TAB_CALCULATION_DEFAULT_INIT:
			//Fired from dummy mouseClick action initally from 
			//FrameBaseWindow#setCalculationAndResultTabScreen()
			//and later by ViewManager#onMouseClick(..).
			//So, set first calculation panel..
			self.mViewManager.setCurrentView(
					ViewType.CALCULATIONS_TAB_BIRTH_TIME_INPUT_PANEL);
			break;
			
		case CALCULATIONS_TAB_BIRTH_TIME_INPUT_PANEL:
			try {
				self.mLogicManager.calculateSuryaUdayadiGhati();
			} catch (Exception e) {
				LogManager.processException(MY_LOG_TYPE, TAG, e);
			} 
			break;
			
		case CALCULATIONS_TAB_DIVAMANA_BHUKTI_LAGNA_INPUT_PANEL:
			try {
				self.mLogicManager.calculateTedi();
			} catch (Exception e) {
				LogManager.processException(MY_LOG_TYPE, TAG, e);
			} 
			break;
			
		case CALCULATIONS_TAB_LAGNA_RECURSIVE_INPUT_PANEL:
			
			//BETTER TO DISABLE BACK CONTROLE BUTTON FOR THIS VIEW TYPE!!!!
			try {								
				self.mLogicManager.calculateLagnaTedi();				
			} catch (Exception e) {
				LogManager.processException(MY_LOG_TYPE, TAG, e);
			} 
			break;
		
		case CALCULATIONS_TAB_NAVAMSHA_INPUT_PANEL:
			try {
				self.mLogicManager.calculateNavamsha();
			} catch (Exception e) {
				LogManager.processException(MY_LOG_TYPE, TAG, e);
			} 
			break;
			
		case CALCULATIONS_TAB_NAKSHATRA_DASHABHUKTI_INPUT_PANEL:
			try {
				self.mLogicManager.performNakshatraCalculations();
				//Do not worry about dasha bhukti calculations
				//as it will be performed once nakshatra calculations
				//are done. i.e via onModelChange(..).
				
			} catch (Exception e) {
				LogManager.processException(MY_LOG_TYPE, TAG, e);
			} 
			break;
			

		default:
			LogManager.processLog(MY_LOG_TYPE, TAG, 
				"Something went wrong while updating model, from view of type = "
					+changedViewType.name());
			break;
		}
	}
		
	
	@Override
	public void onExitRequest(ViewType viewType) {
		//Call exit confirm requeste method to handle further..
		onExitConfirmRequest();	
	}
	
	@Override
	public void onExitConfirmRequest() {
		//Request view manager to show alert..
		self.mViewManager.onExitConfirmRequest();
	}

	@Override
	public void onExitConfirmed() {
		//TODO LogicManager saves and closes.
		
		//Finally
		
		System.exit(0);
		finalize();
	}
	
	@Override
	public void finalize(){
		APP_VALID_INITIALIZATION_COUNT--;
		
		LogManager.logLineSeperator(MY_LOG_TYPE);
		LogManager.processLog(MY_LOG_TYPE, TAG, 
			"Finalizing application. appValidInitiationCount="+APP_VALID_INITIALIZATION_COUNT);
		LogManager.logLineSeperator(MY_LOG_TYPE);
	}

	
	
}
