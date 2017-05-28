/**
 * String Utility file
 * 
 * Copyright (c) 2013 Edgeware Technologies. All Rights Reserved.
 */
package com.trip.common;

import javax.persistence.Query;

/**
 * This is String utility file
 * 
 * @version 1.0 
 * @author Edgeware
 */

public final class StringUtils {
	
	private StringUtils(){
		//Default constructor not called
	}
	/**
	 * Checks the given string is null or blank
	 * @param String
	 * @return boolean
	 */
	public static boolean isNullOrBlank(final String str){
		return null == str || str.trim().isEmpty() || "null".equalsIgnoreCase(str.trim());
	}
	
	/**
	 * Gives blank string when null is there
	 * @param str
	 * @return blank string
	 */
	public static String removeNull(final String str){
		if(isNullOrBlank(str)){
			return "";
		}else{
			return str;
		}
	}
	
	public static boolean isNullCombo(final String str){
	   return null == str || str.isEmpty() || "-1".equals(str) || "null".equalsIgnoreCase(str.trim());
	}
	
	public static String appendWithZero(final String value){		
		if(!isNullOrBlank(value)){
			if(value.indexOf('.') >= 1){
				if(value.substring(value.indexOf('.'), value.length()-1).length() == 1){
					return value+"0";  
				}else{
					return value;
				}
			}else{
				return value +".00";
			}
		}else{
			return "0.00";
		}
	}
	
	public static Double getDoubleValue(final String str){
		Double value = 0.00;
	   if (null == str || str.trim().isEmpty() || "-1".equals(str) ){
		   return value;
	   }
	   try{
		   value = Double.valueOf(str);
	   }catch(NumberFormatException nfe){
		   return 0.00;
	   }
	   return value;
	}
	
	public static String blankToNull(final String str){
	   if (null != str && !str.isEmpty() && !"".equals(str)){
		   return str;
	   }
	   return null;
	}
	
	public static String addEmptyIfNull(final String str){
		if (null == str || str.isEmpty() || "null".equalsIgnoreCase(str)){
			return "";
		}
		return str;
	}
	
	public static String appendParam(String pre, String param, String post){
		if(param == null || param.isEmpty() ){
			return "";
		}
		return pre+param+post;
	}
	public static String appendTwoParams(String pre, String date1, String mid, String date2, String post){
		if(isNullOrBlank(date1) || isNullOrBlank(date2)){
			return "";
		}
		return pre+date1+mid+date2+post;
	}
	public static String appendDateParams(String pre1, String pre2, String date1, String mid, String date2, String post){
		if(!isNullOrBlank(date1) && isNullOrBlank(date2)){
			return pre1+date1+mid+date1+post;
		} else if(isNullOrBlank(date1) && !isNullOrBlank(date2)){
			return pre2+date2+mid+date2+post;
		}
		return "";
	}
	public static String appendTypedParam(String pre, String param){
		if(param == null || param.isEmpty() ){
			return "";
		}
		return pre;
	}
	public static String appendTypedParam(String pre, Integer param){
		if(param == null){
			return "";
		}
		return pre;
	}
	public static void addParm(Query q, String check,String col, Object param){
		if(check != null && !check.isEmpty()){
			q.setParameter(col, param);
		}
	}
}