package org.jyotish.views;

import org.jyotish.observers.ModelUpdateListener;

/**
 * Defines type of view/panels/frames/tabs. Generally view type will be 
 * group of view/cluster of components  that makes up a window/block.
 * This is used for @ {@link ModelUpdateListener} to determining which 
 * logic module should be updated next.
 * <p>
 * On observing {@link ModelUpdateListener#onViewChange(ViewType, ViewType)} chief
 *  controller usually request for model/logic update. How ever this is not mandatory, it
 *  may just change view/UI again and keep quite!
 * @author Chandan
 *
 */
public enum ViewType {

	/**
	 * View type representing splash screen.
	 */
	SPLASH_SCREEN,
	
	/**
	 * View type representing menu screen.
	 */
	MENU_SCREEN,
	
	/**
	 * View type representing calculations & result tab screen.
	 */
	TAB_SCREEN_CALCULATION_RESULT,
	
	/**
	 * View type representing calculations & result tab screen.
	 */
	TAB_CALCULATION_DEFAULT_INIT,
	
	/**
	 * View type representing calculation's first panel for surya udayadi ghati calculations.
	 * i.e panel which reads birth time details.
	 * @see PanelCalculationTabBirthTimeReader
	 */
	CALCULATIONS_TAB_BIRTH_TIME_INPUT_PANEL,
	
	/**
	 * View type representing calculation's second panel for tedi calculations.
	 * i.e panel which reads divamana,bhukti and lagna pramana
	 * @see PanelCalculationTabTediInputs
	 */
	CALCULATIONS_TAB_DIVAMANA_BHUKTI_LAGNA_INPUT_PANEL,
	
	/**
	 * View type representing calculation's third panel for lagna calculations.
	 * i.e panel which reads Lagna and performs recursive calculations
	 * to find out remaining surya udayadi ghati which is LAGNA.
	 * @see PanelCalculationTabLagnaInputs
	 */
	CALCULATIONS_TAB_LAGNA_RECURSIVE_INPUT_PANEL,
	
	/**
	 * View type representing calculation's fourth panel for navamsha calculations.
	 * i.e panel which reads Lagna Pramana and performs navamsha calculations.
	 * @see PanelCalculationTabNavamshaInputs
	 */
	CALCULATIONS_TAB_NAVAMSHA_INPUT_PANEL,
	
	/**
	 * View type representing calculation's fifth panel for nakshatra and 
	 * dasha bhukti calculations.
	 * i.e panel which reads previous star pramana & born star pramana
	 *  and performs nakshatra calculations.
	 * @see PanelCalculationTabNakshatraInputs
	 */
	CALCULATIONS_TAB_NAKSHATRA_DASHABHUKTI_INPUT_PANEL
	
}
