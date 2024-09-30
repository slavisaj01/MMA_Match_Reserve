/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentskiKontroler;
import domain.Borac;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author slavi
 */
public class ModelTabeleBorci extends AbstractTableModel implements Runnable {

    private ArrayList<Borac> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Kilaza", "Kategorija"};
    private String parametar = "";

    public ModelTabeleBorci() {
        try {
            lista = KlijentskiKontroler.getInstance().getAllBorac();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleBorci.class.getName()).log(Level.SEVERE, null, ex);
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
        Borac b = lista.get(row);

        switch (column) {
            case 0:
                return b.getBoracID();
            case 1:
                return b.getIme();
            case 2:
                return b.getPrezime();
            case 3:
                return b.getKilaza() + "kg";
            case 4:
                return b.getKategorija();

            default:
                return null;
        }
    }

    public Borac getSelectedBorac(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleBorci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = KlijentskiKontroler.getInstance().getAllBorac();
            if (!parametar.equals("")) {
                ArrayList<Borac> novaLista = new ArrayList<>();
                for (Borac b : lista) {
                    if (b.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || b.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(b);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
