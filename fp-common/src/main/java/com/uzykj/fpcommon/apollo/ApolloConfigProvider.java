package com.uzykj.fpcommon.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ghostxbh
 * @date 2020/6/9
 * @description
 */
public class ApolloConfigProvider {
    private static final Logger log = Logger.getLogger(ApolloConfigProvider.class.getName());
    public static HashMap<String, String> config = new HashMap();

    public ApolloConfigProvider() {
    }

    public static void init() {
        Config config = ConfigService.getAppConfig();
    }

    public static void initConfig(Config apolloConfig) {
        try {
            Set<String> propertyNames = apolloConfig.getPropertyNames();
            Iterator var2 = propertyNames.iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();
                String value = apolloConfig.getProperty(key, (String)null);
                config.put(key, value);
            }
        } catch (Exception var5) {
            log.log(Level.WARNING, var5.getStackTrace().toString());
        }

    }

    public static String getParam(String key) {
        try {
            String value = (String)config.get(key);
            if (StringUtils.isEmpty(value)) {
                log.log(Level.INFO, "Apollo config getParam key: " + key + " ,is empty");
                return null;
            } else {
                return value;
            }
        } catch (Exception var2) {
            log.log(Level.WARNING, var2.getStackTrace().toString());
            return null;
        }
    }

    public static HashMap<String, String> getAllParams() {
        return config;
    }

    protected static void updateParam(HashMap<String, String> updateParam) {
        try {
            Iterator var1 = updateParam.keySet().iterator();

            while(var1.hasNext()) {
                String key = (String)var1.next();
                String value = (String)config.get(key);
                if (!StringUtils.isEmpty(value)) {
                    log.log(Level.INFO, "Apollo config updateParam key: " + key + " , update newValue: " + (String)updateParam.get(key));
                    config.put(key, updateParam.get(key));
                } else {
                    log.log(Level.WARNING, "Apollo config updateParam key: " + key + "is empty");
                }
            }
        } catch (Exception var4) {
            log.log(Level.WARNING, var4.getStackTrace().toString());
        }

    }
}
