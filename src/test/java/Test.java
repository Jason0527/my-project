import java.util.HashMap;
import java.util.Map;

import com.szkingdom.kwis.common.security.DESEncrypt;

import redis.clients.jedis.Jedis;

public class Test {
	@org.junit.Test
	public void test1(){
		String pwd = "q123456";
		String aaa = DESEncrypt.strEnc(pwd);
		System.out.println(aaa);
	}
	@org.junit.Test
	public void test2(){
		Jedis jd = new Jedis("127.0.0.1",6379);
		String pong = jd.ping();
		System.out.println(pong);
		jd.set("name", "裘千仞");
		System.out.println(jd.get("name"));
		jd.set("age", "24");
		System.out.println(jd.get("age"));
		jd.del("name");
		jd.del("age");
		System.out.println(jd.get("name"));
	}
	@org.junit.Test
	public void test3(){
		Map<String,String> map = new HashMap<String,String>();
	}
	
}
