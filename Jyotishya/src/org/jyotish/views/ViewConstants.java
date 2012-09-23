package org.jyotish.views;

/**
 * Final class which contains all the constants associated with view of the project.
 * 
 * @author Chandan
 *
 */
final class ViewConstants {

	/* Attributes of entire application window. */
	
	public static final String ABOUT_APP_NAME="Jyotishya";
	
	public static final double ABOUT_APP_VERSION=1.0;
	
	public static final String ABOUT_APP_BUILD_DATE="9th April 2012";
	
	public static final String ABOUT_APP_CLIENT=
		"\n\t Manjunath Adiga"
		+"\n\t Saradamane"
		+"\n\t Karagadi, Haniya[P]"
		+"\n\t Hosanagara[Tq]";
	
	public static final String ABOUT_APP_DEVELOPER=
		"\n\t Chandan Adiga"
		+"\n\t Saradamane"
		+"\n\t Karagadi, Haniya[P]"
		+"\n\t Hosanagara[Tq]"
		+"\n\t Mobile : +91 9611875556"
		+"\n\t eMail  : adigachandan@gmail.com";
	
	/**
	 * Screen's initial x-coordinate positioning 
	 */
	public static final short WINDOW_SCREEN_FRAME_X=100;
	
	/**
	 * Screen's initial y-coordinate positioning
	 */
	public static final short WINDOW_SCREEN_FRAME_Y=100;
	
	/**
	 * Screen's width
	 */
	public static final short WINDOW_SCREEN_FRAME_WIDTH=800;
	
	/**
	 * Screen's height
	 */
	public static final short WINDOW_SCREEN_FRAME_HEIGHT=500;
	
	/**
	 * Horizontal gap
	 */
	public static final short WINDOW_CONTENTPANE_BORDER_HORIZONTAL_GAP=0;
	
	/**
	 * Vertical gap
	 */
	public static final short WINDOW_CONTENTPANE_BORDER_VERTICAL_GAP=0;
	
	/* Attributes of Calculation views */
	
	/**
	 * Maximum button string length permitted.
	 */
	public static final short MAXIMUM_BUTTON_LENGTH=15;
	
	/**
	 * Screen's initial x-coordinate positioning 
	 */
//	public static final short CALCULATION_SCREEN_FRAME_X=100;
	
	/**
	 * Screen's initial y-coordinate positioning
	 */
	public static final short CALCULATION_SCREEN_FRAME_Y=100;
	
	/**
	 * Screen's width
	 */
	public static final short CALCULATION_SCREEN_FRAME_WIDTH=WINDOW_SCREEN_FRAME_WIDTH;
	
	/**
	 * Screen's height
	 */
	public static final short CALCULATION_SCREEN_FRAME_HEIGHT=WINDOW_SCREEN_FRAME_HEIGHT;
	
	/**
	 * Horizontal gap
	 */
	public static final short CALCULATION_CONTENTPANE_BORDER_HORIZONTAL_GAP=0;
	
	/**
	 * Vertical gap
	 */
	public static final short CALCULATION_CONTENTPANE_BORDER_VERTICAL_GAP=0;
	
	
	/* Attributes of Result views */
	
	
	/**
	 * Screen's width
	 */
	public static final short RESULT_SCREEN_FRAME_WIDTH=WINDOW_SCREEN_FRAME_WIDTH;
	
	/**
	 * Screen's height
	 */
	public static final short RESULT_SCREEN_FRAME_HEIGHT=WINDOW_SCREEN_FRAME_HEIGHT;
	
	/**
	 * Tab Screen's width
	 */
	public static final short TAB_COMPONENT_WIDTH=WINDOW_SCREEN_FRAME_WIDTH-40;
	
	/**
	 * Tab Screen's height
	 */
	public static final short TAB_COMPONENT_HEIGHT=WINDOW_SCREEN_FRAME_HEIGHT-40;
	
	/**
	 * Screen's width
	 */
	public static final short CONTROL_BUTTON_PANEL_WIDTH=TAB_COMPONENT_WIDTH-40;
	
	/**
	 * Screen's height
	 */
	public static final short CONTROL_BUTTON_PANEL_HEIGHT=40;
	
	
	/**
	 * Horizontal gap
	 */
	public static final short RESULT_CONTENTPANE_BORDER_HORIZONTAL_GAP=0;
	
	/**
	 * Vertical gap
	 */
	public static final short RESULT_CONTENTPANE_BORDER_VERTICAL_GAP=0;
	
	
	/* *************************************************************************** */
	
	/**
	 * Enum for indexing tabs of calculation screen.
	 * @author chandan
	 *
	 */
	static enum TAB_INDEX{
		/**
		 * Index for calculation tab.
		 */
		TAB_CALCULATION,
		
		/**
		 * Index for result tab.
		 */
		TAB_RESULT,
		
		/**
		 * Index for main menu tab.
		 */
		TAB_MAIN_MENU
	}
	
	/**
	 * Defines all tab's name
	 * @author chandan
	 *
	 */
	public static final class TAB_NAME{
		/**
		 * Main Menu
		 */
		public static final String MAIN_MENU="Main Menu";
		
		/**
		 * Result
		 */
		public static final String RESULT="Result";
		
		/**
		 * Calculations
		 */
		public static final String CALCULATIONS="Calculations";
	}
	
	
	/**
	 * Path separator '/'
	 */
	public static final String PATH_SEPERATOR="/";
	
	/**
	 * Defines all folder names used.
	 * @author chandan
	 *
	 */
	public static final class FOLDER_NAME{
		/**
		 * RESOURCES
		 */
		public static final String RESOURCES="RESOURCES";
		
		/**
		 * IMAGES
		 */
		public static final String IMAGES="IMAGES";
	}
	
	
	/**
	 * Defines all files name used.
	 * @author chandan
	 *
	 */
	public static final class FILE_NAME{
		/**
		 * Splash.jpg
		 */
		public static final String SPLASH="Splash.jpg";
	}

	
	/********************************** BUTTON & LABELS ****************************/
	
	final static class BUTTON_NAMES{
		/**
		 * Empty string,but not null!
		 */
		public static final String EMPTY="";
		
		/**
		 * Ok
		 */
		public static final String OK="Ok";
		
		/**
		 * Yes
		 */
		public static final String YES="Yes";
		
		/**
		 * No
		 */
		public static final String NO="No";
		
		/**
		 * Back
		 */
		public static final String BACK="Back";
		
		/**
		 * Reset
		 */
		public static final String RESET="Reset";
		
		/**
		 * Next
		 */
		public static final String NEXT="Next";
		

		/**
		 * Add
		 */
		public static final String OPERATION="Operation";
		
		/**
		 * Add
		 */
		public static final String ADD="Add";
		
		/**
		 * Subtract
		 */
		public static final String SUBTRACT="Subtract";
		
		/**
		 * Cancel
		 */
		public static final String CANCEL="Cancel";
		
		/**
		 * Quit
		 */
		public static final String QUIT="Quit";
		
		/**
		 * Skip
		 */
		public static final String SKIP="Skip";
		
		/**
		 * Perform calculations
		 */
		public static final String PERFORM_CALCULATIONS=
			"Perform calculations";
	}
	
	/**
	 * Defines all labels name.
	 * @author chandan
	 *
	 */
	public static final class LABEL_NAMES{
		
		
		/**
		 * Padding for error message.
		 * <p>
		 * NOTE: 3 spaces
		 */
		public static final String PADDING_FOR_ERROR_MESSAGE = "   ";
		
		/**
		 * Single tabs
		 */
		public static final String TAB_SINGLE = "\t";
		
		/**
		 * Double tabs
		 */
		public static final String TAB_DOUBLE = TAB_SINGLE+TAB_SINGLE;
		
		/**
		 * Single line feed.
		 */
		public static final String LINE_FEED_SINGLE = "\n";
		
		/**
		 * Double line feed.
		 */
		public static final String LINE_FEED_DOUBLE = 
			LINE_FEED_SINGLE+LINE_FEED_SINGLE;
		
		/**
		 * Warning!
		 */
		public static final String WARNING = "Warning!";
		
		/**
		 * Message when user clicked on "Main menu" tab from
		 * calculation-result tab screen.
		 */
		public static final String EXIT_CALCULATION_MESSAGE
			="You may lose all calculations done so far!"
			+LINE_FEED_SINGLE
			+"Are you sure to quit ?";
	
		
		/**
		 * Warning!
		 */
		public static final String EXIT_MESSAGE = 
			"Are you sure to close the application ?";

		/**
		 * Invalid input
		 */
		public static final String INVALID_INPUT = "Invalid input";
		
	/* ++++++++++++++++++++++++++ SURYA UDAYADI GHATI / BIRTH DATE,TIME SUN RISE TIME ++++++++++++++++++++++++*/	
		/**
		 * Defines description for panel which reads input of client name,
		 *  birth date,time and sunrise time.
		 */
		public static final String ABOUT_PANEL_BIRTH_DAY_INPUT = 
			"Submit client's name, his/her birth date, birth time" +
			" and sun rise time of the day in which he born.";
		
		/**
		 * Enter client's Name.
		 */
		public static final String ENTER_CLIENT_NAME =
			"Enter client's Name";

		/**
		 * Pick client's birth date
		 */
		public static final String PICK_CLIENT_BIRTH_DATE = 
			"Pick client's birth date";
		
		/**
		 * Pick client's birth time
		 */
		public static final String PICK_CLIENT_BIRTH_TIME = 
			"Pick client's birth time";
		
		/**
		 * Pick Sun rise time
		 */
		public static final String PICK_SUN_RISE_TIME = "Pick sun rise time";
		
	/* +++++++++++++++++++++++++ TEDI / DIVAMANA, BHUKTI & LAGNA PRAMANA +++++++++++++++++++++++*/	
		/**
		 * Defines description for panel which reads input of Divamana,
		 * Bhukti and Lagna Pramana.
		 */
		public static final String ABOUT_PANEL_TEDI_INPUT = 
			"Submit Divamana, Bhukti and Lagna Pramana, " +
			"which are necessary to calculate Tedi.";
		
		/**
		 * Enter Divamana.
		 */
		public static final String ENTER_DIVAMANA = "Enter Divamana";

		/**
		 * Enter Bhukti.
		 */
		public static final String ENTER_BHUKTI = "Enter Bhukti";
		
		/**
		 * Pick Lagna Pramana
		 */
		public static final String PICK_LAGNA_PRAMANA = "Pick Lagna Pramana";
		
	/* ++++++++++++++++++++++++++++  LAGNA  ++++++++++++++++++++++++++++++++++++*/
	
		/**
		 * Defines description for panel which reads input Lagna Pramana for LAGNA calculations.
		 */
		public static final String ABOUT_PANEL_LAGNA_INPUT =
			"Submit Lagna Pramana, which will be subtracted against remaining " +
			"surya udayadi ghati recursively.\n  To continue subtracting click on 'Subtract'" +
			"and to stop subtracting & proceed for futher calculations click 'Skip'";
		
		/**
		 * Remaining Surya Udayadi Ghati.
		 */
		public static final String REMAINING_SURYA_UDAYADI_GHATI = 
			"Remaining Surya Udayadi Ghati";

		/**
		 * Are you sure to skip further subtracting ?
		 */
		public static final String WARNING_FOR_SKIP_IN_LAGNA_PANEL = 
			"Are you sure to skip further subtracting?";

		
		/* ++++++++++++++++++++++++++++  NAVAMSHA  ++++++++++++++++++++++++++++++++++++*/
	
		/**
		 * Defines description for panel which reads input Lagna Pramana for LAGNA calculations.
		 */
		public static final String ABOUT_PANEL_NAVAMSHA =
			"Submit Lagna Pramana, which will be used for Navamsha calculations.";
		
		/* ++++++++++++++++++++++++++++  NAKSHATRA  ++++++++++++++++++++++++++++++++++++*/

		/**
		 * Defines description for panel which reads input for NAKSHATRA calculations.
		 */
		public static final String ABOUT_PANEL_NAKSHATRA =
			"Submit Previous star Pramana & Born star pramana which will be " +
			"used for Nakshatra calculations.";
		
		
		/**
		 * Pick previous star pramana
		 */
		public static final String PICK_PREVIOUS_STAR_PRAMANA = "Pick previous star pramana";

		/**
		 * Pick born star pramana
		 */
		public static final String PICK_BORN_STAR_PRAMANA = "Pick born star pramana";

		/**
		 * Pick add or subtract ?
		 */
		public static final String PICK_ADD_OR_SUBTRACT = "Pick add or subtract ?";

		/**
		 * Surya udayadi ghati should be added to previous star difference 
		 * OR 
		 * subtract surya udayadi ghati against previous star pramana?";
		 */
		public static final String NAKSHATRA_ADD_OR_SUB_DESCRIPTION = 
			"Surya udayadi ghati should be added to previous star difference " +
			"OR subtract surya udayadi ghati against previous star pramana?";

		/**
		 * Defines description of dasha bhukti calculations. Currently input views
		 * for this calculations are implemented in Nakshatra input panel itself!
		 */
		public static final String DASHABHUKTI_DESCRIPTION = "Dasha bhukti, eshya varsha related calculations will be performed using below inputs.";

		/**
		 * Enter dasha varsha
		 */
		public static final String ENTER_DASHA_VARSHA = "Enter dasha varsha";

		
		
		
		/* ++++++++++++++++++++++++++++    ++++++++++++++++++++++++++++++++++++*/
		
		
	}
	
	public static final class RESULT_LABEL{
		/**
		 * About result.
		 */
		public static final String ABOUT_RESULT = 
			"All calculated results will be udpated here as soon as they are processed.";
		
		public static final String NAME = "Client's Name" ;
		
		public static final String BIRTH_DATE = "Client's Birth date" ;
		
		public static final String BIRTH_TIME = "Client's Birth time" ;
		
		public static final String SUN_RISE_TIME = "Sun rise time" ;
		
		public static final String SURYA_UDAYADI_GHATI = "Surya udayadi ghati" ;
		
		public static final String DIVAMANA = "Divamana" ;
		
		public static final String BHUKTI = "Bhukti" ;
		
		public static final String LAGNA_PRAMANA = "Lagna Pramana" ;
		
		public static final String TEDI = "Tedi" ;
		
		public static final String LAGNA_TEDI = "Lagna tedi" ;
		
		public static final String NAVAMSHA = "Navamsha" ;
		
		public static final String PREVIOUS_STAR_PRAMANA = "Previous star pramana" ;
		
		public static final String BORN_STAR_PRAMANA = "Born star pramana" ;
		
		public static final String PARAMA_GHATI = "Parama ghati" ;
		
		public static final String GATA_GHATI = "Gata ghati" ;
		
		public static final String ESHYA_GHATI = "Eshya ghati" ;
		
		public static final String NAKSHATRA_PADA = "Nakshatra pada" ;
		
		public static final String DASHA_VARSHA = "Dasha varsha" ;
		
		public static final String ESHYA_VARSHA = "Eshya varsha" ;
		
		
		
	}
	
	
	
	
}
