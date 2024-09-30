/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import domain.Klijent;
import java.util.ArrayList;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SOGetAllKlijent extends ApstraktnaSistemskaOperacija {

    private ArrayList<Klijent> lista;

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {
        ArrayList<ApstraktnaDomena> klijenti = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Klijent>) (ArrayList<?>) klijenti;
    }

    public ArrayList<Klijent> getLista() {
        return lista;
    }

}
