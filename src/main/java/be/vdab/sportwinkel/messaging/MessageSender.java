package be.vdab.sportwinkel.messaging;

import be.vdab.sportwinkel.repositories.ArtikelGemaaktRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageSender {
    private final AmqpTemplate template;
    private final ArtikelGemaaktRepository artikelGemaaktRepository;

    public MessageSender(AmqpTemplate template, ArtikelGemaaktRepository artikelGemaaktRepository) {
        this.template = template;
        this.artikelGemaaktRepository = artikelGemaaktRepository;
    }

    @Scheduled(fixedDelay = 5_000)
    @Transactional
    void verstuurMessages() {
        var artikelsGemaakt = artikelGemaaktRepository.findAll();
        artikelsGemaakt.forEach(
                gemaakt -> template.convertAndSend("sportartikels", null, gemaakt)
        );
        artikelGemaaktRepository.deleteAllInBatch(artikelsGemaakt);
    }
}
