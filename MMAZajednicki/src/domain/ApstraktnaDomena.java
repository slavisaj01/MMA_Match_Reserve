/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author slavi
 */
public abstract class ApstraktnaDomena implements Serializable {

    public abstract String nazivTabele();
    public abstract String alijas();
    public abstract String join();
    public abstract ArrayList<ApstraktnaDomena> vratiListu(ResultSet rs) throws SQLException;
    public abstract String koloneZaInsert();
    public abstract String vrednostiZaInsert();
    public abstract String uslov();
    public abstract String vrednostiZaUpdate();
    public abstract String uslovZaSelect();
    
}
