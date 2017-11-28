package com.waylau.netty.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by thomas.su on 2017/11/21 21:35.
 */
public class ByteObjConverter {


    public static Object ByteToObject(byte[] bytes) {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        Object obj = null;

        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(bi);
            obj = objectInputStream.readObject();

        } catch (Exception e) {

        } finally {
            try {
                bi.close();
                objectInputStream.close();
            } catch (Exception e) {

            }
        }

        return obj;
    }


    public static byte[] ObjectToByte(Object obj) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bo);
            out.writeObject(obj);
            bytes = bo.toByteArray();

        } catch (Exception e) {

        } finally {
            try {
                bo.close();
                out.close();
            } catch (Exception e) {
            }
        }

        return bytes;
    }
}
