package com.example.Gaurang;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringReader;

import com.example.Gaurang.WsClient.MessageHandler;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class TestFiles {

	private void invalidCasesI(String loId) {

		WsClient ws = new WsClient("orderlogback?memberId=A&consumerId=abcd&lastOrderLogId=" + loId);
		ws.addMessageHandler(new MessageHandler() {

			@Override
			public void handleMessage(String message) {
				System.out.println(message);
				System.out.println(loId);
				if (loId.isEmpty() ||(Integer.parseInt(loId)>=0)||loId.contentEquals("_"))return;
				else {
				System.out.println("invalid value of loId");
				String expectedErrorMsg = "{\n" + 
						"  \"error\" : {\n" + 
						"    \"code\" : 100,\n" + 
						"    \"message\" : \"Member id is not Valid and this connection should be rejected\"\n" + 
						"  }\n" + 
						"}";
				assertThat(message.trim()).isEqualTo(expectedErrorMsg.trim());
				}

			}

		});
		ws.connect();
	}

	private void invalidCasesC(String consumerId) {

		WsClient ws = new WsClient("orderlogback?memberId=A&consumerId=" + consumerId + "&lastOrderLogId=");
		ws.addMessageHandler(new MessageHandler() {

			@Override
			public void handleMessage(String message) {
				if (consumerId.isEmpty()) {
					System.out.println("consumerId shouldn't be empty");
					String expectedErrorMsg = "[{\n" + 
							"  \"error\" : {\r\n" + 
							"    \"code\" : 100,\n" + 
							"    \"message\" : \"ConsumerId must be a non empty value!!\"\n" + 
							"  }\r\n" + 
							"}]";
					assertThat(message).isEqualTo(expectedErrorMsg);
				}

			}

		});
		ws.connect();
	}

	private void invalidCasesM(String MId) {

		WsClient ws = new WsClient("orderlogback?memberId=" + MId + "&consumerId=abcd&lastOrderLogId=");
		ws.addMessageHandler(new MessageHandler() {

			@Override
			public void handleMessage(String message) {
				System.out.println(message);
				if (MId.isEmpty()) {
					System.out.println("memberId shouldn't be empty");
					String expectedErrorMsg = "{\n" + 
							"  \"error\" : {\n" + 
							"    \"code\" : 100,\n" + 
							"    \"message\" : \"Member id is not Valid and this connection should be rejected\"\n" + 
							"  }\n" + 
							"}";
					assertThat(message).isEqualTo(expectedErrorMsg);
				}

			}

		});
		ws.connect();
	
	}

	public static void main(String[] args) {
		TestFiles tf=new TestFiles();
		tf.invalidCasesM("");
		tf.invalidCasesI("-3");
		tf.invalidCasesC("");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
