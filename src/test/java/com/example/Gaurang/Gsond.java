package com.example.Gaurang;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Gsond {
	List<OrderLogBack> rdata;
	public  List<OrderLogBack> getdata(int j) throws FileNotFoundException {
		rdata=new ArrayList<OrderLogBack>();
		// TODO Auto-generated method stub
		final Type REVIEW_TYPE = new TypeToken<List<OrderLogBack>>() {
		}.getType();
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader("C:\\Users\\vikram\\Downloads\\ExpectedResult1.json"));
		List<OrderLogBack> data = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
		
		for(int i=0;i<data.size();i++) {
			if(i<j)continue;
			//OrderLogBack olbr=new OrderLogBack();
			rdata.add(data.get(i));
			
		}
		for(OrderLogBack d:rdata) {
			System.out.println(d.getIndex_id());
		}
		return rdata;

	}
	
}
