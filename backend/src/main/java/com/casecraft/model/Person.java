package com.casecraft.model;
import jakarta.persistence.*; import java.util.UUID;
@Entity @Table(name="people") @Inheritance(strategy=InheritanceType.SINGLE_TABLE) @DiscriminatorColumn(name="person_type")
public abstract class Person {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="case_id",nullable=false) private CaseFile caseFile;
 @Column(nullable=false) private String name; private String age; private String occupation; @Column(length=1500) private String notes;
 protected Person(){} protected Person(CaseFile c,String n){caseFile=c;name=n;}
 public UUID getId(){return id;} public CaseFile getCaseFile(){return caseFile;} public void setCaseFile(CaseFile c){caseFile=c;} public String getName(){return name;} public void setName(String v){name=v;} public String getAge(){return age;} public void setAge(String v){age=v;} public String getOccupation(){return occupation;} public void setOccupation(String v){occupation=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;} public abstract String getRole();
}
