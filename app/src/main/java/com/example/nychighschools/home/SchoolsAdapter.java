package com.example.nychighschools.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nychighschools.data.SchoolsResponse;
import com.example.nychighschools.databinding.ItemSchoolsBinding;

import java.util.ArrayList;
import java.util.List;

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsAdapter.Holder> {

    private static final String TAG = "RecyclerViewAdapter";
    private final List<SchoolsResponse> schoolsResponses = new ArrayList<>();
    private static OnSchoolSelected listener;

    public SchoolsAdapter(OnSchoolSelected listener) {
        this.listener = listener;
    }


    //we create an empty list array and store list information here from MainActivity
    // through setData below

    public void setData(List<SchoolsResponse> data){
        schoolsResponses.clear(); //anytime setdata is called the list is cleared
        schoolsResponses.addAll(data); //data passed through as a parameter is stored here
        notifyDataSetChanged(); //notify the user that the data in the recycler view has changed
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new Holder(ItemSchoolsBinding
                .inflate(layoutInflater, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.bind(schoolsResponses.get(position));
        //list data from setData is binded with itemsAlbumsBinding
        //
    }

    @Override
    public int getItemCount() {//gets size of the list, so recycler view knows how much items needs
        // to be recycled
        return schoolsResponses.size(); //returns the size of the list
    }

    public static class Holder extends RecyclerView.ViewHolder{
        private final ItemSchoolsBinding itemSchoolsBinding;
        //all the data fields from the UI are stored here.

        public Holder(ItemSchoolsBinding itemAlbumsBinding) {
            super(itemAlbumsBinding.getRoot());
            this.itemSchoolsBinding = itemAlbumsBinding;
            //constructor allows the UI data itemsAlbumsBiding class to get list data from this class
        }

        void bind(SchoolsResponse schoolsResponse){

            itemSchoolsBinding.setSchools(schoolsResponse);
            //albums parameter is set to itemAlbumsBinding class (UI)
            if(listener != null){
                itemSchoolsBinding.setListener(listener);
            }
            itemSchoolsBinding.executePendingBindings();
            //makes sure the binding is done immediately
        }

    }
}
