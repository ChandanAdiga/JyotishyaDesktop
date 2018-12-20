package org.jyotish.observers;

import org.jyotish.views.ViewType;

/**
 * Interface which provides observer methods for exit actions.
 * @author Chandan
 *
 */
public interface ApplicationExitListener {

	/**
	 * Observer for application exit action.
	 * @param viewType type of view which requested exit action.
	 */
	public void onExitRequest(ViewType viewType);
	
	/**
	 * Observer for showing pop up to confirm exit event.
	 */
	public void onExitConfirmRequest();
	
	/**
	 * Observer for confirmed exit action.
	 */
	public void onExitConfirmed();
	
}
