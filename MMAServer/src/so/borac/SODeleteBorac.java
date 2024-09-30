/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.borac;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import domain.Borac;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SODeleteBorac extends ApstraktnaSistemskaOperacija {

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Borac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Borac!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {
        DBBroker.getInstance().obrisi(ado);
    }

}
