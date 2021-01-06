package model.Services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double pricePerDay;
	private Double PricePerHour;

	private BrazilTaxService taxService;

	public RentalService(Double pricePerDay, Double picePerHour, BrazilTaxService taxService) {
		super();
		this.pricePerDay = pricePerDay;
		PricePerHour = picePerHour;
		this.taxService = taxService;
	}

	public void processInvoice(CarRental carRental) {

		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hours = (double) (t2 - t1) / 1000 / 60 / 60;

		double basicPayment;
		if (hours <= 12.0) {
			basicPayment = Math.ceil(hours) * PricePerHour;
		} else {
			basicPayment = Math.ceil(hours / 24) * pricePerDay;
		}

		double tax = taxService.tax(basicPayment);
		carRental.setInvoice(new Invoice(basicPayment, tax));

	}
}
