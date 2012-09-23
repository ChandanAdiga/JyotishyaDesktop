package org.jyotish.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.chandan.utils.StringUtils;
import org.jyotish.models.ModelConstants;
import org.jyotish.observers.ActionListener;
import java.awt.Component;

/**
 * Panel consisting of control buttons
 * @author chandan
 *
 */
final class PanelControlButtons extends JPanel {

	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=PanelControlButtons.class.getSimpleName();

	
	/**
	 * Generated version ID.
	 */
	private static final long serialVersionUID = 2429478144962436792L;
	
	/**
	 * Negative button reference.
	 */
	private JButton mNegativeButton;
	
	/**
	 * Neutral button reference.
	 */
	private JButton mNeutralButton;
	
	/**
	 *  Positive button reference.
	 */
	private JButton mPositiveButton;
	
	
	/**
	 * Listener for standard actions.
	 */
	private ActionListener mActionObserver;
	
	/**
	 * Constructor.
	 * @param listner observer for standard actions
	 */
	PanelControlButtons(ActionListener listner){
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating Control Buttons..");
		
		setSize(ViewConstants.CONTROL_BUTTON_PANEL_WIDTH, 
				ViewConstants.CONTROL_BUTTON_PANEL_HEIGHT);
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);		
		
		mActionObserver=listner;
		
		mNegativeButton=new JButton("123456789012345");
		mNegativeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		springLayout.putConstraint(SpringLayout.WEST, mNegativeButton, 40, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, mNegativeButton, -7, SpringLayout.SOUTH, this);
		mNeutralButton=new JButton("123456789012345");
		springLayout.putConstraint(SpringLayout.NORTH, mNeutralButton, 0, SpringLayout.NORTH, mNegativeButton);
		springLayout.putConstraint(SpringLayout.WEST, mNeutralButton, 139, SpringLayout.EAST, mNegativeButton);
		springLayout.putConstraint(SpringLayout.SOUTH, mNeutralButton, -7, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, mNeutralButton, -295, SpringLayout.EAST, this);
		mNeutralButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		mPositiveButton=new JButton("123456789012345");
		springLayout.putConstraint(SpringLayout.WEST, mPositiveButton, 131, SpringLayout.EAST, mNeutralButton);
		mPositiveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		springLayout.putConstraint(SpringLayout.NORTH, mPositiveButton, 0, SpringLayout.NORTH, mNegativeButton);
		springLayout.putConstraint(SpringLayout.EAST, mPositiveButton, -41, SpringLayout.EAST, this);
		
		mNegativeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				mActionObserver.onNegativeButtonClick();
			}
		});
		
		mNeutralButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				mActionObserver.onNeutralButtonClick();
			}
		});
		
		mPositiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				mActionObserver.onPositiveButtonClick();
			}
		});
	
		add(mNegativeButton);
		add(mNeutralButton);
		add(mPositiveButton);
		
		setPositiveButtonName("");
		setNeutralButtonName("");
		setNegativeButtonName("");
		
	}
	
	/**
	 * Formats button name by controlling maximum length,null check etc.
	 * @param name Button name to be formated.
	 * @return Formated button name.
	 */
	private String getFormatedButtonName(String name){
		if(StringUtils.isEmpty(name)){
			name="";
		}else {
			if(name.length()>ViewConstants.MAXIMUM_BUTTON_LENGTH){
				name=name.substring(0, ViewConstants.MAXIMUM_BUTTON_LENGTH-3)+"...";
			}
		}
		
		while(name.length()<ViewConstants.MAXIMUM_BUTTON_LENGTH){
			name+=" ";
		}
		//Finally your name will be of length ViewConstants.MAXIMUM_BUTTON_LENGTH
		return name;
	}
	
	/**
	 * Set positive button name.
	 * @param name Name of positive button.
	 */
	void setPositiveButtonName(String name){
		if(this.mPositiveButton!=null)
			this.mPositiveButton.setText(getFormatedButtonName(name));
	}
	
	/**
	 * Set neutral button name.
	 * @param name Name of neutral button.
	 */
	void setNeutralButtonName(String name){
		if(this.mNeutralButton!=null)
			this.mNeutralButton.setText(getFormatedButtonName(name));
	}

	/**
	 * Set negative button name.
	 * @param name Name of negative button.
	 */
	void setNegativeButtonName(String name){
		if(this.mNegativeButton!=null)
			this.mNegativeButton.setText(getFormatedButtonName(name));
	}
	
	/**
	 * Enable or disables positive button
	 * @param enable If true enable else disables button.
	 * @see #setPositiveButtonVisibility(boolean)
	 */
	void enablePositiveButton(boolean enable){
		if(this.mPositiveButton!=null)
			this.mPositiveButton.setEnabled(enable);
	}

	/**
	 * Enable or disables neutral button
	 * @param enable If true enable else disables button.
	 * @see #setNeutralButtonVisibility(boolean)
	 */
	void enableNeutralButton(boolean enable){
		if(this.mNeutralButton!=null)
			this.mNeutralButton.setEnabled(enable);
	}

	/**
	 * Enable or disables negative button
	 * @param enable If true enable else disables button.
	 * @see #setNeutralButtonVisibility(boolean)
	 */
	void enableNegativeButton(boolean enable){
		if(this.mNegativeButton!=null)
			this.mNegativeButton.setEnabled(enable);
	}
	
	/**
	 * Sets visibility of the button.
	 * @param visible Sets visible if true else invisible.
	 * @see #enablePositiveButton(boolean)
	 */
	void setPositiveButtonVisibility(boolean visible){
		if(this.mPositiveButton!=null)
			this.mPositiveButton.setVisible(visible);
	}
	
	/**
	 * Sets visibility of the button.
	 * @param visible Sets visible if true else invisible.
	 * @see #enableNeutralButton(boolean)
	 */
	void setNeutralButtonVisibility(boolean visible){
		if(this.mNeutralButton!=null)
			this.mNeutralButton.setVisible(visible);
	}

	/**
	 * Sets visibility of the button.
	 * @param visible Sets visible if true else invisible.
	 * @see #enableNegativeButton(boolean)
	 */
	void setNegativeButtonVisibility(boolean visible){
		if(this.mNegativeButton!=null)
			this.mNegativeButton.setVisible(visible);
	}
	
	
	
}
