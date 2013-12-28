@echo off

echo This system os is %PROCESSOR_ARCHITECTURE%
echo start register lib

if %PROCESSOR_ARCHITECTURE% == AMD64 (
goto :x64
) else (
goto :x86
)

:x86
%systemroot%\System32\regsvr32.exe AutoItX3.dll
goto :eof

:x64
%systemroot%\SysWoW64\regsvr32.exe AutoItX3_x64.dll
goto :eof
