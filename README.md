# Customer-Inteca
url: jdbc:mariadb://localhost:3306/clients</br>
url azure: jdbc:sqlserver://olsonsql.database.windows.net:1433;database=bank;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30; </br>
</br>
CREATE TABLE Customer(</br>
    firstName CHAR NOT NULL,</br>
    id INT UNSIGNED,</br>
    lastName CHAR NOT NULL,</br>
    pesel VARCHAR(11) PRIMARY KEY //przyjmując, że to polski pesel</br>
    )
   </br>
    ![image](https://user-images.githubusercontent.com/64684630/153497123-91c7dcc1-e796-4191-bf7b-5753f8c0c3ad.png)


