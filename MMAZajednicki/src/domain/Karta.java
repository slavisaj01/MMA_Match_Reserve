/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author slavi
 */
public class Karta extends ApstraktnaDomena {

    private Mec mec;
    private Klijent klijent;
    private String sediste;

    public Karta(Mec mec, Klijent klijent, String sediste) {
        this.mec = mec;
        this.klijent = klijent;
        this.sediste = sediste;
    }

    public Karta() {
    }

    @Override
    public String nazivTabele() {
        return " Karta ";
    }

    @Override
    public String alijas() {
        return " kar ";
    }

    @Override
    public String join() {
        return " JOIN KLIJENT KL ON (KL.KLIJENTID = KAR.KLIJENTID) "
                + "JOIN MEC M ON (M.MECID = KAR.MECID) "
                + "JOIN BORAC PRVIBORAC ON (PRVIBORAC.BORACID = M.PRVIBORACID) "
                + "JOIN BORAC DRUGIBORAC ON (DRUGIBORAC.BORACID = M.DRUGIBORACID) "
                + "JOIN KATEGORIJA K ON (K.KATEGORIJAID = PRVIBORAC.KATEGORIJAID) "
                + "JOIN GRAD G ON (G.GRADID = M.GRADID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = M.ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<ApstraktnaDomena> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktnaDomena> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("a.Ime"), rs.getString("a.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Grad g = new Grad(rs.getLong("GradID"),
                    rs.getString("g.Naziv"));

            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("k.Naziv"));

            Borac prviBorac = new Borac(rs.getLong("boracID"), rs.getString("prviBorac.ime"),
                    rs.getString("prviBorac.prezime"), rs.getDouble("prviBorac.kilaza"),
                    rs.getString("prviBorac.opis"), k);

            Borac drugiBorac = new Borac(rs.getLong("drugiBorac.boracID"), rs.getString("drugiBorac.ime"),
                    rs.getString("drugiBorac.prezime"), rs.getDouble("drugiBorac.kilaza"),
                    rs.getString("drugiBorac.opis"), k);

            Mec m = new Mec(rs.getLong("mecID"), rs.getTimestamp("datumVremeOdrzavanja"),
                    rs.getString("m.opis"), rs.getDouble("cenaKarte"),
                    g, prviBorac, drugiBorac, a, null);

            Klijent klijent = new Klijent(rs.getLong("klijentID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("email"), rs.getString("telefon"));

            Karta karta = new Karta(m, klijent, rs.getString("sediste"));

            lista.add(karta);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (mecID, klijentID, sediste) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + mec.getMecID() + ", " + klijent.getKlijentID() + ", "
                + "'" + sediste + "' ";
    }

    @Override
    public String uslov() {
        return " mecID = " + mec.getMecID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE M.MECID = " + mec.getMecID();
    }

    public Mec getMec() {
        return mec;
    }

    public void setMec(Mec mec) {
        this.mec = mec;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public String getSediste() {
        return sediste;
    }

    public void setSediste(String sediste) {
        this.sediste = sediste;
    }

}
