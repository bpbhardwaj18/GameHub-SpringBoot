package com.bp.SpringSecurityEx.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bp.SpringSecurityEx.pojo.GameData;

public interface GameDataRepo extends CrudRepository<GameData, Integer> {
	public List<GameData>  findByCategoryContaining(String category);
}
