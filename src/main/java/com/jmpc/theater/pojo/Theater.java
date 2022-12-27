package com.jmpc.theater.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Representation of theater with name, address, and list of showings
 * @author vinayakbhope
 *
 */
public class Theater {
	
	private String name;
	private String address;
	
	// List of showings represented as maps with key being show id specific to the Theater
	private Map<String, Showing> showings = new HashMap<String, Showing>();
	
	public Theater(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Map<String, Showing> getShowings() {
		return showings;
	}
	public void setShowings(Map<String, Showing> shhowings) {
		this.showings = shhowings;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, name, showings);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Theater other = (Theater) obj;
		return Objects.equals(address, other.address) && Objects.equals(name, other.name)
				&& Objects.equals(showings, other.showings);
	}
	
	

}
