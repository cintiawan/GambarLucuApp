package com.example.evin.applucuhahaha;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Evin on 6/3/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Post> postList;
    String kategori;

    RecycleAdapter(List<Post> postList, String kategori){
        this.postList = postList;
        this.kategori = kategori;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
		RecyclerView.ViewHolder vhold = new RecyclerView.ViewHolder(v) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        return vhold;
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView caption = (TextView) holder.itemView.findViewById(R.id.txtCaption);
        TextView likez = (TextView) holder.itemView.findViewById(R.id.txtLikez);
        TextView dislikez = (TextView) holder.itemView.findViewById(R.id.txtDislikez);
        TextView user = (TextView) holder.itemView.findViewById(R.id.txtUser);
        ImageView iv = (ImageView) holder.itemView.findViewById(R.id.imgGambar);
        caption.setText(postList.get(position).getCaption());
        likez.setText(String.valueOf(postList.get(position).getLikez()));
        dislikez.setText(String.valueOf(postList.get(position).getDislikez()));
        user.setText("Posted by: " + String.valueOf(postList.get(position).getId_user()));

        URL url = null;
        try {
            url = new URL("http://103.52.146.34/penir/penir17/img/" + this.kategori + "/" + postList.get(position).getGambar() + ".jpg");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            iv.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
