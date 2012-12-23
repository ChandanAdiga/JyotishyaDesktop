package org.jyotish.views;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.models.ModelConstants;
import org.jyotish.observers.ActionListener;
import org.jyotish.views.Dialog.ApplicatinDialog;
import org.jyotish.views.ViewConstants.BUTTON_NAMES;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * Panel containing views related to displaying of main menu. 
 * @author Chandan
 *
 */
final class PanelMainMenu extends JPanel {
	
	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=PanelMainMenu.class.getSimpleName();

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = -543538368890240227L;
	
	/**
	 * Observer for mouse click events..
	 */
	private final ActionListener mMouseClickListener;
	
	private final JTextArea mTextAreaAbout;
	
	/**
	 * reference for dialog.
	 */
	@SuppressWarnings("unused")
	private final ApplicatinDialog mDialog;
	
	/**
	 * Create the panel.
	 * @param mouseClickListener Observer for mouse click events
	 * @param dialog Reference for application dialog
	 */
	PanelMainMenu(ActionListener mouseClickListener, ApplicatinDialog dialog) {
		
		setSize(ViewConstants.WINDOW_SCREEN_FRAME_WIDTH, 
				ViewConstants.WINDOW_SCREEN_FRAME_HEIGHT);
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		mMouseClickListener=mouseClickListener;
		mDialog=dialog;
		/*
		setBounds(ViewConstants.RESULT_SsCREEN_FRAME_X,
				ViewConstants.RESULT_SCREEN_FRAME_Y,
				ViewConstants.RESULT_SCREEN_FRAME_WIDTH,
				ViewConstants.RESULT_SCREEN_FRAME_HEIGHT);
		*/
		
		
		JButton mButtonCalculations=new JButton();
		springLayout.putConstraint(SpringLayout.NORTH, mButtonCalculations, 110, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, mButtonCalculations, -331, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mButtonCalculations, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		//Set some attributes..	
		mButtonCalculations.setText(BUTTON_NAMES.PERFORM_CALCULATIONS);
		mButtonCalculations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				mMouseClickListener.onMouseClick(
						ClickedView.BUTTON_CACLULATION_SCREEN_IN_MENUSCREEN);				
			}
		});
		
		//FIXME Drop them in separate panel say- "About"
		mTextAreaAbout=new JTextArea();
		springLayout.putConstraint(SpringLayout.WEST, mTextAreaAbout, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, mTextAreaAbout, -10, SpringLayout.SOUTH, this);
		mTextAreaAbout.setText("Application: "+ViewConstants.ABOUT_APP_NAME//);
		//mTextAreaAbout.append(
				+"\nBuild Version: "+ViewConstants.ABOUT_APP_VERSION//);
		//mTextAreaAbout.append(
				+"\nBuild Date: "+ViewConstants.ABOUT_APP_BUILD_DATE//);
		//mTextAreaAbout.append(
				+"\n\nDeveloped for : "+ViewConstants.ABOUT_APP_CLIENT//);
		//mTextAreaAbout.append(
				+"\n\nDeveloped by : "+ViewConstants.ABOUT_APP_DEVELOPER);
		mTextAreaAbout.setColumns(80);
		mTextAreaAbout.setRows(6);
		mTextAreaAbout.setOpaque(false);
		mTextAreaAbout.setEditable(false);
		mTextAreaAbout.setLineWrap(true);
		mTextAreaAbout.setWrapStyleWord(true);
		
		
		add(mButtonCalculations);
		add(mTextAreaAbout);
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Prepared Main menu screen..");
	}

}
