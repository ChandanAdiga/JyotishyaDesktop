package org.jyotish.views;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.models.ModelConstants;
import org.jyotish.views.ViewConstants.FILE_NAME;
import org.jyotish.views.ViewConstants.FOLDER_NAME;

/**
 * Panel containing views related to displaying of views. 
 * @author Chandan
 *
 */
final class PanelSplash extends JPanel {
	
	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=PanelSplash.class.getSimpleName();

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = -5435383688902402253L;
	
	/**
	 * Image object which holds image data to be set as background..
	 */
	private static Image mImageBackground=null;
	
	/**
	 * Create the panel.
	 */
	PanelSplash() {
		setSize(ViewConstants.WINDOW_SCREEN_FRAME_WIDTH, 
				ViewConstants.WINDOW_SCREEN_FRAME_HEIGHT);
		
		/*
		setBounds(ViewConstants.RESULT_SCREEN_FRAME_X,
				ViewConstants.RESULT_SCREEN_FRAME_Y,
				ViewConstants.RESULT_SCREEN_FRAME_WIDTH,
				ViewConstants.RESULT_SCREEN_FRAME_HEIGHT);
		*/
				
		createBackgroundImage();
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Prepared splash screen..");
	}
	
	@Override
	public void paintComponent(Graphics graphics){
		/*For SWING */
		
		//Hope width and height will fit fine
		//If not, have to play with ancestor component or root component's attributes..
		graphics.drawImage(mImageBackground, 0,0,
				/*width*/getParent().getWidth()
				,/*height*/getParent().getHeight(),null);
	}
	
	/* OR, For AWT
 	@Override
	public void paint(Graphics graphics){
		graphics.drawImage(mImageBackground, 0,0,
				getParent().getWidth()
				,getParent().getHeight(),null);
	}
	*/
	
	/**
	 * 
	 */
	private void createBackgroundImage(){
		
		/* Currently not working...
		 * mImageBackground=Toolkit.getDefaultToolkit()
		 	.createImage(
				ViewConstants.FOLDER_NAME_RESOURCES + ViewConstants.PATH_SEPERATOR
				+ViewConstants.FOLDER_NAME_IMAGES + ViewConstants.PATH_SEPERATOR
				+ViewConstants.IMAGE_NAME_SPLASH);
				*/
		
		//Do not forget to add below parent folder to build path.
		try 
		{
			//[DEBUGGED : when image is placed outside "/src" folder below stuff works fine.
			//But, when exported as jar, its difficult export resources as well..
			//[Due to ma knowledge limitation :P]..
			//mImageBackground=ImageIO
			//	.read(new File(
			//			FOLDER_NAME.RESOURCES +
			//			ViewConstants.PATH_SEPERATOR+						
			//			FOLDER_NAME.IMAGES +
			//			ViewConstants.PATH_SEPERATOR+
			//			FILE_NAME.SPLASH));
			
			mImageBackground=ImageIO
				.read(this.getClass().getResource(
					ViewConstants.PATH_SEPERATOR+
					FOLDER_NAME.RESOURCES +
					ViewConstants.PATH_SEPERATOR+						
					FOLDER_NAME.IMAGES +
					ViewConstants.PATH_SEPERATOR+
					FILE_NAME.SPLASH));
			//DEBUGGED]
		} catch (Exception e) {
			LogManager.processException(MY_LOG_TYPE, TAG, e);
		}
		
	}

}
