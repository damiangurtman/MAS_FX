package pl.Dams.MAS.Models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "KLIENT_NORMALNY")
public class KlientNormalny extends Klient implements IModel{

    @ForeignCollectionField
    private ForeignCollection<ZamowienieDetal> zamowieniaDetal;

    public KlientNormalny() {

    }

    public KlientNormalny(String imie, String nazwisko, AdresZamieszkania adresZamieszkania) {
        super(imie, nazwisko, adresZamieszkania);
    }

    public ForeignCollection<ZamowienieDetal> getZamowieniaDetal() {
        if (zamowieniaDetal == null)
            throw new RuntimeException("Zamowienie detaliczne jest puste!");
        return zamowieniaDetal;
    }

}
