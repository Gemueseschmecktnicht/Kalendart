package de.kuribo.kalendart;

import java.io.Serializable;
public class Event implements Serializable{

    private String eName;
    private String eDatum;
    private String eUrhzeit;
    private String eBeschreibung;

    public Event(String pName, String pDatum, String pUhrzeit, String pBeschreibung){
        eName = pName;
        eDatum = pDatum;
        eUrhzeit = pUhrzeit;
        eBeschreibung = pBeschreibung;

    }

    //Getter + Setter
    public String geteName(){
        return eName;
    }

    public String geteDatum(){
        return eDatum;
    }

    public String geteUrhzeit(){
        return eUrhzeit;
    }

    public String geteBeschreibung(){
        return eBeschreibung;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public void seteDatum(String eDatum) {
        this.eDatum = eDatum;
    }

    public void seteUrhzeit(String eUrhzeit) {
        this.eUrhzeit = eUrhzeit;
    }

    public void seteBeschreibung(String eBeschreibung) {
        this.eBeschreibung = eBeschreibung;
    }
}
