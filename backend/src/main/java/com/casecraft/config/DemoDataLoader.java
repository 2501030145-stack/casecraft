package com.casecraft.config;
import com.casecraft.model.*;import com.casecraft.repository.*;import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;
@Configuration public class DemoDataLoader {
 @Bean CommandLineRunner seedDemo(CaseRepository cases,PersonRepository people,EvidenceRepository evidence,ClueRepository clues,StatementRepository statements,TimelineEventRepository events){return args->{if(cases.count()>0)return;CaseFile c=new CaseFile();c.setTitle("The Ashford Station Incident");c.setSummary("A fictional late-night incident at a quiet railway station. Reconstruct the sequence of events from witness accounts and recovered objects.");c.setLocation("Ashford Station · Platform 2");c=cases.save(c);
 Person witness=new Witness(c,"Mara Ellison");witness.setOccupation("Station attendant");witness.setNotes("Reported hearing a door close shortly before 22:30.");people.save(witness);
 Person suspect=new Suspect(c,"Elias Ward");suspect.setOccupation("Night contractor");suspect.setNotes("Access badge recorded at the station that evening.");people.save(suspect);
 Evidence ticket=new PhysicalEvidence(c,"Torn platform ticket","Recovered beside the east entrance. Printed time: 22:14.");ticket.setCategory("PHYSICAL");evidence.save(ticket);
 Evidence log=new DocumentaryEvidence(c,"Access register extract","A badge entry is recorded at 22:19. Verification pending.");log.setCategory("DOCUMENTARY");evidence.save(log);
 Clue a=new Clue();a.setCaseFile(c);a.setTitle("Witness heard a door");a.setDetail("Mara reports a door closing around 22:25.");a.setImportance("HIGH");a.setX(17);a.setY(22);clues.save(a);
 Clue b=new Clue();b.setCaseFile(c);b.setTitle("Badge entry at 22:19");b.setDetail("The access register places a badge at the east entrance.");b.setImportance("MEDIUM");b.setX(57);b.setY(18);clues.save(b);
 Statement s=new Statement();s.setCaseFile(c);s.setSpeaker("Mara Ellison");s.setContent("I heard the east door close at around 22:25. The platform was otherwise quiet.");statements.save(s);
 TimelineEvent ev=new TimelineEvent();ev.setCaseFile(c);ev.setDescription("Access badge recorded at east entrance");ev.setLocation("East entrance");ev.setOccurredAt(java.time.LocalDateTime.now().minusDays(1).withHour(22).withMinute(19));events.save(ev);
 };}
}
