package org.jyotish.logic;

import java.util.Timer;
import java.util.TimerTask;

import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.jyotish.controllers.DataTransportKeyManager.RESULT_KEY;
import org.jyotish.controllers.DataTransporter;
import org.jyotish.controllers.DataTransportKeyManager.Key;
import org.jyotish.exceptions.CustomDateException;
import org.jyotish.exceptions.EnglishTimeException;
import org.jyotish.exceptions.JyotishyaTimeException;
import org.jyotish.exceptions.TransporterException;
import org.jyotish.models.CustomDate;
import org.jyotish.models.EnglishTime;
import org.jyotish.models.JyotishyaTime;
import org.jyotish.models.Result;
import org.jyotish.models.ModelConstants;
import org.jyotish.observers.ViewUpdateListner;

/**
 * Manager class which controls flow of Jyotishya calculations.
 * @author Chandan
 *
 */
public final class LogicManager {

	
	/**
	 * By default,Log type of the project. However you can customize for debugging
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;


	/**
	 * Tag value used for logging.
	 */
	private static final String TAG=LogicManager.class.getSimpleName();

	
	/**
	 * All the result of calculations done so far, are updated to this instance.
	 */
	private Result mResult=null;
	
	/**
	 * singleton instance of this class.
	 */
	private static LogicManager self=null;
	
	/**
	 * Observer for updating view up on change in model.
	 */
	private ViewUpdateListner mViewUpdateListener=null;
	
	/**
	 * Instance of {@link TimeHelper}
	 */
	private TimeHelper mTimeHelper=null;
	
	
	/**
	 * Timer for splash screen delay
	 */
	private static Timer mSplashTimer=null;
	
	/**
	 * Task to be done after splash timer completes.
	 */
	private static SplashTimerTask mSplashTimerTask=null;
	
	/**
	 * Hidden default constructor.
	 */
	private LogicManager() {
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Instantiating LogicManager..");
		mResult=new Result();
		mTimeHelper=new TimeHelper();
	}
	
	
	/**
	 * Method which provides singleton instance for this class.
	 * @param listener Observer for updating view up on change in model.
	 * @return singleton instance of this class.
	 */
	public static LogicManager getInstance(ViewUpdateListner listener){
		if(self==null){
			self=new LogicManager();
			self.mViewUpdateListener=listener;
		}
		
		return self;
	}
	
	/**
	 * Class which customizes splash timer tasks.
	 * @author chandan
	 * @see Timer 
	 * @see TimerTask 
	 * @see ModelConstants#SPLASH_DELAY_IN_MILI_SEC SPLASH_DELAY
	 */
	private static class SplashTimerTask extends TimerTask{

		@Override
		public void run() {
			self.mViewUpdateListener.onModelChange(
					ModelLogicType.SPLASH_TIMER_ENDED);
		}
		
	}
	
	/**
	 * Starts the splash timer.
	 * <p>
	 *  Note: It internally stops splash timer if running.
	 */
	public void startSplashTimer(){
		if(!isSplashTimerRunning()){
			stopSplashTimer();
		}
		
		mSplashTimer=new Timer();
		mSplashTimerTask=new SplashTimerTask();
		
		mSplashTimer.schedule(mSplashTimerTask, 
				ModelConstants.SPLASH_DELAY_IN_MILI_SEC);
		mViewUpdateListener.onModelChange(
				ModelLogicType.SPLASH_TIMER_STARTED);
	}
	
	/**
	 * Stops splash timer if running.
	 */
	public void stopSplashTimer(){
		if(mSplashTimer!=null){
			mSplashTimer.cancel();
		}
		mSplashTimer=null;
		mSplashTimerTask=null;
	}
	
	/**
	 * Checks whether splash timer is running or not.
	 * @return true if splash timer is running else false.
	 */
	public boolean isSplashTimerRunning(){
		return mSplashTimer!=null;
	}

	
	
	/**
	 * Calculates Surya Udayadi Ghati. Ensure birth time and sunrise time are 
	 * set.
	 * <p>
	 * NOTE: Calculations start from here.
	 * 
	 * @throws EnglishTimeException Exception while calculating birth-sunrise difference.
	 * @throws JyotishyaTimeException Exception while converting time differences.
	 * @throws TransporterException Exception while unloading data
	 * 
	 */
	public void calculateSuryaUdayadiGhati() 
			throws EnglishTimeException,JyotishyaTimeException, TransporterException{
		//TESTING Remove comments
		///*
		DataTransporter dataTransporter=DataTransporter.getInstance();
		
		self.mResult.setName(
				dataTransporter.getString(Key.CLIENT_NAME,"No name!"));
		self.mResult.setBirthDate(
				dataTransporter.getCustomDate(Key.CLIENT_BIRTH_DATE,null));
		self.mResult.setBirthTime(
				dataTransporter.getEnglishTime(Key.CLIENT_BIRTH_TIME,null));
		self.mResult.setSunRiseTime(
				dataTransporter.getEnglishTime(Key.SUN_RISE_TIME,null));
		
		
		EnglishTime birthSunRiseTimeDifference=null;
		EnglishTime birthTimeRef=self.mResult.getBirthTime();
		EnglishTime sunRiseTimeRef=self.mResult.getSunRiseTime();
		
		if(birthTimeRef.compareTo(sunRiseTimeRef)<0){
			//Born before sunrise!, 
			//So have to consider previous day's sunrise time to till birth time.
			birthSunRiseTimeDifference=new EnglishTime(0,0,0);
			birthSunRiseTimeDifference.setHour(24);//Till 0 hours.
			
			//Remember now,birthSunRiseTimeDifference-> maximum time of a day.
			birthSunRiseTimeDifference=self.mTimeHelper.getDifference(sunRiseTimeRef,
					birthSunRiseTimeDifference);
			
			//Add that with birth time.
			birthSunRiseTimeDifference=self.mTimeHelper.getSum(birthSunRiseTimeDifference,
					birthTimeRef);			
		}else{
			//Born after sunrise..
			birthSunRiseTimeDifference=self.mTimeHelper.getDifference(
					sunRiseTimeRef, birthTimeRef);
		}
		
		//Finally, set difference as Surya Udayadi Ghati..
		self.mResult.setSuryaUdayadiGhati(
				self.mTimeHelper.convertToJyotishyaTime(
						birthSunRiseTimeDifference));
		//TESTING comments
		//*/
		//Finally trigger a model changed event
		loadUpdatedResult();
		self.mViewUpdateListener.onModelChange(
				ModelLogicType.SURYA_UDAYADI_GHATI);
	}
	
	/**
	 * Calculates Tedi. Ensure divamana, bhukti & lagna pramana are initialized.
	 * @throws JyotishyaTimeException Exception while 
	 * <p> Note: {@link #calculateSuryaUdayadiGhati()} is performed prior.
	 * @throws TransporterException Exception while unloading data
	 */
	public void calculateTedi() throws JyotishyaTimeException, TransporterException{
		
		//TESTING Remove comments
		///*
		DataTransporter dataTransporter=DataTransporter.getInstance();
		
		self.mResult.setDivamana(
				dataTransporter.getInteger(Key.DIVAMANA,0));
		
		self.mResult.setBhukti(
				dataTransporter.getInteger(Key.BHUKTI,0));
		
		self.mResult.setLagnaPramana(
				dataTransporter.getJyotishyaTime(Key.LAGNA_PRAMANA_FOR_TEDI,null));
			
		
		//Hope int can hold... 
		int product=self.mResult.getDivamana()*self.mResult.getBhukti();
				
		//Get in terms of JyotishyaTime..
		JyotishyaTime divamanaBhuktiProduct=new JyotishyaTime(0, 0);
		divamanaBhuktiProduct.setVigalige(product);
		
		//Finally, distribute product among galige-vigalige.
		divamanaBhuktiProduct.selfCheck();
		
		JyotishyaTime timeDiff=null;
		//Compare to determine older & recent JyotishyaTime instances.
		//Then, Calculate tedi accordingly..
		if(self.mResult.getLagnaPramana().compareTo(
				divamanaBhuktiProduct)<0){
			//-ve => invoker is older..
			//PATTERN : getDifference(older,recent)
			timeDiff=mTimeHelper.getDifference(
					self.mResult.getLagnaPramana(), divamanaBhuktiProduct);
		}else{
			//+ve or '0' => invoker is recent..
			//PATTERN : getDifference(older,recent)
			timeDiff=mTimeHelper.getDifference(
					divamanaBhuktiProduct,self.mResult.getLagnaPramana());
		}
		
		//Finally, set difference as Tedi..
		self.mResult.setTedi(timeDiff);
		
		
		//Ensure mLagnaTedi is set to  Surya Udayadi Ghati,
		//Only first time, for lagna calculation, which is performed soon after this.
		self.mResult.setLagnaTedi(self.mResult.getSuryaUdayadiGhati());
		DataTransporter.getInstance().putJyotishyaTime(
				Key.REMAINING_SURYA_UDAYADI_GHATI, self.mResult.getLagnaTedi());
		
		
		//TESTING comments
		//*/
		//Finally trigger a model changed event
		loadUpdatedResult();
		self.mViewUpdateListener.onModelChange(
				ModelLogicType.TEDI);
	}
	
	/**
	 * Calculates LagnaTedi(Previously referred as Lagna) which is
	 *  nothing but remaining Surya Udayadi Ghati.
	 * "Ensure mLagnaTedi is set to  Surya Udayadi Ghati[See end of {@link #calculateTedi()}]" ,as initial value[for first
	 * recursion only] & Lagna Pramana is set[New for every recursion].Note that 
	 * at the end of every recursion mLagna will be appended with remained 
	 * Surya Udayadi Ghati. 
	 * @throws JyotishyaTimeException Exception while calculating difference.
	 * <p> Note: {@link #calculateTedi()} is performed prior.
	 * @throws TransporterException Exception while unloading data
	 */
	public void calculateLagnaTedi() throws JyotishyaTimeException, TransporterException{
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "calculateLagnaTedi()");
		
		ModelLogicType modelLogicType;
		DataTransporter dataTransporter=DataTransporter.getInstance();
		
		if(dataTransporter.getBoolean(
				Key.SHOULD_SKIP_LAGNA_RECURSION, true/*By default SKIP!*/)){
			
			LogManager.processLog(MY_LOG_TYPE, TAG,
					"calculateLagnaTedi(), SHOULD_SKIP_LAGNA_RECURSION:true");
			
			//FIXME should perform calculations or not :B ?????
			//Right now, not performing calculations..
			modelLogicType=ModelLogicType.LAGNA_SKIP_RECURSION;
		}		
		else
		{			
			LogManager.processLog(MY_LOG_TYPE, TAG,
				"calculateLagnaTedi(), SHOULD_SKIP_LAGNA_RECURSION:false");
			
			//TESTING comments
			///*
			self.mResult.setLagnaPramana(
					dataTransporter.getJyotishyaTime(Key.LAGNA_PRAMANA_FOR_LAGNA,null));
					
			//Ensure mLagna is set to  Surya Udayadi Ghati, in very beginning..
			//This is done at the end of Tedi calculations.. ;)
			//As that is the best way to do initialization only once.  Because
			// this method is recursive and difficult find first recursion :)
			JyotishyaTime remainingSuryaUdayadiGhati=self.mResult.getLagnaTedi();
			
			 //This calculation is RECURSIVE!!..
			 //i.e User keep on entering lagna pramana unless
			 //he likes to skip calculating difference..
			
			//Ensure lagna pramana is set by user in UI before he performs this calculation
			//If he skips,well perform next calculations..
			if(remainingSuryaUdayadiGhati.compareTo(
					//Pre-entered by user					
					self.mResult.getLagnaPramana())<0){
				//-ve => invoker is older..
				//PATTERN : getDifference(older,recent)
				remainingSuryaUdayadiGhati=self.mTimeHelper.getDifference(
					remainingSuryaUdayadiGhati, self.mResult.getLagnaPramana());
			}else{
				//+ve OR '0' => invoker is recent..
				//PATTERN : getDifference(older,recent)
				remainingSuryaUdayadiGhati=mTimeHelper.getDifference(
						self.mResult.getLagnaPramana(), remainingSuryaUdayadiGhati);
			}
			
			//Finally..
			self.mResult.setLagnaTedi(remainingSuryaUdayadiGhati);
			//For UI updation,
			dataTransporter.putJyotishyaTime(
					Key.REMAINING_SURYA_UDAYADI_GHATI, self.mResult.getLagnaTedi());
			
			//TESTING comments
			//*/
			modelLogicType=ModelLogicType.LAGNA_TEDI_CONTINUE_RECURSION;
		}
		
		//Finally trigger a model changed event
		loadUpdatedResult();
		self.mViewUpdateListener.onModelChange(modelLogicType);
	}
		
	/**
	 * Calculates Navamsha. Ensure Lagna is calculated prior to this.Also,fresh Lagna Pramana
	 * is set for navamsha calculations.
	 * <p> Note: {@link #calculateLagnaTedi()} is performed prior.
	 * @throws TransporterException Exception while unloading data
	 */
	public void calculateNavamsha() throws TransporterException{
		
		//TESTING Remove comments
		///*
		DataTransporter dataTransporter=DataTransporter.getInstance();
		
		self.mResult.setLagnaPramana(
				dataTransporter.getJyotishyaTime(Key.LAGNA_PRAMANA_FOR_NAVAMSHA,null));
		//UPDATED from lagna to lagnaTedi
		double lagnaTedi=self.mResult.getLagnaTedi().getInFundamentalAttribute();
		lagnaTedi*=9;
		
		self.mResult.setNavamsha((double)lagnaTedi/self.mResult.getLagnaPramana()
				.getInFundamentalAttribute());
		
		//TESTING comments
		//*/
		//Finally trigger a model changed event
		loadUpdatedResult();
		self.mViewUpdateListener.onModelChange(
				ModelLogicType.NAVAMSHA);
								
	}	
	
	/** Calculations related to Nakshatra are done by this method.Ensure 
	 * Previous star pramana, born star pramana are set prior.
	 * @throws JyotishyaTimeException
     *  <p> Note: {@link #calculateNavamsha()} are done prior.
	 * @throws TransporterException Exception while unloading data
	 */
	public void performNakshatraCalculations() throws JyotishyaTimeException, TransporterException {
		//Ensure previous Star Pramana,  born star pramana
		
		/*
		 * true if surya udayadi ghati should be added to
		 * previous star difference.else false to subtract surya udayadi ghati 
	 	 * against previous star pramana.
	 	 */
		
		//TESTING Remove comments
		///*
		DataTransporter dataTransporter=DataTransporter.getInstance();
		
		self.mResult.setPreviousStarPramana(
				dataTransporter.getJyotishyaTime(Key.PREV_STAR_PRAMANA_FOR_NAVAMSHA,null));
		
		self.mResult.setBornStarPramana(
				dataTransporter.getJyotishyaTime(Key.BORN_STAR_PRAMANA_FOR_NAVAMSHA,null));
		
		boolean shouldSum=dataTransporter.getBoolean(
				Key.SHOULD_SUM_FOR_NAKSHATRA_CALCULATION, true);
				
		/*BUG : time relativity is not checked!
		 * JyotishyaTime previousStarDiff=self.mTimeHelper.getDifference(
			new JyotishyaTime(60, 0), self.mResult.getPreviousStarPramana());
		*/
		//[CORRECTED
		JyotishyaTime tempJyotishyaTime=new JyotishyaTime(60, 0);
		JyotishyaTime previousStarDiff=null;
		if(tempJyotishyaTime.compareTo(
				self.mResult.getPreviousStarPramana())<0){
			//-ve  => invoker is older..
			//PATTERN : getDifference(older,recent)
			previousStarDiff=self.mTimeHelper.getDifference(
					tempJyotishyaTime, self.mResult.getPreviousStarPramana());	
		}
		else {
			//+ve or '0' => invoker is recent/large..
			//PATTERN : getDifference(older,recent)
			
			//So, mostly you are expected here..
			previousStarDiff=self.mTimeHelper.getDifference(
					self.mResult.getPreviousStarPramana() , tempJyotishyaTime);	
		}
		//CORRECTED]
		
		self.mResult.setParamaGhati(mTimeHelper.getSum(
			previousStarDiff, self.mResult.getBornStarPramana()));
		
		JyotishyaTime gataGhati=null;	
		if(shouldSum){
			//Need to add..
			gataGhati=self.mTimeHelper.getSum(
					self.mResult.getSuryaUdayadiGhati(), previousStarDiff);
		}else{
			//Need to subtract..
			if(self.mResult.getSuryaUdayadiGhati().compareTo(
					self.mResult.getPreviousStarPramana())<0){
				//-ve  => invoker is older..
				//PATTERN : getDifference(older,recent)
				gataGhati=self.mTimeHelper.getDifference(
						self.mResult.getSuryaUdayadiGhati(), self.mResult.getPreviousStarPramana());					
			}else{
				//+ve or '0' => invoker is recent..
				//PATTERN : getDifference(older,recent)
				gataGhati=self.mTimeHelper.getDifference(
						self.mResult.getPreviousStarPramana(),self.mResult.getSuryaUdayadiGhati());
			}
		}
		
		self.mResult.setGataGhati(gataGhati);
		
		//Calculate Eshya Ghati..
		if(self.mResult.getParamaGhati().compareTo(self.mResult.getGataGhati())<0){
			//-ve => invoker is older..
			//PATTERN : getDifference(older,recent)
			self.mResult.setEshyaGhati(self.mTimeHelper.getDifference(
					mResult.getParamaGhati(),self.mResult.getGataGhati()));
		}else{
			//+ve or '0' => invoker is recent..
			//PATTERN : getDifference(older,recent)
			self.mResult.setEshyaGhati(self.mTimeHelper.getDifference(
					self.mResult.getGataGhati(),self.mResult.getParamaGhati()));
		}
		
		double quadruple=self.mResult.getParamaGhati().getInFundamentalAttribute()/4;
		double gataGhatiInVigalige=self.mResult.getGataGhati().getInFundamentalAttribute();
		//Now, have to decide that gata ghati lies in which
		// part of parama ghati's 4 parts...
		if(gataGhatiInVigalige<=quadruple){
			self.mResult.setNaskhatraPada(1);
		}
		else if(gataGhatiInVigalige>quadruple&&(gataGhatiInVigalige<=(quadruple*2))){
			self.mResult.setNaskhatraPada(2);
		}
		else if((gataGhatiInVigalige>(quadruple*2))&&(gataGhatiInVigalige<=(quadruple*3))){
			self.mResult.setNaskhatraPada(3);
		}
		else if((gataGhatiInVigalige>(quadruple*3))&&(gataGhatiInVigalige<=(quadruple*4))){
			self.mResult.setNaskhatraPada(4);
		}
			
		//TESTING comments
		//*/
		
		//All is set, So..
		loadUpdatedResult();
		//Finally trigger a model changed event
		self.mViewUpdateListener.onModelChange(
				ModelLogicType.NAKSHATRA);
	}
	
	
	/**Calculations related eshya varsha are processed by this method. At the
	 * end all the eshya varsha attributes will be updated.
	 * Ensure dashaVarsha is set in prior.
	 * @throws CustomDateException Exception due to invalid custom date.
	 *  <p> Note: {@link #performNakshatraCalculations()} are done prior.
	 * @throws TransporterException Exception while unloading data.
	 */
	public void performDashBhuktiCalculations() throws CustomDateException, TransporterException{
		
		//TESTING Remove comments
		///*
		DataTransporter dataTransporter=DataTransporter.getInstance();
		
		self.mResult.setDashaVarsha(
				dataTransporter.getInteger(Key.DASHA_VARSHA,0));
		
		double product=self.mResult.getEshyaGhati().getInFundamentalAttribute()/self.mResult
							.getParamaGhati().getInFundamentalAttribute();
							
		product*=self.mResult.getDashaVarsha();
		
		CustomDate eshyaVarsha=new CustomDate(0, 0, 0);
		
		//Need clarifications.. DONE. as eshya attributes
		//are different from usual year attributes
		eshyaVarsha.setYear((int)product);
		LogManager.processLog(MY_LOG_TYPE, TAG, "Eshya Varsha:"+product);
		
		product=(product-eshyaVarsha.getYear())*12;
		eshyaVarsha.setMonth((int)product);
		LogManager.processLog(MY_LOG_TYPE, TAG, "Eshya Masa:"+product);
		
		product=(product-eshyaVarsha.getMonth())*30;
		eshyaVarsha.setDay((int)product);
		LogManager.processLog(MY_LOG_TYPE, TAG, "Eshya Dina:"+product);
		
		//[DEBUGGED,CORRECTED,UPDATED !!!
		//Really genius :P Done with calculations, who will update ??? :D
		self.mResult.setEshya(eshyaVarsha);
		//DEBUGGED,CORRECTED,UPDATED !!!]
		
		//TESTING comments
		//*/
		//Finally trigger a model changed event
		loadUpdatedResult();
		self.mViewUpdateListener.onModelChange(
				ModelLogicType.DASHABHUKTI);
	}
	
	/**
	 * Loads result on to the {@link DataTransporter} for updating
	 * Result tab view.
	 */
	private void loadUpdatedResult(){
		
		final String YET_TO_COMPUTE="YET TO COMPUTE";
		DataTransporter dataTransporter=DataTransporter.getInstance();			
		try {
			String resultValue="";
			//Name..
			resultValue=""+(self.mResult.getName()==null?
					YET_TO_COMPUTE:self.mResult.getName());
			dataTransporter.putString(RESULT_KEY.NAME, resultValue);
			
			//Birth date..
			resultValue=""+(self.mResult.getBirthDate()==null?
					YET_TO_COMPUTE:self.mResult.getBirthDate().toString());
			dataTransporter.putString(RESULT_KEY.BIRTH_DATE, resultValue);
			
			//Birth time..
			resultValue=""+(self.mResult.getBirthTime()==null?
					YET_TO_COMPUTE:self.mResult.getBirthTime().toString());
			dataTransporter.putString(RESULT_KEY.BIRTH_TIME, resultValue);
			
			//Sun rise time..
			resultValue=""+(self.mResult.getSunRiseTime()==null?
					YET_TO_COMPUTE:self.mResult.getSunRiseTime().toString());
			dataTransporter.putString(RESULT_KEY.SUN_RISE_TIME, resultValue);
			
			//Surya udayadi ghati..
			resultValue=""+(self.mResult.getSuryaUdayadiGhati()==null?
					YET_TO_COMPUTE:self.mResult.getSuryaUdayadiGhati().toString());
			dataTransporter.putString(RESULT_KEY.SURYA_UDAYADI_GHATI, resultValue);
			
			//Divamana..
			resultValue=""+(self.mResult.getDivamana()==0?
					YET_TO_COMPUTE:self.mResult.getDivamana());
			dataTransporter.putString(RESULT_KEY.DIVAMANA, resultValue);
			
			//Bhukti
			resultValue=""+(self.mResult.getBhukti()==0?
					YET_TO_COMPUTE:self.mResult.getBhukti());
			dataTransporter.putString(RESULT_KEY.BHUKTI, resultValue);
			
			//Lagna pramana
			resultValue=""+(self.mResult.getLagnaPramana()==null?
					YET_TO_COMPUTE:self.mResult.getLagnaPramana().toString());
			dataTransporter.putString(RESULT_KEY.LAGNA_PRAMANA, resultValue);
			
			//Tedi
			resultValue=""+(self.mResult.getTedi()==null?
					YET_TO_COMPUTE:self.mResult.getTedi().toString());
			dataTransporter.putString(RESULT_KEY.TEDI, resultValue);
			
			//Lagna
			resultValue=""+(self.mResult.getLagnaTedi()==null?
					YET_TO_COMPUTE:self.mResult.getLagnaTedi().toString());
			dataTransporter.putString(RESULT_KEY.LAGNA_TEDI, resultValue);
			
			//[Navamsha..			
			resultValue=""+(self.mResult.getNavamsha()==0?
					YET_TO_COMPUTE : self.mResult.getFinalNavamsha());
			//Also see self.mResult.getNavamsha() for Navamsha in terms of decimal[accurate]
			dataTransporter.putString(RESULT_KEY.NAVAMSHA, resultValue);
			
			//Previous star pramana..
			resultValue=""+(self.mResult.getPreviousStarPramana()==null?
					YET_TO_COMPUTE:self.mResult.getPreviousStarPramana().toString());
			dataTransporter.putString(RESULT_KEY.PREV_STAR_PRAMANA, resultValue);
			
			//Born star pramana..
			resultValue=""+(self.mResult.getBornStarPramana()==null?
					YET_TO_COMPUTE:self.mResult.getBornStarPramana().toString());
			dataTransporter.putString(RESULT_KEY.BORN_STAR_PRAMANA, resultValue);
			
			//Parama ghati..
			resultValue=""+(self.mResult.getParamaGhati()==null?
					YET_TO_COMPUTE:self.mResult.getParamaGhati().toString());
			dataTransporter.putString(RESULT_KEY.PARAMA_GHATI, resultValue);
			
			//Gata ghati..
			resultValue=""+(self.mResult.getGataGhati()==null?
					YET_TO_COMPUTE:self.mResult.getGataGhati().toString());
			dataTransporter.putString(RESULT_KEY.GATA_GHATI, resultValue);
			
			//Eshya ghati..
			resultValue=""+(self.mResult.getEshyaGhati()==null?
					YET_TO_COMPUTE:self.mResult.getEshyaGhati().toString());
			dataTransporter.putString(RESULT_KEY.ESHYA_GHATI, resultValue);
			
			
			//Nakshatra pada...
			resultValue=""+(self.mResult.getNakshatraPada()==0?
					YET_TO_COMPUTE:self.mResult.getNakshatraPada());
			dataTransporter.putString(RESULT_KEY.NAKSHATRA_PADA, resultValue);
			
			//Dasha varsha pada...
			resultValue=""+(self.mResult.getDashaVarsha()==0?
					YET_TO_COMPUTE:self.mResult.getDashaVarsha());
			dataTransporter.putString(RESULT_KEY.DASHA_VARSHA, resultValue);
			
			//ESHYA....
			/*[START *** DO NOT DO .toString() as it is different from ENGLISH date  ***/
			resultValue="";
			if(self.mResult.getEshyaVarsha()==null){
				resultValue+=YET_TO_COMPUTE;
			}
			else{
				//Eshya varsha/year..
				int varsha =self.mResult.getEshyaVarsha().getYear();
				//Eshya masa/month..
				int masa =self.mResult.getEshyaVarsha().getMonth();
				//Eshya dina/day..
				int dina =self.mResult.getEshyaVarsha().getDay();
				
				resultValue+=("[Varsha:Masa:Dina] "
						 +(varsha<10?"0"+varsha:varsha)
						 +":"
						 +(masa<10?"0"+masa:masa)
						 +":"
						 +(dina<10?"0"+dina:dina));
			}
			
			dataTransporter.putString(RESULT_KEY.ESHYA_VARSHA, resultValue);
			/*END]*/
			
		} catch (TransporterException e) {
			LogManager.processException(MY_LOG_TYPE, TAG, e);
		}
			
	}
	
}