package designPatterns.builder.user;

public class UserBuilder  {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private int phone;
    private String address;

    public UserBuilder addFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public UserBuilder addLasttName(String lastName){
        this.lastName = lastName;
        return this;
    }
    public UserBuilder addAge(int age){
        this.age = age;
        return this;
    }
    public UserBuilder addEmail(String email){
        this.email = email;
        return this;
    }
    public UserBuilder addPhone(int phone){
        this.phone = phone;
        return this;
    }
    public UserBuilder addAddress(String address){
        this.address = address;
        return this;
    }
    public User build(){
        return new User(firstName,lastName,age,email,phone,address);
    }
}
