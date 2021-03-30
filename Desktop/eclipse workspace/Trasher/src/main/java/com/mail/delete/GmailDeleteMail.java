package com.mail.delete;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class GmailDeleteMail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String email = "engimatrojan@gmail.com";
		String password = "EngimA123#";
		String host = "imap.gmail.com";
		String port = "993";

		deleteMails(email, password, host, port);
	}

	public static void deleteMails(String email, String password, String host, String port) {
		Properties properties = new Properties();
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", port);

		// Providing the java file to the Socket Factory Class to manage session
		// timeouts etc.
		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// To handle of something goes wrong
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", port);

		Session session = Session.getDefaultInstance(properties);

		try {
			Store store = session.getStore("imap");
			store.connect(email, password);

			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);

//			Folder trash = store.getFolder("[Gmail]/Trash");
//			inbox.open(Folder.READ_WRITE);

			Message[] message = inbox.getMessages();
			//System.out.println(inbox.getMessages().length);

//			List<String> list = Arrays.asList("Videos uploaded");
//			
//			for(Message m : message)
//			{
//				String subject = m.getSubject();
//				for(String l:list)
//				{
//					if(subject.contains(l))
//					{
//						inbox.copyMessages(new Message[] {m}, trash);
//						System.out.println("Deleted"+subject);
//					}
//				}
//			}
			
			Map<String,Integer> map =  new HashMap();
			for(Message m:message)
			{
				String subject = m.getSubject();
				if(map.containsKey(subject))
				{
					map.put(subject, map.get(subject)+1);
					if(map.get(subject)>0)
						System.out.println(subject);
				}
				else
					map.put(subject, 1);
			}
			
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
