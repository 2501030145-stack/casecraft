package com.casecraft.model;
import jakarta.persistence.*;
@Entity @DiscriminatorValue("SUSPECT")
public class Suspect extends Person { protected Suspect(){} public Suspect(CaseFile c,String n){super(c,n);} @Override public String getRole(){return "SUSPECT";} }
