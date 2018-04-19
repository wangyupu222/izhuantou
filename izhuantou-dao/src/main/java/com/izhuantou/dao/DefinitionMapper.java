package com.izhuantou.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.Definition;

public interface DefinitionMapper extends Mapper<Definition> {

    List<Definition> queryAll();

}
