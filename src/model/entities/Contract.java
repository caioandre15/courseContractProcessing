package model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contract {
	
	// Attributes
	private Integer number;
	private Date date;
	private Double totalValue;

	// Associations
	private List<Installment> installments = new ArrayList<>();

	// Builders
	public Contract() {
	}

	public Contract(Integer number, Date date, Double totalValue) {
		this.number = number;
		this.date = date;
		this.totalValue = totalValue;
	}

	// Accessor Methods

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public List<Installment> getInstallments() {
		return installments;
	}
	
	// Methods
	public void addInstallment(Installment installment) {
		installments.add(installment);
	}
	
	public void removeInstallment(Installment instalment) {
		installments.remove(instalment);
	}

}
