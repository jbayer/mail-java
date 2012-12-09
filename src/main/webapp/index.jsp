<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>SMTP example</title>
</head>
<body>
Try the <a href="MailServlet">Mail Servlet</a> to send a test email. 
<br/>
<form action="MailServlet" method="post">
smtp host: <input name="smtpHost" type="text" value="smtp.sendgrid.net"></input><br/>
smtp port: <input name="smtpPort" type="text" value="465"></input><br/>
user: <input name="smtpAuthUser" type="text" value=""></input><br/>
password: <input name="smtpAuthPwd" type="password" value=""></input><br/>
to: <input name="to" type="text" value=""></input><br/>
from: <input name="from" type="text" value=""></input><br/>
subject: <input name="subject" type="text" value=""></input><br/>
body: <input name="body" type="text" value=""></input><br/>
<input name="submit" type="submit" value="submit"></input><br/>
</form>
</body>
</html>