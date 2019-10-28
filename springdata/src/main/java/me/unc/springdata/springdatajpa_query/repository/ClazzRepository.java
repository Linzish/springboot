package me.unc.springdata.springdatajpa_query.repository;

import me.unc.springdata.springdatajpa_query.bean.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
}
