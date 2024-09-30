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
public class Grad extends ApstraktnaDomena {
    
    private Long gradID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Grad(Long gradID, String naziv) {
        this.gradID = gradID;
        this.naziv = naziv;
    }

    public Grad() {
    }
    
    @Override
    public String nazivTabele() {
        return " Grad ";
    }

    @Override
    public String alijas() {
        return " g ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<ApstraktnaDomena> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktnaDomena> lista = new ArrayList<>();

        while (rs.next()) {
            Grad g = new Grad(rs.getLong("GradID"),
                    rs.getString("Naziv"));

            lista.add(g);
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
        return " GradID = " + gradID;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getGradID() {
        return gradID;
    }

    public void setGradID(Long gradID) {
        this.gradID = gradID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
