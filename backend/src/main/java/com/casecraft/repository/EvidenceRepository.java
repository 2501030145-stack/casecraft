package com.casecraft.repository;
import com.casecraft.model.Evidence; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface EvidenceRepository extends JpaRepository<Evidence,UUID> { List<Evidence> findByCaseFile_Id(UUID caseId); boolean existsByCaseFile_IdAndTitleIgnoreCase(UUID caseId,String title); }
