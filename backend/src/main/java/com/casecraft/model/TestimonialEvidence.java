package com.casecraft.model;
import jakarta.persistence.*;
@Entity @DiscriminatorValue("TESTIMONIAL")
public class TestimonialEvidence extends Evidence { protected TestimonialEvidence(){} public TestimonialEvidence(CaseFile c,String t,String d){super(c,t,d);} @Override public String getEvidenceType(){return "TESTIMONIAL";} }
