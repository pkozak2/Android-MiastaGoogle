package pl.pepe67.miastagoogle.Models;

/**
 * Created by Piotr Kozak on 03.04.2016.
 */
public class Result {

    private AddressComponent[] address_components;

    public Result(AddressComponent[] address_components) {

        this.address_components = address_components;
    }

    public String getComponent(int component){
        return address_components[component].toString();
    }
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (AddressComponent item : address_components) {
            i++;
            sb.append("Adres Component :" + i + ": " + item.toString() + "\n");
        }

        return sb.toString();
    }

}