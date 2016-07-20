package pl.pepe67.miastagoogle.Models;

/**
 * Created by Piotr Kozak on 03.04.2016.
 */
public class Geocode {
    private String status;
    private Result[] results;

    public Geocode(){}
    public Geocode(String status, Result[] results) {
        this.status = status;
        this.results = results;
    }

    public String getStatus(){
        return status;
    }
    public String getResults(int component){
        return results[0].getComponent(component);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Result item : results) {
            i++;
            sb.append( i + ": " + item.toString() + "\n");
        }

        return sb.toString();

    }
}
