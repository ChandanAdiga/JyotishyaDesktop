package org.jyotish.views;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.jyotish.observers.ApplicationExitListener;

/**
 * Custom listener implementation of {@link WindowListener}
 * @author Chandan
 *
 */
class WindowActionListener implements WindowListener {

	/**
	 * Exit events listener
	 */
	private ApplicationExitListener mExitListener;

	/**
	 * Constructor with exit listener.
	 * @param listener Observer for exit events.
	 */
	public WindowActionListener(ApplicationExitListener listener){
		mExitListener=listener;
	}
	
	@Override
	public void windowActivated(WindowEvent event) {
		// TODO windowActivated
		
	}

	@Override
	public void windowClosed(WindowEvent event) {
		// TODO windowClosed
		
	}

	@Override
	public void windowClosing(WindowEvent event) {
		// TODO windowClosing
		//who knows which view type is currently shown..
		//which will be taken care by ViewManager..
		mExitListener.onExitRequest(null);
	}

	@Override
	public void windowDeactivated(WindowEvent event) {
		// TODO windowDeactivated

	}

	@Override
	public void windowDeiconified(WindowEvent event) {
		// TODO windowDeiconified

	}

	@Override
	public void windowIconified(WindowEvent event) {
		// TODO windowIconified

	}

	@Override
	public void windowOpened(WindowEvent event) {
		// TODO windowOpened

	}

}
