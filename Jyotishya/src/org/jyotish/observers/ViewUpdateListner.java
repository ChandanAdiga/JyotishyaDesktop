package org.jyotish.observers;

import org.jyotish.logic.ModelLogicType;

/**
 * Observer class to update view.
 * @author Chandan
 *
 */
public interface ViewUpdateListner {

	/**
	 * Listener method which is implemented by central controller which updates 
	 * view accordingly.
	 * @param changedLogicType Type of logic module changed.
	 * <p>
	 * Note: Don't always rely on second parameter.
	 */
	public void onModelChange(ModelLogicType changedLogicType);
	
	
}
