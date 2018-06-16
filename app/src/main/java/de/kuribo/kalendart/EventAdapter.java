package de.kuribo.kalendart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private ArrayList<Event> eventListe;

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView datum;
        public TextView uhrzeit;
        public TextView beschriebung;


        public EventViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            datum = itemView.findViewById(R.id.tvDatum);
            uhrzeit = itemView.findViewById(R.id.tvUhrzeit);
            beschriebung = itemView.findViewById(R.id.tvBeschreibung);


        }
    }

    public EventAdapter(ArrayList<Event> pEventListe){
        eventListe = pEventListe;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event, parent, false);
        EventViewHolder evh = new EventViewHolder(v);
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
}
