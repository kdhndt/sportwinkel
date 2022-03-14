package be.vdab.sportwinkel.events;

import be.vdab.sportwinkel.domain.Artikel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artikelsgemaakt")
public class ArtikelGemaakt {
    @Id
    private long id;
    private String naam;

    public ArtikelGemaakt(Artikel artikel) {
        this.id = artikel.getId();
        this.naam = artikel.getNaam();
    }

    protected ArtikelGemaakt() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
