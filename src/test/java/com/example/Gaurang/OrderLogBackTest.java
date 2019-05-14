package com.example.Gaurang;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;



public class OrderLogBackTest {
	
	
	@Test
	public void test() {
		List<OrderLogBack>actual=new FetchDataTest().getreal();
		List<OrderLogBack> expected = null;
		try {
			expected = new Gsond().getdata(26);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThat(actual).isEqualTo(expected);
	}

}
