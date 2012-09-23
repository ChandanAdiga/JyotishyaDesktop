package org.jyotish.views;

/**
 * Dialog interface which declares dialog basic behaviors.
 * <p>
 * NOTE: At any stage of application, only one dialog can be active.
 * @author chandan
 *
 */
interface Dialog {

	/**
	 * Application specific dialog properties are declared by this class.Implement 
	 * this class to define dialog.
	 * @author chandan
	 *
	 */
	interface ApplicatinDialog {
		
		/**
		 * Prepares dialog with corresponding attributes.
		 * @param title Title for the dialog.
		 * @param message Message for the dialog.
		 * @param positiveButtonName Positive Button name.
		 * @param negativeButtonName Negative button name.
		 * @param positiveListener Observer for positive button click.
		 * @param negativeListener Observer for negative button click.
		 * @param cancelListener Observer for dialog cancel.
		 * @return Reference of the dialog.
		 */
		ApplicatinDialog prepareDialog(final String title,final String message,
				final String positiveButtonName,final String negativeButtonName,
				final DialogButtonClickListener positiveListener,
				final DialogButtonClickListener negativeListener,
				final OnCancelListener cancelListener);
		
		/**
		 * Indicates whether dialog is visible or not.
		 * <p>
		 * NOTE: Implementing class must maintain a flag in order to make 
		 * this method valid one.
		 * @return true if visible else false.
		 */
		boolean isDialogVisible();
	}
	
	/**
	 * Interface for click on dialog button of the dialog.
	 * @author chandan
	 */
	public interface DialogButtonClickListener{
		
		/**
		 * Observer for click on positive button of the dialog.
		 */
		void onClick();
	}
	
	
	/**
	 *  Interface for hide/cancel of the dialog.
	 * @author chandan
	 */
	public interface OnCancelListener{
		/**
		 * Observer for dialog dismiss event.
		 */
		void onCancelDialog();
	}
}
