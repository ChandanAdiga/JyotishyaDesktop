package org.jyotish.models;

/**
 * Class representing final output of calculations which may be simply displayed 
 * or written to a file or can be printed directly after formating.
 * @author Chandan
 *
 */
public final class Result {
	
	/**
	 * Client's name.
	 */
	private String mName;
	
	/**
	 * Client's birth date.
	 */
	private CustomDate mBirthDate;
	
	/**
	 * Birth time.
	 */
	private EnglishTime mBirthTime;
	
	/**
	 * Sun Rise time.
	 */
	private EnglishTime mSunRiseTime;
	
	/**
	 * Surya Udayadi Ghati
	 */
	private JyotishyaTime mSuryaUdayadiGhati;
	
	/**
	 * Divamana.
	 */
	private int mDivamana;
	
	/**
	 * Bhukti
	 */
	private int mBhukti;
	
	/**
	 * Lagna Pramana expressed as Galige Vigalige.
	 */
	private JyotishyaTime mLagnaPramana;
	
	/**
	 * Tedi expressed as Galige Vigalige.
	 */
	private JyotishyaTime mTedi;
	
	/**
	 *Lagna => Remaining Surya Udayadi Ghati expressed as Galige Vigalige.
	 *<p>
	 *Renamed as LagnaTedi on 8th April 2012 :P
	 */
	private JyotishyaTime mLagnaTedi;
	
	/**
	 * Navamsha.
	 */
	private double mNavamsha;
	
	/**
	 * Previous Star Pramana expressed as Galige Vigalige
	 */
	private JyotishyaTime mPreviousStarPramana;
	
	/**
	 * Born star pramana.
	 */
	private JyotishyaTime mBornStarPramana;
	
	/**
	 * Parama Ghati expressed as Galige Vigalige.
	 */
	private JyotishyaTime mParamaGhati;
	
	/**
	 * Gata Ghati expressed as Galige Vigalige.
	 */
	private JyotishyaTime mGataGhati;
	
	/**
	 * Eshya Ghati expressed in Galige Vigalige format.
	 */
	private JyotishyaTime mEshyaGhati;
	
	/**
	 * Nakshatra Pada.
	 */
	private int mNaskhatraPada;
	
	/**
	 * Dasha Varsha.
	 */
	private int mDashaVarsha;
	
	/**
	 * Eshya year which holds Eshya Varsha, Eshya Masa and Eshya Dina.
	 */
	private CustomDate mEshyaVarsha;
	
	
	/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

	/**
	 * @param name the mBirthTime to set
	 */
	public void setName(String name) {
		this.mName = name;
	}

	/**
	 * @return the mName
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * @param birthDate the mBirthDate to set
	 */
	public void setBirthDate(CustomDate birthDate) {
		this.mBirthDate = birthDate;
	}

	/**
	 * @return the mBirthDate
	 */
	public CustomDate getBirthDate() {
		return mBirthDate;
	}

	/**
	 * @param mBirthTime the mBirthTime to set
	 */
	public void setBirthTime(EnglishTime mBirthTime) {
		this.mBirthTime = mBirthTime;
	}

	/**
	 * @return the mBirthTime
	 */
	public EnglishTime getBirthTime() {
		return mBirthTime;
	}

	/**
	 * @param mSunRiseTime the mSunRiseTime to set
	 */
	public void setSunRiseTime(EnglishTime mSunRiseTime) {
		this.mSunRiseTime = mSunRiseTime;
	}

	/**
	 * @return the mSunRiseTime
	 */
	public EnglishTime getSunRiseTime() {
		return mSunRiseTime;
	}

	/**
	 * @param mSuryaUdayadiGhati the mSuryaUdayadiGhati to set
	 */
	public void setSuryaUdayadiGhati(JyotishyaTime mSuryaUdayadiGhati) {
		this.mSuryaUdayadiGhati = mSuryaUdayadiGhati;
	}

	/**
	 * @return the mSuryaUdayadiGhati
	 */
	public JyotishyaTime getSuryaUdayadiGhati() {
		return mSuryaUdayadiGhati;
	}

	/**
	 * @param mDivamana the mDivamana to set
	 */
	public void setDivamana(int mDivamana) {
		this.mDivamana = mDivamana;
	}

	/**
	 * @return the mDivamana
	 */
	public int getDivamana() {
		return mDivamana;
	}

	/**
	 * @param mBhukti the mBhukti to set
	 */
	public void setBhukti(int mBhukti) {
		this.mBhukti = mBhukti;
	}

	/**
	 * @return the mBhukti
	 */
	public int getBhukti() {
		return mBhukti;
	}

	/**
	 * @param mLagnaPramana the mLagnaPramana to set
	 */
	public void setLagnaPramana(JyotishyaTime mLagnaPramana) {
		this.mLagnaPramana = mLagnaPramana;
	}

	/**
	 * @return the mLagnaPramana
	 */
	public JyotishyaTime getLagnaPramana() {
		return mLagnaPramana;
	}

	/**
	 * @param mTedi the mTedi to set
	 */
	public void setTedi(JyotishyaTime mTedi) {
		this.mTedi = mTedi;
	}

	/**
	 * @return the mTedi
	 */
	public JyotishyaTime getTedi() {
		return mTedi;
	}

	/**
	 * @param mLagnaTedi the mRemainingSuryaUdayadiGhati to set
	 */
	public void setLagnaTedi(
			JyotishyaTime mLagnaTedi) {
		this.mLagnaTedi = mLagnaTedi;
	}

	/**
	 * @return the Remaining SuryaUdayadiGhati as mLagnaTedi
	 */
	public JyotishyaTime getLagnaTedi() {
		return mLagnaTedi;
	}

	/**
	 * @param mNavamsha the mNavamsha to set
	 */
	public void setNavamsha(double mNavamsha) {
		this.mNavamsha = mNavamsha;
	}

	/**
	 * @return the mNavamsha
	 * @see #getFinalNavamsha()
	 */
	public double getNavamsha() {
		return mNavamsha;
	}
	
	/**
	 * Type casts original navamsha to get expected Navamsha.
	 * <p>
	 * EX: if '8.abc', where abc>0 then expected result is '9'
	 * @return Final readable Navamsha.
	 * @see #getNavamsha()
	 */
	public int getFinalNavamsha(){
		int navamshaInt=(int)this.getNavamsha();
		
		navamshaInt=Double.compare(
				this.getNavamsha(),(double)navamshaInt)
				>0? navamshaInt+1:navamshaInt ;
				
		return navamshaInt;
	}

	/**
	 * @param mPreviousStarPramana the mPreviousStarPramana to set
	 */
	public void setPreviousStarPramana(JyotishyaTime mPreviousStarPramana) {
		this.mPreviousStarPramana = mPreviousStarPramana;
	}

	/**
	 * @return the mPreviousStarPramana
	 */
	public JyotishyaTime getPreviousStarPramana() {
		return mPreviousStarPramana;
	}
	
	/**
	 * @param mBornStarPramana the mBornStarPramana to set
	 */
	public void setBornStarPramana(JyotishyaTime mBornStarPramana) {
		this.mBornStarPramana = mBornStarPramana;
	}

	/**
	 * @return the mBornStarPramana
	 */
	public JyotishyaTime getBornStarPramana() {
		return mBornStarPramana;
	}

	/**
	 * @param mParamaGhati the mParamaGhati to set
	 */
	public void setParamaGhati(JyotishyaTime mParamaGhati) {
		this.mParamaGhati = mParamaGhati;
	}

	/**
	 * @return the mParamaGhati
	 */
	public JyotishyaTime getParamaGhati() {
		return mParamaGhati;
	}

	/**
	 * @param mGataGhati the mGataGhati to set
	 */
	public void setGataGhati(JyotishyaTime mGataGhati) {
		this.mGataGhati = mGataGhati;
	}

	/**
	 * @return the mGataGhati
	 */
	public JyotishyaTime getGataGhati() {
		return mGataGhati;
	}

	/**
	 * @param mEshyaGhati the mEshyaGhati to set
	 */
	public void setEshyaGhati(JyotishyaTime mEshyaGhati) {
		this.mEshyaGhati = mEshyaGhati;
	}

	/**
	 * @return the mEshyaGhati
	 */
	public JyotishyaTime getEshyaGhati() {
		return mEshyaGhati;
	}

	/**
	 * @param mNaskhatraPada the mNaskhatraPada to set
	 */
	public void setNaskhatraPada(int mNaskhatraPada) {
		this.mNaskhatraPada = mNaskhatraPada;
	}

	/**
	 * @return the mNaskhatraPada
	 */
	public int getNakshatraPada() {
		return mNaskhatraPada;
	}

	/**
	 * @param mDashaVarsha the mDashaVarsha to set
	 */
	public void setDashaVarsha(int mDashaVarsha) {
		this.mDashaVarsha = mDashaVarsha;
	}

	/**
	 * @return the mDashaVarsha
	 */
	public int getDashaVarsha() {
		return mDashaVarsha;
	}
	
		
	/**
	 * @param mEshya the mEshyaVarsha to set
	 */
	public void setEshya(CustomDate mEshya) {
		this.mEshyaVarsha = mEshya;
	}

	/**
	 * @return the mEshyaVarsha
	 */
	public CustomDate getEshyaVarsha() {
		return mEshyaVarsha;
	}

	

	
}
