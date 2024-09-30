/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kategorija;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import domain.Kategorija;
import java.util.ArrayList;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SOGetAllKategorija extends ApstraktnaSistemskaOperacija {

    private ArrayList<Kategorija> lista;

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Kategorija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kategorija!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {
        ArrayList<ApstraktnaDomena> kategorije = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Kategorija>) (ArrayList<?>) kategorije;
    }

    public ArrayList<Kategorija> getLista() {
        return lista;
    }

}
