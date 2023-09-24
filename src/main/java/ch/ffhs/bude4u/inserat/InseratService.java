package ch.ffhs.bude4u.inserat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public interface InseratService {
    String name = null;
    String beschreibung = null;
    String preis = null;
    String kategorie = null;
    String bild = null;
    String datum = null;
    String status = null;
    String id = null;
    String user = null;


}
