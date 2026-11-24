package com.casecraft.model;
import jakarta.persistence.*;
@Entity @DiscriminatorValue("DETECTIVE")
public class Detective extends Person { protected Detective(){} public Detective(CaseFile c,String n){super(c,n);} @Override public String getRole(){return "DETECTIVE";} }
