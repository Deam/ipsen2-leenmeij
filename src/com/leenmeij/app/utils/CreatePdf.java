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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.leenmeij.app.models.Invoice;
import com.leenmeij.app.models.User;
import com.leenmeij.app.models.Vehicle;

public class CreatePdf {
	private static String fileLocation = "C:/temp/";
	
	//private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	
	public void createPdf(Invoice invoice){
		Random random = new Random();
		
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileLocation + random.nextInt() + invoice.getUser_id() + invoice.getStartdate() + ".pdf"));
			document.open();
			contentPage(document, invoice);
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private static void contentPage(Document document, Invoice invoice) throws DocumentException {		
		// Get the customer information
		User user = new User();
		user = user.getById(invoice.getUser_id());
			
		Chapter catPart = new Chapter(new Paragraph("LeenMeij Factuur gegevens"), 1);
		
		Paragraph subParagraph = new Paragraph("Klantgegevens", subFont);
		Section subCatPart = catPart.addSection(subParagraph);
	    subCatPart.add(new Paragraph("Naam: " + user.getFirstName() + " " + user.getLastName()));
	    subCatPart.add(new Paragraph("Adres: " + user.getAddressLineOne() + " " + user.getAddressLineTwo()));
	    subCatPart.add(new Paragraph("Woonplaats:" + user.getCity()));
	    subCatPart.add(new Paragraph("Land: " + user.getCountry()));

	    
	    subParagraph = new Paragraph("Huurgegevens:", subFont);
	    subCatPart = catPart.addSection(subParagraph);
	    
	    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
	    
	    subCatPart.add(new Paragraph("Startdatum: " + format.format(invoice.getStartdate())));
	    subCatPart.add(new Paragraph("Einddatum: " + format.format(invoice.getEnddate())));

	    // add a list
	    createList(subCatPart);
	    Paragraph paragraph = new Paragraph();
	    addEmptyLine(paragraph, 5);
	    subCatPart.add(paragraph);

	    // add a table
	    createTable(subCatPart, invoice);

	    // now add all this to the document
	    document.add(catPart);
	}
	
	  private static void createTable(Section subCatPart, Invoice invoice) throws BadElementException {
	    PdfPTable table = new PdfPTable(2);
	    
		// Get the vehicle information
		Vehicle vehicle = new Vehicle();
		vehicle = vehicle.getById(invoice.getVehicle_id());
		
		PdfPCell c1 = new PdfPCell(new Phrase(""));
		
	    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Voertuiggegevens"));
	    c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    table.addCell(c1);
	    
	    table.setHeaderRows(1);

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

	    table.addCell("Prijs");
	    table.addCell("€ " + format.format(invoice.getPrice()));

	    
	    table.addCell("BTW");
	    table.addCell("€ " + format.format((invoice.getPrice() / 100) * 21));
	    

	    table.addCell("Totaalprijs:");
	    table.addCell("€ " + format.format(invoice.getTotal()));
	    

	    subCatPart.add(table);

	  }

	  private static void createList(Section subCatPart) {
	    List list = new List(true, false, 10);
//	    list.add(new ListItem("First point"));
//	    list.add(new ListItem("Second point"));
//	    list.add(new ListItem("Third point"));
	    subCatPart.add(list);
	  }
	  
	  private static void addEmptyLine(Paragraph paragraph, int number) {
		 for (int i = 0; i < number; i++) {
		      paragraph.add(new Paragraph(" "));
		    }
	  }
}
