package com.artsoft.scb.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsoft.scb.model.entity.Convocatory;
import com.artsoft.scb.model.entity.Phase;

public interface PhaseRepository extends CrudRepository<Phase, Integer>{

		Phase findById(int id);
		List<Phase> findByConvocatory(Convocatory convocatory);
}
