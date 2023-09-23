package ch.ffhs.bude4u;

import lombok.Getter;

@Getter
public class Inserat {

    private String name;
    private String beschreibung;
    private String preis;
    private String kategorie;
    private String bild;
    private String datum;
    private String status;
    private String id;
    private String user;

    public Inserat(String name, String beschreibung, String preis, String kategorie, String bild, String datum, String status, String id, String user) {

        this.name = name;
        this.beschreibung = beschreibung;
        this.preis = preis;
        this.kategorie = kategorie;
        this.bild = bild;
        this.datum = datum;
        this.status = status;
        this.id = id;
        this.user = user;
    }

}
