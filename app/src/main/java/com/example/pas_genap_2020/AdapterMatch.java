package com.example.pas_genap_2020;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class AdapterMatch extends RecyclerView.Adapter<AdapterMatch.DatakuViewHolder> {
    private ArrayList<ModelMatch> dataList;
    private AdapterMatch.Callback callback;
    View viewku;
    int posku;

    interface Callback {
        void onClick(int position);
        void test();
    }


    public AdapterMatch(ArrayList<ModelMatch> dataList, AdapterMatch.Callback callback) {
        this.callback = callback;
        this.dataList = dataList;
        Log.d("makanan", "MahasiswaAdapter: "+dataList.size()+"");
    }

    @Override
    public AdapterMatch.DatakuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_adapter_match, parent, false);
        return new AdapterMatch.DatakuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterMatch.DatakuViewHolder holder, final int position) {
        holder.tvhomename.setText(dataList.get(position).getHome_name());
        holder.tvhomescore.setText(String.valueOf(dataList.get(position).getHome_score()));
        holder.tvawayname.setText(dataList.get(position).getAway_name());
        holder.tvawayscore.setText(String.valueOf(dataList.get(position).getAway_score()));
        holder.tvvenue.setText(dataList.get(position).getVenue());
        holder.tvleague.setText(dataList.get(position).getLeague());


    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DatakuViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView tvhomename, tvhomescore, tvleague, tvvenue, tvdate, tvawayname, tvawayscore;
        CardView card;

        public DatakuViewHolder(View itemView) {
            super(itemView);
            viewku=itemView;
            card = (CardView) itemView.findViewById(R.id.cardku);
            tvhomename = (TextView) itemView.findViewById(R.id.tvhomename);
            tvhomescore = (TextView) itemView.findViewById(R.id.tvhomescore);
            tvawayname = (TextView) itemView.findViewById(R.id.tvawayname);
            tvawayscore = (TextView) itemView.findViewById(R.id.tvawayscore);
            tvvenue = (TextView) itemView.findViewById(R.id.tvvenue);
            tvleague = (TextView) itemView.findViewById(R.id.tvleague);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Edit");
            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete");
            posku=getAdapterPosition();
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

    }

    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case 1:
                    //Do stuff
                    Toast.makeText(viewku.getContext(), ""+posku, Toast.LENGTH_SHORT).show();
                    break;

                case 2:
                    //Do stuff

                    break;
            }
            return true;
        }
    };

}