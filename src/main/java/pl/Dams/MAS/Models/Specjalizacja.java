package pl.Dams.MAS.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import pl.Dams.MAS.DAO.SpecjalizacjaDao;

import java.util.List;

@DatabaseTable(tableName = "SPECJALIZACJA")
public class Specjalizacja implements IModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, unique = true)
    private String nazwa;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Pracownik pracownik;

    //==================================================================================================================

    public Specjalizacja() {
    }

    public Specjalizacja(String nazwa, Pracownik pracownik) {
        setNazwa(nazwa);
        setPracownik(pracownik);
    }

    //==================================================================================================================

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (nazwa == null)
            throw new RuntimeException("Nazwa specjalizacji nie moze byc pusta!");
        // -- OGRANICZENIE UNIQUE i ORDERED
        SpecjalizacjaDao specjalizacjaDao = new SpecjalizacjaDao();
        List<Specjalizacja> list = specjalizacjaDao.queryForAll(Specjalizacja.class);
        if (list != null)
            for (Specjalizacja specjalizacja : list)
                if (specjalizacja.getNazwa().equals(nazwa))
                    throw new RuntimeException(nazwa + " juz istnieje!");
        this.nazwa = nazwa;

    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        if (pracownik != null)
            this.pracownik = pracownik;
        else
            throw new RuntimeException("Pole pracownik nie moze byc puste!");
    }
}
