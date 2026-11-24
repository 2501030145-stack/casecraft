package com.casecraft.model;
import jakarta.persistence.*;
@Entity @DiscriminatorValue("DOCUMENTARY")
public class DocumentaryEvidence extends Evidence { protected DocumentaryEvidence(){} public DocumentaryEvidence(CaseFile c,String t,String d){super(c,t,d);} @Override public String getEvidenceType(){return "DOCUMENTARY";} }
