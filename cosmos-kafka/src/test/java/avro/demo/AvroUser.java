package avro.demo;

import org.apache.avro.Schema;

/**
 * Created by thomas on 2018/3/15 15:24.
 */
public class AvroUser {
    private String name;
    private Integer favoriteNumber;
    private String favoriteColor;

    public Schema schema = Schema.create(Schema.Type.RECORD);

    public Schema getSchema() {
        return schema;
    }

    public AvroUser setSchema(Schema schema) {
        this.schema = schema;
        return this;
    }

    public AvroUser(){}
    public AvroUser(String name,Integer favoriteNumber,String favoriteColor){
        this.name = name;
        this.favoriteNumber = favoriteNumber;
        this.favoriteColor  = favoriteColor;
    }

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public AvroUser setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
        return this;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public AvroUser setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
        return this;
    }

    public String getName() {
        return name;
    }

    public AvroUser setName(String name) {
        this.name = name;
        return this;
    }
}
