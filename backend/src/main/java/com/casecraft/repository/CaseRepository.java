package com.casecraft.repository;
import com.casecraft.model.CaseFile; import org.springframework.data.jpa.repository.JpaRepository; import java.util.UUID;
public interface CaseRepository extends JpaRepository<CaseFile,UUID> {}
