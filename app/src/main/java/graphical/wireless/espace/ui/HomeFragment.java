package graphical.wireless.espace.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import graphical.wireless.espace.DetailsActivity;
import graphical.wireless.espace.MainActivity;
import graphical.wireless.espace.R;
import graphical.wireless.espace.ui.data.PotdData;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View temp = inflater.inflate(R.layout.fragment_home, container, false);
//        Log.i(TAG, "onCreateView: ");
        recyclerView = temp.findViewById(R.id.home_recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(temp.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        mAdapter = new POTDAdapter(((MainActivity)getActivity()).potdDataset);
        recyclerView.setAdapter(mAdapter);

        return temp;
    }

    class POTDAdapter extends RecyclerView.Adapter<POTDAdapter.MyViewHolder> {
        private PotdData[] data;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public CardView cardView;
            public MyViewHolder(CardView v) {
                super(v);
                cardView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public POTDAdapter(PotdData[] ptodData) {
            data = ptodData;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public POTDAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent,
                                                           int viewType) {
            // create a new view
            CardView v = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_potd, parent, false);

            return new MyViewHolder(v);
        }
        // Replace the contents of a view (invoked by the layout manager)
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int position) {
//            // - get element from your dataset at this position
//            // - replace the contents of the view with that element
//            ViewGroup vg = holder.cardView;
//            final int pos = position;
//
////            vg.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
////
////                    intent.putExtra("potdData", data[pos]);
////                    startActivity(intent);
////                }
////            });
//
//            PotdData temp = data[pos];
//
//            ((TextView)vg.findViewById(R.id.potd_title)).setText(temp.getMainText());
//            ((TextView)vg.findViewById(R.id.potd_date)).setText(temp.getDateText());
//
//            ImageView imageView = vg.findViewById(R.id.potd_image);
//            Picasso.get().load(temp.getHdurl()).into(imageView);
//        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            ViewGroup vg = holder.cardView;
            final int pos = position;

            vg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);

                    intent.putExtra("potdData", data[pos]);
                    startActivity(intent);
                }
            });

            PotdData potd = data[pos];

            ((TextView)vg.findViewById(R.id.potd_title)).setText(potd.getMainText());
            ((TextView)vg.findViewById(R.id.potd_date)).setText(potd.getDateText());

            ImageView imageView = vg.findViewById(R.id.potd_image);
            imageView.setImageResource(potd.getImageId());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return data.length;
        }
    }

}


