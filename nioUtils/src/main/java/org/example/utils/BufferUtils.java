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
    //get的时候的位置
    Integer position=0;
    //真正的从0开始的索引

    int readable=1;
    int Tindex=0;
    public static Integer[] array=new Integer[1000];
    public volatile static int index=0;
    public BufferUtils(Integer capacity,Class obj) {
        String objName = obj.getSimpleName();

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
            byteBuffer.putInt(integer);
        }else if (intBuffer!=null) {
            intBuffer.put(integer);
        }else {
            new RuntimeException("其他Buffer不支持存放Integer对象");
        }

    }

    public void put(Float arg){
        if (byteBuffer!=null){
            byteBuffer.putFloat(arg);
        } else if (floatBuffer!=null) {
            floatBuffer.put(arg);
        } else{
            new RuntimeException("除开float和byte，其他Buffer不支持存放float");
        }
    }

    public void put(Character character){
        if (byteBuffer!=null){
            byteBuffer.putChar(character);
        } else if (character!=null) {
            charBuffer.put(character);
        } else{
            new RuntimeException("除开char和byte，其他Buffer不支持存放char");
        }
    }

    public void put(Double double1){
        if (byteBuffer!=null){
            byteBuffer.putDouble(double1);
        } else if (doubleBuffer!=null) {
            doubleBuffer.put(double1);
        } else{
            new RuntimeException("除开double和byte，其他Buffer不支持存放double");
        }
    }

    public void put(Long long1){
        if (byteBuffer!=null){
            byteBuffer.putLong(long1);
        } else if (longBuffer!=null) {
            longBuffer.put(long1);
        } else{
            new RuntimeException("除开long和byte，其他Buffer不支持存放long");
        }
    }

    public void put(Short short1){
        if (byteBuffer!=null){
            byteBuffer.putShort(short1);
        } else if (shortBuffer!=null) {
            shortBuffer.put(short1);
        } else{
            new RuntimeException("除开short和byte，其他Buffer不支持存放short");
        }
    }

    public void put(Boolean bool){
        if (byteBuffer!=null){
            byteBuffer.put(bool?(byte)1:(byte)0);
        } else{
            new RuntimeException("除开byte，其他Buffer不支持存放bool");
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
        String objName = aclazz.getSimpleName();

        switch (objName) {
            case "int","Integer"->{
                int anInt = byteBuffer.getInt(position);
                addFourByte();
                return anInt;
            }
            case "char","Character"->{
                char aChar = byteBuffer.getChar(position);
                addTwoByte();
                return aChar;
            }
            case "byte","Byte"->{
                byte aByte = byteBuffer.get(position);
                addOneByte();
                return aByte;
            }
            case "double","Double"->{
                double aDouble = byteBuffer.getDouble(position);
                addEightByte();
                return aDouble;
            }
            case "float","Float"->{
                Float anFloat = byteBuffer.getFloat(position);
                addFourByte();
                return anFloat;
            }
            case "long","Long"->{
                Long aLong = byteBuffer.getLong(position);
                addEightByte();
                return aLong;
            }
            case "short","Short"->{
                short aShort = byteBuffer.getShort(position);
                addTwoByte();
                return aShort;
            }
            case "boolean","Boolean"->{
                byte b = byteBuffer.get(position);
                Boolean aBool = (b == 0x00) ? false : true;
                addOneByte();
                return aBool;
            }
            default -> new RuntimeException("输入的参数无法识别");
        }

        if (byteBuffer!=null){
            byte[] bytes=new byte[array[Tindex]];
            for (;position<array[Tindex];position++){
                byte b = byteBuffer.get(position);
                bytes[position]=b;
            }
            if (array[Tindex]>10){
                obj = JSON.parseObject(bytes, aclazz);
            }
            if (Tindex<index-1){
                Tindex++;
                array[Tindex]=position+array[Tindex];
            }

        }
        return obj;
    }

    public void addFourByte(){
        position=position+4;
        array[Tindex]=array[Tindex]+4;
    }
    public void addTwoByte(){
        position=position+2;
        array[Tindex]=array[Tindex]+2;
    }

    public void addOneByte(){
        position=position+1;
        array[Tindex]=array[Tindex]+1;
    }

    public void addEightByte(){
        position=position+8;
        array[Tindex]=array[Tindex]+8;
    }
}
