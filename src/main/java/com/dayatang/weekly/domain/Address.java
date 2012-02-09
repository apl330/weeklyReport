package com.dayatang.weekly.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	protected String address;
	protected String city;
	protected String province;
	protected String country;
	protected String postalCode;
}
