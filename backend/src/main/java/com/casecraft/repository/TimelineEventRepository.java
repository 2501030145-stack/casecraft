package com.casecraft.repository;
import com.casecraft.model.TimelineEvent; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface TimelineEventRepository extends JpaRepository<TimelineEvent,UUID> { List<TimelineEvent> findByCaseFile_Id(UUID caseId); }
