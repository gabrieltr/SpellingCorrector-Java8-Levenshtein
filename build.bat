del out\production\corretor\plural\corretor\*.class
rem PATH=%PATH%;C:\Program Files\Java\jdk1.8.0_91\bin
javac.exe -g  .\src\plural\corretor\*.java -cp ".\src\plural\corretor\;.\out\production\corretor\"  -verbose
move .\src\plural\corretor\*.class .\out\production\corretor\plural\corretor\
dir *.class /s
