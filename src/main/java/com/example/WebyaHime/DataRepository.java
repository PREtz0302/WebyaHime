package com.example.WebyaHime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<OverviewData, Integer> {

	List<OverviewData> findByCategory(String category);
}
