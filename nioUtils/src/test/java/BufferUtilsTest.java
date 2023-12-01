import org.example.Main;
import org.example.utils.BufferUtils;
import org.junit.Test;

public class BufferUtilsTest {

    @Test
    public  void  test001() {
        BufferUtils bufferUtils=new BufferUtils(60);
        Main main=new Main("nhl","123456");
        bufferUtils.put(main);
        Main o = (Main) bufferUtils.get(Main.class);
        System.out.println(o);
    }

    @Test
    public  void  test002() {
        BufferUtils bufferUtils=new BufferUtils(1000);
        Main main=new Main("nh","123456");
        Main main1=new Main("cyl","123456");
        Main main2=new Main("cylsdfas","123456");
        bufferUtils.put(main);
        bufferUtils.put(main1);
        bufferUtils.put(main2);
        Main o = (Main) bufferUtils.get(Main.class);
        Main o1 = (Main) bufferUtils.get(Main.class);
        System.out.println(o);
        System.out.println(o1);

    }

    @Test
    public void test003(){
        BufferUtils bufferUtils=new BufferUtils(1000);
        Main main=new Main("nh","123456");
        Main main1=new Main("cyl","123456");
        bufferUtils.put(main);
        bufferUtils.put(1);
        bufferUtils.put(main1);
        Main o = (Main) bufferUtils.get(Main.class);
        System.out.println(o);
        Object o1 = bufferUtils.get(Integer.class);
        System.out.println(o1);
        Object o2 = bufferUtils.get(Main.class);
        System.out.println(o2);
        bufferUtils.put(3.14f);
        bufferUtils.put(3.400);
        bufferUtils.put(989l);
        bufferUtils.put(true);

        Object o3 = bufferUtils.get(Float.class);
        Object o4 = bufferUtils.get(Double.class);
        Object o5 = bufferUtils.get(Long.class);
        Object o6 = bufferUtils.get(Boolean.class);
        System.out.println(o3);
        System.out.println(o4);
        System.out.println(o5);
        System.out.println(o6);
        Main main14=new Main("cyl","123456");
        bufferUtils.put(main14);
        System.out.println(bufferUtils.get(Main.class));
    }

    @Test
    public void test004(){
        BufferUtils bufferUtils=new BufferUtils(1000);
        bufferUtils.put(1);
        bufferUtils.put(3);
        System.out.println(bufferUtils.get(Integer.class));
        System.out.println(bufferUtils.get(Integer.class));
    }
}
