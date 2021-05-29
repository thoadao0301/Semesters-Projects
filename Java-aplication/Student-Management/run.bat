@echo off
set JVMBIN="C:\Program Files\Java\jdk1.8.0_60\bin\java.exe"
set CLASSPATH=C:\Users\baoth\Desktop\Fall2020\LAB\Assignment_J1.P0001_Management\build\classes
set CLASS=Manage


%JVMBIN% -classpath %CLASSPATH% %CLASS%
pause
