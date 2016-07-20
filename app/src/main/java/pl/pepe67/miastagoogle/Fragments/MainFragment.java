package pl.pepe67.miastagoogle.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import pl.pepe67.miastagoogle.Adapters.CityListAdapter;
import pl.pepe67.miastagoogle.Connection.ConnectionDetector;

import pl.pepe67.miastagoogle.Models.City;
import pl.pepe67.miastagoogle.Models.Geocode;
import pl.pepe67.miastagoogle.R;

import com.google.gson.Gson;



/**
 * Created by Piotr Kozak on 01.04.2016.
 */
public class MainFragment extends Fragment {
    private List<String> data = new ArrayList<>();
    CityListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.main_fragment, container, false);

        adapter = new CityListAdapter(getActivity().getBaseContext(), data);

        final ListView citiesListView = (ListView) view.findViewById(R.id.CityList);
        citiesListView.setAdapter(adapter);

        final EditText editCityText = (EditText) view.findViewById(R.id.editCityText);
        Button btnAdd = (Button) view.findViewById(R.id.BottonAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (!TextUtils.isEmpty(editCityText.getText())) {
                        data.add(editCityText.getText().toString());
                        editCityText.setText("");
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), R.string.emptyCityName, Toast.LENGTH_SHORT).show();
                    }
                }

        });
        citiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (new ConnectionDetector(view.getContext()).isConnectingToInternet()) {
                    TextView textView = (TextView) view.findViewById(R.id.ListItemMisto);

                    //Toast.makeText(getActivity(), "Klik na pozycji: " + textView.getText(), Toast.LENGTH_SHORT).show();

                    GeocodeDownloader geocodeDownload = new GeocodeDownloader();
                    geocodeDownload.execute(textView.getText().toString());
                } else {
                    Toast.makeText(getActivity(), R.string.noconnection, Toast.LENGTH_LONG).show();
                }
            }
        });

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        


        return view;
    }


    private class GeocodeDownloader extends AsyncTask<String, Void, City> {

        @Override
        protected City doInBackground(String... params) {
            String SERVER_URL = "http://maps.googleapis.com/maps/api/geocode/json?sensor=true&language=PL&address="+URLEncoder.encode(params[0]);
            String response = "";
            try {

                URL url = new URL(SERVER_URL);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String s = "";

                while ((s = br.readLine()) != null) {
                    response += s;
                }
                Gson gson = new Gson();

                Geocode geocode = gson.fromJson(response, Geocode.class);
                String statusString = geocode.getStatus();
                Log.d("Status:", statusString);
                    if (statusString.equals("OK")){
                        Log.d("Tag", geocode.toString());
                        return new City(geocode.getResults(0), geocode.getResults(1), geocode.getResults(2), geocode.getResults(3), geocode.getResults(4));

                    }else {
                        return null;
                    }

            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(City result) {
            if(result != null){

                DetailsFragment detailsFragment = new DetailsFragment();
                Bundle args = new Bundle();
                args.putSerializable("DETAILS", result);
                detailsFragment.setArguments(args);


                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, detailsFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
            else
            {
                Toast.makeText(getActivity(), R.string.notexists, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

