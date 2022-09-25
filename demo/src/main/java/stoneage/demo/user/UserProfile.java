package stoneage.demo.user;

public class UserProfile {
          
    private String username;
    
    private String email;
    
    private String password;
    
    private String createdAt;
    
    private String photoUrl;
    
    private Boolean acntDisabled;
    
    private Boolean emailVerified;
    
    /**
    * No args constructor for use in serialization
    *
    */
    public UserProfile() {
    }
    
    /**
    *
    * @param createdAt
    * @param photoUrl
    * @param emailVerified
    * @param password
    * @param acntDisabled
    * @param email
    * @param username
    */
    public UserProfile(String username, String email, String password, String createdAt, String photoUrl, Boolean acntDisabled, Boolean emailVerified) {
    super();
    this.username = username;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
    this.photoUrl = photoUrl;
    this.acntDisabled = acntDisabled;
    this.emailVerified = emailVerified;
    }
     
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
    
    public String getCreatedAt() {
    return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
    }
    
    public String getPhotoUrl() {
    return photoUrl;
    }
    
    
    public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
    }
    
    public Boolean getAcntDisabled() {
    return acntDisabled;
    }
    
    
    public void setAcntDisabled(Boolean acntDisabled) {
    this.acntDisabled = acntDisabled;
    }
    
    
    public Boolean getEmailVerified() {
    return emailVerified;
    }
    
    
    public void setEmailVerified(Boolean emailVerified) {
    this.emailVerified = emailVerified;
    }
    
    
    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(UserProfile.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
    sb.append("createdAt");
    sb.append('=');
    sb.append(((this.createdAt == null)?"<null>":this.createdAt));
    sb.append(',');
    sb.append("photoUrl");
    sb.append('=');
    sb.append(((this.photoUrl == null)?"<null>":this.photoUrl));
    sb.append(',');
    sb.append("acntDisabled");
    sb.append('=');
    sb.append(((this.acntDisabled == null)?"<null>":this.acntDisabled));
    sb.append(',');
    sb.append("emailVerified");
    sb.append('=');
    sb.append(((this.emailVerified == null)?"<null>":this.emailVerified));
    sb.append(',');
    

    sb.append(',');
    if (sb.charAt((sb.length()- 1)) == ',') {
    sb.setCharAt((sb.length()- 1), ']');
    } else {
    sb.append(']');
    }
    return sb.toString();
    }
    
    }