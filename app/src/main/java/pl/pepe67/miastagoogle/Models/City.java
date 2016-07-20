package pl.pepe67.miastagoogle.Models;

import java.io.Serializable;


/**
 * Created by Piotr Kozak on 02.04.2016.
 */
public class City implements Serializable{
    private String city;
    private String community;
    private String county;
    private String province;
    private String country;

    City(){
        city = "";
        community = "";
        county = "";
        province = "";
        country = "";
    }


    public City(String city, String community, String county, String province, String country){
        this.city = city;
        this.community = community;
        this.county = county;
        this.province = province;
        this.country = country;
    }


    public String getCountry() {
        return country;
    }

    private void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    private void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    private void setCounty(String county) {
        this.county = county;
    }

    public String getCommunity() {
        return community;
    }

    private void setCommunity(String community) {
        this.community = community;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String toString(){
        return "City:" + city;
    }
}
