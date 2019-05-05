package com.mrporter.pomangam.common.policy.service;

import com.mrporter.pomangam.common.policy.domain.Policy;
import com.mrporter.pomangam.common.policy.repository.PolicyJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    PolicyJpaRepository policyJpaRepository;

    @Override
    public String getPolicy(String policyName) {
        Policy policy = policyJpaRepository.getByPolicyName(policyName);
        if(policy == null) {
            return null;
        } else {
            return policy.getPolicyContents();
        }
    }
}
