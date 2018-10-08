package com.geo.models;

import java.util.List;

import com.geo.entities.Navigation;

public class Nav {

	private long id;
	private String screenLabel;
	private long parentId;
	private String nameToken;
	private String screenType;
	private int sequenceNo;
	private String iconImage;
	private List<Nav> chNavs;
	
	public Nav() {
		// TODO Auto-generated constructor stub
	}
	public Nav(Navigation navigation) {
		// TODO Auto-generated constructor stub
		this.id=navigation.getId();
		this.screenLabel=navigation.getScreenLabel();
		if (navigation.getParent()!=null) {
			this.parentId=navigation.getParent().getId();	
		}
		this.nameToken=navigation.getNameToken();
		this.screenType=navigation.getScreenType();
		this.sequenceNo=navigation.getSequenceNo();
		this.iconImage=navigation.getIconImage();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getScreenLabel() {
		return screenLabel;
	}

	public void setScreenLabel(String screenLabel) {
		this.screenLabel = screenLabel;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getNameToken() {
		return nameToken;
	}

	public void setNameToken(String nameToken) {
		this.nameToken = nameToken;
	}

	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}

	public List<Nav> getChNavs() {
		return chNavs;
	}

	public void setChNavs(List<Nav> chNavs) {
		this.chNavs = chNavs;
	}

}
