package model.services;

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

	// Accessor Methods
	public OnlinePaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

	// Methods
	public void processContract(Contract contract, Integer months) {
		double basicQuota = contract.getTotalValue() / months;
		for (int i = 1; i <= months; i++) {
			Date dueDate = addMounths(contract.getDate(), i);
			double updateQuota = basicQuota + paymentService.interest(basicQuota, i);
			double fullQuota = updateQuota + paymentService.paymentFee(updateQuota);
			contract.addInstallment(new Installment(dueDate, fullQuota));
		}
	}

	private Date addMounths(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
}
