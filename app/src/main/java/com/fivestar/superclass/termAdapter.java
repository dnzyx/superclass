package com.fivestar.superclass;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fivestar.superclass.api.model.term;

import java.util.List;

/**
 * Created by admin on 2018/1/9.
 */

public class termAdapter extends ArrayAdapter<term> {
    private int resourceId;
    private Context context;
    private List<term> list;
    private SharedPreferences sp;

    public termAdapter(Context context, int resource, List<term> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.context = context;
        this.list=objects;
        sp = this.context.getSharedPreferences("config",0);
    }
        @Override
        public View getView(int position, View convertView , ViewGroup parent){
            final term term = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

            TextView textView = (TextView) view.findViewById(R.id.term_year);

            textView.setText(term.getContent());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("term",term.getId());
                    editor.commit();
                }
            });

            return view;
        }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public term getItem(int position) {
        return list.get(position);
    }
}
