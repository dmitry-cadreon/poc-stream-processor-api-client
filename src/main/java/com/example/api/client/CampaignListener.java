package com.example.api.client;

import com.example.api.client.model.Campaign;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CampaignListener {

    @Autowired
    private CampaignService campaignService;

    @Getter
    private Campaign campaign;

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Campaign processCampaign(Campaign campaign) {
        log.info("[x]Processing campaign: " + campaign);
        this.campaign = campaign;
        campaign.setEntityA(campaignService.processEntityA(campaign));
        log.info("[x]Output campaign: " + campaign);
        return campaign;
    }
}
