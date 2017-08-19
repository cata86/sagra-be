package it.alecata.sagra.service.printer;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.attribute.standard.MediaSize;

public class Printer implements Printable {
	
	List<LineaScontrino> lines;
	
	public Printer(){
		lines = new ArrayList<LineaScontrino>();
	}
	
	public void addLines(LineaScontrino linea){
		lines.add(linea);
	}

	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		int y = 15;
		
		for(LineaScontrino linea : lines){
			int x = 0;
			FontMetrics metrics = g.getFontMetrics(linea.getFont());
			switch(linea.getAlign()){
	            case 1:
	            	break;
	            case 2:  
	        		x = (204 - metrics.stringWidth(linea.getText()));
	        		break;
	            case 3: 
	        		x = (204 - metrics.stringWidth(linea.getText())) /2;
	        		break;
	        	default:
	        		x = linea.getAlign();
	        	
			}
			
			if(linea.getText()!=null){
				g.setFont(linea.getFont());
				g.drawString(linea.getText(), x, y);
			}else if(linea.getImg() !=null){
				BufferedImage img;
				try {
					img = ImageIO.read(new ByteArrayInputStream(linea.getImg() ));
					g.drawImage(img, x, y,50,50, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//g.drawString(iterator, x, y);
			switch(linea.getAlignHeigh()){
	            case 0:
	            	break;
	            case 1:  
	            	y = y + linea.getFont().getSize()+5;
	        		break;
	        	default:
	        		y = y + linea.getAlignHeigh();
			}
		}
		
		//double thickness = 2;
		//Stroke oldStroke = g2d.getStroke();
		//g2d.setStroke(new BasicStroke((float) thickness));
		//g2d.drawRect(0, 0, 204, y+20);
		// g2d.drawRect(0, 80, 200, 100);
		//g2d.setStroke(oldStroke);

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}
	
	
    public static void main(String args[]) {
    	Printer printable = new Printer();
    	Font fontNormal = new Font("Times New Roman", Font.PLAIN, 12);
    	Font fontItalic = new Font("Times New Roman", Font.ITALIC, 20);
    	Font fontBold = new Font("Times New Roman", Font.BOLD, 12);
    	LineaScontrino linea = new LineaScontrino("PROVA SX NORMAL",null, fontNormal, 1,1);
    	printable.addLines(linea);
    	LineaScontrino linea2 = new LineaScontrino("PROVA DX ITALIC",null, fontItalic, 2,1);
    	printable.addLines(linea2);
    	LineaScontrino linea3 = new LineaScontrino("PROVA CENTER BOLD",null, fontBold, 3,1);
    	printable.addLines(linea3);
    	
    	String printer = "bixolon_srp-370";
        //String printer = "BIXOLON_SRP-350plus";
        //String printer = "Virtual_PDF_Printer";
        PrintService service = printable.findPrintService(printer);

    	
		PrinterJob job = PrinterJob.getPrinterJob ();
        PageFormat format = job.defaultPage();
        Paper paper = new Paper();
        paper.setSize(2.83465*72, 117*72);
        paper.setImageableArea(0, 0, 2.83465*72, 1181*72); //devo tenerlo per i primi due 00 altrimenti stampa + in gi�
        format.setPaper(paper);
        format = job.validatePage(format);

        try {
			//job.printDialog();
			job.setPrintService(service);
	        job.setPrintable(printable,format);
			job.print();
		} catch (PrinterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
    public static PrintService findPrintService(String printerName) {

	    printerName = printerName.toLowerCase();

	    PrintService service = null;

	    // Get array of all print services
	    PrintService[] services = PrinterJob.lookupPrintServices();

	    // Retrieve a print service from the array
	    for (int index = 0; service == null && index < services.length; index++) {

	        if (services[index].getName().toLowerCase().indexOf(printerName) >= 0) {
	            service = services[index];
	        }
	    }

	    // Return the print service
	    return service;
    }

}
