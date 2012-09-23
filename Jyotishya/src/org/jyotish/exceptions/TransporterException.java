package org.jyotish.exceptions;

import org.jyotish.controllers.DataTransporter;
import org.jyotish.controllers.DataTransporter.TRANSPORT_TYPE;

/**
 * Exceptions corresponding to {@link DataTransporter}. Refer nested classes.
 * @author chandan
 *
 */
public final class TransporterException extends Exception{

	/**
	 * Generated version id.
	 */
	private static final long serialVersionUID = 1103313865357521981L;
	
	/**
	 * Reason for Transporter exception.
	 * @author chandan
	 *
	 */
	public static enum REASON{
		/**
		 * Transporter is not initialized yet.
		 */
		TRANSPORTER_NOT_READY,
		
		/**
		 * Requested key not found.
		 */
		INVALID_KEY,		
	
		/**
		 * Requested type not found.
		 */
		TYPE_NOT_FOUND,
		
		/**
		 * Requested value not found.
		 */
		@Deprecated
		VALUE_NOT_FOUND
		
	}
	
	/**
	 * Key whose transportation caused exception.
	 */
	private String mKey;
	
	/**
	 * {@link TRANSPORT_TYPE } which caused exception.
	 */
	private TRANSPORT_TYPE mTransportType;
	
	/**
	 * {@link REASON } which caused exception.
	 */
	private REASON mReason;
	
	/**
	 * General instantiation is not permitted.
	 */
	@SuppressWarnings("unused")
	private TransporterException(){
		
	}
	
	/**
	 * Initialize below details which enables tracking of exception.
	 * @param reason {@link REASON}
	 * @param type {@link TRANSPORT_TYPE}
	 * @param key Key which caused transport exception.
	 */
	public TransporterException(REASON reason,TRANSPORT_TYPE type, String key){
		mReason=reason;
		mTransportType=type;
		mKey=key;
	}
	
	
	/**
	 * Formats exception details.
	 * @return Formated readable exception.++++
	 */
	private String getFormatedException(){
		return "Transporter Exception!, REASON: "+getReason()
				+", for DATA TYPE:"+getType()
				+"for KEY : "+mKey;
	}
	
	/**
	 * Returns the readable reason.
	 * @return Returns the readable reason.
	 */
	private String getReason(){
		String reason=null;
		switch (mReason) {
		case TRANSPORTER_NOT_READY:
			reason="Transporter not ready";
			break;
			
		case INVALID_KEY:
			reason="Invalid key";
			break;
			
		case VALUE_NOT_FOUND:
			reason="Value not found";
			break;

		default:
			reason="Unknown";
			break;
		}
		return reason;
	}
	
	/**
	 * Returns the readable {@link TRANSPORT_TYPE}
	 * @return Returns the readable type.
	 */
	private String getType(){
		String type=null;
		switch (mTransportType) {
		
		case BOOLEAN:
			type="Boolean";
			break;
			
		case STRING:
			type="String";
			break;
			
		case SHORT:
			type="Short";
			break;		
			
		case INTEGER:
			type="Integer";
			break;
			
		case DOUBLE:
			type="Double";
			break;			
			
		case FLOAT:
			type="Float";
			break;
			
		case CUSTOME_DATE:
			type="CustomeDate";
			break;
			
		case ENGLISH_TIME:
			type="EnglishTime";
			break;
			
		case JYOTISHYA_TIME:
			type="JyotishyaTime";
			break;
			
		default:
			type="Unknown";
			break;
		}
		return type;
	}
	
	
	@Override
	public String toString()
	{		
		return getFormatedException();
	
	}
}
