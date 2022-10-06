<b>Build</b>
    
    git clone https://github.com/VladislavAnanyev/Boxes
    cd Boxes
    mvn package

<b> Run </b>

    java -jar target/Boxes-1.0-SNAPSHOT.jar

По умолчанию для ввода используется: input.txt файл, а для вывода output.txt

Изменить настройки по умолчанию можно через аргументы командной строки. Например:

    java -jar target/Boxes-1.0-SNAPSHOT.jar -in custom_input.txt -out custom_output.txt
