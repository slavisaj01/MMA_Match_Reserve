/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import dbb.DBBroker;
import domain.ApstraktnaDomena;
import java.sql.SQLException;

/**
 *
 * @author slavi
 */
public abstract class ApstraktnaSistemskaOperacija {
    
    protected abstract void validacija(ApstraktnaDomena ado) throws Exception;
    protected abstract void izvrsiOperaciju(ApstraktnaDomena ado) throws Exception;

    public void izvrsiSablon(ApstraktnaDomena ado) throws Exception {
        try {
            validacija(ado);
            izvrsiOperaciju(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
