package de.kuribo.kalendart;

public class Event {

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
}
