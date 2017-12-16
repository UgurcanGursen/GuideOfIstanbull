package com.ugurcangursen.guideofistanbul;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 291 on 15.12.2017.
 */

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {

    private Context ctx;
    private ArrayList<Guide> guidelist;

    public GuideAdapter(Context ctx, ArrayList<Guide> guidelist) {
        this.ctx = ctx;
        this.guidelist = guidelist;
    }

    @Override
    public GuideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuideAdapter.ViewHolder holder, int position) {

        Guide guides = guidelist.get(position);
        holder.post_baslik.setText(guides.getBaslik());
        holder.post_aciklama.setText(guides.getAciklama());
        holder.post_adres.setText(guides.getAdres());

        byte [] postimg = guides.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(postimg,0,postimg.length);
        holder.post_img.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return guidelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView post_img;
        public TextView post_baslik;
        public TextView post_aciklama;
        public TextView post_adres;

        public ViewHolder(View itemView) {
            super(itemView);

            post_img = (ImageView) itemView.findViewById(R.id.post_image);
            post_baslik = (TextView) itemView.findViewById(R.id.post_baslik);
            post_aciklama = (TextView) itemView.findViewById(R.id.post_aciklama);
            post_adres = (TextView) itemView.findViewById(R.id.post_adres);
        }
    }
}
