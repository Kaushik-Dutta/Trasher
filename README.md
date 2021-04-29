# Trasher

It is a Java application or you can say an example of simple Java Automation which helps in deleting emails which we don't want automatically while running it.

#dependencies - javax.mail

To run this app you have to turn on your gmail less secure App access for your specific account.

Logic :

  1. We first iterate through all the messages and put them in Map to collect some filter      values.

  2. Then we put the filters in a list and iterate through all the messages and copy the      items matching the list to the bin folder. 
