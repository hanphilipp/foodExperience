package api.food;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"tag", "schemaOrgTag", "hasRDI", "sub"})
public class Nutrient {

    public String label;
    public double total;
    public double daily;
    public String unit;


}
