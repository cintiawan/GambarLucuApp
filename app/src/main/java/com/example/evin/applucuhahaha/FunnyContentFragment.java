package com.example.evin.applucuhahaha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FunnyContentFragment extends Fragment {
    public static RecyclerView rv;
    public static RecycleAdapter adapter;

    public static FunnyContentFragment newInstance(ArrayList<Post> posts)  {
        FunnyContentFragment cf = new FunnyContentFragment();
        adapter = new RecycleAdapter(posts, "funny");
        return cf;
    }

    public FunnyContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_content, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recycle);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        return view;
    }

}
