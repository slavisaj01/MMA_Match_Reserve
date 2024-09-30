/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.karta;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import domain.Karta;
import java.util.ArrayList;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SOGetAllKarta extends ApstraktnaSistemskaOperacija {

    private ArrayList<Karta> lista;

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Karta)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Karta!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {
        ArrayList<ApstraktnaDomena> karte = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Karta>) (ArrayList<?>) karte;
    }

    public ArrayList<Karta> getLista() {
        return lista;
    }

}
