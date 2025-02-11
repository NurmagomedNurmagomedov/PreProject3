package space.nurik.preproject3.task_1.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не может быть пустым")
    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Длина имени не короче 2 симаолов и не длинее 30")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Фамилия не может быть пустой")
    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 30, message = "Длина фамилии не короче 2 симаолов и не длинее 30")
    @Column(name = "sur_name")
    private String surName;

    @Min(value = 0, message = "Возраст не может быть меньше 0")
    @Column(name = "age")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Email не может быть пустым")
    @Email
    private String email;

    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Номер телефона должен содержать только цифры и может начинаться с '+'"
    )
    @Column(name = "phone")
    private String phone;

    @NotEmpty(message = "Адрес не может быть пустым")
    @Column(name = "address")
    private String address;

    public User() {

    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
