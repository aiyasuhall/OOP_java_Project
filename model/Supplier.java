// package model;
// public class Supplier {
//     private int id;
//     private String username;
//     private String email;
//     private String password;
//     private String phone;
//     private String address;
//     public Supplier() {
//     }
//     public Supplier(int id, String username, String email, String password, String phone, String address) {
//         this.id = id;
//         this.username = username;
//         this.email = email;
//         this.password = password;
//         this.phone = phone;
//         this.address = address;
//     }
//     public int getId() {
//         return id;
//     }
//     public void setId(int id) {
//         this.id = id;
//     }
//     public String getUsername() {
//         return username;
//     }
//     public void setUsername(String username) {
//         this.username = username;
//     }
//     public String getEmail() {
//         return email;
//     }
//     public void setEmail(String email) {
//         this.email = email;
//     }
//     public String getPassword() {
//         return password;
//     }
//     public void setPassword(String password) {
//         this.password = password;
//     }
//     public String getPhone() {
//         return phone;
//     }
//     public void setPhone(String phone) {
//         this.phone = phone;
//     }
//     public String getAddress() {
//         return address;
//     }
//     public void setAddress(String address) {
//         this.address = address;
//     }
// }
package model;

public class Supplier extends User {

    public Supplier() {
        super(); //used in subclass to call the constructor of its superclass (parent class)
    }

    public Supplier(int id, String username, String email, String password, String phone, String address) {
        super(id, username, email, password, phone, null, null, address);
    }

    // You can add Supplier-specific methods or fields here if needed
}
