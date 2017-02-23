package com.example.nickhoulihan.artistsappexample;

public class Artists {
    private int id;
    private String name;
    private String description;

    public Artists(int id, String name, String description ) {
        this.id = id;
        this.name = name;
        this.description = description;

    }

    public Artists (){
        super();
    }

    /*
    public Artists(int id, String name, String description ) {
        this.id = id;
        this.name = name;
        this.description = "Unknown";
    }
*/
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Artists{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
