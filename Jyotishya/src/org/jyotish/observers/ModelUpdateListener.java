package org.jyotish.observers;

import org.jyotish.views.ViewType;

/**
 * Observer class for model update.
 * @author Chandan
 *
 */
public interface ModelUpdateListener {

	/**
	 * Listener method which is implemented by logic controller which updates model
	 *  corresponds to view which has been just modified.
	 * @param changedViewType type of screen which is modified.
	 * @param nextChangeViewType sub view updated or which has to be updated next 
	 * else null
	 * <p>
	 * Note: Don't always rely on second parameter.
	 */
	public void onViewChange(ViewType changedViewType,ViewType nextChangeViewType);
}
