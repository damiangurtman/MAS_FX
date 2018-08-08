package pl.Dams.MAS.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "KLIENT_NIEAKTYWNY")
public class KlientNieaktywny extends Klient implements IModel{

    @DatabaseField(canBeNull = false)
    private Date ostatnieLogowanie;

    public KlientNieaktywny() {

    }

    public KlientNieaktywny(Klient klient, Date ostatnieLogowanie) {
        super(klient.getImie(), klient.getNazwisko(), klient.getAdresZamieszkania());
        setOstatnieLogowanie(ostatnieLogowanie);
    }

    public Date getOstatnieLogowanie() {
        return ostatnieLogowanie;
    }

    public void setOstatnieLogowanie(Date ostatnieLogowanie) {
        if (ostatnieLogowanie != null)
            this.ostatnieLogowanie = ostatnieLogowanie;
    }
}
