package com.mrporter.pomangam.common.policy.repository;

import com.mrporter.pomangam.common.policy.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface PolicyJpaRepository extends JpaRepository<Policy, Integer> {
    Policy getByPolicyName(@Param("policyName") String policyName);
}
