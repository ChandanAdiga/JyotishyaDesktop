package org.jyotish.controllers;

import java.util.HashMap;

import org.chandan.java.logging.LogManager;
import org.chandan.java.logging.LogType;
import org.chandan.utils.StringUtils;
import org.jyotish.exceptions.TransporterException;
import org.jyotish.exceptions.TransporterException.REASON;
import org.jyotish.models.CustomDate;
import org.jyotish.models.EnglishTime;
import org.jyotish.models.JyotishyaTime;
import org.jyotish.models.ModelConstants;

/**
 * Class which transports data from view layer to logic layer and vice versa.
 * Uses key-value[<String, Object>] technique.
 * <p>
 * For key management, have a look @ {@link DataTransportKeyManager}
 * @author chandan
 *
 */
public final class DataTransporter {
	
	/**
	 * TAG name for logging.
	 */
	private static final String TAG=DataTransporter.class.getSimpleName();
	
	/**
	 *Log type for logging. 
	 */
	private static final LogType MY_LOG_TYPE=ModelConstants.PROJECT_LOG_TYPE;
	
	/**
	 * Static instance.
	 */
	private static DataTransporter self;
	
	/**
	 * Type of objects which can be stored and retrieved
	 * @author chandan
	 *
	 */
	public static enum TRANSPORT_TYPE{
		//Don't forget to update type name in TransporterException#getType()
		
		/**
		 * Type of VALUE object : {@link Boolean}
		 */
		BOOLEAN,
		
		/**
		 * Type of VALUE object : {@link String}
		 */
		STRING,
		
		/**
		 * Type of VALUE object : {@link Short}
		 */
		SHORT,
		
		/**
		 * Type of VALUE object : {@link Integer}
		 */
		INTEGER,
		
		/**
		 * Type of VALUE object : {@link Float}
		 */
		FLOAT,
		
		/**
		 * Type of VALUE object : {@link Double}
		 */
		DOUBLE,
		
		/**
		 * Type of VALUE object : {@link JyotishyaTime}
		 */
		JYOTISHYA_TIME,
		
		/**
		 * Type of VALUE object : {@link EnglishTime}
		 */
		ENGLISH_TIME,
		
		/**
		 * Type of VALUE object : {@link CustomDate}
		 */
		CUSTOME_DATE,
		
	};
	
	/**
	 * HashMap holding key value pairs.
	 */
	private HashMap<String, Object> TRANSPORTER=null;
	
	/**
	 * Default constructor
	 */
	private DataTransporter(){
		LogManager.processLog(MY_LOG_TYPE, TAG,
				"Initializing Transporter.Any previous session data will be erased!");
		
		TRANSPORTER=new HashMap<String, Object>();
	}
	
	/**
	 * Returns singleton instance of transporter.
	 * @return instance of {@link DataTransporter}
	 */
	public static synchronized DataTransporter getInstance(){
		if(self==null){
			self=new DataTransporter();
		}
		
		return self;
	}
	
	/**
	 * Resets transporter. Any previous data will be erased 
	 */
	public static synchronized void resetTransporter(){
		LogManager.processLog(MY_LOG_TYPE, TAG,
			"Resetting Transporter.Any previous session data will be erased!");
		
		if(getInstance().TRANSPORTER!=null)
			self.TRANSPORTER.clear();
		
	}
	
	/**
	 * Checks whether transporter is ready to perform transportation
	 * @param key key which requested transportation.
	 * @param type type of transportation requested.
	 *  @throws TransporterException Transporter exception.
	 */
	private static void checkTransporter(String key,TRANSPORT_TYPE type)
		throws TransporterException{
		if(self==null | self.TRANSPORTER==null)
			throw new TransporterException(
					REASON.TRANSPORTER_NOT_READY, type, key);
	}
	
	/********************* SETTER METHODS *****************************/
	
	
	/**
	 * Drops {@link Boolean} value to transport.
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param value {@link Boolean} value to be transported.
	 * @throws TransporterException Exception during transportation process.
	 */
	public void putBoolean(String key,Boolean value) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.BOOLEAN);
		
		//If transporter is ready, then only we proceed.
		if(!StringUtils.isEmpty(key)){
			self.TRANSPORTER.put(key, value);
			LogManager.processLog(MY_LOG_TYPE, TAG, "Loading data with key="+key
					+", Value="+value);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.BOOLEAN,key);
		}
		
	}
	
	
	/**
	 * Drops {@link String} value to transport.
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param value {@link String} value to be transported.
	 * @throws TransporterException Exception during transportation process.
	 */
	public void putString(String key,String value) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.STRING);
		
		//If transporter is ready, then only we proceed.
		if(!StringUtils.isEmpty(key)){
			self.TRANSPORTER.put(key, value);
			LogManager.processLog(MY_LOG_TYPE, TAG, "Loading data with key="+key
					+", Value="+value);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.STRING,key);
		}
		
	}
	
	/**
	 * Drops {@link Short} value to transport.
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param value {@link Short} value to be transported.
	 * @throws TransporterException Exception during transportation process.
	 */
	public void putShort(String key,Short value) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.SHORT);
		
		//If transporter is ready, then only we proceed.
		if(!StringUtils.isEmpty(key)){
			self.TRANSPORTER.put(key, value);
			LogManager.processLog(MY_LOG_TYPE, TAG, "Loading data with key="+key
					+", Value="+value);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.SHORT,key);
		}
		
	}
	
	/**
	 * Drops {@link Integer} value to transport.
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param value {@link Integer} value to be transported.
	 * @throws TransporterException Exception during transportation process.
	 */
	public void putInteger(String key,Integer value) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.INTEGER);
		
		//If transporter is ready, then only we proceed.
		if(!StringUtils.isEmpty(key)){
			self.TRANSPORTER.put(key, value);
			LogManager.processLog(MY_LOG_TYPE, TAG, "Loading data with key="+key
					+", Value="+value);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.INTEGER,key);
		}
		
	}
	
	/**
	 * Drops {@link Float} value to transport.
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param value {@link Float} value to be transported.
	 * @throws TransporterException Exception during transportation process.
	 */
	public void putFloat(String key,Float value) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.FLOAT);
		
		//If transporter is ready, then only we proceed.
		if(!StringUtils.isEmpty(key)){
			self.TRANSPORTER.put(key, value);
			LogManager.processLog(MY_LOG_TYPE, TAG, "Loading data with key="+key
					+", Value="+value);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.FLOAT,key);
		}
		
	}
	
	/**
	 * Drops {@link Double} value to transport.
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param value {@link Double} value to be transported.
	 * @throws TransporterException Exception during transportation process.
	 */
	public void putDouble(String key,Double value) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.DOUBLE);
		
		//If transporter is ready, then only we proceed.
		if(!StringUtils.isEmpty(key)){
			self.TRANSPORTER.put(key, value);
			LogManager.processLog(MY_LOG_TYPE, TAG, "Loading data with key="+key
					+", Value="+value);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.DOUBLE,key);
		}
		
	}
	
	/**
	 * Drops {@link CustomDate} value to transport.
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param value {@link CustomDate} value to be transported.
	 * @throws TransporterException Exception during transportation process.
	 */
	public void putCustomDate(String key,CustomDate value) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.CUSTOME_DATE);
		
		//If transporter is ready, then only we proceed.
		if(!StringUtils.isEmpty(key)){
			self.TRANSPORTER.put(key, value);
			LogManager.processLog(MY_LOG_TYPE, TAG, "Loading data with key="+key
					+", Value="+value);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.CUSTOME_DATE,key);
		}
		
	}
	
	/**
	 * Drops {@link EnglishTime} value to transport.
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param value {@link EnglishTime} value to be transported.
	 * @throws TransporterException Exception during transportation process.
	 */
	public void putEnglishTime(String key,EnglishTime value) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.ENGLISH_TIME);
		
		//If transporter is ready, then only we proceed.
		if(!StringUtils.isEmpty(key)){
			self.TRANSPORTER.put(key, value);
			LogManager.processLog(MY_LOG_TYPE, TAG, "Loading data with key="+key
					+", Value="+value);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.ENGLISH_TIME,key);
		}
		
	}
	
	/**
	 * Drops {@link JyotishyaTime} value to transport.
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param value {@link JyotishyaTime} value to be transported.
	 * @throws TransporterException Exception during transportation process.
	 */
	public void putJyotishyaTime(String key,JyotishyaTime value) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.JYOTISHYA_TIME);
		
		//If transporter is ready, then only we proceed.
		if(!StringUtils.isEmpty(key)){
			self.TRANSPORTER.put(key, value);
			LogManager.processLog(MY_LOG_TYPE, TAG, "Loading data with key="+key
					+", Value="+value);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.JYOTISHYA_TIME,key);
		}
		
	}
	
	/********************* GETTER METHODS *****************************/
	
	/**
	 * Retrieves transported {@link Boolean} value for specified key..
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param defaultValue {@link Boolean} value to be transported.
	 * @return value of specified key.
	 * @throws TransporterException Exception during transportation process.
	 */
	public Boolean getBoolean(String key,Boolean defaultValue) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.BOOLEAN);
		
		//If transporter is ready, then only we proceed.
		Boolean returnValue=null;
		if(!StringUtils.isEmpty(key)){
			returnValue=(Boolean)self.TRANSPORTER.get(key);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.BOOLEAN,key);
		}
		//No errors.
		//If still,
		if(returnValue==null){
			//May be null assignment also. Who cares if that is desired!,
			returnValue=defaultValue;
		}
		LogManager.processLog(MY_LOG_TYPE, TAG, "Unloading data with key="+key
				+", Value="+returnValue);
		//Finally..
		return returnValue;
	}
	
	
	/**
	 * Retrieves transported {@link String} value for specified key..
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param defaultValue {@link String} value to be transported.
	 * @return value of specified key.
	 * @throws TransporterException Exception during transportation process.
	 */
	public String getString(String key,String defaultValue) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.STRING);
		
		//If transporter is ready, then only we proceed.
		String returnValue=null;
		if(!StringUtils.isEmpty(key)){
			returnValue=(String)self.TRANSPORTER.get(key);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.STRING,key);
		}
		//No errors.
		//If still,
		if(returnValue==null){
			//May be null assignment also. Who cares if that is desired!,
			returnValue=defaultValue;
		}
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Unloading data with key="+key
				+", Value="+returnValue);
		//Finally..
		return returnValue;
	}
	
	/**
	 * Retrieves transported {@link Short} value for specified key..
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param defaultValue {@link Short} value to be transported.
	 * @return value of specified key.
	 * @throws TransporterException Exception during transportation process.
	 */
	public Short getShort(String key,Short defaultValue) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.SHORT);
		
		//If transporter is ready, then only we proceed.
		Short returnValue=null;
		if(!StringUtils.isEmpty(key)){
			returnValue=(Short)self.TRANSPORTER.get(key);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.SHORT,key);
		}
		//No errors.
		//If still,
		if(returnValue==null){
			//May be null assignment also. Who cares if that is desired!,
			returnValue=defaultValue;
		}
		LogManager.processLog(MY_LOG_TYPE, TAG, "Unloading data with key="+key
				+", Value="+returnValue);
		//Finally..
		return returnValue;
	}
	
	/**
	 * Retrieves transported {@link Integer} value for specified key..
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param defaultValue {@link Integer} value to be transported.
	 * @return value of specified key.
	 * @throws TransporterException Exception during transportation process.
	 */
	public Integer getInteger(String key,Integer defaultValue) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.INTEGER);
		
		//If transporter is ready, then only we proceed.
		Integer returnValue=null;
		if(!StringUtils.isEmpty(key)){
			returnValue=(Integer)self.TRANSPORTER.get(key);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.INTEGER,key);
		}
		//No errors.
		//If still,
		if(returnValue==null){
			//May be null assignment also. Who cares if that is desired!,
			returnValue=defaultValue;
		}
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Unloading data with key="+key
				+", Value="+returnValue);
		//Finally..
		return returnValue;
	}
	
	/**
	 * Retrieves transported {@link Float} value for specified key..
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param defaultValue {@link Float} value to be transported.
	 * @return value of specified key.
	 * @throws TransporterException Exception during transportation process.
	 */
	public Float getFloat(String key,Float defaultValue) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.FLOAT);
		
		//If transporter is ready, then only we proceed.
		Float returnValue=null;
		if(!StringUtils.isEmpty(key)){
			returnValue=(Float)self.TRANSPORTER.get(key);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.FLOAT,key);
		}
		//No errors.
		//If still,
		if(returnValue==null){
			//May be null assignment also. Who cares if that is desired!,
			returnValue=defaultValue;
		}
		LogManager.processLog(MY_LOG_TYPE, TAG, "Unloading data with key="+key
				+", Value="+returnValue);
		
		//Finally..
		return returnValue;
	}
	
	/**
	 * Retrieves transported {@link Double} value for specified key..
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param defaultValue {@link Double} value to be transported.
	 * @return value of specified key.
	 * @throws TransporterException Exception during transportation process.
	 */
	public Double getDouble(String key,Double defaultValue) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.DOUBLE);
		
		//If transporter is ready, then only we proceed.
		Double returnValue=null;
		if(!StringUtils.isEmpty(key)){
			returnValue=(Double)self.TRANSPORTER.get(key);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.DOUBLE,key);
		}
		//No errors.
		//If still,
		if(returnValue==null){
			//May be null assignment also. Who cares if that is desired!,
			returnValue=defaultValue;
		}
		LogManager.processLog(MY_LOG_TYPE, TAG, "Unloading data with key="+key
				+", Value="+returnValue);
		
		//Finally..
		return returnValue;
	}
	
	/**
	 * Retrieves transported {@link CustomDate} value for specified key..
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param defaultValue {@link CustomDate} value to be transported.
	 * @return value of specified key.
	 * @throws TransporterException Exception during transportation process.
	 */
	public CustomDate getCustomDate(String key,CustomDate defaultValue) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.DOUBLE);
		
		//If transporter is ready, then only we proceed.
		CustomDate returnValue=null;
		if(!StringUtils.isEmpty(key)){
			returnValue=(CustomDate)self.TRANSPORTER.get(key);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.DOUBLE,key);
		}
		//No errors.
		//If still,
		if(returnValue==null){
			//May be null assignment also. Who cares if that is desired!,
			returnValue=defaultValue;
		}
		LogManager.processLog(MY_LOG_TYPE, TAG, "Unloading data with key="+key
				+", Value="+returnValue);
		//Finally..
		return returnValue;
	}
	
	/**
	 * Retrieves transported {@link EnglishTime} value for specified key..
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param defaultValue {@link EnglishTime} value to be transported.
	 * @return value of specified key.
	 * @throws TransporterException Exception during transportation process.
	 */
	public EnglishTime getEnglishTime(String key,EnglishTime defaultValue) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.DOUBLE);
		
		//If transporter is ready, then only we proceed.
		EnglishTime returnValue=null;
		if(!StringUtils.isEmpty(key)){
			returnValue=(EnglishTime)self.TRANSPORTER.get(key);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.DOUBLE,key);
		}
		//No errors.
		//If still,
		if(returnValue==null){
			//May be null assignment also. Who cares if that is desired!,
			returnValue=defaultValue;
		}
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Unloading data with key="+key
				+", Value="+returnValue);
		//Finally..
		return returnValue;
	}
	
	/**
	 * Retrieves transported {@link JyotishyaTime} value for specified key..
	 * <p>
	 * NOTE: Value is not checked for null. As this may be desired transportation as well!
	 * @param key KEY to store and retrieve value.
	 * @param defaultValue {@link JyotishyaTime} value to be transported.
	 * @return value of specified key.
	 * @throws TransporterException Exception during transportation process.
	 */
	public JyotishyaTime getJyotishyaTime(String key,JyotishyaTime defaultValue) throws TransporterException{
		
		//Check and throw exception if something is wrong.
		checkTransporter(key,TRANSPORT_TYPE.DOUBLE);
		
		//If transporter is ready, then only we proceed.
		JyotishyaTime returnValue=null;
		if(!StringUtils.isEmpty(key)){
			returnValue=(JyotishyaTime)self.TRANSPORTER.get(key);
		}
		else
		{
			throw new TransporterException(REASON.INVALID_KEY,
					TRANSPORT_TYPE.DOUBLE,key);
		}
		//No errors.
		//If still,
		if(returnValue==null){
			//May be null assignment also. Who cares if that is desired!,
			returnValue=defaultValue;
		}
		
		LogManager.processLog(MY_LOG_TYPE, TAG, "Unloading data with key="+key
				+", Value="+returnValue);
		//Finally..
		return returnValue;
	}
	
	
}
