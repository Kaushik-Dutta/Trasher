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
		String subject="abc";

		try {
			Store store = session.getStore("imap");
			store.connect(email, password);

			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);

			Folder trash = store.getFolder("[Gmail]/Trash");
			trash.open(Folder.READ_WRITE);

			Message[] message = inbox.getMessages();
			//System.out.println(inbox.getMessages().length);

			List<String> list = Arrays.asList("engimatrojan,","Dipendra Meena","Security alert",
					"Netflix tonight?",
					"Everyone's eyeing");
			
			for(int i=0;i<message.length;i++)
			{
				Message m = message[i];
				subject = m.getSubject();
				System.out.println(subject);
				for(String l:list)
				{
					if(subject.contains(l))
					{
						inbox.copyMessages(new Message[] {m}, trash);
						System.out.println("Deleted"+subject);
					
					}
				}
			}
			store.close();
			//Check items in inbox and keep their count in Map
			// this helps in finding some filters to delete
//			Map<String,Integer> map =  new HashMap<String,Integer>();
//			for(Message m:message)
//			{
//				subject = m.getSubject();
//				if(map.containsKey(subject))
//				{
//					map.put(subject, map.get(subject)+1);
//					if(map.get(subject)>1)
//						System.out.println(subject);
//				}
//				else
//					map.put(subject, 1);
//			}
			
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
