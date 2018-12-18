package demo;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demo.init.initialization;



/**
 * @author gaurav.kapoor
 *
 */
public class TC_AITR_Login  {
	
	initialization init = new initialization();
	

	
	@BeforeClass
	public void startup() throws Exception{
		init.initProperties();
		

	}
	

	@BeforeMethod
	public void ready() throws Exception{
		
		init.start();

		
	}
	
	
	@AfterMethod
	public void tearDown() throws IOException{
		init.stop();
	}
	
	 @AfterClass
	 public void writeRersults() throws IOException{
			

	 }
	
	@Test
	public void loginTest() throws Exception{
		

		
		
	}
	
	

	
	

	

	
}
