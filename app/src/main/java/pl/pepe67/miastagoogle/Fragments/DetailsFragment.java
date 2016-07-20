package pl.pepe67.miastagoogle.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pl.pepe67.miastagoogle.Models.City;
import pl.pepe67.miastagoogle.R;

/**
 * Created by Piotr Kozak on 01.04.2016.
 */
public class DetailsFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.details_fragment, container, false);


        Bundle args = getArguments();
        City city = (City) args.getSerializable("DETAILS");
        TextView cityTextView = (TextView) view.findViewById(R.id.CityText);
        TextView communityTextView = (TextView) view.findViewById(R.id.CommunityText);
        TextView countyTextView = (TextView) view.findViewById(R.id.CountyText);
        TextView provinceTextView = (TextView) view.findViewById(R.id.ProvinceText);
        TextView countryTextView = (TextView) view.findViewById(R.id.CountryText);

        cityTextView.setText(city.getCity());
        communityTextView.setText(city.getCommunity());
        countyTextView.setText(city.getCounty());
        provinceTextView.setText(city.getProvince());
        countryTextView.setText(city.getCountry());

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        Button btnBack = (Button) view.findViewById(R.id.ButtonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

}
