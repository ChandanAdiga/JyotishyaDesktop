package org.jyotish.models;

import org.jyotish.exceptions.JyotishyaTimeException;

/**
 * Class representation for Jyotishya time.
 * <p>
 * Note that 60 VIGALIGE=1 GALIGE, and 2.5 GALIGE= 1 ENGLISH HOUR.
 * <p>
 * Rest all the processing depends on these convention. 
 * @author Chandan
 * 
 */
public final class JyotishyaTime extends BaseTime implements Comparable<JyotishyaTime>{

	/**
	 * 
	 */
	private int mGalige;
	
	/**
	 * 
	 */
	private int mVigalige;
	
	/**
	 * @param galige of type int
	 * @param vigalige of type int
	 * @throws JyotishyaTimeException Exception due to invalid attributes
	 */
	public JyotishyaTime(int galige,int vigalige) 
		throws JyotishyaTimeException
	{
		setGalige(galige);
		setVigalige(vigalige);
		selfVerify();
	}
	
	@Override
	void selfVerify() 
		throws JyotishyaTimeException
	{
		if(mVigalige>59)
			throw new JyotishyaTimeException();
		//No limit on value of mGalige
	}
	
	

	/**
	 * Say, vigalige's are rounded off and which in turn
	 * updates galige.
	 */
	/* (non-Javadoc)
	 * @see org.jyotish.models.BaseTime#selfValidate()
	 */
	@Override
	public void selfCheck() throws JyotishyaTimeException {
		//Note 60 Vigalige=1 Galige..,
		
		double totalVigalige=this.getInFundamentalAttribute();
		//Calculate mGalige,
		mGalige=(int)totalVigalige/ModelConstants.A_GALIGE_IN_TERMS_OF_VIGALIGE;
		totalVigalige-=(mGalige*ModelConstants.A_GALIGE_IN_TERMS_OF_VIGALIGE);
		
		//Calculate mVigalige 
		mVigalige=(int)totalVigalige;
		
		//Finally,		
		selfVerify();
	}

	/**
	 * @param galige the mGalige to set
	 */
	public void setGalige(int galige) {
		this.mGalige = galige;
	}

	/**
	 * @return the mGalige
	 */
	public int getGalige() {
		return mGalige;
	}

	/**
	 * @param vigalige the mVigalige to set
	 *  @see #selfCheck()
	 */
	public void setVigalige(int vigalige) {
		this.mVigalige = vigalige;
	}

	/**
	 * @return the mVigalige
	 */
	public int getVigalige() {
		return mVigalige;
	}

	@Override
	public String toString() {
		return "[Galige:Vigalige]"
		+(getGalige()<10?"0"+getGalige():getGalige())
		+":"
		+(getVigalige()<10?"0"+getVigalige():getVigalige());
	}

	/**
	 * Expresses whole instance in terms of {@link #mVigalige}
	 * @return All attributes together represented as {@link #mVigalige}
	 */
	@Override
	public double getInFundamentalAttribute() {
		return getGalige()*ModelConstants.A_GALIGE_IN_TERMS_OF_VIGALIGE+getVigalige();
	}

	/**
	 * Implementation of {@link Comparable}.
	 * @param jyotishyaTime instance which has to be compared against.
	 * @return int -ve/0/+ve value. 
	 * <p> -ve if invoking {@link JyotishyaTime} instance is older 
	 * 			than parameter {@link JyotishyaTime} instance
	 * <p> 0 if invoking {@link JyotishyaTime} instance has same time attributes 
	 * 			that of parameter {@link JyotishyaTime} instance
	 * <p> +ve if invoking {@link JyotishyaTime} instance is recent 
	 * 			than parameter {@link JyotishyaTime} instance
	 */
	@Override
	public int compareTo(JyotishyaTime jyotishyaTime) {


		//Assume oldDate is older.
		double isInvokerIsOlder=0;
		
		/* 'm I a donkey  to do such a long procedure?
		
		int isInvokerIsOlder=-1;
		
		//Verify weather old and recent dates are relatively old and recent.
		if(jyotishyaTime.getGalige()<this.getGalige()){
			isInvokerIsOlder=1;
		}else if(jyotishyaTime.getGalige()==this.getGalige()){
			if(jyotishyaTime.getVigalige()<this.getVigalige()){
				isInvokerIsOlder=1;
			} else if(jyotishyaTime.getVigalige()==this.getVigalige()){
				//else no need to change as both time are same..
				isInvokerIsOlder=0;
			}
		}		
		
		return isInvokerIsOlder;
		*/
		//No way!, I am smart :)
		isInvokerIsOlder=this.getInFundamentalAttribute()-jyotishyaTime.getInFundamentalAttribute();
		

		return (isInvokerIsOlder==0?0:(isInvokerIsOlder<0?-1:1));
	}

}
