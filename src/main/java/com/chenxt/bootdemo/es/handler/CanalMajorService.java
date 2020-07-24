package com.chenxt.bootdemo.es.handler;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.chenxt.bootdemo.es.common.CdcMessage;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * canal service
 *
 * @author chenxt
 * @date 2020/07/23
 */
@Slf4j
public class CanalMajorService extends AbstractCanalClient {

    String destination;
    String username;
    String password;
    String ip;
    Integer port;
    String zkServers;
    CdcAction cdcAction;

    public CanalMajorService(String destination, String username, String password, String ip, Integer port, String zkServers, CdcAction cdcAction) {
        this.destination = destination;
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
        this.zkServers = zkServers;
        this.cdcAction = cdcAction;
    }

    @PostConstruct
    public void init() {
        // 根据ip，直接创建链接，无HA的功能
        this.setDestination(destination);
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(ip, port),
                destination,
                username,
                password);

        this.setConnector(connector);
        this.start();
        log.info("## canal consumer started at {}, listen server at {}", AddressUtils.getHostIp(), zkServers);
    }

    @PreDestroy
    public void destroy() {
        try {
            log.info("## stop the canal client");
            this.stop();
        } catch (Throwable e) {
            log.warn("##something goes wrong when stopping canal:", e);
        } finally {
            log.info("## canal client is down.");
        }
    }

    @Override
    public void post(CdcMessage message) {
        cdcAction.action(message);
    }
}
