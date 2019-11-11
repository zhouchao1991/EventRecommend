# EventRecommend
## Introduction
EventRecommend is a Java web application aiming to provide a better experience of searching, management and recommendation of events and tickets. Registered users can search for nearby events, purchase tickets, and archive their favorite events through an interactive web page. Event recommendation can be provided based on search history and records of favorite events.

As summarized in Figure 1, the project was developed and runs on Tomcat Server and is deployed to Amazon EC2. The core functions of the application is realized by a group of Java servelts with RESTful APIs to handle HTTP requests and responses. Event information is obtained from TicketMaster API through TicketMasterClient. User information and events data are stored and managed in MySQL database, which is realized by the MySQL database management package.

<p align="center">
<img src="https://github.com/zhouchao1991/EventRecommend/blob/master/images/Project%20Structure.jpg" alt="drawing" width="600"/>
</p>
