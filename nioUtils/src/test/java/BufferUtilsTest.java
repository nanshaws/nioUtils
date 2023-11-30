import org.example.Main;
import org.example.utils.BufferUtils;
import org.junit.Test;

public class BufferUtilsTest {

    @Test
    public  void  test001() {
        BufferUtils bufferUtils=new BufferUtils(60);
        Main main=new Main("nh","123456");
        bufferUtils.put(main);
        Main o = (Main) bufferUtils.get(Main.class);
        System.out.println(o);
    }

    @Test
    public  void  test002() {
        BufferUtils bufferUtils=new BufferUtils(60);
        Main main=new Main("nh","123456");
        Main main1=new Main("cyl","123456");
        bufferUtils.put(main);
        bufferUtils.put(main1);
        Main o = (Main) bufferUtils.get(Main.class);
        System.out.println(o);
    }
}
