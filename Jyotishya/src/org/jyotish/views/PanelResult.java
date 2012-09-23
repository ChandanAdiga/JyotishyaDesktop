package org.jyotish.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.controllers.DataTransporter;
import org.jyotish.controllers.DataTransportKeyManager.RESULT_KEY;
import org.jyotish.exceptions.TransporterException;
import org.jyotish.models.ModelConstants;
import org.jyotish.views.ViewConstants.LABEL_NAMES;
import org.jyotish.views.ViewConstants.RESULT_LABEL;

/**
 * Panel containing views related to displaying of result. 
 * @author Chandan
 *
 */
final class PanelResult extends JPanel {
	
	/**
	 * By default,Log type of the project. However you can customize for debugging..
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;

	
	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=PanelResult.class.getSimpleName();

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = -5435383688902402244L;
	
	/**
	 * Standard label width 
	 */
	private static final int LABEL_WIDTH=200;
	
	/**
	 * Standard label height
	 */
	private static final int LABEL_HEIGHT=20;
	
	private static final int EACH_GRID_MAX_HEIGHT=2*LABEL_HEIGHT;
	
	private static final int SCROLL_PANEL_WIDTH=ViewConstants.TAB_COMPONENT_WIDTH-20;
	
	private static final int SCROLL_PANEL_HEIGHT=ViewConstants.TAB_COMPONENT_HEIGHT-40;
	
	private final JScrollPane mScrollPaneContainer=new JScrollPane();
	
	private final JPanel mPanelGridParent = new JPanel();
	
	private final JPanel mPanelAbout=new JPanel();
	private final JLabel mLabelAbout=new JLabel();
	
	private final JPanel mPanelName=new JPanel();
	private final JLabel mLabelName=new JLabel();
	private final JLabel mLabelForVauleOfName=new JLabel();
	
	private final JPanel mPanelBirthDate=new JPanel();
	private final JLabel mLabelBirthDate=new JLabel();
	private final JLabel mLabelForVauleOfBirthDate=new JLabel();

	private final JPanel mPanelBirthTime=new JPanel();
	private final JLabel mLabelBirthTime=new JLabel();
	private final JLabel mLabelForVauleOfBirthTime=new JLabel();

	private final JPanel mPanelSunRiseTime=new JPanel();
	private final JLabel mLabelSunRiseTime=new JLabel();
	private final JLabel mLabelForVauleOfSunRiseTime=new JLabel();

	private final JPanel mPanelSuryaUdayadiGhati=new JPanel();
	private final JLabel mLabelSuryaUdayadiGhati=new JLabel();
	private final JLabel mLabelForVauleOfSuryaUdayadiGhati=new JLabel();

	private final JPanel mPanelDivamana=new JPanel();
	private final JLabel mLabelDivamana=new JLabel();
	private final JLabel mLabelForVauleOfDivamana=new JLabel();

	private final JPanel mPanelBhukti=new JPanel();
	private final JLabel mLabelBhukti=new JLabel();
	private final JLabel mLabelForVauleOfBhukti=new JLabel();

	private final JPanel mPanelLagnaPramana=new JPanel();
	private final JLabel mLabelLagnaPramana=new JLabel();
	private final JLabel mLabelForVauleOfLagnaPramana=new JLabel();

	private final JPanel mPanelTedi=new JPanel();
	private final JLabel mLabelTedi=new JLabel();
	private final JLabel mLabelForVauleOfTedi=new JLabel();

	//[Renamed from lagna to lagnaTedi
	private final JPanel mPanelLagnaTedi=new JPanel();
	private final JLabel mLabelLagnaTedi=new JLabel();
	private final JLabel mLabelForVauleOfLagnaTedi=new JLabel();
	//Renamed]
	
	private final JPanel mPanelNavamsha=new JPanel();
	private final JLabel mLabelNavamsha=new JLabel();
	private final JLabel mLabelForVauleOfNavamsha=new JLabel();

	private final JPanel mPanelPreviousStarPramana=new JPanel();
	private final JLabel mLabelPreviousStarPramana=new JLabel();
	private final JLabel mLabelForVauleOfPreviousStarPramana=new JLabel();

	private final JPanel mPanelBornStarPramana=new JPanel();
	private final JLabel mLabelBornStarPramana=new JLabel();
	private final JLabel mLabelForVauleOfBornStarPramana=new JLabel();

	private final JPanel mPanelParamaGhati=new JPanel();
	private final JLabel mLabelParamaGhati=new JLabel();
	private final JLabel mLabelForVauleOfParamaGhati=new JLabel();

	private final JPanel mPanelGataGhati=new JPanel();
	private final JLabel mLabelGataGhati=new JLabel();
	private final JLabel mLabelForVauleOfGataGhati=new JLabel();

	private final JPanel mPanelEshyaGhati=new JPanel();
	private final JLabel mLabelEshyaGhati=new JLabel();
	private final JLabel mLabelForVauleOfEshyaGhati=new JLabel();

	private final JPanel mPanelNakshatraPada=new JPanel();
	private final JLabel mLabelNakshatraPada=new JLabel();
	private final JLabel mLabelForVauleOfNakshatraPada=new JLabel();

	private final JPanel mPanelDashaVarsha=new JPanel();
	private final JLabel mLabelDashaVarsha=new JLabel();
	private final JLabel mLabelForVauleOfDashaVarsha=new JLabel();

	private final JPanel mPanelEshyaVarsha=new JPanel();
	private final JLabel mLabelEshyaVarsha=new JLabel();
	private final JLabel mLabelForVauleOfEshyaVarsha=new JLabel();
	
	
	/**
	 * Create the panel.
	 */
	PanelResult() {
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating result tab..");
		
		setSize(ViewConstants.RESULT_SCREEN_FRAME_WIDTH, 
				ViewConstants.RESULT_SCREEN_FRAME_HEIGHT);
		
		setLayout(new BorderLayout());
		/*
		setBounds(ViewConstants.RESULT_SCREEN_FRAME_X,
				ViewConstants.RESULT_SCREEN_FRAME_Y,
				ViewConstants.RESULT_SCREEN_FRAME_WIDTH,
				ViewConstants.RESULT_SCREEN_FRAME_HEIGHT);
		*/
		initViews();
		
		SpringLayout mSpringLayoutAbout=new SpringLayout();
		mSpringLayoutAbout.putConstraint(SpringLayout.NORTH, mLabelAbout, 10, SpringLayout.NORTH, mPanelAbout);
		mSpringLayoutAbout.putConstraint(SpringLayout.WEST, mLabelAbout, 10, SpringLayout.WEST, mPanelAbout);
			mLabelAbout.setText(RESULT_LABEL.ABOUT_RESULT);
		mPanelAbout.setLayout(mSpringLayoutAbout);
		mPanelAbout.add(mLabelAbout,BorderLayout.NORTH);
		
		
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(10,LABEL_HEIGHT ,SCROLL_PANEL_WIDTH ,SCROLL_PANEL_HEIGHT );
		
		JPanel panelScrollChild=new JPanel();
		panelScrollChild.setLayout(new BorderLayout(0,0));
		panelScrollChild.add(mPanelGridParent,BorderLayout.NORTH);
		scrollPane.setViewportView(panelScrollChild);
		
		//Set panels to main RESULT window..
		add(mPanelAbout,BorderLayout.NORTH);
		add(scrollPane);
		
	}
	
	/**
	 * 
	 */
	private void initViews(){
		
		mPanelAbout.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));
		
		GridLayout gridLayout=new GridLayout(19,0);//19 panels..
		mPanelGridParent.setLayout(gridLayout);
		
		//TODO : Append a tab for all labels for better UI.
		
		//Now, prepare panels one by one..
		preparePanelName();
		preparePanelBirthDate();
		preparePanelBirthTime();
		preparePanelSunRiseTime();	
		preparePanelSuryaUdayadiGhati();
		preparePanelDivamana();
		preparePanelBhukti();
		preparePanelLagnaPramana();
		preparePanelTedi();
		preparePanelLagnaTedi();
		preparePanelNavamsha();
		preparePanelPreviousStarPramana();
		preparePanelBornStarPramana();
		preparePanelParamaGhati();
		preparePanelGataGhati();
		preparePanelEshyaGhati();
		preparePanelNakshatraPada();
		preparePanelDashaVarsha();
		preparePanelEshyaVarsha();
		
		//Add them to grid parent..
		mPanelName.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelName);//1
		
		mPanelGridParent.add(mPanelBirthDate);//2
		
		mPanelBirthTime.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelBirthTime);//3
		
		mPanelGridParent.add(mPanelSunRiseTime);//4

		mPanelSuryaUdayadiGhati.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelSuryaUdayadiGhati);//5
		
		mPanelGridParent.add(mPanelDivamana);//6

		mPanelBhukti.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelBhukti);//7
		
		mPanelGridParent.add(mPanelLagnaPramana);//8

		mPanelTedi.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelTedi);//9
		
		mPanelGridParent.add(mPanelLagnaTedi);//10

		mPanelNavamsha.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelNavamsha);//11
		
		mPanelGridParent.add(mPanelPreviousStarPramana);//12

		mPanelBornStarPramana.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelBornStarPramana);//13
		
		mPanelGridParent.add(mPanelParamaGhati);//14

		mPanelGataGhati.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelGataGhati);//15
		
		mPanelGridParent.add(mPanelEshyaGhati);//16

		mPanelNakshatraPada.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelNakshatraPada);//17
		
		mPanelGridParent.add(mPanelDashaVarsha);//28

		mPanelEshyaVarsha.setBackground(Color.LIGHT_GRAY);
		mPanelGridParent.add(mPanelEshyaVarsha);//19
		
	}
	
	private void preparePanelName(){
		//mLabelName
		//mLabelForVauleOfName
		//mPanelName
		//RESULT_LABEL.NAME
		BorderLayout bl=new BorderLayout();
			mLabelName.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.NAME);
			mLabelName.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfName.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelName.setLayout(bl);
		mPanelName.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));
		mPanelName.add(mLabelName,BorderLayout.LINE_START);
		mPanelName.add(mLabelForVauleOfName);
	}
	
	private void preparePanelBirthDate(){
		BorderLayout bl=new BorderLayout();
			mLabelBirthDate.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.BIRTH_DATE);
			mLabelBirthDate.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfBirthDate.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelBirthDate.setLayout(bl);
		mPanelBirthDate.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));
		mPanelBirthDate.add(mLabelBirthDate,BorderLayout.LINE_START);
		mPanelBirthDate.add(mLabelForVauleOfBirthDate);
	}
	
	private void preparePanelBirthTime(){
		BorderLayout bl=new BorderLayout();
			mLabelBirthTime.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.BIRTH_TIME);
			mLabelBirthTime.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfBirthTime.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelBirthTime.setLayout(bl);
		mPanelBirthTime.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));
		mPanelBirthTime.add(mLabelBirthTime,BorderLayout.LINE_START);
		mPanelBirthTime.add(mLabelForVauleOfBirthTime);
	}
	
	private void preparePanelSunRiseTime(){
		BorderLayout bl=new BorderLayout();
			mLabelSunRiseTime.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.SUN_RISE_TIME);			
			mLabelSunRiseTime.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfSunRiseTime.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelSunRiseTime.setLayout(bl);
		mPanelSunRiseTime.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelSunRiseTime.add(mLabelSunRiseTime,BorderLayout.LINE_START);
		mPanelSunRiseTime.add(mLabelForVauleOfSunRiseTime);
	}
	
	private void preparePanelSuryaUdayadiGhati(){
		BorderLayout bl=new BorderLayout();
			mLabelSuryaUdayadiGhati.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.SURYA_UDAYADI_GHATI);			
			mLabelSuryaUdayadiGhati.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfSuryaUdayadiGhati.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelSuryaUdayadiGhati.setLayout(bl);
		mPanelSuryaUdayadiGhati.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelSuryaUdayadiGhati.add(mLabelSuryaUdayadiGhati,BorderLayout.LINE_START);
		mPanelSuryaUdayadiGhati.add(mLabelForVauleOfSuryaUdayadiGhati);
	}
	
	private void preparePanelDivamana(){
		BorderLayout bl=new BorderLayout();
			mLabelDivamana.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.DIVAMANA);			
			mLabelDivamana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfDivamana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelDivamana.setLayout(bl);
		mPanelDivamana.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelDivamana.add(mLabelDivamana,BorderLayout.LINE_START);
		mPanelDivamana.add(mLabelForVauleOfDivamana);
	}
		
	private void preparePanelBhukti(){
		BorderLayout bl=new BorderLayout();
			mLabelBhukti.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.BHUKTI);			
			mLabelBhukti.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfBhukti.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelBhukti.setLayout(bl);
		mPanelBhukti.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelBhukti.add(mLabelBhukti,BorderLayout.LINE_START);
		mPanelBhukti.add(mLabelForVauleOfBhukti);
	}
	
	private void preparePanelLagnaPramana(){
		BorderLayout bl=new BorderLayout();
			mLabelLagnaPramana.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.LAGNA_PRAMANA);			
			mLabelLagnaPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfLagnaPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelLagnaPramana.setLayout(bl);
		mPanelLagnaPramana.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelLagnaPramana.add(mLabelLagnaPramana,BorderLayout.LINE_START);
		mPanelLagnaPramana.add(mLabelForVauleOfLagnaPramana);
	}
	
	private void preparePanelTedi(){
		BorderLayout bl=new BorderLayout();
			mLabelTedi.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.TEDI);			
			mLabelTedi.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfTedi.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelTedi.setLayout(bl);
		mPanelTedi.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelTedi.add(mLabelTedi,BorderLayout.LINE_START);
		mPanelTedi.add(mLabelForVauleOfTedi);
	}
	
	private void preparePanelLagnaTedi(){
		BorderLayout bl=new BorderLayout();
			mLabelLagnaTedi.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.LAGNA_TEDI);			
			mLabelLagnaTedi.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfLagnaTedi.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelLagnaTedi.setLayout(bl);
		mPanelLagnaTedi.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelLagnaTedi.add(mLabelLagnaTedi,BorderLayout.LINE_START);
		mPanelLagnaTedi.add(mLabelForVauleOfLagnaTedi);
	}
	
	private void preparePanelNavamsha(){
		BorderLayout bl=new BorderLayout();
			mLabelNavamsha.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.NAVAMSHA);			
			mLabelNavamsha.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfNavamsha.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelNavamsha.setLayout(bl);
		mPanelNavamsha.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelNavamsha.add(mLabelNavamsha,BorderLayout.LINE_START);
		mPanelNavamsha.add(mLabelForVauleOfNavamsha);
	}
	
	private void preparePanelPreviousStarPramana(){
		BorderLayout bl=new BorderLayout();
		mLabelPreviousStarPramana.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.PREVIOUS_STAR_PRAMANA);			
		mLabelPreviousStarPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mLabelForVauleOfPreviousStarPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mPanelPreviousStarPramana.setLayout(bl);
			mPanelPreviousStarPramana.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
			mPanelPreviousStarPramana.add(mLabelPreviousStarPramana,BorderLayout.LINE_START);
			mPanelPreviousStarPramana.add(mLabelForVauleOfPreviousStarPramana);
	}
	
	private void preparePanelBornStarPramana(){
		BorderLayout bl=new BorderLayout();
			mLabelBornStarPramana.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.BORN_STAR_PRAMANA);			
			mLabelBornStarPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfBornStarPramana.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelBornStarPramana.setLayout(bl);
		mPanelBornStarPramana.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelBornStarPramana.add(mLabelBornStarPramana,BorderLayout.LINE_START);
		mPanelBornStarPramana.add(mLabelForVauleOfBornStarPramana);
	}
	
	private void preparePanelParamaGhati(){
		BorderLayout bl=new BorderLayout();
			mLabelParamaGhati.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.PARAMA_GHATI);			
			mLabelParamaGhati.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfParamaGhati.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelParamaGhati.setLayout(bl);
		mPanelParamaGhati.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelParamaGhati.add(mLabelParamaGhati,BorderLayout.LINE_START);
		mPanelParamaGhati.add(mLabelForVauleOfParamaGhati);
	}
	
	private void preparePanelGataGhati(){
		BorderLayout bl=new BorderLayout();
			mLabelGataGhati.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.GATA_GHATI);			
			mLabelGataGhati.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfGataGhati.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelGataGhati.setLayout(bl);
		mPanelGataGhati.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelGataGhati.add(mLabelGataGhati,BorderLayout.LINE_START);
		mPanelGataGhati.add(mLabelForVauleOfGataGhati);
	}
	
	private void preparePanelEshyaGhati(){
		BorderLayout bl=new BorderLayout();
			mLabelEshyaGhati.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.ESHYA_GHATI);			
			mLabelEshyaGhati.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfEshyaGhati.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelEshyaGhati.setLayout(bl);
		mPanelEshyaGhati.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelEshyaGhati.add(mLabelEshyaGhati,BorderLayout.LINE_START);
		mPanelEshyaGhati.add(mLabelForVauleOfEshyaGhati);
	}
	
	private void preparePanelNakshatraPada(){
		BorderLayout bl=new BorderLayout();
			mLabelNakshatraPada.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.NAKSHATRA_PADA);			
			mLabelNakshatraPada.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfNakshatraPada.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelNakshatraPada.setLayout(bl);
		mPanelNakshatraPada.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelNakshatraPada.add(mLabelNakshatraPada,BorderLayout.LINE_START);
		mPanelNakshatraPada.add(mLabelForVauleOfNakshatraPada);
	}
	
	private void preparePanelDashaVarsha(){		
		BorderLayout bl=new BorderLayout();
			mLabelDashaVarsha.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.DASHA_VARSHA);			
			mLabelDashaVarsha.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfDashaVarsha.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelDashaVarsha.setLayout(bl);
		mPanelDashaVarsha.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelDashaVarsha.add(mLabelDashaVarsha,BorderLayout.LINE_START);
		mPanelDashaVarsha.add(mLabelForVauleOfDashaVarsha);
	}
	
	private void preparePanelEshyaVarsha(){
		BorderLayout bl=new BorderLayout();
			mLabelEshyaVarsha.setText(LABEL_NAMES.PADDING_FOR_ERROR_MESSAGE+RESULT_LABEL.ESHYA_VARSHA);			
			mLabelEshyaVarsha.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
			mLabelForVauleOfEshyaVarsha.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
		mPanelEshyaVarsha.setLayout(bl);
		mPanelEshyaVarsha.setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH,EACH_GRID_MAX_HEIGHT));		
		mPanelEshyaVarsha.add(mLabelEshyaVarsha,BorderLayout.LINE_START);
		mPanelEshyaVarsha.add(mLabelForVauleOfEshyaVarsha);
	}
	
	
	/**
	 * Loads result on to the {@link DataTransporter} for updating
	 * Result view.
	 */
	public void loadUpdatedResult(){
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "In result panel class,loadUpdatedResult()");
		final String YET_TO_COMPUTE="YET TO COMPUTE";
		DataTransporter dataTransporter=DataTransporter.getInstance();			
		try {
			String resultValue;
			//Name..
			resultValue=dataTransporter.
				getString(RESULT_KEY.NAME, YET_TO_COMPUTE);
			mLabelForVauleOfName.setText(resultValue);
			
			//Birth date..
			resultValue=dataTransporter.
				getString(RESULT_KEY.BIRTH_DATE, YET_TO_COMPUTE);
			mLabelForVauleOfBirthDate.setText(resultValue);
			
			//Birth time..
			resultValue=dataTransporter.
				getString(RESULT_KEY.BIRTH_TIME, YET_TO_COMPUTE);
			mLabelForVauleOfBirthTime.setText(resultValue);
			
			//Sun rise time..
			resultValue=dataTransporter.
				getString(RESULT_KEY.SUN_RISE_TIME, YET_TO_COMPUTE);
			mLabelForVauleOfSunRiseTime.setText(resultValue);
			
			//Surya udayadi ghati..
			resultValue=dataTransporter.
				getString(RESULT_KEY.SURYA_UDAYADI_GHATI, YET_TO_COMPUTE);
			mLabelForVauleOfSuryaUdayadiGhati.setText(resultValue);
			
			//Divamana..
			resultValue=dataTransporter.
				getString(RESULT_KEY.DIVAMANA, YET_TO_COMPUTE);
			mLabelForVauleOfDivamana.setText(resultValue);
			
			//Bhukti
			resultValue=dataTransporter.
				getString(RESULT_KEY.BHUKTI, YET_TO_COMPUTE);
			mLabelForVauleOfBhukti.setText(resultValue);
			
			//Lagna pramana
			resultValue=dataTransporter.
				getString(RESULT_KEY.LAGNA_PRAMANA, YET_TO_COMPUTE);
			mLabelForVauleOfLagnaPramana.setText(resultValue);
			
			//Tedi
			resultValue=dataTransporter.
				getString(RESULT_KEY.TEDI, YET_TO_COMPUTE);
			mLabelForVauleOfTedi.setText(resultValue);
			
			//Lagna
			resultValue=dataTransporter.
				getString(RESULT_KEY.LAGNA_TEDI, YET_TO_COMPUTE);
			mLabelForVauleOfLagnaTedi.setText(resultValue);
			
			//Navamsha..
			resultValue=dataTransporter.
				getString(RESULT_KEY.NAVAMSHA, YET_TO_COMPUTE);
			mLabelForVauleOfNavamsha.setText(resultValue);
			
			//Previous star pramana..
			resultValue=dataTransporter.
				getString(RESULT_KEY.PREV_STAR_PRAMANA, YET_TO_COMPUTE);
			mLabelForVauleOfPreviousStarPramana.setText(resultValue);
			
			//Born star pramana..
			resultValue=dataTransporter.
				getString(RESULT_KEY.BORN_STAR_PRAMANA, YET_TO_COMPUTE);
			mLabelForVauleOfBornStarPramana.setText(resultValue);
			
			//Parama ghati..
			resultValue=dataTransporter.
				getString(RESULT_KEY.PARAMA_GHATI, YET_TO_COMPUTE);
			mLabelForVauleOfParamaGhati.setText(resultValue);
			
			//Gata ghati..
			resultValue=dataTransporter.
				getString(RESULT_KEY.GATA_GHATI, YET_TO_COMPUTE);
			mLabelForVauleOfGataGhati.setText(resultValue);
			
			//Eshya ghati..
			resultValue=dataTransporter.
				getString(RESULT_KEY.ESHYA_GHATI, YET_TO_COMPUTE);
			mLabelForVauleOfEshyaGhati.setText(resultValue);
			
			
			//Nakshatra pada...
			resultValue=dataTransporter.
				getString(RESULT_KEY.NAKSHATRA_PADA, YET_TO_COMPUTE);
			mLabelForVauleOfNakshatraPada.setText(resultValue);
			
			//Dasha varsha pada...
			resultValue=dataTransporter.
				getString(RESULT_KEY.DASHA_VARSHA, YET_TO_COMPUTE);
			mLabelForVauleOfDashaVarsha.setText(resultValue);
			
			//Eshya varsha/year..
			resultValue=dataTransporter.
				getString(RESULT_KEY.ESHYA_VARSHA, YET_TO_COMPUTE);
			mLabelForVauleOfEshyaVarsha.setText(resultValue);
						
			
		} catch (TransporterException e) {
			LogManager.processException(MY_LOG_TYPE, TAG, e);
		}
			
	}
	
}
