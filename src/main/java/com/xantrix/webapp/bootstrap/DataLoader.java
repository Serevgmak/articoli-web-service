package com.xantrix.webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.xantrix.webapp.repository.ArticoliRepository;
import com.xantrix.webapp.repository.BarcodeRepository;


@Component
public class DataLoader implements CommandLineRunner{

    private final ArticoliRepository articoliRepository;
    private final BarcodeRepository barcodeRepository;

    public DataLoader(ArticoliRepository articoliRepository, BarcodeRepository barcodeRepository) {
        this.articoliRepository = articoliRepository;
        this.barcodeRepository = barcodeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
