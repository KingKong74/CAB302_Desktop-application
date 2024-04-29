package com.example.main_sem_proj.model;

public class UserDetails {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public UserDetails(String email, String firstName, String lastName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Getters and setters for user data
     * @return user input
     */
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public String getFirstName(){ return firstName; }
    public String getLastName() {return lastName;}
//    public void setEmail(String email){ this.email = email; }
//    public void setPassword(String password){ this.password = password; }
//    public void setFirstName(String firstName) { this.firstName = firstName; }
//    public void  setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String toString() {
        return "BankAccount{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password +
                '}';
    }
}
