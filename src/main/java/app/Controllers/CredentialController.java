package app.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

public class CredentialController
{

   @CrossOrigin
   @RequestMapping(value = "/oauth2callback", method=GET)
   @ResponseBody
   public void acceptToken() throws IOException
   {
      System.out.println("success!");
   }

}
