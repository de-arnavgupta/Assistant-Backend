package com.arnavgpt.assistantbackend.dto.friendDto;

import com.arnavgpt.assistantbackend.entities.Contact;

import java.time.LocalDate;

public class UpdateContactCommand {
    private String displayName;
    private String givenName;
    private String familyName;
    private String phone;
    private String email;
    private LocalDate birthday; // optional

    public UpdateContactCommand() {}

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

    public void applyTo(Contact existing) {
        if (displayName != null) existing.setDisplayName(displayName);
        if (givenName != null) existing.setGivenName(givenName);
        if (familyName != null) existing.setFamilyName(familyName);
        if (phone != null) existing.setPhone(phone);
        if (email != null) existing.setEmail(email);
        existing.setBirthday(birthday); // can set or clear
    }
}
