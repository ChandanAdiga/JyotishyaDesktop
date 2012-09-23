package org.jyotish.observers;

import org.jyotish.views.ClickedView;
import org.jyotish.views.ViewManager;

/**
 * Interface to transfer User actions to View controller 
 * [Only controller will be {@link ViewManager} ]
 * @author Chandan
 *
 */
public interface ActionListener {

	/**
	 * Observer for negative action,equivalent to back button. 
	 * Will be implemented by {@link ViewManager}.
	 * <p>
	 * NOTE: You may need to refer to current view variable maintained
	 *  by {@link ViewManager}to determine from which 
	 * screen action has occurred.
	 */
	void onNegativeButtonClick();
	
	/**
	 * Observer for neutral action, Will be implemented by {@link ViewManager}.
	 * <p>
	 * NOTE: You may need to refer to current view variable maintained
	 *  by {@link ViewManager}to determine from which 
	 * screen action has occurred.
	 */
	void onNeutralButtonClick();
	
	/**
	 * Observer for positive action,equivalent to next button. 
	 * Will be implemented by {@link ViewManager}.
	 * <p>
	 * NOTE: You may need to refer to current view variable maintained
	 *  by {@link ViewManager}to determine from which 
	 * screen action has occurred.
	 */
	void onPositiveButtonClick();
	
	/**
	 * Observer for refresh action,equivalent to neutral button. Will be implemented by {@link ViewManager}.
	 */
	void refresh(); 
	
	/**
	 *Observer should Set/reset screen for user interaction.
	 * @param enable If true enable, else disable , user interaction on views 
	 */
	void setEnabled(boolean enable);
	
	/**
	 * Triggered whenever user clicks on some view component. View component varies 
	 * from all views which can listen to mouse click action and has implemented the 
	 * same in respective view design.
	 * <p>
	 * NOTE: You may need to refer to current view variable maintained
	 *  by {@link ViewManager}to determine from which 
	 * screen action has occurred.
	 * 
	 * @param clickedView Type of view component which received mouse click event. 
	 * @see ClickedView
	 */
	void onMouseClick(ClickedView clickedView);
	
}
