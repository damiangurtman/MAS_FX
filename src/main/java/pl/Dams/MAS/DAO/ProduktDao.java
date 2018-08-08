package pl.Dams.MAS.DAO;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.ConnectionSource;
import pl.Dams.MAS.Models.Produkt;

import java.sql.SQLException;
import java.util.List;

public class ProduktDao extends CommonDAO{

    // -- prosta querka
    public List<String[]> queryRaw() throws SQLException {
        GenericRawResults<String[]> where = getDao(Produkt.class).queryRaw("SELECT * FROM PRODUKT WHERE NAZWA = 'komp'");
        return where.getResults();
    }
}
