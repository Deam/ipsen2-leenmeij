package com.leenmeij.app.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.leenmeij.app.models.Invoice;
import com.leenmeij.app.models.User;
import com.leenmeij.app.models.Vehicle;

/**
 * This class creates a pdf for the invoice
 * @author Deam Kop (s1075228)
 *
 */
public class CreatePdf {
	private static String fileLocation = "C:/temp/";
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	
	private static PdfWriter writer;
	
	/**
	 * Creates the document
	 * @param invoice
	 */
	public void createPdf(Invoice invoice){
		// Get a random digit for the 
		Random random = new Random(100);
		// Declare a document
		Document document = new Document();
		try {
			// Create an instance
			writer = PdfWriter.getInstance(document, new FileOutputStream(fileLocation + invoice.getUser_id() + invoice.getStartdate() + random.nextInt() + ".pdf"));
			document.open();
			contentPage(document, invoice);
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
		
		// Set the cell text
		PdfPCell c1 = new PdfPCell(new Phrase(""));
		// Set the text alignment
	    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    // Set the border color
	    c1.setBorderColor(new BaseColor(255, 255, 255));
	    // Set the background color
	    c1.setBackgroundColor(new BaseColor(66, 139, 202));
	    // Add the cell to the table
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase(""));
		// Set the text alignment
	    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    // Set the border color
	    c1.setBorderColor(new BaseColor(66, 139, 202));
	    // Set the background color
	    c1.setBackgroundColor(new BaseColor(66, 139, 202));
	    // Add the cell to the table
	    table.addCell(c1);
	    
	    table.setHeaderRows(1);

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
