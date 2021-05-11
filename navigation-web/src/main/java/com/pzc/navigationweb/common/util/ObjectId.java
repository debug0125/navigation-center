package com.pzc.navigationweb.common.util;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ryf
 * @date 5/11/21 6:35 PM
 */
public final class ObjectId implements Comparable<ObjectId>, Serializable {
    public static final String OBJECT_ID_VALIDATION = "^[0-9a-z]{24}$";
    private final int _time = (int)(System.currentTimeMillis() / 1000L);
    private final int _machine;
    private final int _inc;
    private boolean _new;
    private static final int _genmachine;
    private static AtomicInteger _nextInc = new AtomicInteger((new Random()).nextInt());
    private static final long serialVersionUID = -4415279469780082174L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectId.class);

    public ObjectId() {
        this._machine = _genmachine;
        this._inc = _nextInc.getAndIncrement();
        this._new = true;
    }

    public static ObjectId get() {
        return new ObjectId();
    }

    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        } else {
            int len = s.length();
            if (len != 24) {
                return false;
            } else {
                for(int i = 0; i < len; ++i) {
                    char c = s.charAt(i);
                    if ((c < '0' || c > '9') && (c < 'a' || c > 'f') && (c < 'A' || c > 'F')) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    public String toHexString() {
        StringBuilder buf = new StringBuilder(24);
        byte[] var2 = this.toByteArray();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            buf.append(String.format("%02x", b & 255));
        }

        return buf.toString();
    }

    public byte[] toByteArray() {
        byte[] b = new byte[12];
        ByteBuffer bb = ByteBuffer.wrap(b);
        bb.putInt(this._time);
        bb.putInt(this._machine);
        bb.putInt(this._inc);
        return b;
    }

    private int _compareUnsigned(int i, int j) {
        long li = 4294967295L;
        li &= (long)i;
        long lj = 4294967295L;
        lj &= (long)j;
        long diff = li - lj;
        if (diff < -2147483648L) {
            return -2147483648;
        } else {
            return diff > 2147483647L ? 2147483647 : (int)diff;
        }
    }

    public int compareTo(ObjectId id) {
        if (id == null) {
            return -1;
        } else {
            int x = this._compareUnsigned(this._time, id._time);
            if (x != 0) {
                return x;
            } else {
                x = this._compareUnsigned(this._machine, id._machine);
                return x != 0 ? x : this._compareUnsigned(this._inc, id._inc);
            }
        }
    }

    public int getTimestamp() {
        return this._time;
    }

    public Date getDate() {
        return new Date((long)this._time * 1000L);
    }

    public static int getCurrentCounter() {
        return _nextInc.get();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ObjectId that = (ObjectId)o;
            return Objects.equal(-4415279469780082174L, -4415279469780082174L) && Objects.equal(LOGGER, LOGGER) && Objects.equal(this._time, that._time) && Objects.equal(this._machine, that._machine) && Objects.equal(this._inc, that._inc) && Objects.equal(this._new, that._new) && Objects.equal(_nextInc, _nextInc) && Objects.equal(_genmachine, _genmachine);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{-4415279469780082174L, LOGGER, this._time, this._machine, this._inc, this._new, _nextInc, _genmachine});
    }

    @Override
    public String toString() {
        return this.toHexString();
    }

    public static void main(String[] args) {
        System.out.println((new ObjectId()).toHexString());
        System.out.println((new ObjectId()).toHexString());
        System.out.println((new ObjectId()).toHexString());
    }

    static {
        try {
            int machinePiece;
            try {
                StringBuilder sb = new StringBuilder();
                Enumeration e = NetworkInterface.getNetworkInterfaces();

                while(e.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface)e.nextElement();
                    sb.append(ni.toString());
                }

                machinePiece = sb.toString().hashCode() << 16;
            } catch (Throwable var7) {
                LOGGER.error(var7.getMessage(), var7);
                machinePiece = (new Random()).nextInt() << 16;
            }

            LOGGER.info("machine piece post: " + Integer.toHexString(machinePiece));
            int processId = (new Random()).nextInt();

            try {
                processId = ManagementFactory.getRuntimeMXBean().getName().hashCode();
            } catch (Throwable var6) {
            }

            ClassLoader loader = ObjectId.class.getClassLoader();
            int loaderId = loader != null ? System.identityHashCode(loader) : 0;
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toHexString(processId));
            sb.append(Integer.toHexString(loaderId));
            int processPiece = sb.toString().hashCode() & '\uffff';
            LOGGER.info("process piece: " + Integer.toHexString(processPiece));
            _genmachine = machinePiece | processPiece;
            LOGGER.info("machine : " + Integer.toHexString(_genmachine));
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        }
    }
}

