package com.example.tacos.databaseStruct;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
@Data
@Table("Taco_Cloud_Order")
public class TacoOrder implements Serializable {
	@SuppressWarnings("deprecation")
	@Column("customer_name")
	@NotBlank(message="Required delivery name")
	private String deliveryName;
	private String deliveryStreet;
	private String deliveryCity;
	private String deliveryState;
	private String deliveryZip;
	
	private static final long serialVersionUID=1L;
	
	@Id
	private Long id;
	private Date placedAt;
	
	
	@CreditCardNumber(message="not valid")
	private String ccNumber;
	
	@Pattern(regexp = ".*",message="")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="invalid CCV")
	private String ccCVV;
	
	public TacoOrder(String deliveryName, String deliveryStreet, String deliveryCity, String deliveryState,
			String deliveryZip, String ccNumber, String ccExpiration, String ccCVV, List<Taco> tacos) {
		super();
		this.deliveryName = deliveryName;
		this.deliveryStreet = deliveryStreet;
		this.deliveryCity = deliveryCity;
		this.deliveryState = deliveryState;
		this.deliveryZip = deliveryZip;
		this.ccNumber = ccNumber;
		this.ccExpiration = ccExpiration;
		this.ccCVV = ccCVV;
		this.tacos = tacos;
	}

	public TacoOrder() {
		this.deliveryName = "";
		this.deliveryStreet = "";
		this.deliveryCity = "";
		this.deliveryState = "";
		this.deliveryZip = "";
		this.ccNumber = "";
		this.ccExpiration = "";
		this.ccCVV = "";
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	public void setDeliveryStreet(String deliveryStreet) {
		this.deliveryStreet = deliveryStreet;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getDeliveryZip() {
		return deliveryZip;
	}

	public void setDeliveryZip(String deliveryZip) {
		this.deliveryZip = deliveryZip;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcExpiration() {
		return ccExpiration;
	}

	public void setCcExpiration(String ccExpiration) {
		this.ccExpiration = ccExpiration;
	}

	public String getCcCVV() {
		return ccCVV;
	}

	public void setCcCVV(String ccCVV) {
		this.ccCVV = ccCVV;
	}

	public List<Taco> getTacos() {
		return tacos;
	}

	public void setTacos(List<Taco> tacos) {
		this.tacos = tacos;
	}

	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
	
	public List<Taco> tacos=new ArrayList<Taco>();

	@Override
	public String toString() {
		String result="";
		 Field[] fs= TacoOrder.class.getDeclaredFields();
		 for(int i=0;i<fs.length;i++) {
			 fs[i].setAccessible(true);
			 try {
				result=result+fs[i].getName()+": " +fs[i].get(this)+"\n";
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		return result;
		 
	 }
	
}
