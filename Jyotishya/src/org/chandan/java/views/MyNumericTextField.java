package org.chandan.java.views;

import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Customized {@link JTextField} which reads only numeric characters.
 * @author chandan
 */
public final class MyNumericTextField extends JTextField {

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = 669221481360523539L;

	
	@Override
	protected Document createDefaultModel(){
		//if(mFilter.equals(FILTER.DIGITS_ONLY)){
			return new NumericDocument();
		//}
		//else 
		//	return new NumericDocument();
			
	}
	
	/**
	 * Document for numeric characters recognition.
	 * @author chandan
	 *
	 */
	private static class NumericDocument extends PlainDocument{
		
		/**
		 * Generated serial version Id..
		 */
		private static final long serialVersionUID = -2285429122719677322L;
		/**
		 * Pattern for compiling digits.
		 */
		private static final Pattern DIGITS=Pattern.compile("\\d*");
		
		@Override
		public void insertString(int offset, String string, AttributeSet attributes)
			throws BadLocationException{
			if(string!=null && DIGITS.matcher(string).matches())
				super.insertString(offset,string, attributes);
		}
	}
	
}
