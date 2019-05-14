package com.example.Gaurang;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.example.Gaurang.WsClient.MessageHandler;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import junit.framework.Assert;

public class FetchDataTest {
	List<OrderLogBack> obList;

	public List<OrderLogBack> getreal() {

		obList = new ArrayList<OrderLogBack>();

		WsClient ws = new WsClient("orderlogback?memberId=A&consumerId=abcd&lastOrderLogId=27");

		ws.addMessageHandler(new MessageHandler() {

			@Override
			public void handleMessage(String message) {

				String[] nm = message.split("\"result\" :");
				int i = nm[1].lastIndexOf('}');
				String jsonobj = nm[1].substring(0, i).trim();

				Gson gson = new Gson();
				JsonReader reader = new JsonReader(new StringReader(jsonobj));
				reader.setLenient(true);

				OrderLogBack olbObj = gson.fromJson(jsonobj, OrderLogBack.class);
				obList.add(olbObj);

			}

		});

		ws.connect();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < obList.size(); i++) {
			System.out.println("Here is the list" + obList.get(i).getIndex_id());
		}

	return obList;}

}
