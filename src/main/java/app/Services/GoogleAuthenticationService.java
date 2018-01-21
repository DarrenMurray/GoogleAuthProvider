package app.Services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Collections;

@Service
public class GoogleAuthenticationService
{

   public Credential getCredential()
   {
      if (CREDENTIAL != null)
      {
         return CREDENTIAL;
      }
      else
      {
        createGoogleCredential();
        return CREDENTIAL;
      }
   }

   private void createGoogleCredential()
   {
      try
      {
         LocalServerReceiver receiver = new LocalServerReceiver.Builder().setHost("hub.homehubserver.com").setPort(8081).build();
         GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
                 JSON_FACTORY,
                 ClientStorage.CLIENT_ID,
                 ClientStorage.CLIENT_SECRET,
                 Collections.singleton(API_SCOPE))
                 .setAccessType("offline")
                 .setDataStoreFactory(MemoryDataStoreFactory.getDefaultInstance())
                 .build();
         CREDENTIAL = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

      } catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   private Credential CREDENTIAL;
   private JsonFactory JSON_FACTORY = new JacksonFactory();
   private HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
   /**
    * OAuth 2.0 scopes.
    */
   private static final String API_SCOPE = "https://www.googleapis.com/auth/fitness.activity.read";

}
