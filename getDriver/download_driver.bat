@echo off

set sel_ver=2.39.0
::set current_dir=%CD%
set PATH=%CD%\bin

:: wget http://selenium.googlecode.com/files/selenium-server-standalone-%sel_ver%.jar
:: wget http://selenium.googlecode.com/files/selenium-server-%sel_ver%.zip
wget http://selenium.googlecode.com/files/IEDriverServer_x64_%sel_ver%.zip
wget http://selenium.googlecode.com/files/IEDriverServer_Win32_%sel_ver%.zip



wget http://chromedriver.storage.googleapis.com/2.8/chromedriver_win32.zip
wget http://chromedriver.storage.googleapis.com/2.8/notes.txt