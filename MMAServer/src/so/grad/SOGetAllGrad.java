/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.grad;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import domain.Grad;
import java.util.ArrayList;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SOGetAllGrad extends ApstraktnaSistemskaOperacija {

    private ArrayList<Grad> lista;

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Grad)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grad!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {
        ArrayList<ApstraktnaDomena> gradovi = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Grad>) (ArrayList<?>) gradovi;
    }

    public ArrayList<Grad> getLista() {
        return lista;
    }

}
