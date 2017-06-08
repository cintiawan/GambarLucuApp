package com.example.evin.applucuhahaha;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
        TextView user = (TextView) holder.itemView.findViewById(R.id.txtUser);
        ImageView iv = (ImageView) holder.itemView.findViewById(R.id.imgGambar);
        ImageButton like = (ImageButton) holder.itemView.findViewById(R.id.btnLike);
        caption.setText(postList.get(position).getCaption());
        likez.setText(String.valueOf(postList.get(position).getLikez()) + " likez");
        user.setText("Posted by: " + String.valueOf(postList.get(position).getId_user()));

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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

    public Boolean canLogin() throws UnsupportedEncodingException
    {
        BufferedReader reader = null;
        String data = "";
        try {
            URL url = new URL("http://103.52.146.34/penir/penir17/validasi.php?validasi=login");
            URLConnection httpURLConnection = url.openConnection();
            httpURLConnection.setDoOutput(true);
            OutputStreamWriter os = new OutputStreamWriter(httpURLConnection.getOutputStream());
            os.write(data);
            os.flush();

            reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                sb.append(line);
            if(sb.toString().equals("Yes"))
                return true;
            else
                return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {}
        }
    }
}
