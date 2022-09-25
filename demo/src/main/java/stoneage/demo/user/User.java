package stoneage.demo.user;
 
 
public class User {   
    private String username; 
    private String email; 
    private String password; 
    private String createdDate;
    
    public String getUsername() {
    return username;
    }
    
    
    public void setUsername(String username) {
    this.username = username;
    }
    
    
    public String getEmail() {
    return email;
    }
    
    public void setEmail(String email) {
    this.email = email;
    }
    
    
    public String getPassword() {
    return password;
    }
    
    
    public void setPassword(String password) {
    this.password = password;
    }
    
    
    public String getCreatedDate() {
    return createdDate;
    }
    
    
    public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
    }
    
    
    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
    sb.append("username");
    sb.append('=');
    sb.append(((this.username == null)?"<null>":this.username));
    sb.append(',');
    sb.append("email");
    sb.append('=');
    sb.append(((this.email == null)?"<null>":this.email));
    sb.append(',');
    sb.append("password");
    sb.append('=');
    sb.append(((this.password == null)?"<null>":this.password));
    sb.append(',');
    sb.append("createdDate");
    sb.append('=');
    sb.append(((this.createdDate == null)?"<null>":this.createdDate));
    sb.append(',');
    sb.append("additionalProperties");
    sb.append('=');
    if (sb.charAt((sb.length()- 1)) == ',') {
    sb.setCharAt((sb.length()- 1), ']');
    } else {
    sb.append(']');
    }
    return sb.toString();
    }
    
    }