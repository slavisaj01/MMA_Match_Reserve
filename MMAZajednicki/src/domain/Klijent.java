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
public class Klijent extends ApstraktnaDomena {

    private Long klijentID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Email: " + email + ")";
    }

    public Klijent(Long klijentID, String ime, String prezime, String email, String telefon) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
    }

    public Klijent() {
    }

    @Override
    public String nazivTabele() {
        return " Klijent ";
    }

    @Override
    public String alijas() {
        return " kl ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<ApstraktnaDomena> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktnaDomena> lista = new ArrayList<>();

        while (rs.next()) {
            Klijent klijent = new Klijent(rs.getLong("klijentID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("email"), rs.getString("telefon"));

            lista.add(klijent);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String uslov() {
        return " klijentID = " + klijentID;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Long klijentID) {
        this.klijentID = klijentID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

}
