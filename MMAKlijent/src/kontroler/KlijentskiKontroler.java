/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domain.Administrator;
import domain.Borac;
import domain.Grad;
import domain.Karta;
import domain.Kategorija;
import domain.Klijent;
import domain.Mec;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sesija.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.util.StatusOdgovora;
import transfer.util.Operacije;

/**
 *
 * @author slavi
 */
public class KlijentskiKontroler {

    private static KlijentskiKontroler instance;

    private KlijentskiKontroler() {
    }

    public static KlijentskiKontroler getInstance() {
        if (instance == null) {
            instance = new KlijentskiKontroler();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) posaljiZahtev(Operacije.LOGIN, administrator);
    }

    public void addBorac(Borac borac) throws Exception {
        posaljiZahtev(Operacije.ADD_BORAC, borac);
    }

    public void addMec(Mec mec) throws Exception {
        posaljiZahtev(Operacije.ADD_MEC, mec);
    }

    public void deleteBorac(Borac borac) throws Exception {
        posaljiZahtev(Operacije.DELETE_BORAC, borac);
    }

    public void deleteMec(Mec mec) throws Exception {
        posaljiZahtev(Operacije.DELETE_MEC, mec);
    }

    public void updateBorac(Borac borac) throws Exception {
        posaljiZahtev(Operacije.UPDATE_BORAC, borac);
    }

    public void updateMec(Mec mec) throws Exception {
        posaljiZahtev(Operacije.UPDATE_MEC, mec);
    }

    public ArrayList<Borac> getAllBorac() throws Exception {
        return (ArrayList<Borac>) posaljiZahtev(Operacije.GET_ALL_BORAC, null);
    }

    public ArrayList<Mec> getAllMec() throws Exception {
        return (ArrayList<Mec>) posaljiZahtev(Operacije.GET_ALL_MEC, null);
    }

    public ArrayList<Klijent> getAllKlijent() throws Exception {
        return (ArrayList<Klijent>) posaljiZahtev(Operacije.GET_ALL_KLIJENT, null);
    }

    public ArrayList<Kategorija> getAllKategorija() throws Exception {
        return (ArrayList<Kategorija>) posaljiZahtev(Operacije.GET_ALL_KATEGORIJA, null);
    }

    public ArrayList<Grad> getAllGrad() throws Exception {
        return (ArrayList<Grad>) posaljiZahtev(Operacije.GET_ALL_GRAD, null);
    }

    public ArrayList<Karta> getAllKarta(Mec mec) throws Exception {
        return (ArrayList<Karta>) posaljiZahtev(Operacije.GET_ALL_KARTA, mec);
    }

    private Object posaljiZahtev(int operacija, Object parametar) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev(operacija, parametar);

        ObjectOutputStream oos = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        oos.writeObject(kz);

        ObjectInputStream ois = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        ServerskiOdgovor so = (ServerskiOdgovor) ois.readObject();

        if (so.getStatusOdgovora().equals(StatusOdgovora.Error)) {
            throw so.getException();
        } else {
            return so.getOdgovor();
        }

    }
}
