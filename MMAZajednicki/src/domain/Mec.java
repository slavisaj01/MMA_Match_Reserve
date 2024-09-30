/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author slavi
 */
public class Mec extends ApstraktnaDomena {

    private Long mecID;
    private Date datumVremeOdrzavanja;
    private String opis;
    private double cenaKarte;
    private Grad grad;
    private Borac prviBorac;
    private Borac drugiBorac;
    private Administrator administrator;
    private ArrayList<Karta> karte;

    public Mec(Long mecID, Date datumVremeOdrzavanja, String opis, double cenaKarte, Grad grad, Borac prviBorac, Borac drugiBorac, Administrator administrator, ArrayList<Karta> karte) {
        this.mecID = mecID;
        this.datumVremeOdrzavanja = datumVremeOdrzavanja;
        this.opis = opis;
        this.cenaKarte = cenaKarte;
        this.grad = grad;
        this.prviBorac = prviBorac;
        this.drugiBorac = drugiBorac;
        this.administrator = administrator;
        this.karte = karte;
    }

    public Mec() {
    }

    @Override
    public String nazivTabele() {
        return " Mec ";
    }

    @Override
    public String alijas() {
        return " m ";
    }

    @Override
    public String join() {
        return " JOIN BORAC PRVIBORAC ON (PRVIBORAC.BORACID = M.PRVIBORACID) "
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

            lista.add(m);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVremeOdrzavanja, opis, cenaKarte, gradID, prviBoracID, drugiBoracID, "
                + "administratorID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVremeOdrzavanja.getTime()) + "', '" + opis + "', "
                + " " + cenaKarte + ", " + grad.getGradID() + ", " + prviBorac.getBoracID()
                + ", " + drugiBorac.getBoracID() + ", " + administrator.getAdministratorID();
    }

    @Override
    public String uslov() {
        return " mecID = " + mecID;
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumVremeOdrzavanja = '" + new Timestamp(datumVremeOdrzavanja.getTime()) + "', "
                + "opis = '" + opis + "', "
                + "cenaKarte = " + cenaKarte + ", gradID = " + grad.getGradID() + " ";
    }

    @Override
    public String uslovZaSelect() {
        if (prviBorac == null && drugiBorac == null) {
            return "";
        }
        return " WHERE PrviBorac.BoracID = " + prviBorac.getBoracID() + " OR "
                + "DrugiBorac.BoracID = " + drugiBorac.getBoracID();
    }

    public Long getMecID() {
        return mecID;
    }

    public void setMecID(Long mecID) {
        this.mecID = mecID;
    }

    public Date getDatumVremeOdrzavanja() {
        return datumVremeOdrzavanja;
    }

    public void setDatumVremeOdrzavanja(Date datumVremeOdrzavanja) {
        this.datumVremeOdrzavanja = datumVremeOdrzavanja;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCenaKarte() {
        return cenaKarte;
    }

    public void setCenaKarte(double cenaKarte) {
        this.cenaKarte = cenaKarte;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public Borac getPrviBorac() {
        return prviBorac;
    }

    public void setPrviBorac(Borac prviBorac) {
        this.prviBorac = prviBorac;
    }

    public Borac getDrugiBorac() {
        return drugiBorac;
    }

    public void setDrugiBorac(Borac drugiBorac) {
        this.drugiBorac = drugiBorac;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<Karta> getKarte() {
        return karte;
    }

    public void setKarte(ArrayList<Karta> karte) {
        this.karte = karte;
    }

}
