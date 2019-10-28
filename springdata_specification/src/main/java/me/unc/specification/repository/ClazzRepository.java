package me.unc.specification.repository;

import me.unc.specification.bean.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//同时实现JpaRepository接口和JpaSpecificationExecutor接口
public interface ClazzRepository extends JpaRepository<Clazz, Integer>, JpaSpecificationExecutor<Clazz> {
}
