package pierpaolo.u5w2d2.config;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pierpaolo.u5w2d2.entities.Autore;

@Component
public class MailgunSender {
    private String mailgunApiKey;
    private String mailgunDomainName;

    //aggiungere dipendenza unirest java
    public MailgunSender(@Value("${mailgun.apikey}") String mailgunApiKey,
                         @Value("${mailgun.domainname}") String mailgunDomainName){
        this.mailgunApiKey = mailgunApiKey;
        this.mailgunDomainName = mailgunDomainName;
    }
    public void sendRegistrationEmail(String recipient){
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.mailgunDomainName + "/messages")
                .basicAuth("api", this.mailgunApiKey)
                .queryString("from", "Colasante Pierpaolo <colasantep@outlook.it>")
                .queryString("to", "colasantepierpaolo@gmail.com")
                .queryString("subject", "Registrazione avvenuta con successo!")
                .queryString("text", "Complimenti  per esserti registrato " )
                .asJson();
        System.out.println("mail inviata");
    }
}
