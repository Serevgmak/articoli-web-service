package com.xantrix.webapp.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xantrix.webapp.entities.Articoli;
import com.xantrix.webapp.entities.Barcode;
import com.xantrix.webapp.exception.NotFoundException;
import com.xantrix.webapp.service.ArticoliService;
import com.xantrix.webapp.service.BarcodeService;

@RestController
@RequestMapping("/articoli")
public class ArticoliController {

    private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);

    @Autowired
    private BarcodeService barcodeService;

    @Autowired
    private ArticoliService articoliService;

    // ------------------- Ricerca Per Barcode ------------------------------------
	@RequestMapping(value = "/cerca/ean/{barcode}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Articoli> listArtByEan(@PathVariable("barcode") String Barcode)
		throws NotFoundException	 
	{
		logger.info("****** Otteniamo l'articolo con barcode " + Barcode + " *******");

		Articoli articolo;
		Barcode Ean = barcodeService.SelByBarcode(Barcode);

		if (Ean == null)
		{
			String ErrMsg = String.format("Il barcode %s non è stato trovato!", Barcode);

			logger.warn(ErrMsg);

			throw new NotFoundException(ErrMsg);
			//return new ResponseEntity<Articoli>(HttpStatus.NOT_FOUND);
		}
		else
			articolo = Ean.getArticolo();

		return new ResponseEntity<Articoli>(articolo, HttpStatus.OK);
	}

	// ------------------- Ricerca Per Codice ------------------------------------
	@RequestMapping(value = "/cerca/codice/{codart}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Articoli> listArtByCodArt(@PathVariable("codart") String CodArt)  
			throws NotFoundException
	{
		logger.info("****** Otteniamo l'articolo con codice " + CodArt + " *******");

		Articoli articolo = articoliService.SelByCodArt(CodArt);

		if (articolo == null)
		{
			String ErrMsg = String.format("L'articolo con codice %s non è stato trovato!", CodArt);

			logger.warn(ErrMsg);

			throw new NotFoundException(ErrMsg);
		}

		return new ResponseEntity<Articoli>(articolo, HttpStatus.OK);
	}

	// ------------------- Ricerca Per Descrizione ------------------------------------
	@RequestMapping(value = "/cerca/descrizione/{filter}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Articoli>> listArtByDesc(@PathVariable("filter") String Filter)
			throws NotFoundException
	{
		logger.info("****** Otteniamo gli articoli con Descrizione: " + Filter + " *******");

		List<Articoli> articoli = articoliService.SelByDescrizione(Filter + "%");

		if (articoli == null)
		{
			String ErrMsg = String.format("Non è stato trovato alcun articolo avente descrizione %s", Filter);

			logger.warn(ErrMsg);

			throw new NotFoundException(ErrMsg);

		}

		return new ResponseEntity<List<Articoli>>(articoli, HttpStatus.OK);
	}

	// ------------------- Ricerca Per Descrizione Con Paging------------------------------------
	@RequestMapping(value = "/cerca/descrizione/{filter}/{page}/{rows}", method = RequestMethod.GET, 
			produces = "application/json")
	public ResponseEntity<List<Articoli>> listArtByDescPag(@PathVariable("filter") String Filter,
			@PathVariable("page") int PageNum, @PathVariable("rows") int RecXPage) 
					throws NotFoundException
	{
		logger.info("****** Otteniamo gli articoli con Descrizione: " + Filter + " *******");

		RecXPage = (RecXPage < 0) ? 10 : RecXPage;

		List<Articoli> articoli = articoliService.SelByDescrizione(Filter + "%", PageRequest.of(PageNum, RecXPage));

		if (articoli == null)
		{
			String ErrMsg = String.format("Non è stato trovato alcun articolo avente il parametro %s", Filter);

			logger.warn(ErrMsg);

			throw new NotFoundException(ErrMsg);

		}

		return new ResponseEntity<List<Articoli>>(articoli, HttpStatus.OK);
	}
    
}
