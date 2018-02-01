package com.fivestar.superclass.core.model;

import java.io.Serializable;


public class key implements Serializable {

	private String ak, uid, sha1, sha1_dev, pac, content, name;
	private int statu, appid;

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSha1() {
		return sha1;
	}

	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}

	public String getSha1_dev() {
		return sha1_dev;
	}

	public void setSha1_dev(String sha1_dev) {
		this.sha1_dev = sha1_dev;
	}

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "key [ak=" + ak + ", uid=" + uid + ", sha1=" + sha1 + ", sha1_dev=" + sha1_dev + ", pac=" + pac
				+ ", content=" + content + ", statu=" + statu + "]";
	}

	/* ���� Javadoc��
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ak == null) ? 0 : ak.hashCode());
		result = prime * result + ((pac == null) ? 0 : pac.hashCode());
		result = prime * result + ((sha1 == null) ? 0 : sha1.hashCode());
		result = prime * result + ((sha1_dev == null) ? 0 : sha1_dev.hashCode());
		return result;
	}
}