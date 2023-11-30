package org.example.utils;

import com.alibaba.fastjson2.JSON;
import org.example.Main;

import java.nio.*;

public class BufferUtils {
    IntBuffer intBuffer;
    CharBuffer charBuffer;
    ByteBuffer byteBuffer;
    DoubleBuffer doubleBuffer;
    FloatBuffer floatBuffer;
    LongBuffer longBuffer;
    ShortBuffer shortBuffer;
    public static Integer[] array=new Integer[1000];
    public volatile static int index=0;
    public BufferUtils(Integer capacity,Class obj) {
        String objName = obj.getName();

        switch (objName) {
            case "int","Integer"->intBuffer=IntBuffer.allocate(capacity);
            case "char","Character"->charBuffer=CharBuffer.allocate(capacity);
            case "byte","Byte"->byteBuffer=ByteBuffer.allocate(capacity);
            case "double","Double"->doubleBuffer=DoubleBuffer.allocate(capacity);
            case "float","Float"->floatBuffer=FloatBuffer.allocate(capacity);
            case "long","Long"->longBuffer=LongBuffer.allocate(capacity);
            case "short","Short"->shortBuffer=ShortBuffer.allocate(capacity);
            default -> new RuntimeException("输入的参数无法识别");
        }

    }

    public BufferUtils(Integer capacity) {
        byteBuffer=ByteBuffer.allocate(capacity);
    }

    public void put(Object object){
        if (byteBuffer!=null){
            byteBuffer.put(toByteArray(object));
        }else {
            new RuntimeException("其他Buffer不支持存放对象");
        }
    }

    public void put(Integer integer){
        if (byteBuffer!=null){
            byteBuffer.put(toByteArray(integer));
        } else if (intBuffer!=null) {
            intBuffer.put(integer);
        } else{
            new RuntimeException("除开int和byte，其他Buffer不支持存放int");
        }
    }

    public void put(Float arg){
        if (byteBuffer!=null){
            byteBuffer.put(toByteArray(arg));
        } else if (floatBuffer!=null) {
            floatBuffer.put(arg);
        } else{
            new RuntimeException("除开float和byte，其他Buffer不支持存放float");
        }
    }

    public void put(Character character){
        if (byteBuffer!=null){
            byteBuffer.put(toByteArray(character));
        } else if (character!=null) {
            charBuffer.put(character);
        } else{
            new RuntimeException("除开char和byte，其他Buffer不支持存放char");
        }
    }

    public void put(Double double1){
        if (byteBuffer!=null){
            byteBuffer.put(toByteArray(double1));
        } else if (doubleBuffer!=null) {
            doubleBuffer.put(double1);
        } else{
            new RuntimeException("除开double和byte，其他Buffer不支持存放double");
        }
    }

    public void put(Long long1){
        if (byteBuffer!=null){
            byteBuffer.put(toByteArray(long1));
        } else if (longBuffer!=null) {
            longBuffer.put(long1);
        } else{
            new RuntimeException("除开long和byte，其他Buffer不支持存放long");
        }
    }

    public void put(Short short1){
        if (byteBuffer!=null){
            byteBuffer.put(toByteArray(short1));
        } else if (shortBuffer!=null) {
            shortBuffer.put(short1);
        } else{
            new RuntimeException("除开short和byte，其他Buffer不支持存放short");
        }
    }

    public static byte[] toByteArray(Object obj) {
        String jsonStr = JSON.toJSONString(obj);
        byte[] bytes = jsonStr.getBytes();
        array[index]=bytes.length;
        index++;
        return bytes;
    }

    public Object get(Class aclazz){
        Object obj=null;
        if (byteBuffer!=null){
            index--;
            int length=array[index];
            byte[] bytes=new byte[array[index]];
            for (int i=0;i<length;i++){
                byte b = byteBuffer.get(i);
                bytes[i]=b;
            }
            obj = JSON.parseObject(bytes, aclazz);
        }
        return obj;
    }


}
