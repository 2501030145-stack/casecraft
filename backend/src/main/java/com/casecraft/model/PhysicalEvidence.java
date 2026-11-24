package com.casecraft.model;
import jakarta.persistence.*;
@Entity @DiscriminatorValue("PHYSICAL")
public class PhysicalEvidence extends Evidence { protected PhysicalEvidence(){} public PhysicalEvidence(CaseFile c,String t,String d){super(c,t,d);} @Override public String getEvidenceType(){return "PHYSICAL";} }
