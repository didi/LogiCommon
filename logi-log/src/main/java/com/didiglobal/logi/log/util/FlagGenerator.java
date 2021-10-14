package com.didiglobal.logi.log.util;

import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangliang
 * @version $Id: FlagGenerator.java, v 0.1 Aug 6, 2014 10:09:27 AM zhangliang
 *          Exp $
 */
public class FlagGenerator {

    //    private static final ILog    LOGGER   = LogFactory.getLog4j(FlagGenerator.class);
    private static AtomicInteger NEXT_INC = new AtomicInteger((new Random()).nextInt());
    private static final int     GEN_MACHINE;
    static {
        try {
            // build a 2-byte machine piece based on NICs info
            int machinePiece;
            {
                try {
                    StringBuilder sb = new StringBuilder();
                    Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
                    while (e.hasMoreElements()) {
                        NetworkInterface ni = e.nextElement();
                        sb.append(ni.toString());
                    }
                    machinePiece = sb.toString().hashCode() << 16;
                } catch (Throwable e) {
                    // exception sometimes happens with IBM JVM, use random
                    //                    LOGGER.warn(e.getMessage());
                    machinePiece = (new Random().nextInt()) << 16;
                }
                //                LOGGER.info("machine piece post: " + Integer.toHexString(machinePiece));
            }

            // add a 2 byte process piece. It must represent not only the JVM
            // but the class loader.
            // Since static var belong to class loader there could be collisions
            // otherwise
            final int processPiece;
            {
                int processId = new Random().nextInt();
                try {
                    processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode();
                } catch (Throwable t) {
                }

                ClassLoader loader = FlagGenerator.class.getClassLoader();
                int loaderId = loader != null ? System.identityHashCode(loader) : 0;

                StringBuilder sb = new StringBuilder();
                sb.append(Integer.toHexString(processId));
                sb.append(Integer.toHexString(loaderId));
                processPiece = sb.toString().hashCode() & 0xFFFF;
                //                LOGGER.info("process piece: " + Integer.toHexString(processPiece));
            }

            GEN_MACHINE = machinePiece | processPiece;
            //            LOGGER.info("machine : " + Integer.toHexString(_genmachine));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private final int curTime;
    private final int curMachine;
    private final int curInc;

    /**
     * Gets a new object id.
     * 
     * @return the new id
     */
    public static FlagGenerator get() {
        return new FlagGenerator();
    }

    /**
     * Checks if a string could be an <code>ObjectId</code>.
     * 
     * @return whether the string could be an object id
     */
    private static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        final int len = s.length();
        if (len != 24) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'f') {
                continue;
            }
            if (c >= 'A' && c <= 'F') {
                continue;
            }

            return false;
        }

        return true;
    }

    /**
     * Create a new object id.
     */
    private FlagGenerator() {
        curTime = (int) (System.currentTimeMillis() / 1000);
        curMachine = GEN_MACHINE;
        curInc = NEXT_INC.getAndIncrement();
    }

    public String toStringBabble() {
        return babbleToMongod(toStringMongod());
    }

    private String toStringMongod() {
        byte[] b = toByteArray();

        StringBuilder buf = new StringBuilder(24);

        for (int i = 0; i < b.length; i++) {
            int x = b[i] & 0xFF;
            String s = Integer.toHexString(x);
            if (s.length() == 1) {
                buf.append("0");
            }
            buf.append(s);
        }

        return buf.toString();
    }

    private byte[] toByteArray() {
        byte[] b = new byte[12];
        ByteBuffer bb = ByteBuffer.wrap(b);
        bb.putInt(curTime);
        bb.putInt(curMachine);
        bb.putInt(curInc);
        return b;
    }

    private static String _pos(String s, int p) {
        return s.substring(p * 2, (p * 2) + 2);
    }

    private static String babbleToMongod(String b) {
        if (!isValid(b)) {
            throw new IllegalArgumentException("invalid object id: " + b);
        }

        StringBuilder buf = new StringBuilder(24);
        for (int i = 7; i >= 0; i--) {
            buf.append(_pos(b, i));
        }

        for (int i = 11; i >= 8; i--) {
            buf.append(_pos(b, i));
        }

        return buf.toString();
    }

    @Override
    public String toString() {
        return toStringMongod();
    }

}
