package com.example.api.client;

import com.example.api.client.model.Campaign;
import com.example.api.client.model.EntityA;
import com.example.api.client.model.EntityB;
import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.TimeZone;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(workOffline = true, ids = "com.example:rb-stream-source")
public class CampaignListenerTest {

    @Autowired
    private CampaignListener listener;
    @Autowired
    private Sink sink;
    @Autowired
    private StubTrigger stubTrigger;

    private Campaign campaign;

    @Before
    public void setup() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.set(2017, Calendar.OCTOBER, 15, 0,0,0);
        campaign = Campaign.builder()
            .date(calendar.getTime())
            .entityA(EntityA.builder().fieldInt(9999).build()) //new Random().nextInt(10) + 1
            .entityB(EntityB.builder().fieldString("Random String").build())
            .build();
    }

    @Test
    public void test_should_consume_synchronize_campaign_message () throws Throwable{

        Message<Campaign> msg = MessageBuilder.withPayload(campaign).build();

        SubscribableChannel input = sink.input();
        input.send(msg);

        BDDAssertions.then(this.listener.getCampaign().getDate())
            .isEqualTo(campaign.getDate());
    }

    @Test
    public void test_should_consume_synchronize_campaign_message_stub () throws Throwable{

        stubTrigger.trigger("triggerDsp1Campaign");

        BDDAssertions.then(this.listener.getCampaign().getDate())
            .isEqualTo(campaign.getDate());
    }
}
