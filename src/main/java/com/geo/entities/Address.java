package com.geo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The persistent class for the actionpicture database table.
 * https://www.baeldung.com/javax-validation
 * All of the annotations used in the example are standard JSR annotations:

@NotNull – validates that the annotated property value is not null
@AssertTrue – validates that the annotated property value is true
@Size – validates that the annotated property value has a size between the attributes min and max; can be applied to String, Collection, Map, and array properties
@Min – vValidates that the annotated property has a value no smaller than the value attribute
@Max – validates that the annotated property has a value no larger than the value attribute
@Email – validates that the annotated property is a valid email address
Some annotations accept additional attributes, but the message attribute is common to all of them. This is the message that will usually be rendered when the value of the respective property fails validation.

Some additional annotations that can be found in the JSR are:

@NotEmpty – validates that the property is not null or empty; can be applied to String, Collection, Map or Array values
@NotBlank – can be applied only to text values and validated that the property is not null or whitespace
@Positive and @PositiveOrZero – apply to numeric values and validate that they are strictly positive, or positive including 0
@Negative and @NegativeOrZero – apply to numeric values and validate that they are strictly negative, or negative including 0
@Past and @PastOrPresent – validate that a date value is in the past or the past including the present; can be applied to date types including those added in Java 8
@Future and @FutureOrPresent – validates that a date value is in the future, or in the future including the present
 */
/*
 * @ApiModel
 * @ApiModelProperty
 * 
 * swagger annotation for api description
 * 
 * 
*/


@ApiModel("This entity contains all address information ")
@Entity(name = "Address")
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ApiModelProperty("This property contains the address line one")
	@Size(min=10,max=100)
	@Column(name = "address_line_one")
	private String addressLineOne;

	@ApiModelProperty("This property contains the address line two")
	@Column(name = "address_line_two")
	private String addressLineTwo;

	@ApiModelProperty("Enter valid street in this field")
	@NotBlank(message="street can not be blank")
	@Column(name = "street")
	private String street;

	@NotBlank 
	@Column(name = "district")
	private String district;

	@NotBlank 
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