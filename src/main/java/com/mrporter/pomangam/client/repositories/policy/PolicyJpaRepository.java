package com.mrporter.pomangam.client.repositories.policy;

import com.mrporter.pomangam.client.domains.policy.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface PolicyJpaRepository extends JpaRepository<Policy, Long> {
    Policy findByPolicyNameAndIsActiveIsTrue(@Param("policyName") String policyName);
}
