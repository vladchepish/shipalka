package ru.shipal.test.testproject.objects;

import java.io.File;
import java.util.Objects;

public class Good {

    String name;
    String regularPrice;
    String comparingPrice;
    String code;
    String photo;
    String description;

    public Good setPhoto(File photo){
        this.photo = photo.getPath();
        return this;
    }

    public File getPhoto(){
        return new File(photo);
    }

    public String getName() {
        return name;
    }

    public Good setName(String name) {
        this.name = name;
        return this;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public Good setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    public String getComparingPrice() {
        return comparingPrice;
    }

    public Good setComparingPrice(String comparingPrice) {
        this.comparingPrice = comparingPrice;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Good setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Good setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return Objects.equals(name, good.name) &&
                Objects.equals(regularPrice, good.regularPrice) &&
                Objects.equals(comparingPrice, good.comparingPrice) &&
                Objects.equals(code, good.code) &&
                Objects.equals(photo, good.photo) &&
                Objects.equals(description, good.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, regularPrice, comparingPrice, code, photo, description);
    }
}
