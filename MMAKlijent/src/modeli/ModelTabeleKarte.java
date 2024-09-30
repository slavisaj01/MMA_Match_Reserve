/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentskiKontroler;
import domain.Karta;
import domain.Klijent;
import domain.Mec;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author slavi
 */
public class ModelTabeleKarte extends AbstractTableModel {

    private ArrayList<Karta> lista;
    private String[] kolone = {"Klijent", "Sediste"};

    public ModelTabeleKarte() {
        lista = new ArrayList<>();
    }

    public ModelTabeleKarte(Mec mec) {
        try {
            lista = KlijentskiKontroler.getInstance().getAllKarta(mec);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleKarte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Karta k = lista.get(row);

        switch (column) {
            case 0:
                return k.getKlijent();
            case 1:
                return k.getSediste();

            default:
                return null;
        }
    }

    public void dodajKartu(Karta k) {
        lista.add(k);
        fireTableDataChanged();
    }

    public boolean postojiSediste(String sediste) {
        for (Karta karta : lista) {
            if (karta.getSediste().equals(sediste)) {
                return true;
            }
        }
        return false;
    }

    public void obrisiKartu(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public ArrayList<Karta> getLista() {
        return lista;
    }

    public boolean postojiKlijent(Klijent klijent) {
        for (Karta karta : lista) {
            if (karta.getKlijent().getKlijentID().equals(klijent.getKlijentID())) {
                return true;
            }
        }
        return false;
    }

}
