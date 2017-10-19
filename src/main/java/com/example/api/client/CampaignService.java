package com.example.api.client;

import com.example.api.client.model.Campaign;
import com.example.api.client.model.EntityA;
import org.springframework.stereotype.Component;

@Component
public class CampaignService {
    public EntityA processEntityA(Campaign campaign) {
        EntityA entityA = campaign.getEntityA();
        entityA.setFieldInt(entityA.getFieldInt()/2);
        return entityA;
    }
}
