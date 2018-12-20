package org.chandan.java.views;

import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.chandan.utils.StringUtils;

/**
 * Customized {@link JTextField} which reads only alphabetic characters.
 * @author chandan
 */
public final class MyAlphabetTextField extends JTextField {

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = 669221481360523539L;

	
	@Override
	protected Document createDefaultModel(){
		
			return new AlphabeticDocument();			
	}
	
	/**
	 * Document for alphabetic characters recognition.
	 * @author chandan
	 *
	 */
	private static class AlphabeticDocument extends PlainDocument{
		
		/**
		 * Generated serial version Id..
		 */
		private static final long serialVersionUID = -2285429122719677322L;
		/**
		 * Pattern for compiling alphabets.
		 */
		private static final Pattern ALPHABETS=Pattern.compile("[A-Z,a-z]");
		// Note '\\w' can not server our purpose as '\w' reads digits as well!
		
		/**
		 * Pattern for compiling space
		 */
		private static final Pattern SPACES=Pattern.compile("\\s");
		
		@Override
		public void insertString(int offset, String string, AttributeSet attributes)
			throws BadLocationException{
			//Main condition:
			//Input should not be empty[not null & length>0]
			//AND input should be only alphabets.			
			if(!StringUtils.isEmpty(string)){
					if(ALPHABETS.matcher(string).matches()){				
						super.insertString(offset,string, attributes);
					}
					else if (getLength()>2){
						
						//System.out.println("OFFSET:"+offset);
						//System.out.println("getText():"+getText(offset-1,1)+";");
						
						//First more than 3 alphabets should be present
						if(SPACES.matcher(string).matches()//entered space 
							&& !getText(offset-1, 1).contains(" ")
							//AND text field's text don't have ' ' as PREVIOUS character
							//PREVIOUS because user may insert even  in middle/end of already
							//entered text!! 
							)
						{
							super.insertString(offset,string, attributes);
						}
						
					}
			}
		}
	}
	
}
