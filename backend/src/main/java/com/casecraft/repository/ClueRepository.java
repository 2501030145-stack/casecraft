package com.casecraft.repository;
import com.casecraft.model.Clue; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ClueRepository extends JpaRepository<Clue,UUID> { List<Clue> findByCaseFile_Id(UUID caseId); }
