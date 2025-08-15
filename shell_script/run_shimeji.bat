@echo off
cd /d "%~dp0"
echo Starting Shimeji-ee...

if not exist "jre\bin\javaw.exe" (
    echo Error: JRE not found! Please make sure the jre folder exists.
    pause
    exit /b 1
)

if not exist "target\Shimeji-ee.jar" (
    echo Error: Shimeji-ee.jar not found! Please make sure the target folder exists.
    pause
    exit /b 1
)

echo Launching Shimeji-ee in the background...
start "" "jre\bin\javaw.exe" -cp "target\Shimeji-ee.jar;lib\*" com.group_finity.mascot.Main

echo Shimeji-ee has been started successfully!
echo You can now close this window safely.
echo To stop Shimeji-ee, right-click on the mascot or use Task Manager.
echo.
pause
