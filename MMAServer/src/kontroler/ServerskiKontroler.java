/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domain.Administrator;
import domain.Borac;
import domain.Grad;
import domain.Karta;
import domain.Kategorija;
import domain.Klijent;
import domain.Mec;
import java.util.ArrayList;
import so.borac.SOAddBorac;
import so.borac.SODeleteBorac;
import so.borac.SOGetAllBorac;
import so.borac.SOUpdateBorac;
import so.grad.SOGetAllGrad;
import so.karta.SOGetAllKarta;
import so.kategorija.SOGetAllKategorija;
import so.klijent.SOGetAllKlijent;
import so.login.SOLogin;
import so.mec.SOAddMec;
import so.mec.SODeleteMec;
import so.mec.SOGetAllMec;
import so.mec.SOUpdateMec;

/**
 *
 * @author slavi
 */
public class ServerskiKontroler {

    private static ServerskiKontroler instance;

    private ServerskiKontroler() {
    }

    public static ServerskiKontroler getInstance() {
        if (instance == null) {
            instance = new ServerskiKontroler();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.izvrsiSablon(administrator);
        return so.getUlogovani();
    }

    public void addBorac(Borac borac) throws Exception {
        (new SOAddBorac()).izvrsiSablon(borac);
    }

    public void addMec(Mec mec) throws Exception {
        (new SOAddMec()).izvrsiSablon(mec);
    }

    public void deleteBorac(Borac borac) throws Exception {
        (new SODeleteBorac()).izvrsiSablon(borac);
    }

    public void deleteMec(Mec mec) throws Exception {
        (new SODeleteMec()).izvrsiSablon(mec);
    }

    public void updateBorac(Borac borac) throws Exception {
        (new SOUpdateBorac()).izvrsiSablon(borac);
    }

    public void updateMec(Mec mec) throws Exception {
        (new SOUpdateMec()).izvrsiSablon(mec);
    }

    public ArrayList<Borac> getAllBorac() throws Exception {
        SOGetAllBorac so = new SOGetAllBorac();
        so.izvrsiSablon(new Borac());
        return so.getLista();
    }

    public ArrayList<Mec> getAllMec() throws Exception {
        SOGetAllMec so = new SOGetAllMec();
        so.izvrsiSablon(new Mec());
        return so.getLista();
    }

    public ArrayList<Grad> getAllGrad() throws Exception {
        SOGetAllGrad so = new SOGetAllGrad();
        so.izvrsiSablon(new Grad());
        return so.getLista();
    }

    public ArrayList<Kategorija> getAllKategorija() throws Exception {
        SOGetAllKategorija so = new SOGetAllKategorija();
        so.izvrsiSablon(new Kategorija());
        return so.getLista();
    }

    public ArrayList<Klijent> getAllKlijent() throws Exception {
        SOGetAllKlijent so = new SOGetAllKlijent();
        so.izvrsiSablon(new Klijent());
        return so.getLista();
    }

    public ArrayList<Karta> getAllKarta(Mec mec) throws Exception {
        SOGetAllKarta so = new SOGetAllKarta();
        
        Karta karta = new Karta();
        karta.setMec(mec);
        
        so.izvrsiSablon(karta);
        return so.getLista();
    }

}
