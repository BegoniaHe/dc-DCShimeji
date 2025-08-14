@echo off
echo Building Shimeji-ee...
echo.

REM Clean and build the project
ant clean jar

if %ERRORLEVEL% neq 0 (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Build successful! Starting Shimeji-ee...
echo.

REM Run the application
java -cp "target\Shimeji-ee.jar;lib\*" com.group_finity.mascot.Main

if %ERRORLEVEL% neq 0 (
    echo Application failed to start properly.
    pause
    exit /b 1
)

pause
