@echo on

@REM File to be deleted
SET FileToDelete=%HOME%\.IntelliJIdea14\config\options\options.xml

@REM Try to delete the file only if it exists
IF EXIST %FileToDelete% del /F %FileToDelete%

@REM File to be deleted
SET eval=%HOME%\.IntelliJIdea14\config\eval

@REM Try to delete the file only if it exists
IF EXIST %eval% rd /q /s %eval%