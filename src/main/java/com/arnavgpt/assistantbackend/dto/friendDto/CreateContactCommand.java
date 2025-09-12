package com.arnavgpt.assistantbackend.dto.friendDto;

import com.arnavgpt.assistantbackend.entities.Contact;
import java.time.LocalDate;

public class CreateContactCommand {
    private String displayName;
    private String givenName;
    private String familyName;
    private String phone;
    private String email;
    private LocalDate birthday; // optional

    public CreateContactCommand() {}

    // getters and setters
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getGivenName() { return givenName; }
    public void setGivenName(String givenName) { this.givenName = givenName; }
    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public Contact toEntity() {
        Contact c = new Contact();
        c.setDisplayName(displayName);
        c.setGivenName(givenName);
        c.setFamilyName(familyName);
        c.setPhone(phone);
        c.setEmail(email);
        c.setBirthday(birthday);
        return c;
    }
}
