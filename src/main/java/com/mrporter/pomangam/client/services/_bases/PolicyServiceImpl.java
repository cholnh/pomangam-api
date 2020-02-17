package com.mrporter.pomangam.client.services._bases;

import com.mrporter.pomangam.client.domains.policy.Policy;
import com.mrporter.pomangam.client.repositories._bases.PolicyJpaRepository;
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
