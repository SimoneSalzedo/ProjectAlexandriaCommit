package LibraryServices;

import android.hardware.ConsumerIrManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectalexandria.R;

import java.util.ArrayList;

import LibraryServices.ShelfEntry;
import LibraryServices.TwoLineItemViewHolder;

public class ListItemAdapter extends RecyclerView.Adapter<TwoLineItemViewHolder>{

    public ArrayList<ShelfEntry> entrieslist;

    public ListItemAdapter() {
        entrieslist = new ArrayList<>();
    }

    @NonNull
    @Override
    public TwoLineItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        TwoLineItemViewHolder holder = new TwoLineItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TwoLineItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return entrieslist.size();
    }

    public void setEntrieslist(ArrayList<ShelfEntry> entrieslist) {
        this.entrieslist = entrieslist;
        notifyDataSetChanged();
    }
}
