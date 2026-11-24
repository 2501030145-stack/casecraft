package com.casecraft.controller;
import com.casecraft.service.InvestigationService; import com.casecraft.model.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api") public class CaseController { private final InvestigationService service; public CaseController(InvestigationService s){service=s;}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","application","CaseCraft API");}
 @GetMapping("/cases") public List<Map<String,Object>> all(){return service.allCases().stream().map(service::caseDto).toList();}
 @PostMapping("/cases") public Map<String,Object> create(@RequestBody Map<String,Object>b){return service.caseDto(service.createCase(b));}
 @GetMapping("/cases/{id}") public Map<String,Object> detail(@PathVariable UUID id){return service.details(id);}
 @PutMapping("/cases/{id}") public Map<String,Object> update(@PathVariable UUID id,@RequestBody Map<String,Object>b){return service.caseDto(service.updateCase(id,b));}
 @DeleteMapping("/cases/{id}") public Map<String,String> delete(@PathVariable UUID id){service.deleteCase(id);return Map.of("message","Case deleted");}
 @PostMapping("/cases/{id}/people") public Map<String,Object> person(@PathVariable UUID id,@RequestBody Map<String,Object>b){Person p=service.addPerson(id,b);return Map.of("id",p.getId(),"name",p.getName(),"role",p.getRole());}
 @PostMapping("/cases/{id}/evidence") public Map<String,Object> evidence(@PathVariable UUID id,@RequestBody Map<String,Object>b){Evidence e=service.addEvidence(id,b);return Map.of("id",e.getId(),"title",e.getTitle(),"type",e.getEvidenceType());}
 @PatchMapping("/evidence/{id}/verification") public Map<String,Object> verify(@PathVariable UUID id,@RequestBody Map<String,Boolean>b){Evidence e=service.verifyEvidence(id,Boolean.TRUE.equals(b.get("verified")));return Map.of("id",e.getId(),"verified",e.isVerified());}
 @PostMapping("/cases/{id}/clues") public Map<String,Object> clue(@PathVariable UUID id,@RequestBody Map<String,Object>b){Clue c=service.addClue(id,b);return Map.of("id",c.getId(),"title",c.getTitle(),"x",c.getX(),"y",c.getY());}
 @PostMapping("/clues/connect") public Map<String,Object> connect(@RequestBody Map<String,String>b){ClueConnection c=service.connectClues(UUID.fromString(b.get("sourceId")),UUID.fromString(b.get("targetId")),b.get("relationship"));return Map.of("id",c.getId(),"sourceId",c.getSource().getId(),"targetId",c.getTarget().getId(),"relationship",c.getRelationship());}
 @PutMapping("/clues/{id}/position") public Map<String,Object> move(@PathVariable UUID id,@RequestBody Map<String,Object>b){Clue c=service.moveClue(id,b);return Map.of("id",c.getId(),"x",c.getX(),"y",c.getY());}
 @PostMapping("/cases/{id}/statements") public Map<String,Object> statement(@PathVariable UUID id,@RequestBody Map<String,Object>b){Statement s=service.addStatement(id,b);return Map.of("id",s.getId(),"speaker",s.getSpeaker());}
 @PostMapping("/cases/{id}/events") public Map<String,Object> event(@PathVariable UUID id,@RequestBody Map<String,Object>b){TimelineEvent e=service.addEvent(id,b);return Map.of("id",e.getId(),"description",e.getDescription(),"occurredAt",e.getOccurredAt());}
 @DeleteMapping("/people/{id}") public Map<String,String> deletePerson(@PathVariable UUID id){service.deletePerson(id);return Map.of("message","Person deleted");}
 @DeleteMapping("/evidence/{id}") public Map<String,String> deleteEvidence(@PathVariable UUID id){service.deleteEvidence(id);return Map.of("message","Evidence deleted");}
 @DeleteMapping("/clues/{id}") public Map<String,String> deleteClue(@PathVariable UUID id){service.deleteClue(id);return Map.of("message","Clue deleted");}
 @DeleteMapping("/statements/{id}") public Map<String,String> deleteStatement(@PathVariable UUID id){service.deleteStatement(id);return Map.of("message","Statement deleted");}
 @DeleteMapping("/events/{id}") public Map<String,String> deleteEvent(@PathVariable UUID id){service.deleteEvent(id);return Map.of("message","Event deleted");}
 @PostMapping("/cases/{id}/analyze") public Map<String,Object> analyze(@PathVariable UUID id){return service.analyze(id);}
 @GetMapping("/cases/{id}/report") public Map<String,Object> report(@PathVariable UUID id){return service.report(id);}
}
