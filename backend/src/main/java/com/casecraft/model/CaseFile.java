package com.casecraft.model;
import jakarta.persistence.*; import java.time.LocalDateTime; import java.util.*;
@Entity @Table(name="case_files")
public class CaseFile {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
 @Column(nullable=false) private String title;
 @Column(length=3000) private String summary;
 private String status="OPEN"; private String location="Fictional location";
 private LocalDateTime createdAt=LocalDateTime.now(); private LocalDateTime updatedAt=LocalDateTime.now();
 @OneToMany(mappedBy="caseFile",cascade=CascadeType.ALL,orphanRemoval=true) private List<Person> people=new ArrayList<>();
 @OneToMany(mappedBy="caseFile",cascade=CascadeType.ALL,orphanRemoval=true) private List<Evidence> evidence=new ArrayList<>();
 @OneToMany(mappedBy="caseFile",cascade=CascadeType.ALL,orphanRemoval=true) private List<Clue> clues=new ArrayList<>();
 @OneToMany(mappedBy="caseFile",cascade=CascadeType.ALL,orphanRemoval=true) private List<Statement> statements=new ArrayList<>();
 @OneToMany(mappedBy="caseFile",cascade=CascadeType.ALL,orphanRemoval=true) private List<TimelineEvent> events=new ArrayList<>();
 public UUID getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;touch();} public String getSummary(){return summary;} public void setSummary(String v){summary=v;touch();} public String getStatus(){return status;} public void setStatus(String v){status=v;touch();} public String getLocation(){return location;} public void setLocation(String v){location=v;touch();} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;} public void touch(){updatedAt=LocalDateTime.now();}
}
