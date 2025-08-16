@echo off
chcp 65001 > nul
cd /d "%~dp0"
title Shimeji-ee (JRE 21 Portable Version)
echo ========================================
echo   Shimeji-ee - JRE 21 Portable Version
echo ========================================
echo.

REM Check for bundled JRE 21
if exist "jre\bin\java.exe" (
    echo Found bundled JRE 21, using bundled version...
    set JAVA_CMD=jre\bin\java.exe
) else (
    echo Error: Bundled JRE 21 not found
    echo This portable version only supports bundled JRE 21, please ensure jre folder exists
    pause
    exit /b 1
)

if not exist "target\Shimeji-ee.jar" (
    echo Error: Shimeji-ee.jar file not found!
    echo Please ensure target folder exists
    pause
    exit /b 1
)

echo Starting Shimeji-ee with bundled JRE 21...
echo.
echo Launching Shimeji-ee...
start "" "%JAVA_CMD%" -Dfile.encoding=UTF-8 ^
     -Xms64m ^
     -Xmx512m ^
     -cp "target\Shimeji-ee.jar;lib\*" ^
     com.group_finity.mascot.Main

echo.
echo Shimeji-ee has been successfully started! (using bundled JRE 21)
echo You can now safely close this window.
echo To stop Shimeji-ee, right-click on the desktop mascot or use Task Manager.
echo.
echo Note: This portable version uses bundled JRE 21, no additional Java installation required
echo.
pause
