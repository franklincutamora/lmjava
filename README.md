<div>
	<h1>Employee CRUD (Java and MySQL)</h1>
	<br>
	This is a simple Employee CRUD application using Java and Mysql database.
	<br>
	<h3>To Run this application on your computer, please do the following:</h3>
	<ol>
		<li>Install NetBeans IDE 8.0.2.</li>
		<li>Install XAMPP.</li>
		<li>Run XAMPP  and start (Apache, MySQL).</li>
		<li>Open "localhost/phpmyadmin" on the browser.</li>
		<li>Create database "simple_crud" with "utf8_general_ci" collation.</li>
		<li>Import "simple_crud.sql" into "simple_crud" database</li>
		<li>Open Netbeans and perform notes below:</li>
	</ol>
	<br>
	<strong>Note 1:</strong> I'm using datepicker so add datepicker to the pallete by:
	<ol>
		<li>Perform Note 3 below for resolving missing jar files ("C:\Program Files\NetBeans 8.0.2\ide\modules\ext\").</li>
		<li>Tools > Pallete > Swing/AWT Components</li>
		<li>CLick "Add from JAR" button</li>
		<li>Go to "C:\Program Files\NetBeans 8.0.2\ide\modules\ext\" and select "swingx-all-1.6.4.jar"</li>
		<li>Click "Next" button and select "JXDatePicker" from the list of available components.</li>
		<li>Click "Next" button and select "Swing Controls" folder and click finish.</li>
	</ol>
	<br>
	<br>
	<strong>Note 2: </strong> Update necessary information based on your local settings on "DBConnect.java"
	<br>
	<code>DriverManager.getConnection("jdbc:mysql://localhost/simple_crud","root","")</code>
	<br>
	<br>
	<strong>Note 3:</strong> These are the libraries needed in case project problem occurs. Resolve the missing libraries by locating the jar files.
	<ol>
		<li>MySQL JDBC Driver - mysql-connector-java-5.1.23-bin.jar</li>
		<li>swingx-all-1.6.4.jar</li>
		<li>beansbinding-1.2.1.jar</li>
		<li>JDK 1.8</li>
	</ol>
	<br>
	<br>
	
</div>