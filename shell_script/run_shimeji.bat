@echo off
cd /d "%~dp0"
echo Starting Shimeji-ee...

if not exist "jre\bin\java.exe" (
    echo Error: JRE not found! Please make sure the jre folder exists.
    pause
    exit /b 1
)

if not exist "target\Shimeji-ee.jar" (
    echo Error: Shimeji-ee.jar not found! Please make sure the target folder exists.
    pause
    exit /b 1
)

"jre\bin\java.exe" -cp "target\Shimeji-ee.jar;lib\*" com.group_finity.mascot.Main

if errorlevel 1 (
    echo.
    echo Shimeji-ee exited with an error.
    pause
)
