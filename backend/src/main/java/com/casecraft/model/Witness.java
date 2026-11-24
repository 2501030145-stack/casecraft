package com.casecraft.model;
import jakarta.persistence.*;
@Entity @DiscriminatorValue("WITNESS")
public class Witness extends Person { protected Witness(){} public Witness(CaseFile c,String n){super(c,n);} @Override public String getRole(){return "WITNESS";} }
