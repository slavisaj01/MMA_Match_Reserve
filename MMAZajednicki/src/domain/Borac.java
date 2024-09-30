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
public class Borac extends ApstraktnaDomena {

    private Long boracID;
    private String ime;
    private String prezime;
    private double kilaza;
    private String opis;
    private Kategorija kategorija;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Kilaza: " + kilaza + ", Kategorija: " + kategorija + ")";
    }

    public Borac(Long boracID, String ime, String prezime, double kilaza, String opis, Kategorija kategorija) {
        this.boracID = boracID;
        this.ime = ime;
        this.prezime = prezime;
        this.kilaza = kilaza;
        this.opis = opis;
        this.kategorija = kategorija;
    }

    public Borac() {
    }

    @Override
    public String nazivTabele() {
        return " Borac ";
    }

    @Override
    public String alijas() {
        return " b ";
    }

    @Override
    public String join() {
        return " JOIN KATEGORIJA K ON (K.KATEGORIJAID = B.KATEGORIJAID) ";
    }

    @Override
    public ArrayList<ApstraktnaDomena> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktnaDomena> lista = new ArrayList<>();

        while (rs.next()) {

            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("Naziv"));

            Borac b = new Borac(rs.getLong("boracID"), rs.getString("ime"),
                    rs.getString("prezime"), rs.getDouble("kilaza"),
                    rs.getString("opis"), k);

            lista.add(b);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, kilaza, opis, KategorijaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + " " + kilaza + ", '" + opis + "', " + kategorija.getKategorijaID();
    }

    @Override
    public String uslov() {
        return " boracID = " + boracID;
    }

    @Override
    public String vrednostiZaUpdate() {
        return " kilaza = " + kilaza + ", opis = '" + opis + "', "
                + "kategorijaID = " + kategorija.getKategorijaID() + " ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getBoracID() {
        return boracID;
    }

    public void setBoracID(Long boracID) {
        this.boracID = boracID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public double getKilaza() {
        return kilaza;
    }

    public void setKilaza(double kilaza) {
        this.kilaza = kilaza;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

}
