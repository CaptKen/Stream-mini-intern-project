package com.example.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_product")
public class Order {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "orderID")
    private String orderID;
	@Column(name = "product_code")
    private String product_code;
	@Column(name = "product_name")
    private String product_name;
	
	@Column(name = "product")
    private String product;
	@Column(name = "company")
    private String company;
	
	@Column(name = "price_per_unit")
    private  int price_per_unit;
	@Column(name = "unit")
    private int unit;
	@Column(name = "vat")
	private float vat;
	@Column(name = "total_price")
    private int total_price;
    
	@Column(name = "active")
    private int active;

    
    public Order() {
    	
	}
    



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getOrderID() {
  		return orderID;
  	}
  	public void setOrderID(String orderID) {
  		this.orderID = orderID;
  	}
  	public String getProduct_code() {
  		return product_code;
  	}
  	public void setProduct_code(String product_code) {
  		this.product_code = product_code;
  	}
  	public String getProduct_name() {
  		return product_name;
  	}
  	public void setProduct_name(String product_name) {
  		this.product_name = product_name;
  	}
  	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public int getPrice_per_unit() {
  		return price_per_unit;
  	}
  	public void setPrice_per_unit(int price_per_unit) {
  		this.price_per_unit = price_per_unit;
  	}
  	public int getUnit() {
  		return unit;
  	}
  	public void setUnit(int unit) {
  		this.unit = unit;
  	}
  	public float getVat() {
  		return vat;
  	}
  	public void setVat(float vat) {
  		this.vat = vat;
  	}
  	public int getTotal_price() {
  		return total_price;
  	}
  	public void setTotal_price(int total_price) {
  		this.total_price = total_price;
  	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}




	@Override
	public String toString() {
		return "Order [id=" + id + ", orderID=" + orderID + ", product_code=" + product_code + ", product_name="
				+ product_name + ", product=" + product + ", company=" + company + ", price_per_unit=" + price_per_unit
				+ ", unit=" + unit + ", vat=" + vat + ", total_price=" + total_price + ", active=" + active + "]";
	}




	public Order(int id, String orderID, String product_code, String product_name, String product, String company,
			int price_per_unit, int unit, float vat, int total_price, int active) {
		super();
		this.id = id;
		this.orderID = orderID;
		this.product_code = product_code;
		this.product_name = product_name;
		this.product = product;
		this.company = company;
		this.price_per_unit = price_per_unit;
		this.unit = unit;
		this.vat = vat;
		this.total_price = total_price;
		this.active = active;
	}




    

   
}
