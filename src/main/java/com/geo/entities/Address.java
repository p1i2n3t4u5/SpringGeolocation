package com.geo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the actionpicture database table.
 * 
 */

@Entity(name = "Address")
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "address_line_one")
	private String addressLineOne;

	@Column(name = "address_line_two")
	private String addressLineTwo;

	@Column(name = "street")
	private String street;

	@Column(name = "district")
	private String district;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "gps_location_latitude")
	private String gpsLocationLatitude;

	@Column(name = "gps_location_longitude")
	private String gpsLocationLongitude;

	@Column(name = "is_active", nullable = false)
	private boolean isActive = true;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getGpsLocationLatitude() {
		return gpsLocationLatitude;
	}

	public void setGpsLocationLatitude(String gpsLocationLatitude) {
		this.gpsLocationLatitude = gpsLocationLatitude;
	}

	public String getGpsLocationLongitude() {
		return gpsLocationLongitude;
	}

	public void setGpsLocationLongitude(String gpsLocationLongitude) {
		this.gpsLocationLongitude = gpsLocationLongitude;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLineOne=" + addressLineOne + ", addressLineTwo=" + addressLineTwo
				+ ", street=" + street + ", district=" + district + ", state=" + state + ", country=" + country
				+ ", pincode=" + pincode + ", gpsLocationLatitude=" + gpsLocationLatitude + ", gpsLocationLongitude="
				+ gpsLocationLongitude + ", isActive=" + isActive + "]";
	}
	
	

}