/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import kontroler.KlijentskiKontroler;
import domain.Mec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author slavi
 */
public class ModelTabeleMeceva extends AbstractTableModel implements Runnable {

    private ArrayList<Mec> lista;
    private String[] kolone = {"ID", "Prvi borac", "Drugi borac",
        "Grad", "Datum i vreme", "Cena karte"};
    private String parametar = "";

    public ModelTabeleMeceva() {
        try {
            lista = KlijentskiKontroler.getInstance().getAllMec();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleMeceva.class.getName()).log(Level.SEVERE, null, ex);
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
        Mec m = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        switch (column) {
            case 0:
                return m.getMecID();
            case 1:
                return m.getPrviBorac().getIme() + " " + m.getPrviBorac().getPrezime();
            case 2:
                return m.getDrugiBorac().getIme() + " " + m.getDrugiBorac().getPrezime();
            case 3:
                return m.getGrad();
            case 4:
                return sdf.format(m.getDatumVremeOdrzavanja());
            case 5:
                return m.getCenaKarte() + "€";

            default:
                return null;
        }
    }

    public Mec getSelectedMec(int row) {
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
            Logger.getLogger(ModelTabeleMeceva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = KlijentskiKontroler.getInstance().getAllMec();
            if (!parametar.equals("")) {
                ArrayList<Mec> novaLista = new ArrayList<>();
                for (Mec m : lista) {
                    if (m.getPrviBorac().getIme().toLowerCase().contains(parametar.toLowerCase())
                            || m.getPrviBorac().getPrezime().toLowerCase().contains(parametar.toLowerCase())
                            || m.getPrviBorac().getIme().toLowerCase().contains(parametar.toLowerCase())
                            || m.getPrviBorac().getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(m);
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
