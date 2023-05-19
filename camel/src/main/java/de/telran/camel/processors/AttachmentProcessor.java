package de.telran.camel.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.attachment.AttachmentMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
@Slf4j
public class AttachmentProcessor implements Processor {

    @Value("${email.attachments_path}")
    private String filepath;

    @Override
    public void process(Exchange exchange) throws Exception {
        Message m = exchange.getIn();
//        m
        AttachmentMessage message = exchange.getIn(AttachmentMessage.class);
        log.info("Processing new email from {} with subject '{}'", message.getHeader("from"), message.getHeader("subject"));

        Map<String, DataHandler> attachments = message.getAttachments();

        if (attachments != null) {
            for (String s : attachments.keySet()) {
                DataHandler dh = attachments.get(s);

                String filename = dh.getName();
                byte[] data = exchange.getContext().getTypeConverter().convertTo(byte[].class, dh.getInputStream());

                StringBuilder sb = new StringBuilder();
                sb.append(filepath);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm");
                LocalDateTime now = LocalDateTime.now();
                sb.append(dtf.format(now));
                sb.append("_");
                sb.append(filename);

                log.info("Writing file {}", sb);

                FileOutputStream out = new FileOutputStream(sb.toString());
                out.write(data);
                out.flush();
                out.close();
                message.removeAttachment(s);
            }
        }
    }
}
