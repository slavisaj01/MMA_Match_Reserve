/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.borac;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import domain.Borac;
import java.util.ArrayList;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SOGetAllBorac extends ApstraktnaSistemskaOperacija {

    private ArrayList<Borac> lista;

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Borac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Borac!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {
        ArrayList<ApstraktnaDomena> borci = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Borac>) (ArrayList<?>) borci;
    }

    public ArrayList<Borac> getLista() {
        return lista;
    }

}
