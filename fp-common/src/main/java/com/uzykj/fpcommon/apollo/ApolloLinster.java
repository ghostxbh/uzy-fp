package com.uzykj.fpcommon.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ghostxbh
 * @date 2020/6/9
 * @description
 */
@Configuration
public class ApolloLinster {
    private static final Logger log = Logger.getLogger(ApolloLinster.class.getName());
    @Value("${apollo.bootstrap.namespaces}")
    private String namespces;

    public ApolloLinster() {
    }

    @PostConstruct
    private void initConfig() {
        try {
            log.info("namespaces: " + this.namespces);
            String[] split = this.namespces.split(",");
            String[] var2 = split;
            int var3 = split.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String namespace = var2[var4];
                Config config = ConfigService.getConfig(namespace);
                ApolloConfigProvider.initConfig(config);
                config.addChangeListener(new ConfigChangeListener() {
                    @Override
                    public void onChange(ConfigChangeEvent changeEvent) {
                        HashMap<String, String> refreshData = new HashMap<>();
                        Iterator var3 = changeEvent.changedKeys().iterator();

                        while(var3.hasNext()) {
                            String key = (String)var3.next();
                            ConfigChange change = changeEvent.getChange(key);
                            ApolloLinster.log.log(Level.INFO, String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
                            refreshData.put(key, change.getNewValue());
                        }

                        ApolloConfigProvider.updateParam(refreshData);
                    }
                });
            }
        } catch (Exception var7) {
            log.log(Level.WARNING, "init Apollo Config error " + var7.getStackTrace().toString());
        }

    }
}
