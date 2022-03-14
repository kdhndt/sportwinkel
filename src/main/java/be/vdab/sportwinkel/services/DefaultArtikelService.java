package be.vdab.sportwinkel.services;

import be.vdab.sportwinkel.domain.Artikel;
import be.vdab.sportwinkel.events.ArtikelGemaakt;
import be.vdab.sportwinkel.repositories.ArtikelGemaaktRepository;
import be.vdab.sportwinkel.repositories.ArtikelRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultArtikelService implements ArtikelService {
    private final ArtikelRepository artikelRepository;
    private final ArtikelGemaaktRepository artikelGemaaktRepository;
//    private final AmqpTemplate template;

    public DefaultArtikelService(ArtikelRepository artikelRepository, ArtikelGemaaktRepository artikelGemaaktRepository/*, AmqpTemplate template*/) {
        this.artikelRepository = artikelRepository;
        this.artikelGemaaktRepository = artikelGemaaktRepository;
//        this.template = template;
    }

    @Override
    public void create(Artikel artikel) {
        artikelRepository.save(artikel);
        artikelGemaaktRepository.save(new ArtikelGemaakt(artikel));
//        template.convertAndSend("sportartikels", null, new ArtikelGemaakt(artikel));
    }
}
