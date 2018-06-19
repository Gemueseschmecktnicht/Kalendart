package de.kuribo.kalendart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.content.Context;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private ArrayList<Event> eventListe = new ArrayList<Event>();
    private OnDeleteClickListener mListener;
    public interface OnDeleteClickListener {
        void OnEventDelete(int position);
    }

    public void SetOnEventDeleteListener(OnDeleteClickListener listener){
        mListener = listener;
    }

    private Context context;
    public static class EventViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView datum;
        public TextView uhrzeit;
        public TextView beschriebung;
        public ImageView btnDelete;


        public EventViewHolder(View itemView, final OnDeleteClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            datum = itemView.findViewById(R.id.tvDatum);
            uhrzeit = itemView.findViewById(R.id.tvUhrzeit);
            beschriebung = itemView.findViewById(R.id.tvBeschreibung);
            btnDelete = (ImageView) itemView.findViewById(R.id.btnDelete);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.OnEventDelete(position);
                        }
                    }
                }
            });
        }
    }

    public EventAdapter(Context pContext){
        context = pContext;
        load();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event, parent, false);
        EventViewHolder evh = new EventViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event currentEvent = eventListe.get(position);

        holder.name.setText(currentEvent.geteName());
        holder.datum.setText(currentEvent.geteDatum());
        holder.uhrzeit.setText(currentEvent.geteUrhzeit());
        holder.beschriebung.setText(currentEvent.geteBeschreibung());
    }

    @Override
    public int getItemCount() {
        return eventListe.size();
    }

    public void save() {
        ObjectOutputStream outputStream = null;
        File path = context.getFilesDir();
        String file_name = "event_data.txt";
        File tasks_file = new File(path, file_name);
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(tasks_file));

            for ( Event e : eventListe ) {
                outputStream.writeObject(e);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Gespeichert");
    }

    public void load(){
        ObjectInputStream inputStream = null;
        File path = context.getFilesDir();
        String file_name = "event_data.txt";
        File tasks_file = new File(path, file_name);
        try {
            inputStream = new ObjectInputStream(new FileInputStream(tasks_file));
            boolean b = true;
            while (b) {
                Event e = (Event) inputStream.readObject();
                if (e != null) {
                    eventListe.add(e);
                } else {
                    b = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Geladen");
    }

    public void addEvent(String pName, String pDatum, String pUhrzeit, String pBeschreibung){
        eventListe.add(new Event(pName, pDatum, pUhrzeit, pBeschreibung));
    }

    public ArrayList<Event> getEventListe() {
        return eventListe;
    }

    public void setEventListe(ArrayList<Event> eventListe) {
        this.eventListe = eventListe;
    }
}
