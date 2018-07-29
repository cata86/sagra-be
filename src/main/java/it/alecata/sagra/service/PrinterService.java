package it.alecata.sagra.service;

import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.alecata.sagra.domain.Ordine;
import it.alecata.sagra.domain.Pietanza;
import it.alecata.sagra.domain.PietanzaCategoria;
import it.alecata.sagra.domain.PietanzaOrdinata;
import it.alecata.sagra.domain.Sagra;
import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.repository.OrdineRepository;
import it.alecata.sagra.repository.PietanzaCategoriaRepository;
import it.alecata.sagra.repository.TavoloAccomodatoRepository;
import it.alecata.sagra.service.printer.LineaScontrino;
import it.alecata.sagra.service.printer.Printer;

@Service
@Transactional
public class PrinterService {
	
	private final Logger log = LoggerFactory.getLogger(PrinterService.class);
	
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	Font fontBold18 = new Font("Times New Roman", Font.BOLD, 18);
	Font fontNormal18 = new Font("Times New Roman", Font.PLAIN, 18);
	
	Font fontBold16 = new Font("Times New Roman", Font.BOLD, 16);
	Font fontNormal16 = new Font("Times New Roman", Font.PLAIN, 16);
	
	Font fontBold14 = new Font("Times New Roman", Font.BOLD, 14);
	Font fontNormal14 = new Font("Times New Roman", Font.PLAIN, 14);
	
	Font fontNormal12 = new Font("Times New Roman", Font.PLAIN, 12);
	Font fontBold12 = new Font("Times New Roman", Font.BOLD, 12);
	
	Font fontNormal10 = new Font("Times New Roman", Font.PLAIN, 10);
	Font fontBold110 = new Font("Times New Roman", Font.BOLD, 10);
	
	//String printer = "bixolon_srp-370";	
    String printer = "BIXOLON SRP-350plus";
    //String printer = "Virtual_PDF_Printer";
	
    private final SagraService sagraService;
    private final OrdineRepository ordineRepository;
    private final PietanzaCategoriaRepository pietanzaCategoriaRepository;
    private final TavoloAccomodatoRepository tavoloAccomodatoRepository;


    public PrinterService(SagraService sagraService, OrdineRepository ordineRepository,
    		PietanzaCategoriaRepository pietanzaCategoriaRepository,TavoloAccomodatoRepository tavoloAccomodatoRepository) {
        this.sagraService = sagraService;
        this.ordineRepository = ordineRepository;
        this.pietanzaCategoriaRepository = pietanzaCategoriaRepository;
        this.tavoloAccomodatoRepository = tavoloAccomodatoRepository;
    }
	
	@Transactional
	public void printOrder(Long ordineID){
		
		Sagra sagra = sagraService.findAll().get(0);
		Ordine ordine = ordineRepository.findOne(ordineID);
		
		Printer printable = new Printer();
		
		//LOGO
		printable.addLines(new LineaScontrino(null,sagra.getLogo(), fontNormal12, 0,1));
    	
		//TESTATA
		//FIXME CABLATONA TARTUFO
		//printable.addLines(new LineaScontrino(sagra.getNome(),null, fontNormal12, 55,1));
		printable.addLines(new LineaScontrino("Ass. Tartufai Bondeno",null, fontNormal12, 55,1));
		printable.addLines(new LineaScontrino("'Al Ramiol'",null, fontNormal12, 55,1));
		
    	printable.addLines(new LineaScontrino(sagra.getIndirizzo(),null, fontNormal12, 55,1));
    	printable.addLines(new LineaScontrino(sagra.getTestataScontrino(),null, fontNormal12, 55,1));
    	printable.addLines(new LineaScontrino("P.IVA  "+sagra.getPiva(),null, fontNormal12, 55,1));
    	printable.addLines(new LineaScontrino("",null, fontNormal12, 50,10));
    	
    	printable.addLines(new LineaScontrino(dateTimeFormatter.format(ordine.getDataOrdine()),null, fontNormal12, 55,1));
    	printable.addLines(new LineaScontrino("",null, fontNormal12, 1,15));
    	
		if(ordine.isAsporto())
			printable.addLines(new LineaScontrino("ASPORTO - "+ordine.getTavoloAccomodato().getNomeAsporto(),null, fontBold14, 3,1));
		else
			printable.addLines(new LineaScontrino("TAVOLO N. "+ordine.getTavoloAccomodato().getCodice(),null, fontBold14, 3,1));
    	
    	
		boolean soloDolci = true;
    	//PIETANZE
    	for(PietanzaOrdinata pietanzaOrdinata : ordine.getPietanzeOrdinate()){
    		if(!pietanzaOrdinata.getPietanza().getPietanzaCategoria().getDescrizioneBreve().toUpperCase().contains("DOLCI")){
    			soloDolci = false;
    		}
    		
    		if(pietanzaOrdinata.getPietanza().getNome().length()>20)
    			printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome().substring(0, 20)+"..",null, fontNormal12, 1,0));
    		else
    			printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome(),null, fontNormal12, 1,0));
    		printable.addLines(new LineaScontrino(pietanzaOrdinata.getQuantita()+" x "+pietanzaOrdinata.getPietanza().getPrezzo(),null, fontNormal12, 120,0));
    		printable.addLines(new LineaScontrino(pietanzaOrdinata.getQuantita()*pietanzaOrdinata.getPietanza().getPrezzo()+"",null, fontNormal12, 2,1));
    		
    	}
    	
    	//TOTALE
    	printable.addLines(new LineaScontrino("",null, fontNormal12, 1,10));
    	printable.addLines(new LineaScontrino("TOTALE: "+ordine.getTotale() ,null, fontBold14, 1,1));
    	printable.addLines(new LineaScontrino("Quota Persona: "+String.format("%.2f", ordine.getQuotaPersona()) ,null, fontBold14, 1,1));
    	if(ordine.getPersonaOrdine()!=null)
    		printable.addLines(new LineaScontrino("Operatore: "+ordine.getPersonaOrdine() ,null, fontNormal12, 1,1));
    	printable.addLines(new LineaScontrino(sagra.getFooterScontrino() ,null, fontNormal12, 20,1));
    	

        
    	String nomeStampante = null;
    	if(soloDolci)
    		nomeStampante = ordine.getPietanzeOrdinate().get(0).getPietanza().getPietanzaCategoria().getNomeStampante();
    	
    	printPrintable(printable,nomeStampante);
		
	}
	
	@Transactional
	public void printCucina(Long ordineID){
		Sagra sagra = sagraService.findAll().get(0);
		Ordine ordine = ordineRepository.findOne(ordineID);
		
		//FIXME CABLATONA TARTUFO
		List<PietanzaCategoria> pietanzeCategorie = pietanzaCategoriaRepository.findAllByOrderByCodiceAsc();
		//for(PietanzaCategoria pietanzaCategoria : pietanzeCategorie){
		//	stampaCategoria(pietanzaCategoria,ordine);
		//}
		
		stampaExtraTartufo(pietanzeCategorie,ordine);
		stampaCategorieTartufo(pietanzeCategorie,ordine);
		
	}
	
	private PietanzaCategoria getPietanzaCategoriaByName(String nomePietanza,List<PietanzaCategoria> pietanzaCategorie){
		for(PietanzaCategoria pietanzaCategoria :  pietanzaCategorie){
			if(pietanzaCategoria.getCodice().equals(nomePietanza))
				return pietanzaCategoria;
		}
		return null;
	}
	
	@Transactional
	private void stampaExtraTartufo(List<PietanzaCategoria> pietanzaCategorie, Ordine ordine){
		Printer printable = new Printer();
		
		printable.addLines(new LineaScontrino("COPERTO e BEVANDE",null, fontBold16, 1,1));
		if(ordine.getPersonaOrdine()!=null)
			printable.addLines(new LineaScontrino("Operatore: "+ordine.getPersonaOrdine() ,null, fontNormal12, 1,1));
		printable.addLines(new LineaScontrino(dateTimeFormatter.format(ordine.getDataOrdine()),null, fontNormal16, 1,1));
		if(ordine.isAsporto())
			printable.addLines(new LineaScontrino("ASPORTO - "+ordine.getTavoloAccomodato().getNomeAsporto(),null, fontBold18, 3,1));
		else
			printable.addLines(new LineaScontrino("TAVOLO N. "+ordine.getTavoloAccomodato().getCodice(),null, fontBold18, 3,1));
		
		
		boolean soloDolci = true;
		int numPietanze = 0;
		
		
		//COPERTO
		PietanzaCategoria pietanzaCategoria = getPietanzaCategoriaByName("01",pietanzaCategorie);
		for(PietanzaOrdinata pietanzaOrdinata : ordine.getPietanzeOrdinate()){
    		if(!pietanzaOrdinata.getPietanza().getPietanzaCategoria().getDescrizioneBreve().toUpperCase().contains("DOLCI")){
    			soloDolci = false;
    		}
			
			if(pietanzaOrdinata.getPietanza().getPietanzaCategoria().equals(pietanzaCategoria)){
				if(pietanzaOrdinata.getPietanza().getNome().length()>20)
					printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome().substring(0, 20)+"... X "+pietanzaOrdinata.getQuantita(),null, fontNormal16, 1,1));
				else
					printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome()+" X "+pietanzaOrdinata.getQuantita(),null, fontNormal16, 1,1));
				numPietanze++;
			}
		}
		
		pietanzaCategoria = getPietanzaCategoriaByName("02",pietanzaCategorie);
		for(PietanzaOrdinata pietanzaOrdinata : ordine.getPietanzeOrdinate()){
    		if(!pietanzaOrdinata.getPietanza().getPietanzaCategoria().getDescrizioneBreve().toUpperCase().contains("DOLCI")){
    			soloDolci = false;
    		}
			
			if(pietanzaOrdinata.getPietanza().getPietanzaCategoria().equals(pietanzaCategoria)){
				if(pietanzaOrdinata.getPietanza().getNome().length()>20)
					printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome().substring(0, 20)+"... X "+pietanzaOrdinata.getQuantita(),null, fontNormal16, 1,1));
				else
					printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome()+" X "+pietanzaOrdinata.getQuantita(),null, fontNormal16, 1,1));
				numPietanze++;
			}
		}
		
		
		printable.addLines(new LineaScontrino("" ,null, fontNormal14, 1,10));
		printable.addLines(new LineaScontrino("_" ,null, fontNormal14, 1,10));

		
		if(numPietanze>0){
			if(soloDolci)
				printPrintable(printable,pietanzaCategoria.getNomeStampante());
			else
				printPrintable(printable,null);
		}
		
		
		
		
	}
	
	@Transactional
	private void stampaCategorieTartufo(List<PietanzaCategoria> pietanzaCategorie, Ordine ordine){
		Printer printable = new Printer();
		

		
		
		boolean soloDolci = true;
		int numPietanze = 0;
		
		if(ordine.getPersonaOrdine()!=null)
			printable.addLines(new LineaScontrino("Operatore: "+ordine.getPersonaOrdine() ,null, fontNormal12, 1,1));
		printable.addLines(new LineaScontrino(dateTimeFormatter.format(ordine.getDataOrdine()),null, fontNormal16, 1,1));
		if(ordine.isAsporto())
			printable.addLines(new LineaScontrino("ASPORTO - "+ordine.getTavoloAccomodato().getNomeAsporto(),null, fontBold18, 3,1));
		else
			printable.addLines(new LineaScontrino("TAVOLO N. "+ordine.getTavoloAccomodato().getCodice(),null, fontBold18, 3,1));
		
		
		for(PietanzaCategoria pietanzaCategoria : pietanzaCategorie){
			if((!pietanzaCategoria.getCodice().equals("01"))&&(!pietanzaCategoria.getCodice().equals("02"))){
				boolean prima = true;
				for(PietanzaOrdinata pietanzaOrdinata : ordine.getPietanzeOrdinate()){
		    		if(!pietanzaOrdinata.getPietanza().getPietanzaCategoria().getDescrizioneBreve().toUpperCase().contains("DOLCI")){
		    			soloDolci = false;
		    		}
					
					if(pietanzaOrdinata.getPietanza().getPietanzaCategoria().equals(pietanzaCategoria)){
						if(prima){
							printable.addLines(new LineaScontrino(pietanzaCategoria.getDescrizioneBreve().toUpperCase(),null, fontBold16, 1,1));	
							prima = false;
						}
						if(pietanzaOrdinata.getPietanza().getNome().length()>20)
							printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome().substring(0, 20)+"... X "+pietanzaOrdinata.getQuantita(),null, fontNormal16, 1,1));
						else
							printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome()+" X "+pietanzaOrdinata.getQuantita(),null, fontNormal16, 1,1));
						numPietanze++;
					}
				}
				printable.addLines(new LineaScontrino("" ,null, fontNormal14, 1,15));
			}
		}
		
		
		printable.addLines(new LineaScontrino("" ,null, fontNormal14, 1,10));
		printable.addLines(new LineaScontrino("_" ,null, fontNormal14, 1,10));

		
		if(numPietanze>0){
			if(soloDolci)
				printPrintable(printable,getPietanzaCategoriaByName("DOLCI",pietanzaCategorie).getNomeStampante());
			else
				printPrintable(printable,null);
		}
		
		
		
		
	}
	
	@Transactional
	private void stampaCategoria(PietanzaCategoria pietanzaCategoria, Ordine ordine){
		Printer printable = new Printer();
		
		printable.addLines(new LineaScontrino(pietanzaCategoria.getDescrizioneBreve().toUpperCase(),null, fontBold16, 1,1));
		if(ordine.getPersonaOrdine()!=null)
			printable.addLines(new LineaScontrino("Operatore: "+ordine.getPersonaOrdine() ,null, fontNormal12, 1,1));
		printable.addLines(new LineaScontrino(dateTimeFormatter.format(ordine.getDataOrdine()),null, fontNormal16, 1,1));
		if(ordine.isAsporto())
			printable.addLines(new LineaScontrino("ASPORTO - "+ordine.getTavoloAccomodato().getNomeAsporto(),null, fontBold18, 3,1));
		else
			printable.addLines(new LineaScontrino("TAVOLO N. "+ordine.getTavoloAccomodato().getCodice(),null, fontBold18, 3,1));
			
		boolean soloDolci = true;
		int numPietanze = 0;
		for(PietanzaOrdinata pietanzaOrdinata : ordine.getPietanzeOrdinate()){
    		if(!pietanzaOrdinata.getPietanza().getPietanzaCategoria().getDescrizioneBreve().toUpperCase().contains("DOLCI")){
    			soloDolci = false;
    		}
			
			if(pietanzaOrdinata.getPietanza().getPietanzaCategoria().equals(pietanzaCategoria)){
				if(pietanzaOrdinata.getPietanza().getNome().length()>20)
					printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome().substring(0, 20)+"... X "+pietanzaOrdinata.getQuantita(),null, fontNormal16, 1,1));
				else
					printable.addLines(new LineaScontrino(pietanzaOrdinata.getPietanza().getNome()+" X "+pietanzaOrdinata.getQuantita(),null, fontNormal16, 1,1));
				numPietanze++;
			}
		}
		printable.addLines(new LineaScontrino("" ,null, fontNormal14, 1,10));
		printable.addLines(new LineaScontrino("_" ,null, fontNormal14, 1,10));

		
		if(numPietanze>0){
			if(soloDolci)
				printPrintable(printable,pietanzaCategoria.getNomeStampante());
			else
				printPrintable(printable,null);
		}
	}
	
	@Transactional
	public void printSerata(Serata serata){
		Sagra sagra = sagraService.findAll().get(0);
		List<TavoloAccomodato> tavoliAccomodati = tavoloAccomodatoRepository.findAllBySerata(serata);
		HashMap<Long,Long> pietanzeQuantità = new HashMap<Long,Long>();
		
		for(TavoloAccomodato tavolo : tavoliAccomodati){
			if(tavolo.getOrdini()!=null){
				for(Ordine ordine : tavolo.getOrdini()){
					calcolaQuantita(ordine,pietanzeQuantità);
				}
			}
		}
		
		stampaQuantita(pietanzeQuantità,sagra,serata);
	}
	
	@Transactional
	private void calcolaQuantita(Ordine ordine, HashMap<Long,Long> pietanzeQuantità){
		for(PietanzaOrdinata pietanzaOrdinata : ordine.getPietanzeOrdinate()){
			Long numQuantita = pietanzeQuantità.get(pietanzaOrdinata.getPietanza().getId());
			if(numQuantita==null)
				numQuantita = new Long(0);
			numQuantita+=pietanzaOrdinata.getQuantita();
			pietanzeQuantità.put(pietanzaOrdinata.getPietanza().getId(), numQuantita);
		}
	}
	
	@Transactional
	private void stampaQuantita(HashMap<Long,Long> pietanzeQuantità,Sagra sagra,Serata serata){
		Printer printable = new Printer();
		
		printable.addLines(new LineaScontrino(null,sagra.getLogo(), fontNormal12, 0,1));
    	
		//TESTATA
		//FIXME CABLATONA TARTUFO
		//printable.addLines(new LineaScontrino(sagra.getNome(),null, fontNormal12, 55,1));
		printable.addLines(new LineaScontrino("Ass. Tartufai Bondeno",null, fontNormal12, 55,1));
		printable.addLines(new LineaScontrino("'Al Ramiol'",null, fontNormal12, 55,1));
		
    	printable.addLines(new LineaScontrino(sagra.getIndirizzo(),null, fontNormal12, 55,1));
    	printable.addLines(new LineaScontrino("P.IVA  "+sagra.getPiva(),null, fontNormal12, 55,1));
    	printable.addLines(new LineaScontrino("",null, fontNormal12, 50,10));
    	
    	printable.addLines(new LineaScontrino(dateTimeFormatter.format(ZonedDateTime.now()),null, fontNormal12, 55,1));
    	printable.addLines(new LineaScontrino("",null, fontNormal12, 1,15));
    	
		
		printable.addLines(new LineaScontrino("TOTALI SERATA "+dateFormatter.format(serata.getData()),null, fontBold14, 3,1));

		float totale = 0;
		List<PietanzaCategoria> pietanzeCategorie = pietanzaCategoriaRepository.findAllByOrderByCodiceAsc();
		for(PietanzaCategoria pietanzaCategoria : pietanzeCategorie){
			for(Pietanza pietanza : pietanzaCategoria.getPietanze()){
				if(pietanzeQuantità.get(pietanza.getId())!=null){
					
		    		if(pietanza.getNome().length()>20)
		    			printable.addLines(new LineaScontrino(pietanza.getNome().substring(0, 20)+"..",null, fontNormal12, 1,0));
		    		else
		    			printable.addLines(new LineaScontrino(pietanza.getNome(),null, fontNormal12, 1,0));
		    		printable.addLines(new LineaScontrino(pietanzeQuantità.get(pietanza.getId())+" x "+pietanza.getPrezzo(),null, fontNormal12, 120,0));
		    		printable.addLines(new LineaScontrino(pietanzeQuantità.get(pietanza.getId())*pietanza.getPrezzo()+"",null, fontNormal12, 2,1));
		    		
					totale = totale + (pietanzeQuantità.get(pietanza.getId())*pietanza.getPrezzo());
				}
			}
		}
		
		printable.addLines(new LineaScontrino("",null, fontNormal12, 1,10));
		printable.addLines(new LineaScontrino("TOTALE: "+totale ,null, fontBold14, 1,1));
		
		printPrintable(printable,null);
		
	}
	

	
	private void printPrintable(Printable printable,String printerName){
		PrinterJob job = PrinterJob.getPrinterJob ();
		PrintService service;
		if((printerName==null)||printerName.isEmpty())
			service = PrintServiceLookup.lookupDefaultPrintService(); //Printer.findPrintService(printer);
		else
			service = Printer.findPrintService(printerName);
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
	
}

