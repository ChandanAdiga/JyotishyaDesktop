package org.jyotish.logic;

import org.jyotish.observers.ViewUpdateListner;

/**
 *  Defines type of logic/model/calculations. 
 *  This is used for @ {@link ViewUpdateListner} to determining which view should be
 *  updated next.
 *  <p>
 *  On observing {@link ViewUpdateListner#onModelChange(ModelLogicType)} chief
 *  controller usually request for view update. How ever this is not mandatory, it
 *  may just change back-end/logic again and keep quite!
 * @author Chandan
 */
public enum ModelLogicType {

	/**
	 * On change of splash logic. i.e Soon after splash time started.
	 */
	SPLASH_TIMER_STARTED,
	
	/**
	 * On change of splash logic. i.e Soon after splash time over. 
	 */
	SPLASH_TIMER_ENDED,
	
	/**
	 * After performing surya udayadi ghati calculations.
	 * @see LogicManager#calculateSuryaUdayadiGhati()
	 */
	SURYA_UDAYADI_GHATI,
	
	/**
	 * After performing tedi calculations.
	 * @see LogicManager#calculateTedi()
	 */
	TEDI,
	
	/**
	 * After performing lagna calculations and user liked to continue subtraction.
	 * @see LogicManager#calculateLagnaTedi()
	 */
	LAGNA_TEDI_CONTINUE_RECURSION,
	
	/**
	 * After performing lagna calculations and user liked to SKIP further subtraction.
	 * @see LogicManager#calculateLagnaTedi()
	 */
	LAGNA_SKIP_RECURSION,
	
	/**
	 * After performing navamsha calculations.
	 * @see LogicManager#calculateNavamsha()
	 */
	NAVAMSHA,
	
	/**
	 * After performing nakshatra calculations.
	 * @see LogicManager#performNakshatraCalculations() 
	 */
	NAKSHATRA,
	
	/**
	 * After performing DashaBhukti calculations.
	 * @see	LogicManager#performDashBhuktiCalculations()
	 */
	DASHABHUKTI
	
}
