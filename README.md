Java Send Mail Example
=================

This sample illustrates how to send smtp emails using a simple Servlet-based Java webapp easily in Cloud Foundry using email providers like [SendGrid](https://sendgrid.com/user/signup) and [MailGun](http://www.mailgun.com/pricing) that both have free levels of service that allow you to send up to 200 emails per day and paid plans to do more. The index.jsp of the application presents a form where a user can provide inputs from the mail provider. Note that on Cloud Foundry smtp outbound on port 25 is blocked. But other ports are open, such as the unsecured port 587 or secured port 465.

The form on the index.jsp submits the values to a MailServlet, which uses a MailUtility class to actually attempt to send the email. If you configure the environment variables with valid email provider information, then you can invoke the MailServlet without parameters and it will use defaults. Note in this case, you should change the hard-coded values in the To, From, Subject, Body inside of the MailUtility.sendMailWithEnvValues() method.


Building the Application
-----------------------
mvn install

Running the Application
-----------------------

To run the application, make sure you have [VMC](http://docs.cloudfoundry.com/tools/vmc/installing-vmc.html "VMC")
installed and that you are logged in successfully for your desired target environment (e.g. http://api.cloudfoundry.com).
Then, *cd* into the directory of this README.md file and make sure the manifest.yml values look correct and then execute:

```
vmc push --path target/mail-java-1.0.war 
Using manifest file manifest.yml

Creating mail-java... OK

Creating route mail-3ef46.cloudfoundry.com... OK
Binding mail-3ef46.cloudfoundry.com to mail-java... OK
Uploading mail-java... OK
Starting mail-java... OK
Checking mail-java... OK

vmc app mail-java
Using manifest file manifest.yml

mail-java: running
  platform: java_web on java7
  usage: 128M × 1 instance
  urls: mail-3ef46.cloudfoundry.com
```

Your url will likely be different if you are using the ${random-word} functionality in the manifest.yml file. Invoking that URL should display the index.jsp.

For SendGrid, you should be able to use values like:
```
smtp host: smtp.sendgrid.net
smtp port: 465
user: SENDGRID_USER
password: SENDGRID_PASSWORD
```

For MailGun, you should be able to use values from your ["Domains"](https://mailgun.net/cp/domains "Domains") page.
```
smtp host: smtp.mailgun.org
smtp port: 465
user: postmaster@YOUR_DOMAIN.mailgun.org
password: DOMAIN_PASSWORD
```

