package com.pzc.navigationweb.constant;

/**
 * @author ryf
 * @date 5/10/21 3:22 PM
 */
public class FileConstants {

    public static final String RCENTER_UPLOAD_HOST = "RCENTER_UPLOAD_HOST";
    public static final String RCENTER_UPLOAD_PORT = "RCENTER_UPLOAD_PORT";
    public static final String RCENTER_DOWNLOAD_DOMAIN = "RCENTER_DOWNLOAD_DOMAIN";
    public static final String RCENTER_ALI_OSS_REGION = "RCENTER_ALI_OSS_REGION";
    public static final String RCENTER_ALI_OSS_UPLOADHOST = "RCENTER_ALI_OSS_UPLOADHOST";
    public static final String RCENTER_ALI_OSS_DOWNLOADDOMAIN = "RCENTER_ALI_OSS_DOWNLOADDOMAIN";
    public static final String RCENTER_ALI_OSS_ACCESSKEYID = "RCENTER_ALI_OSS_ACCESSKEYID";
    public static final String RCENTER_ALI_OSS_ACCESSKEYSECRET = "RCENTER_ALI_OSS_ACCESSKEYSECRET";
    public static final String RCENTER_ALI_OSS_BUCKETNAME = "RCENTER_ALI_OSS_BUCKETNAME";

    public FileConstants() {
    }

    public static enum FILE_TYPE {
        TYPE_PDF,
        TYPE_JPG,
        TYPE_JPEG,
        TYPE_PNG,
        TYPE_GIF,
        TYPE_OTHER;

        private FILE_TYPE() {
        }
    }
}
