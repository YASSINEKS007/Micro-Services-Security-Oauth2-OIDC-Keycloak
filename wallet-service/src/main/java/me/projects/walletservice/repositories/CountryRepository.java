package me.projects.walletservice.repositories;


import me.projects.walletservice.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findByCountryName(String countryName);
    Country findByIsoCode(String isoCode);
    List<Country> findByContinentId(Long id);
}
