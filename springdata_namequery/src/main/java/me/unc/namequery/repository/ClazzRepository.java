package me.unc.namequery.repository;


import me.unc.namequery.bean.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
}
