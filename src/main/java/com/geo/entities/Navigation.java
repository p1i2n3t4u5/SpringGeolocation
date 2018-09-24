package com.geo.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * The persistent class for the actionpart database table.
 * 
 */

@NamedQueries({
		@NamedQuery(name = Navigation.FIND_ALL_MAIN_NAVIGATION, query = "SELECT u FROM Navigation u Where u.parent is null order by u.sequenceNo ASC"),
		@NamedQuery(name = Navigation.FIND_ALL_SUB_NAVIGATION_BY_PARENTID, query = "SELECT u FROM Navigation u Where  u.parent is not null and  u.parent.id=:parentId  order by u.sequenceNo ASC"),
		@NamedQuery(name = Navigation.FIND_ALL_BY_ROLES, query = "SELECT  DISTINCT rn.navigation  FROM RoleNavigation rn Where rn.role.id in :roleIds") })

@Entity(name = "Navigation")
@Table(name = "navigation")
public class Navigation implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL_MAIN_NAVIGATION = "Navigation.findAllMainNavigation";
	public static final String FIND_ALL_SUB_NAVIGATION_BY_PARENTID = "Navigation.findAllSubNavigation";
	public static final String FIND_ALL_BY_ROLES = "Navigation.findAllByRoles";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "screen_label")
	private String screenLabel;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "parent_id")
	private Navigation parent;

	@Column(name = "name_token", unique = true)
	private String nameToken;

	@Column(name = "screen_type")
	private String screenType;

	@Column(name = "sequence_no")
	private int sequenceNo;

	@Column(name = "icon_image")
	private String iconImage;

	@Version
	@Column(name = "version")
	private long version;

	public Navigation() {
	}

	public Navigation(String screenLabel, Navigation parent, String nameToken, String screenType, String iconImage,
			int sequenceNo) {
		this.screenLabel = screenLabel;
		this.nameToken = nameToken;
		this.parent = parent;
		this.screenType = screenType;
		this.sequenceNo = sequenceNo;
		this.iconImage = iconImage;
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

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getNameToken() {
		return nameToken;
	}

	public void setNameToken(String nameToken) {
		this.nameToken = nameToken;
	}
	

	public Navigation getParent() {
		return parent;
	}

	public void setParent(Navigation parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Navigation [id=" + id + ", screenLabel=" + screenLabel + ", parent=" + parent + ", nameToken="
				+ nameToken + ", screenType=" + screenType + ", sequenceNo=" + sequenceNo + ", iconImage=" + iconImage
				+ ", version=" + version + "]";
	}

}