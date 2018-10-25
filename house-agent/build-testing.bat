@echo off
set curdir=%~dp0
cd /d %curdir%
echo "===========================	remove dir build	==========================="
	rd /s/q build
echo "===========================	sencha app build	==========================="
	start /wait sencha app build testing
echo "===========================	remove classic dir  ==========================="
	rd /s/q ..\src\main\webapp\HouseAgentSystem\classic
echo "===========================	mkdir classic di	==========================="
	mkdir ..\src\main\webapp\HouseAgentSystem\classic
echo "=========================== copy testing to webapp ==========================="
	 xcopy /s /y build\testing\HouseAgentSystem ..\src\main\webapp\HouseAgentSystem  /e/h
pause
