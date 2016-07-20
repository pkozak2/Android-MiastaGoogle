package pl.pepe67.miastagoogle.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pl.pepe67.miastagoogle.R;

/**
 * Created by Piotr Kozak on 01.04.2016.
 */
public class CityListAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater linflater;
    List<String> data;

    public CityListAdapter(Context context, List<String> data){
        ctx = context;
        this.data = data;
        linflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = linflater.inflate(R.layout.list_item, parent, false);
        }
        if (position % 2 == 0) {
            view.setBackgroundResource(R.drawable.list_primary_color);
        } else {
            view.setBackgroundResource(R.drawable.list_secondary_color);
        }

        ((TextView) view.findViewById(R.id.ListItemMisto)).setText(data.get(position));

        return view;
    }

}
