/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author Alejandro
 */
public class driver {

    private int id;
    private String name;
    private String date_birthday;
    private String type_license;

    public driver() {
    }

    public driver(int id, String name, String date_birthday, String type_license) {
        this.id = id;
        this.name = name;
        this.date_birthday = date_birthday;
        this.type_license = type_license;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_birthday() {
        return date_birthday;
    }

    public void setDate_birthday(String date_birthday) {
        this.date_birthday = date_birthday;
    }

    public String getType_license() {
        return type_license;
    }

    public void setType_license(String type_license) {
        this.type_license = type_license;
    }

}
