package com.example.Gaurang;

import static org.junit.Assert.assertEquals;

//import  org.junit.Assert.assertEquals;
import org.junit.Test;

import com.example.Gaurang.WsClient.MessageHandler;

import kafka.utils.Json;

public class FetchData2{
	@Test
	public static void main(String[] args) {

		WsClient ws = new WsClient("xchange/orderstreaming/orderlogback?memberId=A&consumerId=abcd&lastOrderLogId=0");
		ws.addMessageHandler(new MessageHandler() {

			@Override
			public void handleMessage(String message) {
				// TODO Auto-generated method stub
				String expected = "";
				assertEquals(Json.parseFull(message).get().toString(), Json.parseFull(expected).get().toString());
				try {
					ws.close();
					synchronized (this) {
						this.notifyAll();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
	}

}
