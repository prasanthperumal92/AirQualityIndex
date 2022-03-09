package com.proximity.airqualitymonitoring.data.utils;


import com.proximity.airqualitymonitoring.data.models.CityAQDB;
import com.proximity.airqualitymonitoring.data.repositaries.AirQualityRepositary;
import com.proximity.airqualitymonitoring.data.socketevents.AirQualitySockEvent;
import com.proximity.airqualitymonitoring.utils.Logger;

import java.util.ArrayList;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class AppSocketClient {
    public static AppSocketClient socketClient;
    private WebSocket mSocket;

    public static  AppSocketClient getSocketClient() {
        if (socketClient==null)
        {
            socketClient = new AppSocketClient();
        }
        if (socketClient.mSocket==null) {
            Request build = new Request.Builder().url(ServiceUrl.socketUrl).build();
            socketClient.mSocket = new OkHttpClient().newWebSocket(build, new WebSocketListener() {
                @Override
                public void onOpen(WebSocket webSocket, Response response) {
                    super.onOpen(webSocket, response);
                    Logger.Companion.e("websocket connected");
                }

                @Override
                public void onMessage(WebSocket webSocket, String text) {
                    super.onMessage(webSocket, text);
                    Logger.Companion.e(text);
                    try {
                        new AirQualitySockEvent(text).parseAndSave(new AirQualityRepositary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onMessage(WebSocket webSocket, ByteString bytes) {
                    super.onMessage(webSocket, bytes);
                }

                @Override
                public void onClosing(WebSocket webSocket, int code, String reason) {
                    super.onClosing(webSocket, code, reason);
                    Logger.Companion.e("websocket closing "+ reason);

                }

                @Override
                public void onClosed(WebSocket webSocket, int code, String reason) {
                    super.onClosed(webSocket, code, reason);
                }

                @Override
                public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                    super.onFailure(webSocket, t, response);
                    try {
                        Logger.Companion.e("websocket failure "+ response.body().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }


        return socketClient;
    }



    public void closeSocket(){
        try {
            socketClient.mSocket.close(1000,"Application_Killed");
            socketClient.mSocket = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
