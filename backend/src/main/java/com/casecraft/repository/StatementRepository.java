package com.casecraft.repository;
import com.casecraft.model.Statement; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface StatementRepository extends JpaRepository<Statement,UUID> { List<Statement> findByCaseFile_Id(UUID caseId); }
