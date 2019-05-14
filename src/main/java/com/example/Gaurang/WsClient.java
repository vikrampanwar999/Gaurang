//package com.daollar.orderentry.orderstreaming.utility;
package com.example.Gaurang;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


@ClientEndpoint
public class WsClient {
    Session userSession = null;
    private MessageHandler messageHandler;
    String memberId="A";
    String consumerId="abcd";
    String lastOrderLogId="0";
   // private static String endpointURI = ApplicationProperties.getInstance().getProperty("server.endpoint");
    private static String endpointURI = "ws://3.93.103.201:20003/xchange/orderstreaming/";

    private String query;

    public WsClient(String query) {
    	this.query = query;
    }
    
    public void connect() {
        try {
            System.out.println(endpointURI+query);

            WebSocketContainer container = ContainerProvider
                    .getWebSocketContainer();
            container.connectToServer(this, new URI(endpointURI+query));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    public void close() throws IOException {
    	try {
    		if(userSession!=null) {
    	    	userSession.close();    			
    		}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }

    @OnOpen
    public void onOpen(Session userSession) {
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
    	//Log.info("Session Close"+reason);
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null)
            this.messageHandler.handleMessage(message);
    }

    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    public static interface MessageHandler {
        public void handleMessage(String message);
    }
}
  