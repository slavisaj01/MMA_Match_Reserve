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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SOAddMec extends ApstraktnaSistemskaOperacija {

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Mec)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Mec!");
        }

        Mec mec = (Mec) ado;

        if (mec.getPrviBorac().getBoracID().equals(mec.getDrugiBorac().getBoracID())) {
            throw new Exception("Prvi i drugi borac moraju biti razliciti!");
        }

        if (!mec.getPrviBorac().getKategorija().getNaziv().
                equals(mec.getDrugiBorac().getKategorija().getNaziv())) {
            throw new Exception("Borci moraju biti iste kategorije!");
        }

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

        PreparedStatement ps = DBBroker.getInstance().ubaci(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long mecID = tableKeys.getLong(1);
       
        Mec mec = (Mec) ado;
        mec.setMecID(mecID);
        
        for (Karta karta : mec.getKarte()) {
            karta.setMec(mec);
            DBBroker.getInstance().ubaci(karta);
        }

    }

}
