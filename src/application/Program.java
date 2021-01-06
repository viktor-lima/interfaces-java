package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.Services.BrazilTaxService;
import model.Services.RentalService;
import model.entities.CarRental;
import model.entities.Vehicle;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:ss");
		
		System.out.println("Enter rental data");
		System.out.print("Car rental: ");
		String carModel = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyy hh:ss): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Return (dd/MM/yyyy hh:ss): ");
		Date finish = sdf.parse(sc.nextLine());
		
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("enter price per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rs = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		
		rs.processInvoice(cr);
		
		System.out.print("INVOICE: ");
		System.out.println("Basic payment: " + String.format("%.2f",cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f",cr.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f",cr.getInvoice().getTotalPayment()));
		sc.close();
	}
}
