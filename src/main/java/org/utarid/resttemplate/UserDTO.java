package org.utarid.resttemplate;


public class UserDTO {

    private boolean admin;

    private String name;
    private int age;

    public UserDTO() {
    }

    public UserDTO(boolean admin, String name, int age) {
        this.admin = admin;
        this.name = name;
        this.age = age;
    }

    public UserDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "isAdmin=" + admin +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}