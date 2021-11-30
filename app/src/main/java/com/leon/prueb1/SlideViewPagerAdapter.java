package com.leon.prueb1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideViewPagerAdapter extends PagerAdapter {

    Context ctx;

    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen, container, false);

        ImageView fondoimg = view.findViewById(R.id.fondoimg);
        ImageView ind1 = view.findViewById(R.id.ind1);
        ImageView ind2 = view.findViewById(R.id.ind2);
        ImageView ind3 = view.findViewById(R.id.ind3);

        TextView titulo = view.findViewById(R.id.titulo);
        TextView textintro = view.findViewById(R.id.textintro);
        Button btnskip = view.findViewById(R.id.btnskip);

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(i);
            }
        });

        switch (position){
            case 0:
                fondoimg.setImageResource(R.drawable.introscreen1);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);
                titulo.setText("Calcula tu riesgo COVID");
                textintro.setText("Risk Covid te permite obtener un estimado de probabilidad de contagio de COVID dependiendo de tus datos y ubicación.");
                break;
            case 1:
                fondoimg.setImageResource(R.drawable.introscreen2);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);
                titulo.setText("Genera un punto COVID");
                textintro.setText("Genera o consulta puntos geolocalizados sobre sospechas propias de COVID o en zonas cercanas.");
                break;
            case 2:
                fondoimg.setImageResource(R.drawable.introscreen3);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);
                titulo.setText("Regístrate e inicia sesión\n");
                textintro.setText("Puedes llevar un control sobre el riesgo de contagio en tu localidad y en base a tus datos registrándote en Risk Covid.");
                break;

        }

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
