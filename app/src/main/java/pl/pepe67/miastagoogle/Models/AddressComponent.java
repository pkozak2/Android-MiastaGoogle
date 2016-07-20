package pl.pepe67.miastagoogle.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Piotr Kozak on 03.04.2016.
 */
public class AddressComponent {
    @SerializedName("long_name")
    @Expose
    private String longName;

    AddressComponent(){ }
    AddressComponent(String longName){
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String toString() {

        return longName;
    }

}