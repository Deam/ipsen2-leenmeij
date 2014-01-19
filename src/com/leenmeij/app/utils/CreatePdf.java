package com.leenmeij.app.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.leenmeij.app.models.Invoice;
import com.leenmeij.app.models.User;
import com.leenmeij.app.models.Vehicle;
import com.leenmeij.app.models.VehicleOption;

/**
 * This class creates a pdf for the invoice
 * All the values that we pass to the invoice
 * is processed in this class, so we can put
 * the data in the pdf.
 * @author Deam Kop (s1075228)
 *
 */
public class CreatePdf {
	private static String fileLocation = "C:/temp/";
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);

	/**
	 * Creates the document
	 * @param invoice
	 */
	public void createPdf(Invoice invoice){
		// Declare a document
		Document document = new Document();
		try {
			// Create an instance
			PdfWriter.getInstance(document, new FileOutputStream(fileLocation  + invoice.getStartdate() + "-" +invoice.getUser_id() + "-" + (int)(Math.random() * 50 + 1) + ".pdf"));
			// Create the document
			document.open();
			// Add the content
			contentPage(document, invoice);
			// Close the document
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the content page of the PDF file
	 * @param document
	 * @param invoice
	 * @throws DocumentException
	 */
	private static void contentPage(Document document, Invoice invoice) throws DocumentException {		
		// Get the customer information
		User user = new User();
		user = user.getById(invoice.getUser_id());
			
		// Create a chapter
		Chapter catPart = new Chapter(new Paragraph("LeenMeij Factuur gegevens", catFont), 1);
		
		// Add a sub paragraph
		Paragraph subParagraph = new Paragraph("Klantgegevens", subFont);
		Section subCatPart = catPart.addSection(subParagraph);

		// Set the user information
	    subCatPart.add(new Paragraph("Naam: " + user.getFirstName() + " " + user.getLastName()));
	    subCatPart.add(new Paragraph("Adres: " + user.getAddressLineOne() + " " + user.getAddressLineTwo()));
	    subCatPart.add(new Paragraph("Woonplaats:" + user.getCity()));
	    subCatPart.add(new Paragraph("Land: " + user.getCountry()));

	    // Add another subparagraph
	    subParagraph = new Paragraph("Huurgegevens:", subFont);
	    subCatPart = catPart.addSection(subParagraph);
	    // Format the date for showing purposes
	    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
	    // Add the dates
	    subCatPart.add(new Paragraph("Startdatum: " + format.format(invoice.getStartdate())));
	    subCatPart.add(new Paragraph("Einddatum: " + format.format(invoice.getEnddate())));


	    Paragraph paragraph = new Paragraph();
	    addEmptyLine(paragraph, 5);
	    subCatPart.add(paragraph);

	    // add a table
	    createTable(subCatPart, invoice);

	    // now add all this to the document
	    document.add(catPart);
	}
	
	/**
	 * Create a table with the invoice data
	 * @param subCatPart
	 * @param invoice
	 * @throws BadElementException
	 */
	private static void createTable(Section subCatPart, Invoice invoice) throws BadElementException {
	    PdfPTable table = new PdfPTable(2);
	    
		// Get the vehicle information
		Vehicle vehicle = new Vehicle();
		vehicle = vehicle.getById(invoice.getVehicle_id());
		

	    // Add the vehicle information
	    table.addCell("Merk");
	    table.addCell(vehicle.getBrand());
	    
	    table.addCell("Model");
	    table.addCell(vehicle.getModel());
	    
	    table.addCell("Kenteken");
	    table.addCell(vehicle.getLicenseplate());
	    
	    table.addCell("Prijs per dag");
	    // Set the format for the doubless
	    DecimalFormat format = new DecimalFormat("#.00");
	    table.addCell("€ " + format.format(vehicle.getHourlyrate() * 24));

	    
	    //Add the chosen vehicleoptions
	    VehicleOption vehicleOption = new VehicleOption();
	    for (Integer o : vehicleOption.all(invoice.getReservation_id())) {
	    	int day = 24*60*60*1000;
			// Calculate the number of days
			long days = Math.abs((invoice.getStartdate().getTime() - invoice.getEnddate().getTime()) / day);	    	
	    	// Set the name of the option
			table.addCell(vehicleOption.getByID(o).getName());
			// Set the price of the option
			table.addCell("€ " + format.format(vehicleOption.getByID(o).getPrice() * 24 * days));
		}
	    
	    
	    // Set the prices
	    table.addCell("Prijs");
	    table.addCell("€ " + format.format(invoice.getPrice()));

	    
	    table.addCell("BTW");
	    table.addCell("€ " + format.format((invoice.getPrice() / 100) * 21));
	    

	    table.addCell("Totaalprijs:");
	    table.addCell("€ " + format.format(invoice.getTotal()));
	    
	    // Add the table to the document
	    subCatPart.add(table);

	  }
	  
	/**
	 * Adds an empty row for better overview
	 * @param paragraph
	 * @param number
	 */
	  private static void addEmptyLine(Paragraph paragraph, int number) {
		 for (int i = 0; i < number; i++) {
		      paragraph.add(new Paragraph(" "));
		    }
	  }
}
