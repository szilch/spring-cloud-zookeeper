package de.szilch.weatherdata.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
@Slf4j
public class LeadershipService {

    @Value("${app.leadership-group}")
    private String leaderGroup;
    private final CuratorFramework zkClient;
    private LeaderLatch latch;

    public LeadershipService(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @PostConstruct
    private void onStart() throws Exception {
        latch = new LeaderLatch(zkClient, leaderGroup);
        latch.start();
        log.info("LeadershipService started!");
    }

    @PreDestroy
    private void onStop() throws IOException {
        latch.close();
        log.info("LeadershipService stopped!");
    }

    public void addListener(LeaderLatchListener listener) {
        this.latch.addListener(listener);
    }

    public void removeListener(LeaderLatchListener listener) {
        this.latch.removeListener(listener);
    }

    public boolean isLeader() {
        return latch.hasLeadership();
    }
}
