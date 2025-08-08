package com.fooddeliverysystem.entities;

public class DeliveryPerson {
	private int deliveruPersonId;
	private String name;
	private long contactNo;
	
	
	public DeliveryPerson(int deliveruPersonId, String name, long contactNo) {
		super();
		this.deliveruPersonId = deliveruPersonId;
		this.name = name;
		this.contactNo = contactNo;
	}

	public int getDeliveruPersonId() {
		return deliveruPersonId;
	}

	public String getName() {
		return name;
	}

	public long getContactNo() {
		return contactNo;
	}

	@Override
	public String toString() {
		return "DeliveryPerson [deliveruPersonId=" + deliveruPersonId + ", name=" + name + ", contactNo=" + contactNo
				+ "]";
	}
	
}
