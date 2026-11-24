package com.casecraft.model;
import jakarta.persistence.*;
@Entity @DiscriminatorValue("DIGITAL")
public class DigitalEvidence extends Evidence { protected DigitalEvidence(){} public DigitalEvidence(CaseFile c,String t,String d){super(c,t,d);} @Override public String getEvidenceType(){return "DIGITAL";} }
