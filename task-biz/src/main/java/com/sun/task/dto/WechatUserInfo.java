package com.sun.task.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Document(expiry=2592000)
public class WechatUserInfo extends BaseBean {
	// 直接交互时获取用户信息时特有字段，subscribe 订阅标示 值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	private String subscribe;
	// 直接交互时获取用户信息时特有字段，用户的语言，简体中文为zh_CN
	private String language;
	// 直接交互时获取用户信息时特有字段，用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	private String subscribeTime;
	//	openid 	用户的唯一标识
	private String openId;
	//	nickname 	用户昵称
	private String nickname;
	//	sex 	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String sex;
	//	province 	用户个人资料填写的省份
	private String province;
	//	city 	普通用户个人资料填写的城市
	private String city;
	//	country 	国家，如中国为CN
	private String country;
	//	headimgurl 	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	private String headimgurl;
	// OAuth授权时获取用户信息时特有字段，privilege 	用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	private String privilege;
	//用户类型：QYH 企业号用户  FWH：服务号用户
	private String userType;


	 //员工UserID。对应管理端的帐号,暂时不使用，借用openId
	//private String userid;
	//成员名称
	private String name;
	//成员所属部门id列表
	private String department;
	//职位信息
	private String position;
	//手机号码 ,暂时不使用，借用mobilePhone
	//private String mobile;
	//邮箱
	private String email;
	//微信号
	private String weixinid;
	//头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可
	private String avatar;
	//关注状态: 1=已关注，2=已冻结，4=未关注
	private String status;
	//扩展属性
	private String extattr;

	/**
	 * 地理位置经度
	 */
	private String longitude;
	/**
	 * 地理位置纬度
	 */
	private String latitude;
	/**
	 * 地理位置精度
	 */
	private String precision;
	/**
	 * 头像文件本地路径
	 */
	private String headimgurlLocal;
	/**
	 * 地理位置更新时间 （格式为：yyyy-MM-dd HH:mm:ss）
	 */
	private String updateTimeLocation;

	/**
	 * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间（格式为：yyyy-MM-dd HH:mm:ss）
	 */
	private String unSubscribetime;

	/**
	 * 是否关注公众号，空或1：关注  2：已经取消了关注
	 */
	private String subscribeFlag;


	/**
	 * 对应的cherry中的品牌Code
	 */
	private String cherryBrandCode;
	/**
	 * 对应的Cherry系统中的会员ID
	 */
	private String memberId;
	/**
	 * 会员卡号
	 */
	private String memberCode;
	/**
	 * 会员姓名
	 */
	private String memberName;
	/**
	 * 所属柜台号
	 */
	private String departCode;
	/**
	 * 所属柜台名称
	 */
	private String departName;

	/**
	 * 微信信息更新时间
	 */
	private String updateTimeWechat;

	/**
	 * cherry信息更新时间
	 */
	private String updateTimeCherry;

	/**
	 * 可兑换积分
	 */
	private String changablePoint;

	/**
	 * 会员等级
	 */
	private String levelCode;

	/**
	 * 会员等级名称
	 */
	private String levelName;

	/**
	 * 手机号
	 */
	private String mobilePhone;

	/**
	 * 用户绑定会员卡号时间（格式为：yyyy-MM-dd HH:mm:ss）
	 */
	private String bindMemberCodeTime;

	/**
	 * 最近一次和微信交互的时间（格式为：yyyy-MM-dd HH:mm:ss）
	 */
	private String lastAccessTime;

	/**
	 * 用户关注的渠道，若用户多次关注，则以最后一次关注的渠道
	 */
	private String subscribeChannel;

	public WechatUserInfo(String openId){
		super();
		super.setVersion("1.0");
		super.setCategory("WechatUserInfo");
		super.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		super.setDocId("System:WechatUserInfo:"+openId);
		super.setSubType("");
		super.setType("");
		this.setOpenId(openId);
	}

	public void setUpdateTimeCherry(String updateTimeCherry) {
		this.updateTimeCherry = updateTimeCherry;
	}

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public WechatUserInfo(){
		super();
	}


	public String getUpdateTimeWechat() {
		return updateTimeWechat;
	}

	public void setUpdateTimeWechat(String updateTimeWechat) {
		this.updateTimeWechat = updateTimeWechat;
	}

	public String getUpdateTimeCherry() {
		return updateTimeCherry;
	}

	@Override
	public void setBeanVersion() {
		// TODO Auto-generated method stub
		super.setVersion("1.0");
	}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		//强制将用户的语言都处理为简体中文
		return "zh_CN";
		//return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCherryBrandCode() {
		return cherryBrandCode;
	}

	public void setCherryBrandCode(String cherryBrandCode) {
		this.cherryBrandCode = cherryBrandCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getHeadimgurlLocal() {
		//return MulberryConfig.getSystemConfig("MulberryRootURL")+headimgurlLocal;
		return headimgurl;
	}

	public void setHeadimgurlLocal(String headimgurlLocal) {
		this.headimgurlLocal = headimgurlLocal;
	}
	
	public String getUpdateTimeLocation() {
		return updateTimeLocation;
	}

	public void setUpdateTimeLocation(String updateTimeLocation) {
		this.updateTimeLocation = updateTimeLocation;
	}

	public String getChangablePoint() {
		return changablePoint;
	}

	public void setChangablePoint(String changablePoint) {
		this.changablePoint = changablePoint;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getUnSubscribetime() {
		return unSubscribetime;
	}

	public void setUnSubscribetime(String unSubscribetime) {
		this.unSubscribetime = unSubscribetime;
	}

	public String getSubscribeFlag() {
		return subscribeFlag;
	}

	public void setSubscribeFlag(String subscribeFlag) {
		this.subscribeFlag = subscribeFlag;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getBindMemberCodeTime() {
		return bindMemberCodeTime;
	}

	public void setBindMemberCodeTime(String bindMemberCodeTime) {
		this.bindMemberCodeTime = bindMemberCodeTime;
	}

	public String getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(String lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public String getSubscribeChannel() {
		return subscribeChannel;
	}

	public void setSubscribeChannel(String subscribeChannel) {
		this.subscribeChannel = subscribeChannel;
	}

	public String getUserType() {
		if(null==userType||"".equals(userType)){
			return "FWH";
		}
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExtattr() {
		return extattr;
	}

	public void setExtattr(String extattr) {
		this.extattr = extattr;
	}

	@Override
	public String toString() {
		return "WechatUserInfo{" +
				"subscribe='" + subscribe + '\'' +
				", language='" + language + '\'' +
				", subscribeTime='" + subscribeTime + '\'' +
				", nickname='" + nickname + '\'' +
				", sex='" + sex + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", headimgurl='" + headimgurl + '\'' +
				", privilege='" + privilege + '\'' +
				", userType='" + userType + '\'' +
				", name='" + name + '\'' +
				", department='" + department + '\'' +
				", position='" + position + '\'' +
				", email='" + email + '\'' +
				", weixinid='" + weixinid + '\'' +
				", avatar='" + avatar + '\'' +
				", status='" + status + '\'' +
				", extattr='" + extattr + '\'' +
				", longitude='" + longitude + '\'' +
				", latitude='" + latitude + '\'' +
				", precision='" + precision + '\'' +
				", updateTimeLocation='" + updateTimeLocation + '\'' +
				", unSubscribetime='" + unSubscribetime + '\'' +
				", subscribeFlag='" + subscribeFlag + '\'' +
				", cherryBrandCode='" + cherryBrandCode + '\'' +
				", memberId='" + memberId + '\'' +
				", memberCode='" + memberCode + '\'' +
				", memberName='" + memberName + '\'' +
				", departCode='" + departCode + '\'' +
				", departName='" + departName + '\'' +
				", updateTimeWechat='" + updateTimeWechat + '\'' +
				", updateTimeCherry='" + updateTimeCherry + '\'' +
				", changablePoint='" + changablePoint + '\'' +
				", levelCode='" + levelCode + '\'' +
				", levelName='" + levelName + '\'' +
				", mobilePhone='" + mobilePhone + '\'' +
				", bindMemberCodeTime='" + bindMemberCodeTime + '\'' +
				", subscribeChannel='" + subscribeChannel + '\'' +
				'}';
	}
}
