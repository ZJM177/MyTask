package com.sun.task.dto;

import java.util.List;

/**
 * 微信问答主记录Bean
 * 
 * @author WangCT
 * @version 1.0 2013.11.25
 */
public class WeChatQaMain extends BaseBean {
	
	/** 微信号 **/
	private String openId;
	
	/** 微信昵称 **/
	private String nickname;
	
	/** 微信头像地址 **/
	private String headImgurl;
	
	/** 客户类型 **/
	private String customType;
	
	/** 会员ID **/
	private String memberId;
	
	/** 会员姓名 **/
	private String memberName;
	
	/** 会员卡号 **/ 
    private String memCode;
    
    /** 会员手机号 **/ 
    private String mobilePhone;
	
	/** 客服ID **/
	private String userId;
	
	/** 客服姓名 **/
	private String userName;
	
	/** 客服头像地址 **/
	private String userHeadImgurl;
	
	/** 问答标签 **/
	private String qaTag;
	
	/** 问题发起方(0：微信用户 1：客服用户) **/
	private String qaStarter;
	
	/** 问题结束方(0：微信用户 1：客服用户) **/
	private String qaEnder;
	
	/** 问答时间 **/
	private String qaTime;
	
	/** 问答结束时间 **/
	private String qaEndTime;
	
	/** 受理时长（以秒为单位） **/
	private int acceptedTime;
	
	/** 受理状态（1：成功受理 2：遇忙拒绝 3：非服务时间段拒绝） **/
	private String acceptedStatus;
	
	/** 是否应答（0：未应答 1：应答） **/
	private String answerFlag;
	
	/** 关联问题ID **/
	private String weChatQaMainId;
	
	/** 问答明细List **/
	private List<WeChatQaDetail> weChatQaDetailList;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImgurl() {
		return headImgurl;
	}

	public void setHeadImgurl(String headImgurl) {
		this.headImgurl = headImgurl;
	}

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemCode() {
		return memCode;
	}

	public void setMemCode(String memCode) {
		this.memCode = memCode;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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

	public String getQaTag() {
		return qaTag;
	}

	public void setQaTag(String qaTag) {
		this.qaTag = qaTag;
	}

	public String getQaStarter() {
		return qaStarter;
	}

	public void setQaStarter(String qaStarter) {
		this.qaStarter = qaStarter;
	}

	public String getQaEnder() {
		return qaEnder;
	}

	public void setQaEnder(String qaEnder) {
		this.qaEnder = qaEnder;
	}

	public String getQaTime() {
		return qaTime;
	}

	public void setQaTime(String qaTime) {
		this.qaTime = qaTime;
	}

	public String getQaEndTime() {
		return qaEndTime;
	}

	public void setQaEndTime(String qaEndTime) {
		this.qaEndTime = qaEndTime;
	}

	public int getAcceptedTime() {
		return (int)Math.ceil(acceptedTime/60.0);
	}

	public void setAcceptedTime(int acceptedTime) {
		this.acceptedTime = acceptedTime;
	}

	public String getAcceptedStatus() {
		return acceptedStatus;
	}

	public void setAcceptedStatus(String acceptedStatus) {
		this.acceptedStatus = acceptedStatus;
	}

	public String getAnswerFlag() {
		return answerFlag;
	}

	public void setAnswerFlag(String answerFlag) {
		this.answerFlag = answerFlag;
	}
	
	public String getWeChatQaMainId() {
		return weChatQaMainId;
	}

	public void setWeChatQaMainId(String weChatQaMainId) {
		this.weChatQaMainId = weChatQaMainId;
	}

	public List<WeChatQaDetail> getWeChatQaDetailList() {
		return weChatQaDetailList;
	}

	public void setWeChatQaDetailList(List<WeChatQaDetail> weChatQaDetailList) {
		this.weChatQaDetailList = weChatQaDetailList;
	}

	@Override
	public void setBeanVersion() {
		super.setVersion("1.0");
	}

}
