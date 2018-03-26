package com.sun.task.dto;


/**
 * 微信问答明细Bean
 * 
 * @author WangCT
 * @version 1.0 2013.11.25
 */
public class WeChatQaDetail {
	
	/** 用户类型 **/
	private String userType;
	
	/** 用户ID **/
	private String userId;
	
	/** 用户姓名 **/
	private String userName;
	
	/** 用户头像地址 **/
	private String userHeadImgurl;
	
	/** 问答内容类型 **/
	private String messageType;
	
	/** 问答内容 **/
	private String message;
	
	/** 问答时间 **/
	private String messageTime;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserHeadImgurl() {
		return userHeadImgurl;
	}

	public void setUserHeadImgurl(String userHeadImgurl) {
		this.userHeadImgurl = userHeadImgurl;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

}
