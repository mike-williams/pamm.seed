PAMM: Front-end - Feature, Business Process, Components
=

Component
-
A PAMM component is a section of independent functionality that is responsible for conveying and/or manipulating some information to user.  It comprises of one or more HTML partials in the presentation layer, and JavaScript modules in the application layer for the model and controller logic.  
<br>
<img src="img/component.png" width="50%" />
<br>

*TODO expand*

PAMM is aimed at developing enterprise applications and hence it is heavily focused on implementing business processes.  A business process is a collection of human and/or automated tasks that together provide a particular service.  For example, consider the a common self-registration process:

1. User register account via self service portal
2. System verify that account is not already registered
3. System sends activation email

In the agile world this would equate to a story or feature.  In the context of PAMM,  a feature consists of a collection of human and automated tasks.  In other words, a work flow.  Although a business process may contains only automated tasks but as PAMM applications are WEB based, it will always consist of some form of human tasks.

<br/>
###Feature and Comonent Directory structure###
<br/>
<img src="img/features-and-compoents-directory.png" />
<br/>
###Read Component Life Cycle###
<br/>
<img src="img/read-fsm.png" width="50%" />
<br/>
###Read/Write Component Life Cycle###
<br/>
<img src="img/read-write-fsm.png"/>
<br/>
