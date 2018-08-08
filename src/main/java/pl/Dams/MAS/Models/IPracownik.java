package pl.Dams.MAS.Models;

import java.util.List;

public interface IPracownik {

    public abstract double getPensja();
    public abstract String getAdresMailowy();
    public abstract List<String> getSzkolenie();
}
