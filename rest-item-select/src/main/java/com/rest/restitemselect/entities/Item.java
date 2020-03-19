package com.rest.restitemselect.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	private Integer id;

	private String itemName;

	private Integer price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Item(Integer id, String itemName, Integer price) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
	}

	public Item() {

	}

}
