package com.mvcmasters.ems.repository;

import com.mvcmasters.ems.base.BaseMapper;
import com.mvcmasters.ems.model.TreeModel;
import com.mvcmasters.ems.vo.Module;

import java.util.List;

/**
 * Mapper interface for Module entities.
 * This interface extends BaseMapper and
 * provides additional methods specific to the Module entity.
 */
public interface ModuleMapper extends BaseMapper<Module, Integer> {
    /**
     * Queries and retrieves all modules as a list of TreeModel instances.
     * Each TreeModel instance represents a module
     * in a tree-like hierarchical structure.
     * This is typically used for representing module
     * data in a format suitable for tree-based UI components.
     *
     * @return A list of TreeModel instances representing all available modules.
     */
    List<TreeModel> queryAllModules();
}
