@echo off
title Infest Administrator

net start | find "MySQL57" > nul 2>&1 
if not .%errorlevel%.==.0. (
	echo MySQL server is not started. Trying to start the services...
	goto startService 
) else (
	goto startProgram
)

:startProgram
echo Starting program...
echo  -------------------------------------------------------------------------
echo  [                                                                       ]
echo  [                     INFEST ADMINISTRATOR CONSOLE                      ] 
echo  [                                                                       ]
echo  -------------------------------------------------------------------------
javaw -jar "store\Infest-Administrator.jar"
pause
exit

:startService
REM 
if "%PROCESSOR_ARCHITECTURE%" equ "amd64" (
	>nul 2>&1 "%SYSTEMROOT%\SysWOW64\cacls.exe" "%SYSTEMROOT%\SysWOW64\config\system"
) else (
	>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"
)

REM 
if '%errorlevel%' NEQ '0' (
    echo Requesting administrative privileges...
    goto UACPrompt
) else (
	goto gotAdmin
)

:UACPrompt
    echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
    set params = %*:"=""
    echo UAC.ShellExecute "cmd.exe", "/c ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs"

    "%temp%\getadmin.vbs"
    del "%temp%\getadmin.vbs"
    exit /B

:gotAdmin
    echo Requesting administrative privileges... got administrative privileges!
    pushd "%CD%"
    CD /D "%~dp0"
    net start "MySQL57"
    echo Service started.
    goto startProgram