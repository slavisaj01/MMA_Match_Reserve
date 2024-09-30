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
public class SOAddBorac extends ApstraktnaSistemskaOperacija {

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Borac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Borac!");
        }

        Borac b = (Borac) ado;

        if (b.getKilaza() < 50 || b.getKilaza() > 150) {
            throw new Exception("Kilaza mora biti izmedju 50kg i 150kg!");
        }

        if (b.getKategorija().getNaziv().equals("Super featherweight")) {
            if (b.getKilaza() < 54 || b.getKilaza() > 59) {
                throw new Exception("Super featherweight mora biti izmedju 54kg i 59kg!");
            }
        }

        if (b.getKategorija().getNaziv().equals("Middleweight")) {
            if (b.getKilaza() < 66 || b.getKilaza() > 71) {
                throw new Exception("Middleweight mora biti izmedju 66kg i 71kg!");
            }
        }

        if (b.getKategorija().getNaziv().equals("Light heavyweight")) {
            if (b.getKilaza() < 91 || b.getKilaza() > 99) {
                throw new Exception("Light heavyweight mora biti izmedju 91kg i 99kg!");
            }
        }

        if (b.getKategorija().getNaziv().equals("Ultra heavyweight")) {
            if (b.getKilaza() < 99 || b.getKilaza() > 150) {
                throw new Exception("Ultra heavyweight mora biti izmedju 99kg i 150kg!");
            }
        }

    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {
        DBBroker.getInstance().ubaci(ado);
    }

}
