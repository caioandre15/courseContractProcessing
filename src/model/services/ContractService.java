package model.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	// Associations
	private OnlinePaymentService paymentService;
	
	// Builders
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	//Accessor Methods
	public OnlinePaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

	// Methods
	public void processContract(Contract contract, Integer months) {
		Date date = contract.getDate();
		
		for (int i=1; i<=months; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, i);
			Date dueDate = cal.getTime();
			double basicPayment = contract.getTotalValue() / months;
			double amount = paymentService.paymentFee(paymentService.interest(basicPayment, i));
			contract.addInstallment(new Installment(dueDate, amount));
		}
	}
}
