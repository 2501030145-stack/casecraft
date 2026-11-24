package com.casecraft.repository;
import com.casecraft.model.Person; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface PersonRepository extends JpaRepository<Person,UUID> { List<Person> findByCaseFile_Id(UUID caseId); }
