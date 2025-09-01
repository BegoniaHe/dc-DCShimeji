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

REM Run the application with optimized memory settings and GC
java -Xmx512M -Xms128M -XX:ReservedCodeCacheSize=128M -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+UseStringDeduplication -cp "target\Shimeji-ee.jar;lib\*" com.group_finity.mascot.Main

if %ERRORLEVEL% neq 0 (
    echo Application failed to start properly.
    pause
    exit /b 1
)

pause
