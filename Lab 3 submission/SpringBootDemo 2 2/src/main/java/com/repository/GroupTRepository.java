package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.entity.GroupT;

public interface GroupTRepository extends CrudRepository<GroupT, Long> {


    GroupT findByGroupId(Integer groupid);
}
