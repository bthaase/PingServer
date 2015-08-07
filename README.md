# PingServer
Java-based ICMP echo server with a Jersey-powered REST endpoint. Designed for use with Tomcat 7. 

#### Why?
I've been developing an informational dashboard that is hosted from a Windows 7 machine. Due to restrictions in my current environment, I am unable to acquire an IIS server for use, and I am unable to domain join a Linux machine to the domain for use. 
Faced with the challenge of pinging numerous devices across the company on demand from the web application, I wrote this application to sit on top of a Tomcat 7 installation on Windows, to provide native ICMP pings when requested to by the REST endpoint. 

#### How do I use this?

Build in Eclipse to produce a *.WAR file you can deploy to Tomcat 7. 

Once spun up, it provides a pair of endpoints at /ping
```
GET /ping
 Responds with { status: alive }, which is used for one of our service monitors.
GET /ping/{Target}
 Pings the target IP or DNS name and responds with JSON data containing the results. 
```

#### JSON Response Packet

Contains the following elements:
```
successFlag: 1 if the ping was successful.
address: The target address.
message: The contents of the error message, if one occured.
size: Bytes in the response. 
rtt: Round Trip Time
ttl: Time To Live
```

#### Credits
This application uses Open Source components. You can find the source code of their open source porjects along with license information below. 

Project: ICMP4J<BR/>
Repo: [Sourgeforge.net](http://sourceforge.net/projects/icmp4j/)<BR/>
License: Apache Software License, LGPLv3
