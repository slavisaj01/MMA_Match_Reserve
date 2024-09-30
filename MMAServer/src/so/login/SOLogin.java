/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import domain.Administrator;
import java.util.ArrayList;
import so.ApstraktnaSistemskaOperacija;

/**
 *
 * @author slavi
 */
public class SOLogin extends ApstraktnaSistemskaOperacija {
    
    Administrator ulogovani;

    @Override
    protected void validacija(ApstraktnaDomena ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().vrati(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                return;
            }
        }

        throw new Exception("Ne postoji administrator sa tim kredencijalima.");
        
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }
    
    

}
