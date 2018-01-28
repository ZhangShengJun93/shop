package cn.wolfcode.shop.cache;

import org.apache.ibatis.cache.CacheException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * created by king on 2018/1/20
 */
public final class SerializeUtil {
    public SerializeUtil() {
    }

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception var4) {
            throw new CacheException(var4);
        }
    }

    public static Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        } else {
            ByteArrayInputStream bais = null;

            try {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            } catch (Exception var3) {
                throw new CacheException(var3);
            }
        }
    }
}