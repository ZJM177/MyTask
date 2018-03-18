package com.sun.task.dto;

import org.springframework.data.annotation.Id;

/**
 * 对应CouchBase数据库的共通Bean
 * 
 * @author WangCT
 * @version 1.0 2013.11.25
 */
public abstract class BaseBean {
	
	@Id
	private String docId;

	/** 品牌ID **/
	private String brandId;

	/** 文档类型（相当于表名） **/
	private String category;

	/** 业务类型 **/
	private String type;

	/** 业务类型的细分类型 **/
	private String subType;

	/** 数据写入CouchBase的时间 **/
	private String createTime;

	/** 数据的版本号 **/
	private String version;

	private String wcaID;

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getBrandId() {
		if(null==brandId || "".equals(brandId)){
			return wcaID;
		}
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public abstract void setBeanVersion();

	public String getWcaID() {
		return wcaID;
	}

	public void setWcaID(String wcaID) {
		this.wcaID = wcaID;
	}
}
