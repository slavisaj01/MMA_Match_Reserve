/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import kontroler.ServerskiKontroler;
import domain.Administrator;
import domain.Borac;
import domain.Mec;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.util.StatusOdgovora;
import transfer.util.Operacije;

/**
 *
 * @author slavi
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket socket;

    ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev kz = (KlijentskiZahtev) ois.readObject();
                ServerskiOdgovor so = obradiZahtev(kz);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(so);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor obradiZahtev(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor(null, null, StatusOdgovora.Success);
        try {
            switch (kz.getOperacija()) {
                case Operacije.ADD_BORAC:
                    ServerskiKontroler.getInstance().addBorac((Borac) kz.getParametar());
                    break;
                case Operacije.ADD_MEC:
                    ServerskiKontroler.getInstance().addMec((Mec) kz.getParametar());
                    break;
                case Operacije.DELETE_BORAC:
                    ServerskiKontroler.getInstance().deleteBorac((Borac) kz.getParametar());
                    break;
                case Operacije.DELETE_MEC:
                    ServerskiKontroler.getInstance().deleteMec((Mec) kz.getParametar());
                    break;
                case Operacije.UPDATE_BORAC:
                    ServerskiKontroler.getInstance().updateBorac((Borac) kz.getParametar());
                    break;
                case Operacije.UPDATE_MEC:
                    ServerskiKontroler.getInstance().updateMec((Mec) kz.getParametar());
                    break;
                case Operacije.GET_ALL_BORAC:
                    so.setOdgovor(ServerskiKontroler.getInstance().getAllBorac());
                    break;
                case Operacije.GET_ALL_GRAD:
                    so.setOdgovor(ServerskiKontroler.getInstance().getAllGrad());
                    break;
                case Operacije.GET_ALL_KARTA:
                    so.setOdgovor(ServerskiKontroler.getInstance().getAllKarta((Mec) kz.getParametar()));
                    break;
                case Operacije.GET_ALL_KATEGORIJA:
                    so.setOdgovor(ServerskiKontroler.getInstance().getAllKategorija());
                    break;
                case Operacije.GET_ALL_KLIJENT:
                    so.setOdgovor(ServerskiKontroler.getInstance().getAllKlijent());
                    break;
                case Operacije.GET_ALL_MEC:
                    so.setOdgovor(ServerskiKontroler.getInstance().getAllMec());
                    break;
                case Operacije.LOGIN:
                    Administrator administrator = (Administrator) kz.getParametar();
                    Administrator ulogovani = ServerskiKontroler.getInstance().login(administrator);
                    so.setOdgovor(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            so.setStatusOdgovora(StatusOdgovora.Error);
            so.setException(ex);
        }
        return so;
    }

}
