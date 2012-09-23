package org.jyotish.controllers;

import org.jyotish.models.CustomDate;
import org.jyotish.models.EnglishTime;
import org.jyotish.models.JyotishyaTime;

/**
 * Defines & categorizes classes which internally defines keys. 
 * @see Key
 * @author chandan
 */
public final class DataTransportKeyManager {

	/**
	 * Defines all keys for smooth data transportation 
	 * between view and logic layers, via {@link DataTransporter}
	 * @author chandan
	 *
	 */
	public static final class Key {
		
		/* +++++++++++++++++++ SURYA UDAYADIGHATI ++++++++++++++++++++++++++*/
		
		/**
		 * Key for client name data transportation.
		 * Expected data will be of type {@link String}
		 */
		public static final String CLIENT_NAME="client_name";
		
		/**
		 * Key for client birth date data transportation.
		 * Expected data will be of type {@link CustomDate}
		 */
		public static final String CLIENT_BIRTH_DATE="client_birth_date";
		
		/**
		 * Key for client birth time data transportation.
		 * Expected data will be of type {@link EnglishTime}
		 */
		public static final String CLIENT_BIRTH_TIME="client_birth_time";
			
		/**
		 * Key for sun rise time data transportation.
		 * Expected data will be of type {@link EnglishTime}
		 */
		public static final String SUN_RISE_TIME="sun_rise_time";
	
		/* +++++++++++++++++++ TEDI ++++++++++++++++++++++++++*/
		
		/**
		 * Key for divamana data transportation.
		 * Expected data will be of type {@link Integer}/ int.
		 */
		public static final String DIVAMANA="divamana";
		
		/**
		 * Key for bhukti data transportation.
		 * Expected data will be of type {@link Integer}/ int.
		 */
		public static final String BHUKTI="bhukti";
		
		/**
		 * Key for Lagna Pramana data transportation for Tedi calculations.
		 * Expected data will be of type {@link JyotishyaTime}
		 * <p>
		 * NOTE: Don't get confused with {@link #LAGNA_PRAMANA_FOR_LAGNA} or
		 * {@link #LAGNA_PRAMANA_FOR_NAVAMSHA}
		 */
		public static final String LAGNA_PRAMANA_FOR_TEDI="lagna_pramana_for_tedi";
		
		/* +++++++++++++++++++ LAGNA ++++++++++++++++++++++++++*/
		
		/**
		 * Key for Remaining suryadi ghati data transportation.
		 * Expected data will be of type {@link JyotishyaTime}
		 */
		public static final String REMAINING_SURYA_UDAYADI_GHATI="remaining_surya_udayadi_ghati";
		
		/**
		 * Key for Lagna Pramana data transportation.
		 * Expected data will be of type {@link JyotishyaTime}
		 * <p>
		 * NOTE: Don't get confused with {@link #LAGNA_PRAMANA_FOR_TEDI} or
		 * {@link #LAGNA_PRAMANA_FOR_NAVAMSHA}
		 */
		public static final String LAGNA_PRAMANA_FOR_LAGNA="lagna_pramana_for_lagna";
		
		/**
		 * Key of the flag to determine, whether to continue Lagna recursive
		 *  calculation or to skip
		 * Expected data will be of type {@link Boolean}
		 */
		public static final String SHOULD_SKIP_LAGNA_RECURSION = "should_skip_lagna_recursion";
		
		/* +++++++++++++++++++ NAVAMSHA ++++++++++++++++++++++++++*/
		
		/**
		 * Key for Lagna Pramana data transportation.
		 * Expected data will be of type {@link JyotishyaTime}
		 * <p>
		 * NOTE: Don't get confused with {@link #LAGNA_PRAMANA_FOR_TEDI} or 
		 * {@link #LAGNA_PRAMANA_FOR_LAGNA}
		 */
		public static final String LAGNA_PRAMANA_FOR_NAVAMSHA="lagna_pramana_for_navamsha";
		
		/* +++++++++++++++++++ NAKSHATRA ++++++++++++++++++++++++++*/

		/**
		 *  Key for Previous star pramana data transportation.
		 *  Expected data will be of type {@link JyotishyaTime}
		 */
		public static final String PREV_STAR_PRAMANA_FOR_NAVAMSHA = "prev_star_pramana";
		
		/**
		 *  Key for Born star pramana data transportation.
		 *  Expected data will be of type {@link JyotishyaTime}
		 */
		public static final String BORN_STAR_PRAMANA_FOR_NAVAMSHA = "born_star_pramana";

		/**
		 * Key for Should add flag data transportation.
		 * Expected data will be of type {@link Boolean}
		 */
		public static final String SHOULD_SUM_FOR_NAKSHATRA_CALCULATION = "should_sum";
		
		/**
		 * Key for dasha varsha data transportation.
		 * Expected data will be of type {@link Integer}
		 */
		public static final String DASHA_VARSHA = "dasha_varsha";

	
		/* +++++++++++++++++++  ++++++++++++++++++++++++++*/
		
	}
	
	/**
	 * Defines key associated with Result's data transportaion.
	 * @author chandan
	 *
	 */
	public static final class RESULT_KEY{
		
		/**
		 * Prefix to differentiate result keys from the rest.
		 */
		private static final String RESULT_KEY_PREFIX="result_";
		/**
		 * key Client's name.
		 */
		public static final String NAME=RESULT_KEY_PREFIX+"name";
		
		/**
		 * Client's birth date.
		 */
		public static final String  BIRTH_DATE=RESULT_KEY_PREFIX+"birth_date";
		
		/**
		 * Birth time.
		 */
		public static final String  BIRTH_TIME=RESULT_KEY_PREFIX+"birth_time";
		
		/**
		 * Sun Rise time.
		 */
		public static final String  SUN_RISE_TIME=RESULT_KEY_PREFIX+"sun_rise_time";
		
		/**
		 * Surya Udayadi Ghati
		 */
		public static final String  SURYA_UDAYADI_GHATI=RESULT_KEY_PREFIX+"surya_udayadi_ghati";
		
		/**
		 * Divamana.
		 */
		public static final String DIVAMANA=RESULT_KEY_PREFIX+"divamana";
		
		/**
		 * Bhukti
		 */
		public static final String BHUKTI=RESULT_KEY_PREFIX+"bhukti";
		
		/**
		 * Lagna Pramana expressed as Galige Vigalige.
		 */
		public static final String LAGNA_PRAMANA=RESULT_KEY_PREFIX+"lagna_pramana";
		
		/**
		 * Tedi expressed as Galige Vigalige.
		 */
		public static final String TEDI=RESULT_KEY_PREFIX+"tedi";
		
		/**
		 *LagnaTedi[Old name:Lagna] => Remaining Surya Udayadi Ghati expressed as Galige Vigalige.
		 */
		public static final String LAGNA_TEDI=RESULT_KEY_PREFIX+"lagna_tedi";
		
		/**
		 * Navamsha.
		 */
		public static final String NAVAMSHA=RESULT_KEY_PREFIX+"navamsha";
		
		/**
		 * Previous Star Pramana expressed as Galige Vigalige
		 */
		public static final String PREV_STAR_PRAMANA=RESULT_KEY_PREFIX+"prev_star_pramana";
		
		/**
		 * Born star pramana.
		 */
		public static final String BORN_STAR_PRAMANA=RESULT_KEY_PREFIX+"born_star_pramana";
		
		/**
		 * Parama Ghati expressed as Galige Vigalige.
		 */
		public static final String PARAMA_GHATI=RESULT_KEY_PREFIX+"parama_ghati";
		
		/**
		 * Gata Ghati expressed as Galige Vigalige.
		 */
		public static final String GATA_GHATI=RESULT_KEY_PREFIX+"gata_ghati";
		
		/**
		 * Eshya Ghati expressed in Galige Vigalige format.
		 */
		public static final String ESHYA_GHATI=RESULT_KEY_PREFIX+"eshya_ghati";
		
		/**
		 * Nakshatra Pada.
		 */
		public static final String NAKSHATRA_PADA=RESULT_KEY_PREFIX+"nakshatra_pada";
		
		/**
		 * Dasha Varsha.
		 */
		public static final String DASHA_VARSHA=RESULT_KEY_PREFIX+"dasha_versha";
		
		/**
		 * Eshya year which holds Eshya Varsha, Eshya Masa and Eshya Dina.
		 */
		public static final String ESHYA_VARSHA=RESULT_KEY_PREFIX+"eshya_varsha";
	}
}
