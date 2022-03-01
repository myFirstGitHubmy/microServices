package com.repositories;

import com.domain.ListNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ListNotesRepo extends JpaRepository<ListNotes, Long> {
}
