package com.example.jurgen.petlistdb.model;

/**
 * Created by Jurgen on 4/17/2017.
 */

public class Pet {

    private int id;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String breed;
    private String colour;
    private String distiguishingMarks;
    private int ChipID;
    private String OwnerName;
    private String OwnerAddress;
    private String OwnerPhone;
    private String vetName;
    private String vetAddress;
    private String vetPhone;
    private String comments;
    private int imageUri;

    public Pet(int id, String name, String dateOfBirth, String gender, String breed, String colour, String distiguishingMarks, int chipID, String ownerName, String ownerAddress, String ownerPhone, String vetName, String vetAddress, String vetPhone, String comments, int imageUri) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.breed = breed;
        this.colour = colour;
        this.distiguishingMarks = distiguishingMarks;
        ChipID = chipID;
        OwnerName = ownerName;
        OwnerAddress = ownerAddress;
        OwnerPhone = ownerPhone;
        this.vetName = vetName;
        this.vetAddress = vetAddress;
        this.vetPhone = vetPhone;
        this.comments = comments;
        this.imageUri = imageUri;
    }

    public Pet() {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDistiguishingMarks() {
        return distiguishingMarks;
    }

    public void setDistiguishingMarks(String distiguishingMarks) {
        this.distiguishingMarks = distiguishingMarks;
    }

    public int getChipID() {
        return ChipID;
    }

    public void setChipID(int chipID) {
        ChipID = chipID;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getOwnerAddress() {
        return OwnerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        OwnerAddress = ownerAddress;
    }

    public String getOwnerPhone() {
        return OwnerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        OwnerPhone = ownerPhone;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getVetAddress() {
        return vetAddress;
    }

    public void setVetAddress(String vetAddress) {
        this.vetAddress = vetAddress;
    }

    public String getVetPhone() {
        return vetPhone;
    }

    public void setVetPhone(String vetPhone) {
        this.vetPhone = vetPhone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getImageUri() {
        return imageUri;
    }

    public void setImageUri(int imageUri) {
        this.imageUri = imageUri;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
