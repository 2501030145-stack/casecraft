package com.casecraft.model;
import jakarta.persistence.*;
@Entity @DiscriminatorValue("VICTIM")
public class Victim extends Person { protected Victim(){} public Victim(CaseFile c,String n){super(c,n);} @Override public String getRole(){return "VICTIM";} }
