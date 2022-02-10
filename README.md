# Customer-Inteca
jdbc:mariadb://localhost:3306/creditdata</br>
</br>
CREATE TABLE Customer(</br>
    firstName CHAR NOT NULL,</br>
    id INT UNSIGNED,</br>
    lastName CHAR NOT NULL,</br>
    pesel VARCHAR(11) PRIMARY KEY //przyjmując, że to polski pesel</br>
    )
