package com.pzc.navigationweb.common.util;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by xiezhonggui on 2017/5/16.
 */
public class DockerUtil {
    private static final Logger LOGGER = Logger.getLogger(DockerUtil.class);

    private DockerUtil() {
    }

    public static String ensureDockerIpFromEtcResolveConf() {
        String docker = System.getenv("C_CONTAINER");
        if (StringUtils.isEmpty(docker) || !"docker".equalsIgnoreCase(docker)) {
            return null;
        }

        File file = new File("/etc/resolv.conf");
        if (!file.exists()) {
            return null;
        }

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("nameserver")) {
                    String dockerIp = line.replaceAll("nameserver", "").trim();
                    LOGGER.info("docker容器IP： " + dockerIp);
                    return dockerIp;
                }
            }
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }

            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }
        }
        return null;
    }
}
