package com.pzc.navigationweb.common.util;

import cn.hutool.core.util.StrUtil;
import com.pzc.navigationweb.constant.ConfigConstant;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Host相关判定，如判定环境
 *
 * @author Xia Yubing on 2018/5/19
 */
public class HostUtil {
    private static final Logger LOGGER = Logger.getLogger(HostUtil.class);
    private static final String OS_NAME = "os.name";
    private static final String WINDOWS = "windows";

    private HostUtil() {
    }

    /**
     * 获取本地IP
     *
     * @return 当前IP
     */
    public static String getLocalIP() {
        if (isWindowsOS()) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                LOGGER.warn("获取Windows本地IP异常，默认使用" + ConfigConstant.LOCAL_HOST);
            }
        } else {
            String dockerIP = DockerUtil.ensureDockerIpFromEtcResolveConf();
            if (StrUtil.isEmpty(dockerIP)) {
                return getLinuxLocalIp();
            } else {
                return dockerIP;
            }
        }
        return ConfigConstant.LOCAL_HOST;
    }

    public static void main(String[] args) {
        System.out.println(getLocalIP());
    }


    private static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty(OS_NAME);
        if (osName.toLowerCase().contains(WINDOWS)) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     */
    private static String getLinuxLocalIp() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();
                String name = networkInterface.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddresses = networkInterface.getInetAddresses(); enumIpAddresses.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipAddress = inetAddress.getHostAddress();
                            if (!ipAddress.contains("::") && !ipAddress.contains("0:0:") && !ipAddress.contains("fe80")) {
                                ip = ipAddress;
                                LOGGER.info("Current IP is: " + ipAddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            ip = ConfigConstant.LOCAL_HOST;
            LOGGER.error("获取Linux本地IP异常，默认使用" + ConfigConstant.LOCAL_HOST, e);
        }
        return ip;
    }
}
