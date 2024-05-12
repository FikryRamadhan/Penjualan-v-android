package com.example.testapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;
import java.util.logging.Handler;


public class ProgramAdapter extends ArrayAdapter<String>
{
    Handler mHandler;
Context context;
String[] images;
String[] programName;
String[] programDescription;
String[] programCount;
ImageButton btnKernjang;

ImageButton btnMin;

ImageButton btnPlus;

ImageButton btnProfile;

EditText textCount;
//ProgramViewHolder holder = null;

    public ProgramAdapter(Context context, String[] programName, String[] images, String[] programDescription, ImageButton btnKernjang, ImageButton btnPlus, ImageButton btnMin, EditText textCount,String[] programCount) {
        super(context, R.layout.single_item,R.id.textView1,programName);
        this.context = context;
        this.images = images;
        this.programName = programName;
        this.programDescription = programDescription;
        this.btnKernjang = btnKernjang;
        this.btnPlus = btnPlus;
        this.btnMin = btnMin;
        this.btnProfile = btnProfile;
        this.textCount = textCount;
        this.programCount = programCount;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View singleItem = convertView;
        ProgramViewHolder holder ;


        if (singleItem == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_item,parent,false);
            holder = new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        }
        else
        {
            holder = (ProgramViewHolder) singleItem.getTag();

        }

        /*
        * For Image Barang
        * */
        holder.programeTitle.setText(programName[position]);
        holder.programDescription.setText(programDescription[position]);
        holder.ProgramText.setText(programCount[position]);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                                .error(R.mipmap.ic_launcher_round);

        Glide.with(getContext()).load(images[position].toString())
                .apply(options).into(holder.itemImage);

        /*
        * Untuk Aksi Btn Keranjang
        * */
        holder.btnKernjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "Data"+ programName[position] +"Telah Dimasukan Keranjang ", Toast.LENGTH_SHORT).show();

            }
        });

        /*
        * Untuk Aksi Btn +
        * */


        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nilaiAwal = holder.textCount.getText().toString();

                int i = Integer.parseInt(nilaiAwal);
                i = i + 1;
                String ihasilnya = String.valueOf(i);
                holder.ProgramText.setText(ihasilnya);
            }
        });


        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nilaiAwal = holder.textCount.getText().toString();
                String ihasilnya ="0";
                int i = Integer.parseInt(nilaiAwal);

                i = i - 1;
                if (i < 0)
                {
                     ihasilnya = String.valueOf(0);
                }
                else
                {
                    ihasilnya = String.valueOf(i);
                }

                holder.ProgramText.setText(ihasilnya);
            }
        });

        return singleItem;
    }


}
