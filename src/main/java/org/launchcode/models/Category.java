package org.launchcode.models;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(min = 3, max = 15)
    private String Name;

    @OneToMany
    @JoinColumn(name="category_id")
    private List<Cheese> cheeses = new ArrayList<>();


    public Category(){

    }

    public Category(String name){
        Category nCategory = new Category();

        nCategory.setName(name);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
