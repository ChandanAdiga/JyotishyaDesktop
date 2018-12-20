package org.jyotish.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.models.ModelConstants;
import org.jyotish.observers.ActionListener;
import org.jyotish.observers.ApplicationExitListener;
import org.jyotish.views.Dialog.ApplicatinDialog;
import org.jyotish.views.Dialog.OnCancelListener;
import org.jyotish.views.Dialog.DialogButtonClickListener;
import org.jyotish.views.ViewConstants.BUTTON_NAMES;
import org.jyotish.views.ViewConstants.LABEL_NAMES;
import org.jyotish.views.ViewConstants.TAB_INDEX;
import org.jyotish.views.ViewConstants.TAB_NAME;

/**
 * Application view holder. This class has been made singleton to maintain
 * single application window at any instant. This class switches screens as 
 * directed by {@link ViewManager}.
 * @author Chandan
 *
 */
final class FrameBaseWindow extends JFrame implements ApplicatinDialog{
	
	/*
	 * Design will be dividing entire window into two parts. One is
	 * result part and second is calculation part. Result part has 
	 * only readable elements. Calculation part has input fields and controllers 
	 * to navigate forward and backward.
	 * 		Any changes in foreground will be updated when user uses controllers
	 * and navigate from one screen to another. Even calculations are 
	 * designed to suit screen navigation and vice versa.
	 * 		Further, changes/calculations done in previous screen affects view,
	 * specifically result view.
	 * ------------------------------------------------------------------------
	 * 		This base window has two panels- CalculationPanel[CALCULATION TAB] and 
	 * ResultPanel[RESULT TAB]. Each will be updated accordingly.
	 * 
	 */
	
	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=FrameBaseWindow.class.getSimpleName();

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = -1811002233016076248L;
	
	
	/**
	 * TabPanel holds calculations tab and result tab.
	 */
	private JTabbedPane mTab;
	
	/**
	 * Panel which holds all views associated with result.
	 * @see PanelResult
	 */
	private JPanel mResultPanel;
	
	/**
	 * Panel which holds all views related to calculations.
	 */
	private JPanel mCalculationPanel;
	
	/**
	 * Application dialog state reference.
	 */
	private boolean mIsDialogActive;
	
	/**
	 * Mouse click event observer.
	 */
	private ActionListener mMouseClickActionListener;
	
	/**
	 * Text area for alert dialog's message.
	 */
	private JTextArea mTextAreaDialogMessage;
	
	/**
	 * Holds CURRENTLY VISIBLE tab's index.
	 * <p>
	 *    i.e When user clicks on a tab, selectedTabIndex will be clicked tab's index.
	 * But, this variable holds index of tab which was  visible prior to  user click.
	 */
	private int mPreviouslySelectedTabIndex=0;
	
	/**
	 * Hidden default constructor.
	 * @param actionListener Specifically for mouse click action observer
	 * @param exitListener Exit event observer
	 */
	private FrameBaseWindow(ActionListener actionListener,
			ApplicationExitListener exitListener) {
		setResizable(false);
		//Set UI Look And Feel..
		try {
			//UIManager.getSystemLookAndFeelClassName();//Windows style..
			//UIManager.getCrossPlatformLookAndFeelClassName();//Java swing standard
			//"com.sun.java.swing.plaf.motif.MotifLookAndFeel" //Moti style.
			setTitle(ViewConstants.ABOUT_APP_NAME);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			LogManager.processException(MY_LOG_TYPE, TAG, e);
		}
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating FrameBaseWindow..");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Set custom listener for Window actions, specifically for exit action
		addWindowListener(new WindowActionListener(exitListener));
		
		setBounds(ViewConstants.WINDOW_SCREEN_FRAME_X,
			ViewConstants.WINDOW_SCREEN_FRAME_Y,
			ViewConstants.WINDOW_SCREEN_FRAME_WIDTH,
			ViewConstants.WINDOW_SCREEN_FRAME_HEIGHT);
		
		//setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(new BorderLayout(
			ViewConstants.WINDOW_CONTENTPANE_BORDER_HORIZONTAL_GAP,
					ViewConstants.WINDOW_CONTENTPANE_BORDER_VERTICAL_GAP));

		
		//Static testing..
		//Fine setMainMenuScreen();
		//Fine setSplashScreen();
		//Fine setCalculationAndResultTabScreen();
			
	};
	
		
	/**
	 * Singleton instance of this class.
	 */
	private static FrameBaseWindow self;
	
	/**
	 * Method which returns singleton instance of this class.
	 * @param actionListener Specifically for mouse click observer
	 * @param exitListener Exit event observer
	 * @return singleton instance of {@link FrameBaseWindow}
	 */
	public static FrameBaseWindow getInstance(ActionListener actionListener,
			ApplicationExitListener exitListener){
		
		if(self==null){
			self=new FrameBaseWindow(actionListener,exitListener);
		}
		
		self.mMouseClickActionListener=actionListener;
		
		self.mTextAreaDialogMessage=new JTextArea();
		self.mTextAreaDialogMessage.setColumns(40);
		self.mTextAreaDialogMessage.setOpaque(false);
		self.mTextAreaDialogMessage.setEditable(false);
		self.mTextAreaDialogMessage.setLineWrap(true);
		self.mTextAreaDialogMessage.setWrapStyleWord(true);
		
		
		return self;
	}
	
	/**
	 * Requests application window visibility.
	 * @param visible boolean flag which if true requests window visible else minimize.
	 */
	void requestWindowVisibility(final boolean visible){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogManager.processLog(MY_LOG_TYPE,TAG,
							"setWinddowVisible("+visible+")");
					if(visible){
						self.setVisible(true);
						self.toFront();
					}else{
						self.toBack();
					}
					
					
					//self.setVisible(true);
				} catch (Exception e) {
					LogManager.processException(MY_LOG_TYPE, TAG, e);
				}
			}
		});
	}
	
	/**
	 * Enables or disables user interaction.
	 * @param permitted permitted if true, else disabled.
	 */
	void setUserInteractionPermitted(boolean permitted){
		LogManager.processLog(MY_LOG_TYPE, TAG, "User interaction permitted ?"+permitted);
		self.setEnabled(permitted);
	}
	
	
	/**
	 * Sets splash screen. No other views will be visible during splash screen.
	 * <p>
	 * Note that you have to take care of duration of splash screen and 
	 * responsibility to switch to next desired screen after splash duration.
	 * <p>
	 * Typically {@link ViewManager} will take care of above stuffs.
	 */
	void setSplashScreen(){
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating splash screen..");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					//First remove all current panels..
					getContentPane().removeAll();		
					
					//Set requested panel to frame..
					getContentPane().add(new PanelSplash());
					//Finally validate..
					validate();
				} catch (Exception e) {
					LogManager.processException(MY_LOG_TYPE, TAG, e);
				}
			}
		});	
	}
	
	/**
	 * Sets menu screen.
	 * <p>
	 * Typically {@link ViewManager} will take care of controlling this.
	 */
	void setMainMenuScreen(){
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating main menu screen..");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					//First remove all current panels..
					getContentPane().removeAll();		
					
					//Set requested panel to frame..
					getContentPane().add(new PanelMainMenu(mMouseClickActionListener,self));
					//Finally validate..
					validate();
				} catch (Exception e) {
					LogManager.processException(MY_LOG_TYPE, TAG, e);
				}
			}
		});	
	}
	
	/**
	 * Sets tab panel which holds calculation and result tabs.
	 * <p>
	 * Typically {@link ViewManager} will take care of controlling this.
	 */
	void setCalculationAndResultTabScreen(){
		//FIXME Need to maintain flag which gives status of tab screen in main window.
		//Set calculation panel ..
		self.mCalculationPanel=new JPanel();
		self.mCalculationPanel.setLayout(new BorderLayout());
		self.mCalculationPanel.setName(TAB_NAME.CALCULATIONS);
		self.mCalculationPanel.setSize(ViewConstants.CALCULATION_SCREEN_FRAME_WIDTH, 
				ViewConstants.CALCULATION_SCREEN_FRAME_HEIGHT);
		//If not tab panel add(mCalculationPanel);
		
		//Set result panel ..
		self.mResultPanel=new PanelResult();
		self.mResultPanel.setName(TAB_NAME.RESULT);
		//If not tab panel add(mResultPanel);		
		
		//Set dummy panel for main menu tab option..
		JPanel mMainMenuPanel=new JPanel();
		mMainMenuPanel.setName(TAB_NAME.MAIN_MENU);
		
		self.mTab=new JTabbedPane();
		self.mTab.add(self.mCalculationPanel,
				ViewConstants.TAB_INDEX.TAB_CALCULATION.ordinal());
		self.mTab.add(self.mResultPanel,
				ViewConstants.TAB_INDEX.TAB_RESULT.ordinal());
		self.mTab.add(mMainMenuPanel,
				ViewConstants.TAB_INDEX.TAB_MAIN_MENU.ordinal());
		
		self.mTab.addChangeListener(new MyCalculationResultTabChangeListener());
				
		//Tab view is set.So add tab view to base window..
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					//First remove all current panels..
					getContentPane().removeAll();		
					
					//Set requested panel to frame..
					getContentPane().add(self.mTab);
					//Finally validate..
					validate();					
				} catch (Exception e) {
					LogManager.processException(MY_LOG_TYPE, TAG, e);
				}
			}
		});	
		
		//All is set..Notify view manager that tab view is set..
		
		//By default, calculation tab is set. So need to add default/first panel
		//of calculation. This is controlled by ChiefManger. 
		//hence just notify dummy mouse click which will set initial calculation panel.
		self.mMouseClickActionListener.onMouseClick(
				ClickedView.BUTTON_READ_BIRTH_TIME_DETAILS_FOR_CACLULATION_TAB);
	}
	
	
	
	/**
	 * Sets view of type {@link ViewType}  as view to the calculation tab view.
	 * @param viewType Calculation view type to be set.
	 */
	void setCalculationPanel(final ViewType viewType){
		if(self.mTab!=null & self.mTab.isVisible()
				& mTab.getSelectedIndex()==TAB_INDEX.TAB_CALCULATION.ordinal()){
			//Then only proceed..
			switch (viewType) {
			case CALCULATIONS_TAB_BIRTH_TIME_INPUT_PANEL:
				//LogManager.processLog(MY_LOG_TYPE, TAG,
				//		"Setting default screen for calculation tab..");	
				//FIXME : Reset calculation ENDED FLAG.[Via DataTransporter]
				setCalculationPanel(
						new PanelCalculationTabBirthTimeReader(
								self.mMouseClickActionListener,self));
				break;
				
			case CALCULATIONS_TAB_DIVAMANA_BHUKTI_LAGNA_INPUT_PANEL:
				//LogManager.processLog(MY_LOG_TYPE, TAG,
				//		"Setting tedi screen input reader...");				
				setCalculationPanel(
						new PanelCalculationTabTediInputs(
								self.mMouseClickActionListener,self));
				break;
				
			case CALCULATIONS_TAB_LAGNA_RECURSIVE_INPUT_PANEL:
				LogManager.processLog(MY_LOG_TYPE, TAG,
						"Setting lagna screen input reader...");				
				setCalculationPanel(
						new PanelCalculationTabLagnaInputs(
								self.mMouseClickActionListener,self));
				break;
				
			case CALCULATIONS_TAB_NAVAMSHA_INPUT_PANEL:
				//LogManager.processLog(MY_LOG_TYPE, TAG,
				//		"Setting Navamsha screen input reader...");				
				setCalculationPanel(
						new PanelCalculationTabNavamshaInputs(
								self.mMouseClickActionListener,self));
				break;
				
			case CALCULATIONS_TAB_NAKSHATRA_DASHABHUKTI_INPUT_PANEL:
				//LogManager.processLog(MY_LOG_TYPE, TAG,
				//		"Setting Nakshatra calculation screen input reader...");				
				setCalculationPanel(
						new PanelCalculationTabNakshatraInputs(
								self.mMouseClickActionListener,self));
				break;
				
				

			default:
				LogManager.processLog(MY_LOG_TYPE, TAG,
						"Unexpected request setCalculationPanel("
						+viewType.name()+")");
				break;
			}
		}
	}
	
	/**
	 * Sets Children of type {@link JPanel}  as view to the application frame/window. {@link JPanel}
	 * will be super class for screen requested. 
	 * @param screen Screen to set.
	 */
	private void setCalculationPanel(final JPanel screen){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {		
					//FIXME Need to check whether calculation-result tab pane is set or not.
					//If set , then only we can proceed..
					//Else probably show alert something went wrong..
					
					//First remove all current panels..
					self.mCalculationPanel.removeAll();		
					
					//Set requested panel to frame..
					self.mCalculationPanel.add(screen);
					//Finally validate..
					validate();
				} catch (Exception e) {
					LogManager.processException(MY_LOG_TYPE, TAG, e);
				}
			}
		});				
	}

	/**
	 * Sets the desired tab visible.
	 * @param index index of the tab to set.
	 * @see TAB_INDEX
	 */
	void switchToTab(TAB_INDEX index){
		switch(index){
			case TAB_CALCULATION:
				if(self.mTab!=null & self.mTab.isVisible())
					self.mTab.setSelectedIndex(TAB_INDEX.TAB_CALCULATION.ordinal());
				break;
				
			case TAB_RESULT:
				if(self.mTab!=null & self.mTab.isVisible())
					self.mTab.setSelectedIndex(TAB_INDEX.TAB_RESULT.ordinal());
				break;
				
			//In rest of the case do not do any stuffs..
		}
	}
	
	/**
	 * Polymorphic  version of {@link #switchToTab(TAB_INDEX)} with
	 * int parameter.
	 * @param index Index of tab to switch.
	 */
	private void switchToTab(int index){
		switch(index){
			case 0:
				switchToTab(TAB_INDEX.TAB_CALCULATION);
				break;
				
			case 1:
				//And for default,
			default:
				switchToTab(TAB_INDEX.TAB_RESULT);
				break;
		}
	}

	/**
	 * Observer class for tab change events of calculation-result tabview.
	 * @author chandan
	 */
	private class MyCalculationResultTabChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent event) {
			if(self.mTab!=null & self.mTab.isVisible()){
				switch(self.mTab.getSelectedIndex()){
				case 0:
					//ViewConstants.TAB_INDEX.TAB_CALCULATION.ordinal()
					LogManager.processLog(MY_LOG_TYPE, TAG, "Switching to calculation tab..");	
					break;
					
				case 1:
					//ViewConstants.TAB_INDEX.TAB_RESULT.ordinal()
					LogManager.processLog(MY_LOG_TYPE, TAG, "Switching to result tab..");	
					if(self.mResultPanel!=null){
						((PanelResult)self.mResultPanel).loadUpdatedResult();
					}					
					break;
					
				case 2:
					//ViewConstants.TAB_INDEX.TAB_MAIN_MENU.ordinal()
					LogManager.processLog(MY_LOG_TYPE, TAG, "Switching from tabview to main menu..");
					self.prepareDialog(
							LABEL_NAMES.WARNING, 
							LABEL_NAMES.EXIT_CALCULATION_MESSAGE,
							BUTTON_NAMES.YES,
							BUTTON_NAMES.NO,
							/*YES TO QUIT*/new DialogButtonClickListener() {								
								@Override
								public void onClick() {
									setMainMenuScreen();
								}
							},
							/* NO TO QUIT*/new DialogButtonClickListener() {								
								@Override
								public void onClick() {
									switchToTab(mPreviouslySelectedTabIndex);
								}
							},
							new OnCancelListener() {								
								@Override
								public void onCancelDialog() {
									switchToTab(mPreviouslySelectedTabIndex);									
								}
							});
					break;					
				}//END of switch block..
				//Set 
				self.mPreviouslySelectedTabIndex=
					self.mTab.getSelectedIndex();
				
			}
		}
	}

	/* ******************* DIALOG REALTED STUFFS.[START] ************************/

	@Override
	public ApplicatinDialog prepareDialog(final String title,final  String message,
			final String positiveButtonName,final  String negativeButtonName,
			final DialogButtonClickListener positiveListener,
			final DialogButtonClickListener negativeListener,
			final OnCancelListener cancelListener) {
		
		final Object []dialogOptions=new Object[]{
				positiveButtonName,
				negativeButtonName
			};
		
		self.mTextAreaDialogMessage.setText(message);
				
		final DialogButtonClickListener [] buttonClickListner=new DialogButtonClickListener[]{
				positiveListener,
				negativeListener
			};
		
		self.mIsDialogActive=true;
	
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try 
				{		
					int result= JOptionPane.showOptionDialog(
						self, self.mTextAreaDialogMessage, title, 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, 
						dialogOptions, dialogOptions[1]/*Negative button*/);
					
					if(result>=0 && result<buttonClickListner.length){
						if(buttonClickListner[result]!=null)
							buttonClickListner[result].onClick();
					}						
					if(cancelListener!=null)
						cancelListener.onCancelDialog();
					self.mIsDialogActive=false;
					
				} catch (Exception e) {
					LogManager.processException(MY_LOG_TYPE, TAG, e);
				}
			}
		});	
		
		return self;
	}
	
	@Override
	public boolean isDialogVisible() {
		
		return self.mIsDialogActive;
	}
	
	
	/* ******************* DIALOG REALTED STUFFS. [END]  ************************/
	
	/**
	 * Pop ups alert dialog, when user attempts to close application.
	 * If user confirms from dialog, then exit confirmed event will be
	 * fired on the observer.
	 * @param exitConfirmedListner Observer for exit confirmation.
	 */
	public void showExitAlert(final ApplicationExitListener exitConfirmedListner){
		self.prepareDialog(
				LABEL_NAMES.WARNING, 
				LABEL_NAMES.EXIT_MESSAGE,
				BUTTON_NAMES.YES, 
				BUTTON_NAMES.NO, 
				new DialogButtonClickListener() {					
					@Override
					public void onClick() {
						//On click BUTTON_NAMES.YES
						exitConfirmedListner.onExitConfirmed();
					}
				}, null,null);
		
	}


}
