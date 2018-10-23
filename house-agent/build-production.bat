@echo off
set curdir=%~dp0
cd /d %curdir%
echo "===========================	remove dir build	==========================="
	rd /s/q build
echo "===========================	sencha app build	==========================="
	start /wait sencha app build production
echo "===========================	remove classic dir  ==========================="
	rd /s/q ..\src\main\webapp\classic
echo "===========================	mkdir classic di	==========================="
	mkdir ..\src\main\webapp\classic
echo "=========================== copy production to webapp ==========================="
	 xcopy /s /y build\production\Admin ..\src\main\webapp  /e/h
pause
