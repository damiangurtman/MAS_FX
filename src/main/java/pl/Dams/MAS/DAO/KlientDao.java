package pl.Dams.MAS.DAO;

import com.j256.ormlite.dao.Dao;
import pl.Dams.MAS.Models.AdresZamieszkania;
import pl.Dams.MAS.Models.Klient;

import java.sql.SQLException;

public class KlientDao extends CommonDAO{


    public <T extends Klient, I> void delete(T klient) {
        try {
            Dao<T, I> daoKlient = getDao((Class<T>) klient.getClass());
            Dao<AdresZamieszkania, I> daoAdresZamieszkania = getDao(AdresZamieszkania.class);

            if (klient.getAdresZamieszkania() != null)
                daoAdresZamieszkania.delete(klient.getAdresZamieszkania());

            daoKlient.delete(klient);
            klient = null;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

}
