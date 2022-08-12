package com.krypton.citysearchapi.repository;

import com.krypton.citysearchapi.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query(value = "SELECT * FROM cities where name ilike %:cityName% limit :size offset :page", nativeQuery = true)
    public List<City> getAllPaginatedCities(@Param(value = "page") int page, @Param(value = "size") int size, @Param(value = "cityName") String cityName);

    @Query(value = "SELECT count(*) FROM cities where name ilike %:cityName%", nativeQuery = true)
    public int getAllCounts(@Param(value = "cityName") String cityName);
}