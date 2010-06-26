package com.done.model;

public class UserRole  implements java.io.Serializable {


    // Fields    

     private UserRoleId id;


    // Constructors

    /** default constructor */
    public UserRole() {
    }

    
    /** full constructor */
    public UserRole(UserRoleId id) {
        this.id = id;
    }

   
    // Property accessors

    public UserRoleId getId() {
        return this.id;
    }
    
    public void setId(UserRoleId id) {
        this.id = id;
    }
   








}