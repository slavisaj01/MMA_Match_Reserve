/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.mec;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import domain.Mec;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SODeleteMec extends ApstraktnaSistemskaOperacija {

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Mec)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Mec!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {
        DBBroker.getInstance().obrisi(ado);
    }

}
