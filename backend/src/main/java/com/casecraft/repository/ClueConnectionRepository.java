package com.casecraft.repository;
import com.casecraft.model.ClueConnection; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ClueConnectionRepository extends JpaRepository<ClueConnection,UUID> { List<ClueConnection> findBySource_CaseFile_IdOrTarget_CaseFile_Id(UUID sourceCase,UUID targetCase); }
