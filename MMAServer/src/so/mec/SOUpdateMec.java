/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.mec;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import domain.Karta;
import domain.Mec;
import java.util.Date;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SOUpdateMec extends ApstraktnaSistemskaOperacija {

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Mec)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Mec!");
        }
        
        Mec mec = (Mec) ado;

        if (!mec.getDatumVremeOdrzavanja().after(new Date())) {
            throw new Exception("Datum i vreme mora biti u buducnosti!");
        }

        if (mec.getCenaKarte() < 100 || mec.getCenaKarte() > 5000) {
            throw new Exception("Cena karte mora biti izmedju 100€ i 1000€!");
        }

        if (mec.getKarte().size() < 2) {
            throw new Exception("Mec mora imati barem 2 karte!");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {
        DBBroker.getInstance().izmeni(ado);
        
        Mec mec = (Mec) ado;

        DBBroker.getInstance().obrisi(mec.getKarte().get(0));

        for (Karta karta : mec.getKarte()) {
            DBBroker.getInstance().ubaci(karta);
        }

        
    }

}
